package efub.ebmt.eeojum.domain.question.dto;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.dto.MemberInfoResponseDto;
import efub.ebmt.eeojum.domain.question.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QuestionResponseDto {
    private Long questionId;
    private String title;
    private String content;
    private MemberInfoResponseDto writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public QuestionResponseDto(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.writer = new MemberInfoResponseDto(question.getWriter()); // MemberInfoResponseDto를 생성
        this.createdAt = question.getCreatedAt();
        this.modifiedAt = question.getModifiedAt();
    }
}
