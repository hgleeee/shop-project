package shop.gagagashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.gagagashop.domain.Address;
import shop.gagagashop.domain.Member;
import shop.gagagashop.domain.MemberGrade;
import shop.gagagashop.domain.QMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static shop.gagagashop.domain.QMember.*;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Member> findBySSR(String sixSSR, String sevenSSR) {
        Member findCustomer = queryFactory
                .selectFrom(member)
                .where(member.frontSixSSR.like(sixSSR).and(member.endSevenSSR.like(sevenSSR)))
                .fetchOne();
        return Optional.ofNullable(findCustomer);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
        return Optional.ofNullable(findMember);
    }

    @Override
    public Long increaseVisitCount(String loginId) {
        long ret = queryFactory
                .update(member)
                .set(member.visitCount, member.visitCount.add(1))
                .where(member.loginId.eq(loginId))
                .execute();
        return ret;
    }

    @Override
    public Long findIdByLoginId(String loginId) {
        return queryFactory
                .select(member.id)
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
    }

    @Override
    public List<Member> findIdNameGradeBySub(String sub) {
        return queryFactory
                .selectFrom(member)
                .where(member.loginId.contains(sub).or(member.name.contains(sub)))
                .orderBy(member.name.asc())
                .limit(20)
                .fetch();
    }

    @Override
    public MemberGrade findMemberGradeById(Long id) {
        return queryFactory
                .select(member.memberGrade)
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
    }

    @Override
    public void updateMemberGrade(Long id, MemberGrade memberGrade) {
        queryFactory
                .update(member)
                .set(member.memberGrade, memberGrade)
                .where(member.id.eq(id))
                .execute();
    }

    @Override
    public Address findAddressByLoginId(String loginId) {
        return queryFactory
                .select(member.address)
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
    }

    @Override
    public Integer findBonusPointByLoginId(String loginId) {
        return queryFactory
                .select(member.bonusPoint)
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
    }
}
