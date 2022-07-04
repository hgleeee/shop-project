package shop.gagagashop.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.UploadFile;

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
    public Clothes(String itemName, int price, int stockQuantity, UploadFile imageFile, ClothesSort clothesSort) {
        super(itemName, price, stockQuantity, imageFile);
        this.clothesSort = clothesSort;
    }
}
