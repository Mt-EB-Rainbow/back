package efub.ebmt.eeojum.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyUpdateRequestDto {
    private Long writerId;
    private String contents;

    @Builder
    public ReplyUpdateRequestDto(Long writerId, String contents){
        this.writerId = writerId;
        this.contents = contents;
    }
}
