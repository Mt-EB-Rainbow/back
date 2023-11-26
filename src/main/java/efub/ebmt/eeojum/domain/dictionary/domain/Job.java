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

    private String imageUrl;

    private Long categoryId; //카테고리

    @Column(columnDefinition = "TEXT")
    private String description; //직무소개

    private String coreCompetency; //핵심역량

    @Column(columnDefinition = "TEXT")
    private String competencies; //필요한 역량, 적성

    @Column(columnDefinition = "TEXT")
    private String pros; //장점

    @Column(columnDefinition = "TEXT")
    private String cons; //단점

    private Long nightWork; //야간 근무(0: 없음, 1: 있음)

    private Long fullTime; //풀타임 근무(0: 풀타임, 1: 파트타임)

    private Long flexible; //근무시간 유연(0: 고정, 1: 유연)

    private Long physical; //몸을 많이 쓰는 직업(0: 안씀, 1: 씀)

    private String related1; //연관 직업 1

    private String related2; //연관 직업 2

    private String related3; //연관 직업 3

    private String keyword1; //키워드 1

    private String keyword2; //키워드 2

    private String keyword3; //키워드 3

}
