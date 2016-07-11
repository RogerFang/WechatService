/**
 * Created by Roger on 2016/7/11.
 */
$(document).ready(function(){
    $("#nextstep").click(function(){
        var pattern= /^[0-9]*$/;
        if($("input[name='phone']").val().length==0){
            $.alert("请填写手机号码");
        }else if($("input[name='phone']").val().length!==11||!pattern.test($("input[name='phone']").val())){
            $.alert("请填写正确的手机号码");
        }else{
            $("#header a").hide();
            $("#header span").text("在线支付").css({"margin-left":0});
            $("#joinActivity").fadeOut(function(){
                $("#phoneNum span").text($("input[name='phone']").val());
                $("#payforActivity").fadeIn();
            });
        }
    })
})