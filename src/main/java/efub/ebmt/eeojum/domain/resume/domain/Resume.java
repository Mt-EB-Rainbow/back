package efub.ebmt.eeojum.domain.resume.domain;

import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Resume extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long resumeId;

    @Column(nullable = false)
    private Long memberId;

    @Column(columnDefinition = "varchar(255) default '제목 없음'")
    @Builder.Default
    private String title = "제목 없음";

    @Column
    private String introduction;

    @Column
    @Builder.Default
    private Boolean isPrivate = false;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'NOT_ASKED'")
    @Builder.Default
    private ResumeStatus resumeStatus = ResumeStatus.NOT_ASKED;

    @Column
    private String jobName;

    public void updateResumeStatus(ResumeStatus resumeStatus){
        this.resumeStatus = resumeStatus;
    }

    public Resume updateResume(ResumeUpdateRequest resumeUpdateRequest){
        this.title = resumeUpdateRequest.getTitle();
        this.isPrivate = resumeUpdateRequest.getIsPrivate();
        this.jobName = resumeUpdateRequest.getJobName();
        return this;
    }

}
