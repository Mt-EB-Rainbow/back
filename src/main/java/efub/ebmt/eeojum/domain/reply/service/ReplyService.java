package efub.ebmt.eeojum.domain.reply.service;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
import efub.ebmt.eeojum.domain.question.domain.Question;
import efub.ebmt.eeojum.domain.question.repository.QuestionRepository;
import efub.ebmt.eeojum.domain.reply.domain.Reply;
import efub.ebmt.eeojum.domain.reply.dto.ReplyRequestDto;
import efub.ebmt.eeojum.domain.reply.dto.ReplyResponseDto;
import efub.ebmt.eeojum.domain.reply.dto.ReplyUpdateRequestDto;
import efub.ebmt.eeojum.domain.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public Reply createReply(ReplyRequestDto requestDto) {
        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(()->new EntityNotFoundException("계정을 찾을 수 없습니다."));

        Question question = questionRepository.findById(requestDto.getQuestionId())
                .orElseThrow(()->new EntityNotFoundException("질문을 찾을 수 없습니다."));

        return replyRepository.save(
                Reply.builder()
                        .contents(requestDto.getContent())
                        .writer(writer)
                        .question(question)
                        .build()
        );
    }

    // 댓글 전체 조회
    @Transactional(readOnly = true)
    public List<Reply> getReplyList(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new EntityNotFoundException("질문을 찾을 수 없습니다. ID = " + questionId));
        return replyRepository.findAllByQuestion(question);
    }

    // 댓글 개별 조회
    @Transactional(readOnly = true)
    public Reply getReply(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(()->new EntityNotFoundException("댓글을 찾을 수 없습니다. ID = " + replyId));
    }

    // 댓글 수정
    public Reply updateReply(Long replyId, ReplyUpdateRequestDto requestDto) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()->new EntityNotFoundException("댓글을 찾을 수 없습니다. ID = " + replyId));

        if (reply.getWriter().getMemberId().equals(requestDto.getWriterId()))
            reply.updateReply(requestDto);
        else
            throw new IllegalArgumentException("댓글의 작성자만 댓글을 수정할 수 있습니다.");

        return reply;
    }

    // 댓글 삭제
    public void deleteReply(Long replyId){
        Reply comment = replyRepository.findById(replyId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다. ID = " + replyId));

        replyRepository.delete(comment);
    }

    // 메서드
    @Transactional(readOnly = true)
    public Reply findById(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(()->new EntityNotFoundException("댓글을 찾을 수 없습니다. ID = " + replyId));
    }

    @Transactional(readOnly = true)
    public Optional<Reply> findBestReplyByQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("질문을 찾을 수 없습니다. ID = " + questionId));

        return replyRepository.findTopByQuestionOrderByReplyLikeCountDesc(question);
    }
}
