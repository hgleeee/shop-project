package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.gagagashop.domain.item.Color;

@Data
@NoArgsConstructor
public class BagDTO extends ItemDTO {
    private Color color;

    public BagDTO(Long id, String itemKind, String itemName, int price, int quantity, Color color) {
        super(id, itemKind, itemName, price, quantity);
        this.color = color;
    }
}
