package efub.ebmt.eeojum.domain.reply.domain;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.question.domain.Question;
import efub.ebmt.eeojum.domain.reply.dto.ReplyUpdateRequestDto;
import efub.ebmt.eeojum.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", updatable = false)
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(nullable = false)
    @ColumnDefault("false") // false면 좋아요 누르지 않은 상태
    public boolean likeStatus;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long replyLikeCount;

    @Builder
    public Reply(Question question, Member writer, String contents, boolean likeStatus, Long replyLikeCount) {
        this.question = question;
        this.writer = writer;
        this.contents = contents;
        this.likeStatus = likeStatus;
        this.replyLikeCount = replyLikeCount;
    }

    public void updateReply(ReplyUpdateRequestDto requestDto){
        this.contents = requestDto.getContents();
    }

    public void updateLikeCount(Long newReplyLikeCount){
        this.replyLikeCount = newReplyLikeCount;
    }

    public void updateLike(boolean newReplyLikeStatus){
        this.likeStatus = newReplyLikeStatus;
    }
}
