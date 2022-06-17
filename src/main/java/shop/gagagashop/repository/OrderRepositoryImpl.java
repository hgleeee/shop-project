package shop.gagagashop.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shop.gagagashop.domain.QMember;
import shop.gagagashop.domain.customer_related.Order;
import shop.gagagashop.domain.customer_related.OrderStatus;
import shop.gagagashop.domain.customer_related.QOrder;
import shop.gagagashop.dto.OrderSearch;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.gagagashop.domain.QMember.*;
import static shop.gagagashop.domain.customer_related.QOrder.*;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> findAll(OrderSearch orderSearch) {
        return queryFactory.selectFrom(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
                .limit(100)
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }
        return order.status.eq(orderStatus);
    }

    private BooleanExpression nameLike(String memberName) {
        if (!StringUtils.hasText(memberName)) {
            return null;
        }
        return member.name.like(memberName);
    }
}
