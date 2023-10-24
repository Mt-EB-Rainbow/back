package efub.ebmt.eeojum.domain.resume.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResumeStatus {
    NOT_ASKED(0, "피드백 요청하지 않은 상태"),
    WAITING(1, "피드백 대기중인 상태"),
    ARRIVED(2, "피드백 도착 후 확인하지 않은 상태"),;

    private final Integer Id;
    private final String description;
}
