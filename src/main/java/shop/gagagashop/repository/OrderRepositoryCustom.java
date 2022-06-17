package shop.gagagashop.repository;

import shop.gagagashop.domain.customer_related.Order;
import shop.gagagashop.dto.OrderSearch;

import java.util.List;

public interface OrderRepositoryCustom {

    public List<Order> findAll(OrderSearch orderSearch);
}
