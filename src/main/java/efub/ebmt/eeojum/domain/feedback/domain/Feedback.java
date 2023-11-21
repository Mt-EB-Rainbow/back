package efub.ebmt.eeojum.domain.feedback.domain;

import efub.ebmt.eeojum.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feedback extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long feedbackId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long resumeId;

    @Column
    private String content;

    @Builder
    public Feedback(Long memberId, Long resumeId, String content){
        this.memberId = memberId;
        this.resumeId = resumeId;
        this.content = content;
    }
}
