package shop.gagagashop.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "E")
public class EtcItem extends Item {

    @Builder
    public EtcItem(String itemName, int price, int stockQuantity) {
        super(itemName, price, stockQuantity);
    }
}
