package shop.gagagashop.dto;

import lombok.Data;
import shop.gagagashop.domain.customer_related.OrderStatus;

@Data
public class OrderSearch {
    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태[ORDER, CANCEL]
}
