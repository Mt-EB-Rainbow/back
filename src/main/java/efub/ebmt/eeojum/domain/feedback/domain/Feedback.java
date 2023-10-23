package efub.ebmt.eeojum.domain.feedback.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long feedbackId;

    @Column(nullable = false)
    private Long resumeId;

    @Column
    private String overall;

    @Column
    private String title;

    @Column
    private String education;

    @Column
    private String experience;

    @Column
    private String language;

    @Column
    private String awards;
}
