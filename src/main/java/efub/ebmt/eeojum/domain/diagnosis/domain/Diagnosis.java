package efub.ebmt.eeojum.domain.diagnosis.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long diagnosisId;

    private Long memberId;

    private Long investigative; //탐구형

    private Long artistic; //예술형

    private Long social; //사회형

    private Long enterprising; //진취형

    private Long conventional; //관습형

    private Long realistic; //현실형

    private String recommended; //추천 직업

}