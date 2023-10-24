package efub.ebmt.eeojum.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id", updatable = false)
    private Long infoId;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "career_break", nullable = false, length = 100)
    private String careerBreak;

    @Column(name = "prg_status", length = 20)
    private boolean prgStatus;

    @Column(name = "desired_salary", nullable = false, length = 32)
    private Long desiredSalary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Information(String address, String careerBreak, boolean prgStatus, Long desiredSalary, Member member) {
        this.address = address;
        this.careerBreak = careerBreak;
        this.prgStatus = prgStatus;
        this.desiredSalary = desiredSalary;
        this.member = member;
    }
}
