package shop.gagagashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateParamDto {
    String ItemName;
    int price;
    int stockQuantity;
}
