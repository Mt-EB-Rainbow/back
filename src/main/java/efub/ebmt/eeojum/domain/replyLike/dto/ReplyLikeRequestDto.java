package efub.ebmt.eeojum.domain.replyLike.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyLikeRequestDto {
    private Long memberId;
}
