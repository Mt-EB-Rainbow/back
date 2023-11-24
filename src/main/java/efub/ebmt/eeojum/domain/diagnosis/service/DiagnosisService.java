package efub.ebmt.eeojum.domain.diagnosis.service;

import efub.ebmt.eeojum.domain.diagnosis.domain.Option;
import efub.ebmt.eeojum.domain.diagnosis.domain.Question;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionResponse;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionsResponse;
import efub.ebmt.eeojum.domain.diagnosis.repository.OptionRepository;
import efub.ebmt.eeojum.domain.diagnosis.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiagnosisService {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public List<Option> optionsList(Long questionId){
        return optionRepository.findByQuestionId(questionId);
    }

    public QuestionsResponse questionList(){
        List<Question> questions = questionRepository.findAll();
        return new QuestionsResponse(questions.stream()
                .map(q -> new QuestionResponse(q.getQuestion(), optionsList(q.getQuestionId())))
                .collect(Collectors.toList()));
    }
}
