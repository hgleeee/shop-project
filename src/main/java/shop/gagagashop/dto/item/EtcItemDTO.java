package shop.gagagashop.dto.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.UploadFile;

@Data
@NoArgsConstructor
public class EtcItemDTO extends ItemDTO {

    public EtcItemDTO(Long id, String itemKind, String itemName, int price, int quantity, UploadFile imageFile) {
        super(id, itemKind, itemName, price, quantity, imageFile);
    }
}
