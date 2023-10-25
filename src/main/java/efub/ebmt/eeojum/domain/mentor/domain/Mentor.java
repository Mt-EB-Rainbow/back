package efub.ebmt.eeojum.domain.mentor.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    private String Email;

    @Column
    private Long KakaoUserid;

    @Column
    private String introduction;

    @Column
    private String company;

}
