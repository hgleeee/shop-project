package shop.gagagashop;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.MemberGrade;
import shop.gagagashop.dto.item.ClothesDTO;
import shop.gagagashop.dto.item.ItemDTO;
import shop.gagagashop.dto.RegisterForm;
import shop.gagagashop.repository.MemberRepository;
import shop.gagagashop.service.MemberService;
import shop.gagagashop.service.ItemService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    @PostConstruct
    public void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        RegisterForm member1 = RegisterForm.builder()
                .name("김철수")
                .loginId("test")
                .password("test!")
                .frontSixSSR("123123")
                .endSevenSSR("1231234")
                .build();
        RegisterForm member2 = RegisterForm.builder()
                .name("이민수")
                .loginId("test123")
                .password("test123")
                .frontSixSSR("987987")
                .endSevenSSR("9879876")
                .build();
        memberService.join(member1);
        memberService.join(member2);

        memberRepository.save(Member.builder()
                .name("최정수")
                .loginId("admin")
                .password(passwordEncoder.encode("admin"))
                .frontSixSSR("456456")
                .endSevenSSR("4564564")
                .memberGrade(MemberGrade.ADMIN)
                .build()
        );

        for (int i = 0; i < 200; ++i) {
            itemService.saveItem(ItemDTO.builder()
                    .itemName("clothes" + i)
                    .price(i * 1000)
                    .quantity(i)
                    .itemKind("Clothes")
                    .build());
            itemService.saveItem(ItemDTO.builder()
                    .itemName("bag" + i)
                    .price(i * 1000)
                    .quantity(i)
                    .itemKind("Bag")
                    .build());
            itemService.saveItem(ItemDTO.builder()
                    .itemName("water" + i)
                    .price(i * 1000)
                    .quantity(i)
                    .itemKind("Etc")
                    .build());
        }

    }
}
