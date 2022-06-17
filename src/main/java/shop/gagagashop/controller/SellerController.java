package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gagagashop.domain.item.Bag;
import shop.gagagashop.domain.item.Clothes;
import shop.gagagashop.domain.item.EtcItem;
import shop.gagagashop.dto.item.BagDTO;
import shop.gagagashop.dto.item.ClothesDTO;
import shop.gagagashop.dto.item.EtcItemDTO;
import shop.gagagashop.dto.item.ItemDTO;
import shop.gagagashop.service.ItemService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/seller")
public class SellerController {

    private final ItemService itemService;

    @GetMapping()
    public String sellerHome() {
        return "seller/index";
    }

    @GetMapping("/items/add")
    public String getAddItem(Model model) {
        model.addAttribute("itemForm", new ItemDTO());
        return "seller/addItemForm";
    }

    @PostMapping("/items/add")
    public String addItem(@Valid @ModelAttribute(name = "itemForm") ItemDTO itemForm) {

        itemService.saveItem(itemForm);
        return "redirect:/seller";
    }
}
