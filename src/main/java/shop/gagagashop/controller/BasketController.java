package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.gagagashop.domain.Address;
import shop.gagagashop.domain.Basket;
import shop.gagagashop.dto.BasketToOrderForm;
import shop.gagagashop.service.BasketService;
import shop.gagagashop.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;
    private final MemberService memberService;

    @GetMapping("/mypage/basket")
    public String getBasketItems(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        List<Basket> basketItems = basketService.findByMemberId(memberService.findIdByLoginId(principal.getName()));
        int totalPrice = basketItems.stream().mapToInt(o -> o.getPrice()*o.getQuantity()).sum();
        model.addAttribute("basketItems", basketItems);
        model.addAttribute("totalPrice", totalPrice);
        return "mypage/basket";
    }

    @PostMapping("/basket/add")
    public String addBasket(HttpServletRequest request, Principal principal, @RequestParam("itemId") Long itemId,
                            @RequestParam("quantity") int quantity) {
        String referer = request.getHeader("referer");
        basketService.addBasket(principal.getName(), itemId, quantity);
        return "redirect:"+referer;
    }

    @PostMapping("/mypage/basket/{basketId}/remove")
    public String removeBasket(@PathVariable("basketId") Long basketId) {
        basketService.removeById(basketId);
        return "redirect:/mypage/basket";
    }

//    @PostMapping("/mypage/basket")
//    public String PostBasketItems(@RequestParam("basket") List<String> list, RedirectAttributes rttr) {
//        List<BasketToOrderForm> orderFormList = new ArrayList<>();
//        for (int i = 0; i < list.size()/3; ++i) {
//            orderFormList.add(new BasketToOrderForm(list.get(i*3),
//                    Integer.parseInt(list.get(i*3+1)), Integer.parseInt(list.get(i*3+2))));
//        }
//        rttr.addFlashAttribute("orderFormList", orderFormList);
//        return "redirect:/mypage/order";
//    }

}
