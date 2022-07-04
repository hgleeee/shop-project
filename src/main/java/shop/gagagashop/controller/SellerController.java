package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.gagagashop.domain.item.Bag;
import shop.gagagashop.domain.item.Clothes;
import shop.gagagashop.domain.item.EtcItem;
import shop.gagagashop.domain.item.Item;
import shop.gagagashop.dto.item.*;
import shop.gagagashop.file.FileStore;
import shop.gagagashop.service.ItemService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/seller")
public class SellerController {

    private final ItemService itemService;
    private final FileStore fileStore;

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
    public String addItem(@Valid @ModelAttribute(name = "itemForm") ItemForm itemForm, RedirectAttributes redirectAttributes) throws IOException {
        Item item = itemService.saveItem(itemForm);
        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/seller/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findById(id);
        model.addAttribute("item", item);
        return "seller/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        System.out.println(filename);
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
