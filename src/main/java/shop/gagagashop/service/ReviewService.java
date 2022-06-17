package shop.gagagashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gagagashop.domain.Review;
import shop.gagagashop.dto.ReviewDTO;
import shop.gagagashop.repository.ItemRepository;
import shop.gagagashop.repository.MemberRepository;
import shop.gagagashop.repository.ReviewRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void insertReview(ReviewDTO reviewDTO) {
        Review review = Review.createReview(reviewDTO.getContent());
        review.setItem(itemRepository.findById(reviewDTO.getItemId()).get());
        review.setMember(memberRepository.findByLoginId(reviewDTO.getMemberId()).get());
        reviewRepository.save(review);
    }

    public List<ReviewDTO> findByItemIdOrderByTimeAsc(Long itemId) {
        return reviewRepository.findByItemIdOrderByTimeAsc(itemId)
                .stream().map(r -> new ReviewDTO(r.getMember().getName(), r.getContent(),
                        r.getLikeNum(), r.getInsertTime().toLocalDate()))
                .collect(Collectors.toList());
    }
}
