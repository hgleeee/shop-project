package shop.gagagashop.domain;

import lombok.Getter;
import shop.gagagashop.domain.item.Item;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class EachSellerItemList {

    @Id @GeneratedValue
    private Long Id;

    @OneToOne(mappedBy = "eachSellerItemList")
    private Member member;

    @OneToMany(mappedBy = "eachSellerItemList")
    private List<Item> sellItems;
}
