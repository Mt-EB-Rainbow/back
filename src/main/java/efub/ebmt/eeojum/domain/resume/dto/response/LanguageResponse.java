package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LanguageResponse {
    private Long languageId;
    private LocalDateTime gainedDate;
    private String language;
    private String testName;
    private String score;

    public LanguageResponse(Language language){
        this.languageId = language.getLanguageId();
        this.gainedDate = language.getGainedDate();
        this.language = language.getLanguage();
        this.testName = language.getTestName();
        this.score = language.getScore();
    }
}
