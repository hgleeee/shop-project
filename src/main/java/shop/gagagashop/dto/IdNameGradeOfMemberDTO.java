package shop.gagagashop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.gagagashop.domain.MemberGrade;

@Data
@NoArgsConstructor
public class IdNameGradeOfMemberDTO {
    private long id;
    private String loginId;
    private String name;
    private String memberGrade;

    public IdNameGradeOfMemberDTO(long id, String loginId, String name, MemberGrade memberGrade) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        if (memberGrade.equals(MemberGrade.SELLER)) {
            this.memberGrade = "seller";
        } else if (memberGrade.equals(MemberGrade.CUSTOMER)) {
            this.memberGrade = "customer";
        } else {
            this.memberGrade = "admin";
        }
    }
}
