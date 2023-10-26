package efub.ebmt.eeojum.domain.replyLike.service;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.service.MemberService;
import efub.ebmt.eeojum.domain.reply.domain.Reply;
import efub.ebmt.eeojum.domain.reply.service.ReplyService;
import efub.ebmt.eeojum.domain.replyLike.domain.ReplyLike;
import efub.ebmt.eeojum.domain.replyLike.repository.ReplyLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class ReplyLikeService {
    private final MemberService memberService;
    private final ReplyService replyService;
    private final ReplyLikeRepository replyLikeRepository;

    // 좋아요 생성
    public void createReplyLike(Long memberId, Long replyId) {
        Reply reply = replyService.findById(replyId);
        Member member = memberService.findById(memberId);

        ReplyLike replyLike = ReplyLike.builder()
                .member(member)
                .reply(reply)
                .build();

        replyLikeRepository.save(replyLike);

        Long replyLikeCount = getReplyLikeCount(reply);
        reply.updateLikeCount(replyLikeCount);

        boolean like = isLikeStatus(memberId, reply);
        reply.updateLike(like);
    }

    // 좋아요 삭제
    public void deleteReplyLike(Long memberId, Long replyId) {
        Reply reply = replyService.findById(replyId);
        Member member = memberService.findById(memberId);

        ReplyLike replyLike = replyLikeRepository.findByMemberAndReply(member, reply)
                .orElseThrow(() -> new RuntimeException("좋아요가 존재하지 않습니다."));
        replyLikeRepository.delete(replyLike);

        Long replyLikeCount = getReplyLikeCount(reply);
        reply.updateLikeCount(replyLikeCount);

        boolean like = isLikeStatus(memberId, reply);
        reply.updateLike(like);
    }

    // 메서드
    public boolean isLikeStatus(Long memberId, Reply reply){
        Member member = memberService.findById(memberId);
        return isExistsByMemberAndReply(member, reply);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByMemberAndReply(Member member, Reply reply){
        return replyLikeRepository.existsByMemberAndReply(member, reply);
    }

    @Transactional (readOnly = true)
    public Long getReplyLikeCount(Reply reply) {
        Long replyLikeCount = replyLikeRepository.countByReply(reply);
        return replyLikeCount;
    }
}
