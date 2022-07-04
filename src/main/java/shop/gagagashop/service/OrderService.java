package shop.gagagashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gagagashop.domain.Address;
import shop.gagagashop.domain.Delivery;
import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.customer_related.Order;
import shop.gagagashop.domain.customer_related.OrderItem;
import shop.gagagashop.domain.item.Item;
import shop.gagagashop.dto.OrderSearch;
import shop.gagagashop.repository.ItemRepository;
import shop.gagagashop.repository.MemberRepository;
import shop.gagagashop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static shop.gagagashop.domain.item.QItem.item;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        // 배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setAddress(optionalMember.get().getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(optionalItem.get(), optionalItem.get().getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(optionalMember.get(), delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public Long order(String loginId, List<String> orderItemList, Address address) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < orderItemList.size()/3; ++i) {
            Long itemId = Long.parseLong(orderItemList.get(i*3));
            int subPrice = Integer.parseInt(orderItemList.get(i*3+1));
            int count = Integer.parseInt(orderItemList.get(i*3+2));
            orderItems.add(OrderItem.createOrderItem(itemRepository.findById(itemId).get(), count, subPrice));
        }

        // 배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setAddress(address);

        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);

        // 주문 생성
        OrderItem[] orderItemArr = orderItems.toArray(new OrderItem[orderItems.size()]);
        Order order = Order.createOrder(optionalMember.get(), delivery, orderItemArr);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId).get();
        // 주문 취소
        order.cancel();
    }


    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
