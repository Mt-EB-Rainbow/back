package efub.ebmt.eeojum.domain.feedback.repository;

import efub.ebmt.eeojum.domain.feedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByResumeId(Long resumeId);
}
