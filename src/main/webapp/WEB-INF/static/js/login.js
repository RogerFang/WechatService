/**
 * Created by Administrator on 2016/5/14.
 */
$(document).ready(function(){
    var $delete="<span class='delete'></span>";
    $("input[type='text']").keyup(function(){
        if($(this).parent('div').find('span').length==0){
            $(this).parent('div').append($delete);
        }
    })

    $(document).on('click','.delete',function(){
        $(this).siblings('input').val('');
        $(this).remove();
    })

    $("form").submit(function(){
        var $username=$("input[name=mobile]");
        var $password=$("input[name=password]");
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
})