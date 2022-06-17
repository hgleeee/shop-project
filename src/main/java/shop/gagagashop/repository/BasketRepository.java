package shop.gagagashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.gagagashop.domain.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>, BasketRepositoryCustom {
}
