package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LanguageRequest {
    //private Long resumeId;
    private LocalDateTime gainedDate;
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
