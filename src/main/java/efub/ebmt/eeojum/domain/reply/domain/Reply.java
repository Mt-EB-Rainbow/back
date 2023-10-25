package efub.ebmt.eeojum.domain.reply.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.question.domain.Question;
import efub.ebmt.eeojum.domain.reply.dto.ReplyUpdateRequestDto;
import efub.ebmt.eeojum.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @JsonIgnoreProperties({"replies"}) // replies는 Question 엔터티의 컬렉션 필드 이름일 수 있음
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Builder
    public Reply(Question question, Member writer, String contents) {
        this.question = question;
        this.writer = writer;
        this.contents = contents;
    }

    public void updateReply(ReplyUpdateRequestDto requestDto){
        this.contents = requestDto.getContents();
    }
}
