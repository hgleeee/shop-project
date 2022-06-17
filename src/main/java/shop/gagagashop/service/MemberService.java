package shop.gagagashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.MemberGrade;
import shop.gagagashop.dto.IdName.MemberIdName;
import shop.gagagashop.dto.IdNameGradeOfMemberDTO;
import shop.gagagashop.dto.RegisterForm;
import shop.gagagashop.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final 이 있는 필드만 생성자로 만들어준다.
@Service
@Transactional(readOnly = true) // false 가 기본값, 읽기 전용모드면 최적화해준다.
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(RegisterForm form) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        validateDuplicateCustomer(form.toEntity());
        return memberRepository.save(form.toEntity()).getId();
    }

    private void validateDuplicateCustomer(Member member) {
       //  Exception
        Optional<Member> findCustomer = memberRepository.findBySSR(member.getFrontSixSSR(), member.getEndSevenSSR());
        if (!findCustomer.isEmpty()) {
            throw new IllegalStateException("이미 가입하신 회원입니다.");
        }
    }

    public List<MemberIdName> findAllIdName() {
        return memberRepository.findAll().stream().map(m -> new MemberIdName(m.getId(), m.getName()))
                .collect(Collectors.toList());

    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Long findIdByLoginId(String loginId) {
        return memberRepository.findIdByLoginId(loginId);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberRepository.findByLoginId(loginId);
        Member findMember = memberWrapper.orElseThrow(() -> {
            throw new UsernameNotFoundException("해당 id의 유저를 찾을 수 없습니다.");
        });
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (findMember.getMemberGrade().equals(MemberGrade.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(MemberGrade.ADMIN.getValue()));
        } else if (findMember.getMemberGrade().equals(MemberGrade.CUSTOMER)) {
            authorities.add(new SimpleGrantedAuthority(MemberGrade.CUSTOMER.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberGrade.SELLER.getValue()));
        }

        memberRepository.increaseVisitCount(loginId);

        return new User(findMember.getLoginId(), findMember.getPassword(), authorities);
    }

    public List<IdNameGradeOfMemberDTO> findIdNameGradeBySub(String sub) {
        List<IdNameGradeOfMemberDTO> list = new ArrayList<>();
        for (Member member : memberRepository.findIdNameGradeBySub(sub)) {
            IdNameGradeOfMemberDTO element = new IdNameGradeOfMemberDTO();
            element.setName(member.getName());
            element.setId(member.getId());
            element.setLoginId(member.getLoginId());
            if (member.getMemberGrade().equals(MemberGrade.SELLER)) {
                element.setMemberGrade("판매자");
            } else if (member.getMemberGrade().equals(MemberGrade.CUSTOMER)) {
                element.setMemberGrade("고객");
            } else {
                element.setMemberGrade("관리자");
            }
            list.add(element);
        }
        return list;
    }

    public IdNameGradeOfMemberDTO findMemberGradeById(Long id) {
        return memberRepository.findById(id).map(m -> new IdNameGradeOfMemberDTO(
                m.getId(), m.getLoginId(), m.getName(), m.getMemberGrade())).get();

    }

    @Transactional
    public void updateMemberGrade(Long id, String str) {
        MemberGrade memberGrade = null;
        if (str.equals("admin")) {
            memberGrade = MemberGrade.ADMIN;
        } else if (str.equals("seller")) {
            memberGrade = MemberGrade.SELLER;
        } else {
            memberGrade = MemberGrade.CUSTOMER;
        }
        memberRepository.updateMemberGrade(id, memberGrade);
    }
}
