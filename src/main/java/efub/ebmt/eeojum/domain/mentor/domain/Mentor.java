package efub.ebmt.eeojum.domain.mentor.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id")
    private Long mentorId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String jobCategory;

    @Column(nullable = false)
    private LocalDateTime hiredDate;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private Long kakaoUserid;

    @Column
    private String introduction;

    @Column
    private String company;
}
