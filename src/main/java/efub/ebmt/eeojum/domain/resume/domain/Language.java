package efub.ebmt.eeojum.domain.resume.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long languageId;

    @Column(nullable = false)
    private Integer resumeId;

    @Column(nullable = false)
    private LocalDateTime gainedDate;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String testName;

    @Column(nullable = false)
    private String score;
}
