package shop.gagagashop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IntoBasketDTO {

    private String itemName;
    private int price;
    private int quantity;
}
