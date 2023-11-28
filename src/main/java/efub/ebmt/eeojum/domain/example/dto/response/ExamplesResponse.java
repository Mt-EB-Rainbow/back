package efub.ebmt.eeojum.domain.example.dto.response;

import efub.ebmt.eeojum.domain.example.domain.Example;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExamplesResponse {
    private List<Example> examples;

    public ExamplesResponse(List<Example> examples){
        this.examples = examples;
    }
}
