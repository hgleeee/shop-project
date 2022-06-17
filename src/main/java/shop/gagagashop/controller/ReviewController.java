package shop.gagagashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.gagagashop.dto.ReviewDTO;
import shop.gagagashop.service.ReviewService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/write")
    public String insertReview(Principal principal, @RequestParam("idx") Long idx, @RequestParam("content") String content) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setItemId(idx);
        reviewDTO.setContent(content);
        reviewDTO.setMemberId(principal.getName());
        reviewService.insertReview(reviewDTO);
        return "redirect:/item/" + idx;
    }

    @GetMapping("/getCommentList")
    @ResponseBody
    private List<ReviewDTO> getCommentList(@RequestParam("idx") long idx) {
        return reviewService.findByItemIdOrderByTimeAsc(idx);
    }
}
