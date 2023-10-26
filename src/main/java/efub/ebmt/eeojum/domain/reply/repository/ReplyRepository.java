package efub.ebmt.eeojum.domain.reply.repository;

import efub.ebmt.eeojum.domain.question.domain.Question;
import efub.ebmt.eeojum.domain.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByQuestion(Question question);
    Optional<Reply> findTopByQuestionOrderByReplyLikeCountDesc(Question question);
}
