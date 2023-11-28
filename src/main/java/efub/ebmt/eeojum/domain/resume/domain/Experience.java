package efub.ebmt.eeojum.domain.resume.domain;

import efub.ebmt.eeojum.domain.resume.dto.request.ExperienceRequest;
import lombok.AccessLevel;
import lombok.Builder;
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
    private Long resumeId;

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

    @Builder
    public Experience(Long resumeId, LocalDateTime startDate, LocalDateTime finishDate, Boolean isPresent, String department, String position){
        this.resumeId = resumeId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.isPresent = isPresent;
        this.department = department;
        this.position = position;
    }

    public Experience update(ExperienceRequest experienceRequest){
        this.startDate = experienceRequest.getStartDate();
        this.finishDate = experienceRequest.getFinishDate();
        this.isPresent = experienceRequest.getIsPresent();
        this.department = experienceRequest.getDepartment();
        this.position = experienceRequest.getPosition();
        return this;
    }
}
