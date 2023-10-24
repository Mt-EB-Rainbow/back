package efub.ebmt.eeojum.domain.question.dto;

import lombok.Getter;

@Getter
public class QuestionRequestDto {
    private String title;
    private String content;
    private Long writerId;
}
