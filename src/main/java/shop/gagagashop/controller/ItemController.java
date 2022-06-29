package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.gagagashop.PagingConst;
import shop.gagagashop.dto.item.ItemDTO;
import shop.gagagashop.service.BasketService;
import shop.gagagashop.service.ItemService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final BasketService basketService;

//    @GetMapping("/totalItems")
//    @ResponseBody
//    public List<ItemDTO> getTotalItem2(@PageableDefault(size = 10) Pageable pageable, Model model) {
//        return itemService.findAll(pageable);
//    }

    @GetMapping("/item/total")
    public String getTotalItem(Principal principal, @PageableDefault(page = 1) Pageable pageable, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        Page<ItemDTO> items = itemService.findAllOrderByNameAsc(pageable);
        model.addAttribute("items", items);
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/PagingConst.BLOCK_LIMIT)))-1)*PagingConst.BLOCK_LIMIT+1;
        int endPage = (startPage+PagingConst.BLOCK_LIMIT-1 < items.getTotalPages()) ? startPage+PagingConst.BLOCK_LIMIT-1 : items.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "item/totalItem";
    }

    @GetMapping("/item/{itemId}")
    public String getItemDetail(@PathVariable("itemId") Long itemId, Model model) {
        ItemDTO findOne = itemService.findOne(itemId);
        model.addAttribute("item", findOne);
        return "item/itemDetailForCustomer";
    }
}
