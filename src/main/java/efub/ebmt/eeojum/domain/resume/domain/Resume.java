package efub.ebmt.eeojum.domain.resume.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long resumeId;

    @Column(nullable = false)
    private Long title;

    @Column
    private String introduction;

    @Enumerated(EnumType.STRING)
    private ResumeStatus resumeStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
