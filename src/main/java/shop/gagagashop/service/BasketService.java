package shop.gagagashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gagagashop.domain.Basket;
import shop.gagagashop.repository.BasketRepository;
import shop.gagagashop.repository.ItemRepository;
import shop.gagagashop.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasketService {

    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public List<Basket> findByMemberId(Long memberId) {
        return basketRepository.findByMemberId(memberId);
    }

    @Transactional
    public void addBasket(String loginId, Long itemId, int quantity) {
        if (basketRepository.existItem(itemId, loginId)) {
            basketRepository.addQuantity(itemId, loginId, quantity);
            return;
        }
        Basket basket = new Basket();
        basket.setItem(itemRepository.findById(itemId).get());
        basket.setMember(memberRepository.findByLoginId(loginId).get());
        basket.setPrice(itemRepository.findById(itemId).get().getPrice() * quantity);
        basket.setQuantity(quantity);
        basketRepository.save(basket);
    }

    @Transactional
    public void removeById(Long basketId) {
        basketRepository.removeById(basketId);
    }

    public int basketCountByLoginId(String loginId) {
        return basketRepository.basketCountByLoginId(loginId);
    }
}
