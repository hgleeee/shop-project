package shop.gagagashop.domain.item;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import shop.gagagashop.domain.Basket;
import shop.gagagashop.domain.EachSellerItemList;
import shop.gagagashop.domain.Review;
import shop.gagagashop.domain.UploadFile;
import shop.gagagashop.domain.customer_related.OrderItem;
import shop.gagagashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String itemName;
    private int price;
    private int stockQuantity;
    private int salesPerMonth;
    private String uploadFileName;
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_items_key")
    private EachSellerItemList eachSellerItemList;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Basket> basketList = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    public Item(String itemName, int price, int stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Item(String itemName, int price, int stockQuantity, UploadFile imageFile) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        if (imageFile == null) {
            this.uploadFileName = null;
            this.storeFileName = null;
        } else {
            this.uploadFileName = imageFile.getUploadFileName();
            this.storeFileName = imageFile.getStoreFileName();
        }
    }

    public void changeItem(String itemName, int price, int stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        eachSellerItemList.getSellItems().add(this);
    }

    /* 비즈니스 로직 */
    public void plusStockQuantity(int plusQuantity) {
        stockQuantity += plusQuantity;
    }

    public void minusStockQuantity(int minusQuantity) {
        int resultStock = stockQuantity - minusQuantity;
        if (resultStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다");
        } else {
            stockQuantity = resultStock;
        }
    }

}
