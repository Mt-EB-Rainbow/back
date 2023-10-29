package efub.ebmt.eeojum.domain.replyLike.repository;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.reply.domain.Reply;
import efub.ebmt.eeojum.domain.replyLike.domain.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {
    Long countByReply(Reply reply);
    boolean existsByMemberAndReply(Member member, Reply reply);
    Optional<ReplyLike> findByMemberAndReply(Member member, Reply reply);
}
