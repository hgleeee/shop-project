$('.radio-value').on('click', function() {
var valueCheck = $('.radio-value:checked').val(); // 체크된 Radio 버튼의 값을 가져옵니다.
    if ( valueCheck == 'no' ) {
        $('.radio-value-detail').attr('disabled', false);
        $('#value-start').focus();
    } else {
        if ($('#city1').value == null) {
            handle();
            return;
        }
        $('#city').val($('#city1').val());
        $('#street').val($('#street1').val());
        $('#zipcode').val($('#zipcode1').val());
        $('.radio-value-detail').attr('disabled', true);
    }
});

$("#discount").on("propertychange change keyup paste input", function() {
    var currentVal = $(this).val();
    if(currentVal > Number($('#point').val())) {
        $(this).val($('#point').val());
    }
    currentVal = $(this).val();
    const discount = document.getElementById('disVal').value = currentVal;
    document.getElementById('discountVal').innerHTML = currentVal;
    const total = document.getElementById('totalVal').value;
    document.getElementById('final').innerHTML = Number(total)-Number(discount);
});

function handle() {
    alert('등록된 주소가 없습니다');
    $('#auto').prop('checked', false);
    $('#manual').prop('checked', true);
    $('.radio-value-detail').attr('disabled', false);
    $('#value-start').focus;
}

function fillDiscountFull() {
    const point = document.getElementById("point").value;
    document.getElementById("discount").value = point;
    const discount = document.getElementById('disVal').value = $('#point').val();
    document.getElementById('discountVal').innerHTML = $('#point').val();
    const total = document.getElementById('totalVal').value;
    document.getElementById('final').innerHTML = Number(total)-Number(discount);
}