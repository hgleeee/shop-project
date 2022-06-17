package shop.gagagashop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long itemId;
    private String memberId;
    private String content;
    private int likeNum;
    private LocalDate insertDate;
    private LocalDateTime insertTime;

    public ReviewDTO(String memberId, String content, int likeNum, LocalDate insertDate) {
        this.memberId = memberId;
        this.content = content;
        this.likeNum = likeNum;
        this.insertDate = insertDate;
    }

    public ReviewDTO(String memberId, String content, int likeNum, LocalDateTime insertTime) {
        this.memberId = memberId;
        this.content = content;
        this.likeNum = likeNum;
        this.insertTime = insertTime;
    }
}
