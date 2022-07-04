package shop.gagagashop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import shop.gagagashop.domain.item.*;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import java.util.List;

import static shop.gagagashop.domain.item.QBag.*;
import static shop.gagagashop.domain.item.QClothes.*;
import static shop.gagagashop.domain.item.QEtcItem.*;
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
                .selectFrom(clothes)
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

    @Override
    public Page<Clothes> findClothes(Pageable pageable) {
        QueryResults<Clothes> results = queryFactory
                .selectFrom(clothes)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Clothes> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Bag> findBag(Pageable pageable) {
        QueryResults<Bag> results = queryFactory
                .selectFrom(bag)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Bag> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Clothes> findAccessories(Pageable pageable) {
        return null;
    }

    @Override
    public Page<EtcItem> findEtcItem(Pageable pageable) {
        QueryResults<EtcItem> results = queryFactory
                .selectFrom(etcItem)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<EtcItem> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
