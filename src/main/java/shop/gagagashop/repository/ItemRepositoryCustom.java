package shop.gagagashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gagagashop.domain.item.Clothes;
import shop.gagagashop.domain.item.Item;

import java.util.List;

public interface ItemRepositoryCustom {

    public List<Clothes> findClothesAll();
    public Item findMaxSales();
    public List<Item> findSecondToFifthSales();
}
