package shop.gagagashop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.MemberGrade;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class RegisterForm {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String name;

    private String age;

    @NotEmpty
    @Size(min = 6, max = 6)
    private String frontSixSSR;

    @NotEmpty
    @Size(min = 7, max = 7)
    private String endSevenSSR;

    private String homePhoneNumber;

    @NotEmpty
    private String cellphoneNumber;

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .frontSixSSR(frontSixSSR)
                .endSevenSSR(endSevenSSR)
                .homePhoneNumber(homePhoneNumber)
                .cellphoneNumber(cellphoneNumber)
                .memberGrade(MemberGrade.CUSTOMER)
                .build();
    }

    @Builder
    public RegisterForm(String loginId, String password, String confirmPassword, String name,
                        String frontSixSSR, String endSevenSSR, String homePhoneNumber, String cellphoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.frontSixSSR = frontSixSSR;
        this.endSevenSSR = endSevenSSR;
        this.homePhoneNumber = homePhoneNumber;
        this.cellphoneNumber = cellphoneNumber;
    }
}
