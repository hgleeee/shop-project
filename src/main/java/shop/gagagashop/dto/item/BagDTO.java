package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.UploadFile;
import shop.gagagashop.domain.item.Color;

@Data
@NoArgsConstructor
public class BagDTO extends ItemDTO {
    private Color color;

    public BagDTO(Long id, String itemKind, String itemName, int price, int quantity, UploadFile imageFile, Color color) {
        super(id, itemKind, itemName, price, quantity, imageFile);
        this.color = color;
    }
}
