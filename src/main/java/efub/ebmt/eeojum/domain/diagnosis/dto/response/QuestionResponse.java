package efub.ebmt.eeojum.domain.diagnosis.dto.response;

import efub.ebmt.eeojum.domain.diagnosis.domain.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionResponse {
    private String question;
    private List<Option> options;

    public QuestionResponse(String question, List<Option> options){
        this.question = question;
        this.options = options;
    }
}
