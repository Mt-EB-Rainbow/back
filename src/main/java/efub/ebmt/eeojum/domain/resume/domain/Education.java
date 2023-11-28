package efub.ebmt.eeojum.domain.resume.domain;

import efub.ebmt.eeojum.domain.resume.dto.request.EducationRequest;
import lombok.AccessLevel;
import lombok.Builder;
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
    private Long resumeId;

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

    @Builder
    public Education(Long resumeId, LocalDateTime startDate, LocalDateTime finishDate, EducationStatus educationStatus, String name, String major, String degree){
        this.resumeId = resumeId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.educationStatus = educationStatus;
        this.name = name;
        this.major = major;
        this.degree = degree;
    }

    public Education update(EducationRequest educationRequest){
        this.startDate = educationRequest.getStartDate();
        this.finishDate = educationRequest.getFinishDate();
        this.educationStatus = educationRequest.getEducationStatus();
        this.name = educationRequest.getName();
        this.major = educationRequest.getMajor();
        this.degree = educationRequest.getDegree();
        return this;
    }
}
