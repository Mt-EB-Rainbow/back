package efub.ebmt.eeojum.domain.question.controller;

import efub.ebmt.eeojum.domain.question.domain.Question;
import efub.ebmt.eeojum.domain.question.dto.QuestionRequestDto;
import efub.ebmt.eeojum.domain.question.dto.QuestionResponseDto;
import efub.ebmt.eeojum.domain.question.dto.QuestionUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import efub.ebmt.eeojum.domain.question.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Tag(name = "게시글 관련 API", description = "게시글 생성, 조회, 수정, 삭제 기능을 제공합니다.")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "게시글 생성 API입니다.")
    public QuestionResponseDto createQuestion(@RequestBody QuestionRequestDto requestDto) {
        Question question = questionService.createQuestion(requestDto);
        return new QuestionResponseDto(question);
    }

    @GetMapping
    @Operation(summary = "게시글 목록 조회 API입니다.")
    public List<QuestionResponseDto> getQuestionList() {
        List<Question> questionList = questionService.findQuestionList();
        List<QuestionResponseDto> responseDtoList = new ArrayList<>();

        for(Question q : questionList){
            responseDtoList.add(new QuestionResponseDto(q));
        }

        return responseDtoList;
    }

    @GetMapping("/{questionId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "게시글 상세 조회 API입니다.")
    public QuestionResponseDto getQuestionDetail(@PathVariable Long questionId) {
        Question question = questionService.findQuestion(questionId);
        return new QuestionResponseDto(question);
    }

    @PutMapping("/{questionId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "게시글 수정 API입니다.")
    public QuestionResponseDto updateQuestion(@PathVariable Long questionId, @RequestBody QuestionUpdateRequestDto requestDto) {
        Question question = questionService.updateQuestion(questionId, requestDto);
        return new QuestionResponseDto(question);
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "게시글 삭제 API입니다.")
    public String deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return "게시글이 성공적으로 삭제되었습니다.";
    }
}
