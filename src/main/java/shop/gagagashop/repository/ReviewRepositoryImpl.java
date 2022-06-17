package shop.gagagashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.gagagashop.domain.QMember;
import shop.gagagashop.domain.QReview;
import shop.gagagashop.domain.Review;
import shop.gagagashop.domain.item.Item;
import shop.gagagashop.domain.item.QItem;
import shop.gagagashop.dto.ReviewDTO;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static shop.gagagashop.domain.QMember.*;
import static shop.gagagashop.domain.QReview.*;
import static shop.gagagashop.domain.item.QItem.*;


@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Review> findByItemIdOrderByTimeAsc(Long itemId) {
        return queryFactory
                .selectFrom(review)
                .innerJoin(review.item, item)
                .where(review.item.id.eq(itemId))
                .orderBy(review.insertTime.asc())
                .fetch();
    }
}
