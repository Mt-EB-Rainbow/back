package efub.ebmt.eeojum.domain.training.dto;

import lombok.Getter;
import lombok.Setter;

/*
    {
        "responseBody" : [
            {
                "applyUrl" : "String",
                "contentsName" : "String",
                "courseName" : "String",
                "courseSeq" : int,
                "detailUrl" : "String",
                "fileUrl" : "String",
                "majorCategoryName" : "String",
                "onlineTrainingTime" : "String",
                "trainingGoal" : <html />,
                "trainingSummary" : <html />
            }
        ]
    }
*/
@Getter
@Setter
public class CourseDto {
    private String applyUrl;
    private String contentsName;
    private String courseSeq;
    private String detailUrl;
    private String courseName;
    private String fileUrl;
    private String majorCategoryName;
    private String middleCategoryName;
    private String trainingGoal;
    private String trainingSummary;
    private String onlineTrainingTime;
    private String type;
}
