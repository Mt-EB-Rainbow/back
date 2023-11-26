package efub.ebmt.eeojum.domain.training.dto;

import efub.ebmt.eeojum.domain.training.domain.Training;
import lombok.Getter;

@Getter
public class CourseResponseDto {
    private final String applyUrl;
    private final String contentsName;
    private final String courseSeq;
    private final String detailUrl;
    private final String courseName;
    private final String fileUrl;
    private final String majorCategoryName;
    private final String middleCategoryName;
    private final String onlineTrainingTime;

    public CourseResponseDto(String applyUrl, String contentsName, String courseSeq, String detailUrl,
                       String courseName, String fileUrl, String majorCategoryName,
                       String middleCategoryName, String onlineTrainingTime) {
        this.applyUrl = applyUrl;
        this.contentsName = contentsName;
        this.courseSeq = courseSeq;
        this.detailUrl = detailUrl;
        this.courseName = courseName;
        this.fileUrl = fileUrl;
        this.majorCategoryName = majorCategoryName;
        this.middleCategoryName = middleCategoryName;
        this.onlineTrainingTime = onlineTrainingTime;
    }

    public static CourseResponseDto fromEntity(Training training) {
        return new CourseResponseDto(
                training.getApplyUrl(),
                training.getContentsName(),
                training.getCourseSeq(),
                training.getDetailUrl(),
                training.getCourseName(),
                training.getFileUrl(),
                training.getMajorCategoryName(),
                training.getMiddleCategoryName(),
                training.getOnlineTrainingTime()
        );
    }
}
