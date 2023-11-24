package efub.ebmt.eeojum.domain.site.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum SiteType {
    EMPLOYMENT(0, "취업, 교육, 상담"),
    REGIONAL(1, "지역 별"),
    NURTURE(2, "보육"),;

    private final Integer Id;
    private final String description;
}
