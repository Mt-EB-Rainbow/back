package efub.ebmt.eeojum.domain.nurture.controller;

import efub.ebmt.eeojum.domain.nurture.dto.request.NurtureRequest;
import efub.ebmt.eeojum.domain.nurture.dto.response.NurturesResponse;
import efub.ebmt.eeojum.domain.nurture.service.NurtureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurtures")
@RequiredArgsConstructor
@Tag(name = "보육 정보 조회 API", description = "보육 정보 조회 기능을 제공합니다.")
public class NurtureController {
    private final NurtureService nurtureService;

    @GetMapping
    @Operation(summary = "보육 시설 정보 조회")
    public ResponseEntity<NurturesResponse> getNurtureList(@RequestBody NurtureRequest nurtureRequest){
        return new ResponseEntity<>(nurtureService.nurtureList(nurtureRequest), HttpStatus.OK);
    }
}
