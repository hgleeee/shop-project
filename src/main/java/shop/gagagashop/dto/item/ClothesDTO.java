package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.gagagashop.domain.item.ClothesSort;
import shop.gagagashop.domain.item.Color;

@Data
@NoArgsConstructor
public class ClothesDTO extends ItemDTO {

    private ClothesSort clothesSort;

    public ClothesDTO(Long id, String itemKind, String itemName, int price, int quantity, ClothesSort clothesSort) {
        super(id, itemKind, itemName, price, quantity);
        this.clothesSort = clothesSort;
    }
}
