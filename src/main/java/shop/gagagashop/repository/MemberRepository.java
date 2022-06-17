package shop.gagagashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gagagashop.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
