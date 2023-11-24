package efub.ebmt.eeojum.domain.example.dto.response;

import efub.ebmt.eeojum.domain.example.domain.Example;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExampleResponse {
    private Long exampleId;
    private String title;
    private String summary;
    private String content1;
    private String content2;
    private String imageUrl;

    public ExampleResponse(Example example){
        this.exampleId = example.getExampleId();
        this.title = example.getTitle();
        this.summary = example.getSummary();
        this.content1 = example.getContent1();
        this.content2 = example.getContent2();
        this.imageUrl = example.getImageUrl();
    }
}
