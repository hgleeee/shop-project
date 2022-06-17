package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.gagagashop.domain.item.Bag;

@Getter @Setter
@NoArgsConstructor
public class ItemDTO {
    Long id;
    String itemKind;
    String itemName;
    int price;
    int quantity;

    @Builder
    public ItemDTO(Long id, String itemKind, String itemName, int price, int quantity) {
        this.id = id;
        this.itemKind = itemKind;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
