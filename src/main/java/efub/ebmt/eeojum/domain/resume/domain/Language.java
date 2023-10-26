package efub.ebmt.eeojum.domain.resume.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long languageId;

    @Column(nullable = false)
    private Long resumeId;

    @Column(nullable = false)
    private LocalDateTime gainedDate;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String testName;

    @Column(nullable = false)
    private String score;

    @Builder
    public Language(Long resumeId, LocalDateTime startDate, LocalDateTime gainedDate, String language, String testName, String score){
        this.resumeId = resumeId;
        this.gainedDate = gainedDate;
        this.language = language;
        this.testName = testName;
        this.score = score;
    }
}
