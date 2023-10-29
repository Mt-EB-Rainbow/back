package efub.ebmt.eeojum.domain.replyLike.domain;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.reply.domain.Reply;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ReplyLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_like_id", updatable = false)
    private Long replyLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id", nullable = false)
    private Reply reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public ReplyLike(Reply reply, Member member) {
        this.reply = reply;
        this.member = member;
    }
}
