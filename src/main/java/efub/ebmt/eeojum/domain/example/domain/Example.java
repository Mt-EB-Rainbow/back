package efub.ebmt.eeojum.domain.example.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long exampleId;

    private String title;

    private String summary;

    private String content1;

    private String content2;

    private String imageUrl;

    @Builder
    public Example(Long exampleId, String title, String summary, String content1, String content2, String imageUrl){
        this.exampleId = exampleId;
        this.title = title;
        this.summary = summary;
        this.content1 = content1;
        this.content2 = content2;
        this.imageUrl = imageUrl;
    }
}
