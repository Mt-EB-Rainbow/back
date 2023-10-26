package efub.ebmt.eeojum.domain.mentor.dto;

public class MentorDto {
    private String nickname;
    private String instruction;
    private String company;
    private String job_category;
    private String phone_number;
    private String email;
    private Long kakao_userid;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob_category() {
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getKakao_userid() {
        return kakao_userid;
    }

    public void setKakao_userid(Long kakao_userid) {
        this.kakao_userid = kakao_userid;
    }
}
