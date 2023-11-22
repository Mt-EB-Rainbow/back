package efub.ebmt.eeojum.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    RESUME_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 이력서입니다."),
    MENTOR_BAD_REQUEST(HttpStatus.BAD_REQUEST, "이메일, 휴대폰번호, 카카오아이디 중 최소 한 가지는 입력해야 합니다."),
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 직업 아이디입니다."),
    WRONG_DISTRICT(HttpStatus.BAD_REQUEST, "잘못된 자치구 입력입니다.");
    ;

    private final HttpStatus status;
    private final String message;
}
