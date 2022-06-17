package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.gagagashop.vo.ItemVO;
import shop.gagagashop.service.BasketService;
import shop.gagagashop.service.ItemService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BasketService basketService;
    private final ItemService itemService;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        ItemVO maxOne = itemService.findMaxSales();
        List<ItemVO> secondFifth = itemService.findSecondToFifthSales();
        model.addAttribute("max", maxOne);
        model.addAttribute("list", secondFifth);
        return "home";
    }
}
