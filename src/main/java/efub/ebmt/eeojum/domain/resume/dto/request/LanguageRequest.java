package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LanguageRequest {
    private LocalDate gainedDate;
    private String language;
    private String testName;
    private String score;

    public Language of(Long resumeId){
        return Language.builder()
                .resumeId(resumeId)
                .gainedDate(gainedDate)
                .language(language)
                .testName(testName)
                .score(score)
                .build();
    }
}
