package shop.gagagashop.repository;

import shop.gagagashop.domain.Basket;

import java.util.List;

public interface BasketRepositoryCustom {

    public List<Basket> findByMemberId(Long memberId);

    void removeById(Long basketItemId);

    int basketCountByLoginId(String loginId);
}
