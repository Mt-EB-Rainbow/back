package efub.ebmt.eeojum.domain.train.dto.response;

import efub.ebmt.eeojum.domain.train.domain.Train;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="data")
public class TrainResponse {
    private String courseName;
    private String fileUrl;
    private String contentsName;
    private String applyUrl;

    public TrainResponse(XmlResponse.ResponseBody.List.Data data){
        this.courseName = data.getCourseName();
        this.fileUrl = data.getFileUrl();
        this.contentsName = data.getContentsName();
        this.applyUrl = data.getApplyUrl();
    }

    public TrainResponse(Train education){
        this.courseName = education.getCourseName();
        this.fileUrl = education.getFileUrl();
        this.contentsName = education.getContentsName();
        this.applyUrl = education.getApplyUrl();
    }
}
