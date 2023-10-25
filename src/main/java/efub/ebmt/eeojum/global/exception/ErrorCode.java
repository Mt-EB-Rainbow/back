package efub.ebmt.eeojum.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    RESUME_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 이력서입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
