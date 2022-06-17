package shop.gagagashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.gagagashop.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {


}
