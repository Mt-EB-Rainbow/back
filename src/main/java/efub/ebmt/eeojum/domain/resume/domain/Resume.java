package efub.ebmt.eeojum.domain.resume.domain;

import efub.ebmt.eeojum.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Resume extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long resumeId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String title;

    @Column
    private String introduction;

    @Enumerated(EnumType.STRING)
    private ResumeStatus resumeStatus;

    @Builder
    public Resume(Long memberId, String title, String introduction, ResumeStatus resumeStatus){
        this.memberId = memberId;
        this.title = title;
        this.introduction = introduction;
        this.resumeStatus = resumeStatus;
    }

    public void updateResume(String title, String introduction, ResumeStatus resumeStatus){
        this.title = title;
        this.introduction = introduction;
        this.resumeStatus = resumeStatus;
    }

    public void updateResumeStatus(ResumeStatus resumeStatus){
        this.resumeStatus = resumeStatus;
    }
}
