package efub.ebmt.eeojum.domain.question.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionUpdateRequestDto {
    private String title;
    private String content;

    public QuestionUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
