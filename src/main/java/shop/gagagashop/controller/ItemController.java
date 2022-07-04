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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final BasketService basketService;

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

    @GetMapping("/item/clothes")
    public String getClothesItem(Principal principal, @PageableDefault(page = 1) Pageable pageable, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        Page<ItemDTO> items = itemService.findClothesOrderByNameAsc(pageable);
        model.addAttribute("items", items);
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/PagingConst.BLOCK_LIMIT)))-1)*PagingConst.BLOCK_LIMIT+1;
        int endPage = (startPage+PagingConst.BLOCK_LIMIT-1 < items.getTotalPages()) ? startPage+PagingConst.BLOCK_LIMIT-1 : items.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "item/clothes";
    }

    @GetMapping("/item/bag")
    public String getBagItem(Principal principal, @PageableDefault(page = 1) Pageable pageable, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        Page<ItemDTO> items = itemService.findBagOrderByNameAsc(pageable);
        model.addAttribute("items", items);
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/PagingConst.BLOCK_LIMIT)))-1)*PagingConst.BLOCK_LIMIT+1;
        int endPage = (startPage+PagingConst.BLOCK_LIMIT-1 < items.getTotalPages()) ? startPage+PagingConst.BLOCK_LIMIT-1 : items.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "item/bag";
    }

    @GetMapping("/item/accessories")
    public String getAccessoryItem(Principal principal, @PageableDefault(page = 1) Pageable pageable, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        Page<ItemDTO> items = itemService.findAccessoryOrderByNameAsc(pageable);
        model.addAttribute("items", items);
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/PagingConst.BLOCK_LIMIT)))-1)*PagingConst.BLOCK_LIMIT+1;
        int endPage = (startPage+PagingConst.BLOCK_LIMIT-1 < items.getTotalPages()) ? startPage+PagingConst.BLOCK_LIMIT-1 : items.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "item/accessories";
    }

    @GetMapping("/item/etc")
    public String getEtcItem(Principal principal, @PageableDefault(page = 1) Pageable pageable, Model model) {
        if (principal != null) {
            model.addAttribute("basketCount", basketService.basketCountByLoginId(principal.getName()));
        }
        Page<ItemDTO> items = itemService.findEtcOrderByNameAsc(pageable);
        model.addAttribute("items", items);
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/PagingConst.BLOCK_LIMIT)))-1)*PagingConst.BLOCK_LIMIT+1;
        int endPage = (startPage+PagingConst.BLOCK_LIMIT-1 < items.getTotalPages()) ? startPage+PagingConst.BLOCK_LIMIT-1 : items.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "item/etc";
    }

    @GetMapping("/item/{itemId}")
    public String getItemDetail(HttpServletRequest request, @PathVariable("itemId") Long itemId, Model model) {
        ItemDTO findOne = itemService.findById(itemId);
        model.addAttribute("item", findOne);
        return "item/itemDetailForCustomer";
    }


}
