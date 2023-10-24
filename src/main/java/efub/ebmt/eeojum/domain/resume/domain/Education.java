package efub.ebmt.eeojum.domain.resume.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long educationId;

    @Column(nullable = false)
    private Integer resumeId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime finishDate;

    @Enumerated(EnumType.STRING)
    private EducationStatus educationStatus;

    @Column(nullable = false)
    private String name;

    @Column
    private String major;

    @Column
    private String degree;
}
