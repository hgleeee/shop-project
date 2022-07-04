package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.UploadFile;

@Data
@NoArgsConstructor
public class ItemForm {

    private Long id;
    private String itemKind;
    private String itemName;
    private int price;
    private int quantity;
    private MultipartFile imageFile;

    @Builder
    public ItemForm(Long id, String itemKind, String itemName, int price, int quantity, MultipartFile imageFile) {
        this.id = id;
        this.itemKind = itemKind;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imageFile = imageFile;
    }
}
