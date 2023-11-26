package efub.ebmt.eeojum.domain.education.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="data")
public class EducationResponse {
    private String courseName;
    private String fileUrl;
    private String contentsName;
    private String applyUrl;

    public EducationResponse(XmlResponse.ResponseBody.List.Data data){
        this.courseName = data.getCourseName();
        this.fileUrl = data.getFileUrl();
        this.contentsName = data.getContentsName();
        this.applyUrl = data.getApplyUrl();
    }
}
