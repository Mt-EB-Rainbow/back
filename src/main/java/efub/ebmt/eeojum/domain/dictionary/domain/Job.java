package efub.ebmt.eeojum.domain.dictionary.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long jobId;

    private String name; //직업명

    private String hollandType1; //홀랜드유형1

    private String hollandType2; //홀랜드유형2

    private String description; //직무소개

    private String coreCompetency; //핵심역량

    private String competencies; //필요한 역량, 적성

    private String pros; //장점

    private String cons; //단점

    private Boolean nightWork; //야간 근무

    private Boolean fullTime; //풀타임 근무

    private Boolean physical; //몸을 많이 쓰는 직업

    private String related1; //연관 직업 1

    private String related2; //연관 직업 2

    private String related3; //연관 직업 3

    private String related4; //연관 직업 4

    private String keyword1; //키워드 1

    private String keyword2; //키워드 2

    private String keyword3; //키워드 3

}
