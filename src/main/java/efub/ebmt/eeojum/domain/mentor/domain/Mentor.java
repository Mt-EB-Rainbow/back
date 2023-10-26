package efub.ebmt.eeojum.domain.mentor.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id")
    private Long mentor_id;

    @Column(nullable = false)
    private Long member_id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String job_category;

    @Column(nullable = false)
    private LocalDateTime hired_date;

    @Column
    private String phone_number;

    @Column
    private String email;

    @Column
    private Long kakao_userid;

    @Column
    private String introduction;

    @Column
    private String company;

}
