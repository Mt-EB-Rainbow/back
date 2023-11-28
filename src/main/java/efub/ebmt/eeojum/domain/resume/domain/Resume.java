package efub.ebmt.eeojum.domain.resume.domain;

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
    private Boolean isPrivate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'NOT_ASKED'")
    @Builder.Default
    private ResumeStatus resumeStatus = ResumeStatus.NOT_ASKED;

    public void updateResume(String title, String introduction, Boolean isPrivate){
        this.title = title;
        this.introduction = introduction;
        this.isPrivate = isPrivate;
    }

    public void updateResumeStatus(ResumeStatus resumeStatus){
        this.resumeStatus = resumeStatus;
    }

}
