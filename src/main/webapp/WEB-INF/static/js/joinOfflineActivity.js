/**
 * Created by Administrator on 2016/6/9.
 */
$(document).ready(function(){
    $("form").submit(function(){
        var pattern= /^[0-9]*$/;
        if($("input[name='mobile']").val().length==0){
            $.alert("请填写手机号码");
            return false;
        }else if($("input[name='mobile']").val().length!==11||!pattern.test($("input[name='mobile']").val())){
            $.alert("请填写正确的手机号码");
            return false;
        }else{
            return true;
        }
    })
})