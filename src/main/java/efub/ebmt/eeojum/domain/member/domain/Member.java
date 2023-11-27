package efub.ebmt.eeojum.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,24}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Column(nullable = false, length = 120)
    private String pw;

    @Column(name = "is_mentor")
    private Boolean isMentor;

    public Member update(String name) {
        this.name = name;
        return this;
    }
    @Builder
    public Member(String name, String email, String pw, String nickname, Date birth, boolean isMentor) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.isMentor = false;
    }
}
