package shop.gagagashop.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@DiscriminatorValue(value = "C")
public class Clothes extends Item {

    @Column(name = "CLOTHES_KIND") @Enumerated
    private ClothesSort clothesSort;

    @Builder
    public Clothes(String itemName, int price, int stockQuantity, ClothesSort clothesSort) {
        super(itemName, price, stockQuantity);
        this.clothesSort = clothesSort;
    }
}
