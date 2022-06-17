package shop.gagagashop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.gagagashop.domain.Sex;

@Data
@NoArgsConstructor
public class MemberInfoDto {

    private Long id;
    private String name;
    private int age;
    private Sex sex;
    private String emailAddress;
    private int frontSixSSR;
    private int endSevenSSR;
    private String loginId;
    private String password;
    private String homePhoneNumber;
    private String cellphoneNumber;

    @Builder
    public MemberInfoDto(Long id, String name, int age, Sex sex, String emailAddress, int frontSixSSR, int endSevenSSR,
                         String loginId, String password, String homePhoneNumber, String cellphoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.emailAddress = emailAddress;
        this.frontSixSSR = frontSixSSR;
        this.endSevenSSR = endSevenSSR;
        this.loginId = loginId;
        this.password = password;
        this.homePhoneNumber = homePhoneNumber;
        this.cellphoneNumber = cellphoneNumber;
    }
}
