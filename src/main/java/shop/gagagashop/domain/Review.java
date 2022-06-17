package shop.gagagashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import shop.gagagashop.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int likeNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /** 등록일 */
    private LocalDateTime insertTime;

    /** 수정일 */
    private LocalDateTime updateTime;

//    /** 삭제일 */
//    private LocalDateTime deleteTime;

    /** 연관관계 편의 메서드 */
    public void setItem(Item item) {
        this.item = item;
        item.getReviews().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    /** 생성 메서드 */
    public static Review createReview(String content) {
        Review review = new Review();
        review.content = content;
        review.insertTime = LocalDateTime.now();
        return review;
    }
}
