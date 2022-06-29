package shop.gagagashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.gagagashop.domain.Basket;
import shop.gagagashop.domain.QBasket;
import shop.gagagashop.domain.QMember;
import shop.gagagashop.domain.item.QItem;

import javax.persistence.EntityManager;

import java.util.List;

import static shop.gagagashop.domain.QBasket.*;
import static shop.gagagashop.domain.QMember.*;
import static shop.gagagashop.domain.item.QItem.*;

@Repository
public class BasketRepositoryImpl implements BasketRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BasketRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Basket> findByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(basket)
                .leftJoin(basket.member, member)
                .where(member.id.eq(memberId))
                .fetch();
    }

    @Override
    public void removeById(Long basketId) {
        queryFactory
                .delete(basket)
                .where(basket.id.eq(basketId))
                .execute();
    }

    @Override
    public int basketCountByLoginId(String loginId) {
        return queryFactory
                .select(basket.count().castToNum(Integer.class))
                .from(basket)
                .leftJoin(basket.member, member)
                .where(basket.member.loginId.eq(loginId))
                .fetchOne();
    }

    @Override
    public boolean existItem(Long itemId, String loginId) {
        List<Long> result = queryFactory
                .select(basket.item.id)
                .from(basket)
                .join(basket.member, member)
                .join(basket.item, item)
                .where(basket.member.loginId.eq(loginId).and(basket.item.id.eq(itemId)))
                .fetch();
        if (result.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void addQuantity(Long itemId, String loginId, int quantity) {
        queryFactory
                .update(basket)
                .set(basket.quantity, basket.quantity.add(quantity))
                .where(basket.item.id.eq(itemId))
                .execute();
    }
}
