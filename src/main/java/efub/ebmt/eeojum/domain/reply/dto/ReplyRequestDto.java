package efub.ebmt.eeojum.domain.reply.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyRequestDto {
    private String content;
    private Long writerId;
    private Long questionId;
}
