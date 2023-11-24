package efub.ebmt.eeojum.domain.diagnosis.service;

import efub.ebmt.eeojum.domain.diagnosis.domain.Option;
import efub.ebmt.eeojum.domain.diagnosis.domain.Question;
import efub.ebmt.eeojum.domain.diagnosis.dto.request.DiagnosisRequest;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.DiagnosisResponse;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionResponse;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionsResponse;
import efub.ebmt.eeojum.domain.diagnosis.repository.OptionRepository;
import efub.ebmt.eeojum.domain.diagnosis.repository.QuestionRepository;
import efub.ebmt.eeojum.domain.dictionary.domain.Job;
import efub.ebmt.eeojum.domain.dictionary.dto.response.JobResponse;
import efub.ebmt.eeojum.domain.dictionary.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiagnosisService {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final JobRepository jobRepository;

    public List<Option> optionsList(Long questionId){
        return optionRepository.findByQuestionId(questionId);
    }

    public QuestionsResponse questionList(){
        List<Question> questions = questionRepository.findAll();
        return new QuestionsResponse(questions.stream()
                .map(q -> new QuestionResponse(q.getQuestion(), optionsList(q.getQuestionId())))
                .collect(Collectors.toList()));
    }

    /*
    q1: 미성년인 자녀가 있나요에 대한 응답 (0 또는 1)
    q2: 아이를 맡아줄 곳이 있나요 (0 또는 1)
    q3: 야간 근무가 가능한가요 (네: 0, 아니오: 1) nightwork
    q4: 근무 시간의 유연한 조정을 선호하나요 (0 또는 1) flexible
    q5: 풀타임 근무가 가능한가요 (0 또는 1) fulltime
    q6: 신체적 피로도가 높은 일도 가능한가요 (0 또는 1) physical

    private Boolean nightWork; //야간 근무(0: 없음, 1: 있음)
    private Boolean fullTime; //풀타임 근무(0: 풀타임, 1: 파트타임)
    private Boolean flexible; //근무시간 유연(0: 고정, 1: 유연)
    private Boolean physical; //몸을 많이 쓰는 직업(0: 안씀, 1: 씀)
    */
    public DiagnosisResponse diagnosisResult(DiagnosisRequest diagnosisRequest){
        List<Job> jobs = jobRepository.findAll();
        //야간 근무 불가능할 시 야간 근무 없는 직업만 남김
        if(diagnosisRequest.getQ3().equals(1L)){ //filter: 조건에 일치하는 것만 남음
            jobs = jobs.stream().filter(j -> j.getNightWork().equals(0L)).collect(Collectors.toList());
        }

        //근무 시간 유연하길 선호할 경우 유연 근무만 남김
        if(diagnosisRequest.getQ4().equals(0L)){
            jobs = jobs.stream().filter(j -> j.getFlexible().equals(1L)).collect(Collectors.toList());
        }

        //풀타임 근무 불가능한 경우 파트타임만 남김
        if(diagnosisRequest.getQ5().equals(1L)){
            jobs = jobs.stream().filter(j -> j.getFullTime().equals(1L)).collect(Collectors.toList());
        }

        //신체적 피로도 높은 일 불가능할 경우 신체 안쓰는 일만 남김
        if(diagnosisRequest.getQ6().equals(0L)){
            jobs = jobs.stream().filter(j -> j.getPhysical().equals(0L)).collect(Collectors.toList());;
        }

        List<JobResponse> jobsResponse = jobs.stream()
                .map(j -> new JobResponse(j.getJobId(), j.getName(), j.getImageUrl()))
                .collect(Collectors.toList());

        Collections.shuffle(jobsResponse);

        return new DiagnosisResponse(jobsResponse);
    }
}
