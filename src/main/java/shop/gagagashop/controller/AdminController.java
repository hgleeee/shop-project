package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.gagagashop.dto.IdNameGradeOfMemberDTO;
import shop.gagagashop.dto.item.ItemDTO;
import shop.gagagashop.dto.item.ItemForm;
import shop.gagagashop.service.ItemService;
import shop.gagagashop.service.MemberService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("/items")
    public String adminItemList(Model model) {
        List<ItemDTO> itemsDTO = itemService.findAll();
        model.addAttribute("items", itemsDTO);
        return "admin/itemsForAdmin";
    }

    @GetMapping("/items/{itemId}")
    public String adminItemListDetail(@PathVariable long itemId, Model model) {
        ItemDTO findItem = itemService.findOne(itemId);
        model.addAttribute("item", findItem);
        return "admin/itemDetailForAdmin";
    }

    @PostMapping("/items/{itemId}")
    public String removeItem(@PathVariable long itemId) {
        itemService.deleteById(itemId);
        return "redirect:/admin/items";
    }

    @GetMapping("/items/add")
    public String addItemForm(Model model) {
        model.addAttribute("itemForm", new ItemDTO());
        return "admin/itemAddTest";
    }

    @PostMapping("/items/add")
    public String addItem(@Valid @ModelAttribute(name = "itemForm") ItemForm itemForm) throws IOException {
        itemService.saveItem(itemForm);
        return "redirect:/admin/items";
    }

    @GetMapping()
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/authorize")
    public String getAuthorize() {
        return "admin/authorize";
    }

    @GetMapping("/authorize/getMemberList")
    @ResponseBody
    public List<IdNameGradeOfMemberDTO> getMemberList(@RequestParam(name = "sub") String sub) {
        if (sub.isEmpty()) {
            return null;
        }
        return memberService.findIdNameGradeBySub(sub);
    }

    @GetMapping("/authorize/{memberId}")
    public String getMemberAuthEdit(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("member", memberService.findMemberGradeById(memberId));
        return "admin/editMemberAuth";
    }

    @PostMapping("/editMemberAuth")
    public String postMemberAuthEdit(@RequestParam("memberId") Long memberId, @RequestParam("grade") String grade) {
        memberService.updateMemberGrade(memberId, grade);
        return "redirect:/admin/authorize";
    }
}
