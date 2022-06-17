package shop.gagagashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gagagashop.domain.customer_related.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

}
