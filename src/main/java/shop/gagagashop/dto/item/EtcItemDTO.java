package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EtcItemDTO extends ItemDTO {

    public EtcItemDTO(Long id, String itemKind, String itemName, int price, int quantity) {
        super(id, itemKind, itemName, price, quantity);
    }
}
