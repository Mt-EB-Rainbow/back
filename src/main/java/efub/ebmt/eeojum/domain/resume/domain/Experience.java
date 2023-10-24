package efub.ebmt.eeojum.domain.resume.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long experienceId;

    @Column(nullable = false)
    private Integer resumeId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime finishDate;

    @Column(nullable = false)
    private Boolean isPresent;

    @Column
    private String department;

    @Column
    private String position;
}
