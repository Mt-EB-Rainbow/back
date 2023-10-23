package efub.ebmt.eeojum.domain.Member.controller;

import efub.ebmt.eeojum.domain.Member.dto.InformationRequestDto;
import efub.ebmt.eeojum.domain.Member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "회원 상세 정보 API", description = "회원 상세 정보를 추가하는 API를 제공합니다.")
public class InformationController {
    private final MemberService memberService;

    @PostMapping("/info/{memberId}")
    @Operation(summary = "회원 상세 정보를 추가하는 API입니다.")
    public ResponseEntity<String> addInformation(@PathVariable Long memberId, @RequestBody InformationRequestDto infoDto) {
        memberService.addInformation(memberId, infoDto);
        return ResponseEntity.ok("추가 정보 입력 완료");
    }
}
