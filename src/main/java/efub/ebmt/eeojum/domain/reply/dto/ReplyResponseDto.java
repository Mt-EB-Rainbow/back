package efub.ebmt.eeojum.domain.reply.dto;

import efub.ebmt.eeojum.domain.reply.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReplyResponseDto {
    private Long replyId;
    private String content;
    private Long writerId;
    private Long questionId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ReplyResponseDto(Reply reply) {
        this.replyId = reply.getReplyId();
        this.content = reply.getContents();
        this.writerId = reply.getWriter().getMemberId();
        this.questionId = reply.getQuestion().getQuestionId();
        this.createdAt = reply.getCreatedAt();
        this.modifiedAt = reply.getModifiedAt();
    }

    public ReplyResponseDto(Long replyId, String content, Long writerId, Long questionId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.replyId = replyId;
        this.content = content;
        this.writerId = writerId;
        this.questionId = questionId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static ReplyResponseDto from(Reply reply) {
        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getContents(),
                reply.getWriter().getMemberId(),
                reply.getQuestion().getQuestionId(),
                reply.getCreatedAt(),
                reply.getModifiedAt()
        );
    }
}
