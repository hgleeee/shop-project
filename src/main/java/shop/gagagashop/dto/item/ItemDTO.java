package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.UploadFile;
import shop.gagagashop.domain.item.Bag;

@Getter @Setter
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String itemKind;
    private String itemName;
    private int price;
    private int quantity;
    private UploadFile imageFile;

    @Builder
    public ItemDTO(Long id, String itemKind, String itemName, int price, int quantity, UploadFile imageFile) {
        this.id = id;
        this.itemKind = itemKind;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imageFile = imageFile;
    }
}
