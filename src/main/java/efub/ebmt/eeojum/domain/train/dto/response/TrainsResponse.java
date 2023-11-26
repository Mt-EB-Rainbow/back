package efub.ebmt.eeojum.domain.train.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TrainsResponse {
    List<TrainResponse> educations;

    public TrainsResponse(List<TrainResponse> educations){
        this.educations = educations;
    }
}
