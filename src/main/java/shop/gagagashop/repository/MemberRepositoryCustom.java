package shop.gagagashop.repository;

import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.MemberGrade;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    public Optional<Member> findBySSR(String sixSSR, String sevenSSR);
    public Optional<Member> findByLoginId(String loginId);
    public Long increaseVisitCount(String loginId);
    public Long findIdByLoginId(String loginId);
    public List<Member> findIdNameGradeBySub(String sub);
    public MemberGrade findMemberGradeById(Long id);
    public void updateMemberGrade(Long id, MemberGrade memberGrade);
}
