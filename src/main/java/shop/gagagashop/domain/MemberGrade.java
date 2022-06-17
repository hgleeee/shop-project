package shop.gagagashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberGrade {
    ADMIN("ROLE_ADMIN"),
    SELLER("ROLE_SELLER"),
    CUSTOMER("ROLE_CUSTOMER");
    private String value;
}
