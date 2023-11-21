package efub.ebmt.eeojum.domain.diagnosis.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long answerId;

    private Long diagnosisId;

    private Long q1; //미성년인 자녀가 있나요에 대한 응답 (0 또는 1)

    private Long q2; //아이를 맡아줄 곳이 있나요 (0 또는 1)

    private Long q3; //야간 근무가 가능한가요 (0 또는 1)

    private Long q4; //근무 시간의 유연한 조정을 선호하나요 (0 또는 1)

    private Long q5; //풀타임 근무가 가능한가요 (0 또는 1)

    private Long q6; //신체적 피로도가 높은 일도 가능한가요 (0 또는 1)

}
