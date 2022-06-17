package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.gagagashop.domain.Basket;
import shop.gagagashop.service.BasketService;
import shop.gagagashop.service.MemberService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;
    private final MemberService memberService;

    @GetMapping("/mypage/basket")
    public String getBasketItems(Principal principal, Model model) {
        List<Basket> basketItems = basketService.findByMemberId(memberService.findIdByLoginId(principal.getName()));
        int totalPrice = basketItems.stream().mapToInt(o -> o.getPrice()).sum();
        model.addAttribute("basketItems", basketItems);
        model.addAttribute("totalPrice", totalPrice);
        return "mypage/basket";
    }

    @PostMapping("/basket/add")
    public String addBasket(Principal principal, @RequestParam("itemId") Long itemId,
                            @RequestParam("quantity") int quantity) {
        basketService.saveItem(principal.getName(), itemId, quantity);
        return "redirect:/";
    }

    @PostMapping("/mypage/basket/{basketId}/remove")
    public String removeBasket(@PathVariable("basketId") Long basketId) {
        basketService.removeById(basketId);
        return "redirect:/mypage/basket";
    }
}
