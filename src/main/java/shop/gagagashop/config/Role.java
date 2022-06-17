package shop.gagagashop.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    SELLER("ROLE_SELLER"),
    CUSTOMER("ROLE_CUSTOMER");

    private String value;
}
