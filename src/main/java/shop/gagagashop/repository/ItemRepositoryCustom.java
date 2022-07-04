package shop.gagagashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gagagashop.domain.item.Bag;
import shop.gagagashop.domain.item.Clothes;
import shop.gagagashop.domain.item.EtcItem;
import shop.gagagashop.domain.item.Item;

import java.util.List;

public interface ItemRepositoryCustom {

    public List<Clothes> findClothesAll();
    public Item findMaxSales();
    public List<Item> findSecondToFifthSales();
    public Page<Clothes> findClothes(Pageable pageable);
    public Page<Bag> findBag(Pageable pageable);
    public Page<Clothes> findAccessories(Pageable pageable);
    public Page<EtcItem> findEtcItem(Pageable pageable);
}
