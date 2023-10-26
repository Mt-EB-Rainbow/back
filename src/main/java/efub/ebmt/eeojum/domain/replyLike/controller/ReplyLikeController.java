package efub.ebmt.eeojum.domain.replyLike.controller;

import efub.ebmt.eeojum.domain.replyLike.dto.ReplyLikeRequestDto;
import efub.ebmt.eeojum.domain.replyLike.service.ReplyLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{replyId}/likes")
@RequiredArgsConstructor
@Tag(name = "댓글 좋아요 관련 API", description = "댓글 좋아요 생성, 삭제 기능을 제공합니다.")
public class ReplyLikeController {

    private final ReplyLikeService replyLikeService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "좋아요 생성 API입니다.")
    public String createReplyLike(@PathVariable Long replyId, @RequestBody ReplyLikeRequestDto requestDto){
        Long memberId = requestDto.getMemberId();

        if (memberId != null){
            replyLikeService.createReplyLike(memberId, replyId);
            return "좋아요를 눌렀습니다.";
        } else {
            return "로그인이 필요한 서비스입니다.";
        }
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "좋아요 삭제 API입니다.")
    public String deleteReplyLike(@PathVariable Long replyId, @RequestBody ReplyLikeRequestDto requestDto){
        Long memberId = requestDto.getMemberId();

        if (memberId != null){
            replyLikeService.deleteReplyLike(memberId, replyId);
            return "좋아요가 취소되었습니다.";
        } else{
            return "로그인이 필요한 서비스입니다.";
        }
    }
}
