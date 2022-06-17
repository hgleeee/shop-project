package shop.gagagashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gagagashop.domain.Member;
import shop.gagagashop.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Optional<Member> login(String loginId, String password) {
        Member getCustomer = memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        return Optional.ofNullable(getCustomer);
    }
}