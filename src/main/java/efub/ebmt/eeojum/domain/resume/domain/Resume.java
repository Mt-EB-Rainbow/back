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

    @Column // 공개여부
    private boolean isSecrete;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'NOT_ASKED'")
    @Builder.Default
    private ResumeStatus resumeStatus = ResumeStatus.NOT_ASKED;

    public void updateResume(String title, String introduction, boolean isSecrete){
        this.title = title;
        this.introduction = introduction;
        this.isSecrete = isSecrete;
    }

    public void updateResumeStatus(ResumeStatus resumeStatus){
        this.resumeStatus = resumeStatus;
    }

    public boolean getIsSecrete() {
        return this.isSecrete;
    }
}
