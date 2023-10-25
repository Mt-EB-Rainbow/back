package efub.ebmt.eeojum.domain.question.service;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
import efub.ebmt.eeojum.domain.question.domain.Question;
import efub.ebmt.eeojum.domain.question.dto.QuestionRequestDto;
import efub.ebmt.eeojum.domain.question.dto.QuestionUpdateRequestDto;
import efub.ebmt.eeojum.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    // 게시글 생성
    public Question createQuestion(QuestionRequestDto requestDto) {
        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return questionRepository.save(
                Question.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .build()
        );
    }

    // 게시글 업데이트
    public Question updateQuestion(Long questionId, QuestionUpdateRequestDto requestDto) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        question.updatePost(requestDto);
        return question;
    }

    // 게시글 삭제
    public void deleteQuestion(Long questionId) {
        Question post = questionRepository.findById(questionId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

        questionRepository.delete(post);
    }

    // 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<Question> findQuestionList() {
        return questionRepository.findAll();
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public Question findQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
}
