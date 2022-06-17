package shop.gagagashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import shop.gagagashop.domain.item.Clothes;
import shop.gagagashop.domain.item.Item;
import shop.gagagashop.domain.item.QClothes;
import shop.gagagashop.domain.item.QItem;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.gagagashop.domain.item.QItem.*;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Clothes> findClothesAll() {
        List<Clothes> findClothes = queryFactory
                .selectFrom(QClothes.clothes)
                .fetch();
        return findClothes;
    }

    @Override
    public Item findMaxSales() {
        return queryFactory
                .selectFrom(item)
                .orderBy(item.salesPerMonth.desc())
                .offset(0)
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<Item> findSecondToFifthSales() {
        return queryFactory
                .selectFrom(item)
                .orderBy(item.salesPerMonth.desc())
                .offset(1)
                .limit(4)
                .fetch();
    }
}
