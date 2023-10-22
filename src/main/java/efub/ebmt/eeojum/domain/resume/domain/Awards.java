package efub.ebmt.eeojum.domain.resume.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Awards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long awardsId;

    @Column(nullable = false)
    private Integer resumeId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime finishDate;

    @Column
    private String activity;

    @Column
    private String content;

}
