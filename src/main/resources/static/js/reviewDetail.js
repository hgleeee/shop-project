$(document).ready(function() {
    getCommentList();
})

function getCommentList() {
    const idx = $('input[name=idx]').val();
    $.ajax({
        type: 'GET',
        url: '/review/getCommentList',
        data: {idx},
        success: function(result) {
            var str = "<hr/>";
            var cnt = result.length;

            if (result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    str += "<div>";
                    str += "<div><span><strong>" + result[i].memberId+"</strong></span>";
                    str += "<span class='text_right'>" + result[i].insertDate + " </span>"
                    str += "<span class='text_right'> 공감 수: " + result[i].likeNum + "</span>"
                    str += "</div>";
                    str += result[i].content + "</div>";
                }
            } else {
                str += "<div>";
                str += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                str += "</table></div>";
                str += "</div>";
            }
            $("#cnt").html(cnt);
            $("#comment").html(str);
        },
        error: function(result) {
        },
        complete: function() {
        }
    })
}