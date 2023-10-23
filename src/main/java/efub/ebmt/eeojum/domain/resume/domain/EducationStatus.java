package efub.ebmt.eeojum.domain.resume.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationStatus {
    ATTENDING(0, "재학"),
    ABSENCE(1, "휴학"),
    GRADUATED(2, "졸업"),
    EXPECTED(3, "졸업 예정"),
    DROPOUT(4, "중퇴");

    private final Integer Id;
    private final String description;
}
