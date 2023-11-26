package efub.ebmt.eeojum.domain.dictionary.dto.response;

import efub.ebmt.eeojum.domain.dictionary.domain.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobDetailResponse {
    private Long jobId;
    private String name; //직업명
    private Long categoryId; //카테고리
    private String description; //직무소개
    private String coreCompetency; //핵심역량
    private String competencies; //필요한 역량, 적성
    private String pros; //장점
    private String cons; //단점

    public JobDetailResponse(Job job){
        this.jobId = job.getJobId();
        this.name = job.getName();
        this.categoryId = job.getCategoryId();
        this.description = job.getDescription();
        this.coreCompetency = job.getCoreCompetency();
        this.competencies = job.getCompetencies();
        this.pros = job.getPros();
        this.cons = job.getCons();
    }
}
