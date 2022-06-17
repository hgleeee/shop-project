package shop.gagagashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gagagashop.domain.customer_related.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String sex;
    private String emailAddress;
    private String frontSixSSR;
    private String endSevenSSR;
    private String loginId;
    private String password;
    private String homePhoneNumber;
    private String cellphoneNumber;
    private int visitCount;

    @OneToOne
    @JoinColumn(name = "item_list_id")
    private EachSellerItemList eachSellerItemList;

    @OneToMany(mappedBy = "member")
    private List<Basket> basketList;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade;

    @Embedded
    private Address address;

    @Builder
    public Member(Long id, String name, int age, String sex, String emailAddress, String frontSixSSR, String endSevenSSR,
                  String loginId, String password, String homePhoneNumber, String cellphoneNumber, int visitCount,
                  EachSellerItemList eachSellerItemList, MemberGrade memberGrade, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.emailAddress = emailAddress;
        this.frontSixSSR = frontSixSSR;
        this.endSevenSSR = endSevenSSR;
        this.loginId = loginId;
        this.password = password;
        this.homePhoneNumber = homePhoneNumber;
        this.cellphoneNumber = cellphoneNumber;
        this.visitCount = visitCount;
        this.eachSellerItemList = eachSellerItemList;
        this.memberGrade = memberGrade;
        this.address = address;
    }

    public void setMemberGrade(MemberGrade memberGrade) {
        this.memberGrade = memberGrade;
    }

}
