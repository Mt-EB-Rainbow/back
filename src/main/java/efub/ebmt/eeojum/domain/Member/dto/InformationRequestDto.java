package efub.ebmt.eeojum.domain.Member.dto;

import lombok.Getter;

@Getter
public class InformationRequestDto {
    private String address;
    private String careerBreak;
    private boolean prgStatus;
    private Long desiredSalary;
}
