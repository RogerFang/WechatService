/**
 * Created by Administrator on 2016/5/15.
 */
$(document).ready(function(){
    //短信验证倒计时效果
    $("#msgValidation a").click(inMsgValidation);
    var time=0;
    var countTimer=null;
    function inMsgValidation(){
        time=5;
        $("#msgValidation a").addClass("inMsg");
        $("#msgValidation a").unbind("click");
        $("#msgValidation a").text(time+"秒后重新获取");
        countTimer=setInterval(Timer,1000);

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

    //表单信息验证
    $("#registerForm").submit(function(){
        var pattern= /^[0-9]*$/;
        var $phonenum=$("#registerForm input[name='mobile']");
        var $password=$("#registerForm input[name='password']");
        var $captcha=$(" #registerForm input[name='captcha']");
        var $confirmpassword=$("#registerForm input[name='confirmpassword']");
        var $checkbox=$("#registerForm input[type='checkbox']");
        if($phonenum.val().length<11||!pattern.test($phonenum.val())){
            $.alert("请输入正确的11位手机号码","");
            return false;
        }else if($captcha.val().length<6){
            $.alert("请输入正确的验证码","");
            return false;
        }else if($password.val()==""){
            $.alert("请输入密码","");
            return false;
        }else if($confirmpassword.val()==""){
            $.alert("请确认密码","");
            return false;
        }else if($confirmpassword.val()!==$password.val()){
            $.alert("密码不一致，请重新确认密码","");
            return false;
        }else if(!$checkbox.is(':checked')){
            $.alert("请阅读并同意用户协议","");
            return false;
        }else{
            $.alert("恭喜您已成功注册Herpink会员","");
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

    //登录表单信息验证
    $("#loginContent form").submit(function(){
        var $username=$("#loginContent input[name=mobile]");
        var $password=$("#loginContent input[name=password]");
        if($username.val()==""){
            $.alert("请输入手机号","");
            return false;
        }else if($password.val()==""){
            $.alert("请输入密码","");
            return false;
        }else{
            return true;
        }
    })

    $("#info a").click(function(){
        $index=$(this).index();
        $("#content>div").eq($index).show()
            .siblings().hide();
    })
})