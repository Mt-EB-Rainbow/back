package efub.ebmt.eeojum.domain.education.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class XmlResponse {

    @XmlElement(name="response_header")
    private ResponseHeader responseHeader;
    @XmlElement(name="response_body")
    private ResponseBody responseBody;

    @Getter
    @Setter
    @XmlRootElement(name = "response_header")
    public static class ResponseHeader{
        private String version;
        private String message;
    }

    @Getter
    @Setter
    @XmlRootElement(name = "response_body")
    public static class ResponseBody{
        private String result;
        private String type;
        private List list;

        @Getter
        @Setter
        @XmlRootElement(name = "list")
        public static class List{
            private java.util.List<Data> data;

            @Getter
            @Setter
            @XmlRootElement(name = "data")
            public static class Data{
                private String courseSeq;
                private String courseName;
                private String fileUrl;
                private String majorCategoryName;
                private String middleCategoryName;
                private String contentsName;
                private String trainingGoal;
                private String trainingSummary;
                private String detailUrl;
                private String onlineTrainingTime;
                private String applyUrl;
            }
        }
    }
}
