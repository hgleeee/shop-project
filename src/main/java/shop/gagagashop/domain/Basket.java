package shop.gagagashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.gagagashop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Basket {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int price;
    private int quantity;

    // item 로직
    public void setItem(Item item) {
        this.item = item;
        item.getBasketList().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getBasketList().add(this);
    }
}
