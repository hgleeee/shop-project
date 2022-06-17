package shop.gagagashop.repository;

import shop.gagagashop.domain.Review;
import shop.gagagashop.dto.ReviewDTO;

import java.util.List;

public interface ReviewRepositoryCustom {

    public List<Review> findByItemIdOrderByTimeAsc(Long itemId);
}
