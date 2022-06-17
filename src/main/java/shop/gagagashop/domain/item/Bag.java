package shop.gagagashop.domain.item;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@DiscriminatorValue(value = "B")
public class Bag extends Item {

    private Color color;

    @Builder
    public Bag(String itemName, int price, int stockQuantity, Color color) {
        super(itemName, price, stockQuantity);
        this.color = color;
    }
}
