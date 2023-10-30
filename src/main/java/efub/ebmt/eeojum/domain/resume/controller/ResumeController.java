package efub.ebmt.eeojum.domain.resume.controller;

import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeDetailResponse;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeResponse;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumesResponse;
import efub.ebmt.eeojum.domain.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@Tag(name = "이력서 관련 API", description = "이력서 생성, 작성, 조회 기능을 제공합니다.")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    @Operation(summary = "이력서 생성 API입니다. 멤버 아이디를 입력해 이력서를 생성합니다.")
    public ResponseEntity<ResumeResponse> resumeAdd(@RequestBody ResumeRequest resumeRequest) {
        ResumeResponse resumeResponse = resumeService.addResume(resumeRequest);
        return new ResponseEntity<>(resumeResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{resumeId}")
    @Operation(summary = "이력서 내용 작성 API입니다.")
    public ResponseEntity<ResumeResponse> resumeSave(@PathVariable Long resumeId, @RequestBody ResumeUpdateRequest resumeUpdateRequest){
        ResumeResponse resumeResponse = resumeService.modifyResume(resumeId, resumeUpdateRequest);
        return new ResponseEntity<>(resumeResponse, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "이력서 전체 조회 API입니다.")
    public ResponseEntity<ResumesResponse> resumeList(){
        ResumesResponse resumesResponse = resumeService.findAllResume();
        return new ResponseEntity<>(resumesResponse, HttpStatus.OK);
    }

    @GetMapping("/{resumeId}")
    @Operation(summary = "이력서 상세 조회 API입니다.")
    public ResponseEntity<ResumeDetailResponse> resumeDetails(@PathVariable Long resumeId){
        ResumeDetailResponse resumeDetailResponse = resumeService.findResume(resumeId);
        return new ResponseEntity<>(resumeDetailResponse, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "이력서 멤버 별 조회 API입니다.")
    public ResponseEntity<ResumesResponse> resumeListByMember(@PathVariable Long memberId){
        ResumesResponse resumesResponse = resumeService.findResumeByMember(memberId);
        return new ResponseEntity<>(resumesResponse, HttpStatus.OK);
    }
}
