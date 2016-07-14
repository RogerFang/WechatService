/**
 * Created by Roger on 2016/7/14.
 */
/**
 * Created by Administrator on 2016/7/14.
 */
$(document).ready(function(){
    //短信验证倒计时效果
    $("#msgValidation a").click(inMsgValidation);
    var time=0;
    var countTimer=null;
    var code;
    function inMsgValidation(){
        var $phonenum=$("#retrievePassword input[name=mobile]");
        var pattern= /^[0-9]*$/;
        if($phonenum.val().length<11||!pattern.test($phonenum.val())){
            $.alert("请输入正确的11位手机号码","");
            return false;
        }

        time=60;
        $("#msgValidation a").addClass("inMsg");
        $("#msgValidation a").unbind("click");
        $("#msgValidation a").text(time+"秒后重新获取");
        countTimer=setInterval(Timer,1000);

        $.post(
            "sendCaptcha",
            {"mobile":$phonenum.val()},
            function(data){
                code = data;
                // alert(data);
            }
        );
    }
    function Timer(){
        time--;
        if(time>=0){
            $("#msgValidation a").text(time+"秒后重新获取");
        } else if(time<0){
            clearInterval(countTimer);
            $("#msgValidation a").removeClass("inMsg");
            $("#msgValidation a").text("点击获取验证码");
            $("#msgValidation a").bind("click",inMsgValidation);
        }
    }

    //注册表单信息验证
    $("form").submit(function(){
        var pattern= /^[0-9]*$/;
        var $phonenum=$("#retrievePassword input[name=mobile]");
        var $password=$("#retrievePassword input[name=password]");
        var $captcha=$("#retrievePassword input[name=captcha]");
        var $confirmpassword=$("#retrievePassword input[name=confirmpassword]");
        if($phonenum.val().length<11||!pattern.test($phonenum.val())){
            $.alert("请输入正确的11位手机号码","");
            return false;
        }else if($captcha.val().length<6 || $captcha.val() != code){
            $.alert("请输入正确的验证码","");
            return false;
        }else if($password.val()==""){
            $.alert("请输入密码","");
            return false;
        }else if ($password.val().length<6||$password.val().length>16){
            $.alert("密码应为6-16个字母或数字","");
            return false;
        }else if($confirmpassword.val()==""){
            $.alert("请确认密码","");
            return false;
        }else if($confirmpassword.val()!==$password.val()){
            $.alert("密码不一致，请重新确认密码","");
            return false;
        }else{
            $.alert("重新设置密码成功!","");
            return true;
        }
    })
    //表单字段删除功能
    var $delete="<span class='delete'></span>";
    $(".msg input[type='text']").keyup(function(){
        if($(this).parent('div').find('span').length==0){
            $(this).parent('div').append($delete);
        }
    })

    $(document).on('click','.delete',function(){
        $(this).siblings('input').val('');
        $(this).remove();
    })

})