package efub.ebmt.eeojum.domain.diagnosis.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionsResponse {
    List<QuestionResponse> questionResponseList;

    public QuestionsResponse(List<QuestionResponse> questionsResponse){
        this.questionResponseList = questionsResponse;
    }
}
