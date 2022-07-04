package shop.gagagashop.domain.item;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.UploadFile;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@DiscriminatorValue(value = "B")
public class Bag extends Item {

    private Color color;

    @Builder
    public Bag(String itemName, int price, int stockQuantity, UploadFile imageFile, Color color) {
        super(itemName, price, stockQuantity, imageFile);
        this.color = color;
    }
}
