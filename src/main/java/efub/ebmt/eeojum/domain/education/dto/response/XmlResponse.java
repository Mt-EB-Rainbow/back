package efub.ebmt.eeojum.domain.education.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class XmlResponse {

    @XmlElement(name="response_header")
    private ResponseHeader responseHeader;
    @XmlElement(name="response_body")
    private ResponseBody responseBody;

    @Getter
    @XmlRootElement(name = "response_header")
    public static class ResponseHeader{
        private String version;
        private String message;
    }

    @Getter
    @XmlRootElement(name = "response_body")
    public static class ResponseBody{
        private String result;
        private String type;
        private List list;

        @Getter
        @XmlRootElement(name = "list")
        public static class List{
            private java.util.List<Data> dataList;

            @Getter
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
