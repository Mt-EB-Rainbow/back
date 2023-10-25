package efub.ebmt.eeojum.domain.reply.controller;

import efub.ebmt.eeojum.domain.reply.domain.Reply;
import efub.ebmt.eeojum.domain.reply.dto.ReplyRequestDto;
import efub.ebmt.eeojum.domain.reply.dto.ReplyResponseDto;
import efub.ebmt.eeojum.domain.reply.dto.ReplyUpdateRequestDto;
import efub.ebmt.eeojum.domain.reply.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
@Tag(name = "댓글 관련 API", description = "댓글 생성, 조회, 수정, 삭제 기능을 제공합니다.")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "댓글 생성 API입니다.")
    public ReplyResponseDto createReply(@RequestBody ReplyRequestDto replyRequestDto) {
        Reply reply = replyService.createReply(replyRequestDto);
        return new ReplyResponseDto(reply);
    }

    // 전체 댓글 조회
    @GetMapping("/question/{questionId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "댓글 목록 조회 API입니다.")
    public List<ReplyResponseDto> getReplyList(@PathVariable Long questionId) {
        List<Reply> replyList = replyService.getReplyList(questionId);
        List<ReplyResponseDto> responseDtoList = replyList.stream()
                .map(ReplyResponseDto::from)
                .collect(Collectors.toList());

        return responseDtoList;
    }

    // 댓글 개별 조회
    @GetMapping("/{replyId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "댓글 상세 조회 API입니다.")
    public ReplyResponseDto getReply(@PathVariable Long replyId) {
        Reply reply = replyService.getReply(replyId);
        return new ReplyResponseDto(reply);
    }

    // 댓글 수정
    @PutMapping("/{replyId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "댓글 수정 API입니다.")
    public ReplyResponseDto updateReply(@PathVariable Long replyId, @RequestBody ReplyUpdateRequestDto requestDto) {
        Reply reply = replyService.updateReply(replyId, requestDto);
        return new ReplyResponseDto(reply);
    }

    // 댓글 삭제
    @DeleteMapping("/{replyId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "댓글 삭제 API입니다.")
    public String deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return "댓글이 삭제되었습니다.";
    }
}
