package efub.ebmt.eeojum.domain.member.dto;

import lombok.Getter;

@Getter
public class InformationRequestDto {
    private String address;
    private String careerBreak;
    private boolean prgStatus;
    private Long desiredSalary;
}
