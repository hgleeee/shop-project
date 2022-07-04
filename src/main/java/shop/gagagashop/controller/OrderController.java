package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.gagagashop.domain.Address;
import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.customer_related.Order;
import shop.gagagashop.domain.item.Item;
import shop.gagagashop.dto.BasketToOrderForm;
import shop.gagagashop.dto.IdName.MemberIdName;
import shop.gagagashop.dto.OrderSearch;
import shop.gagagashop.service.ItemService;
import shop.gagagashop.service.MemberService;
import shop.gagagashop.service.OrderService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<MemberIdName> members = memberService.findAllIdName();
        List<MemberIdName> items = itemService.findAllIdName();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }


    @GetMapping("/mypage/order")
    public String getOrderPage(Principal principal, Model model, @RequestParam("basket") List<String> list) {
        Address address = memberService.findAddressByLoginId(principal.getName());
        int bonusPoint = memberService.findBonusPointByLoginId(principal.getName());
        model.addAttribute("address", address);
        model.addAttribute("point", bonusPoint);
        List<BasketToOrderForm> orderFormList = new ArrayList<>();
        int totalPrice = 0;
        for (int i = 0; i < list.size()/4; ++i) {
            orderFormList.add(new BasketToOrderForm(Long.parseLong(list.get(i*4)), list.get(i*4+1),
                    Integer.parseInt(list.get(i*4+2)), Integer.parseInt(list.get(i*4+3))));
            totalPrice += Integer.parseInt(list.get(i*4+2)) * Integer.parseInt(list.get(i*4+3));
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderFormList", orderFormList);
        return "mypage/orderForm";
    }

    @PostMapping("/mypage/order")
    public String postOrder(Principal principal, @RequestParam("order") List<String> orderItemList,
                            @RequestParam("address") List<String> addressList) {
        Address address = new Address(addressList.get(0), addressList.get(1), addressList.get(2));
        orderService.order(principal.getName(), orderItemList, address);
        return "redirect:/mypage/order/complete";
    }

    @GetMapping("/mypage/order/complete")
    public String getOrderComplete() {
        return "mypage/orderComplete";
    }
}