/**
 * Created by Administrator on 2016/6/5.
 */
$(document).ready(function(){
    //tab切换效果
    $("#tab>div").click(function(){
        var $index=$(this).index();
        $(this).addClass("selected")
            .siblings().removeClass("selected");
        $("#contentMain>div").eq($index).show()
            .siblings().hide();
    })
})

/*$(document).on("click","#all li button",function(){
    //通过clicked类标识是否已被点击过
    if($(this).hasClass("clicked")){
        $.alert("您今日已经送出宝贵的一票，请明日再来哦");
    }else{
        $.alert("投票成功！");
        $(this).addClass("clicked");
        //获取当前投票图片的编号
        var num=$(this).parent().prev().prev().find("span").text();
        //判断投票记录中是否存在该记录，若不存在则将该节点复制一份插入到投票记录中
        if(!findRecord(num)){
            $clone=$(this).parents("li").clone(true);
            $clone.find("button").text("再次支持");
            $clone.appendTo("#record ul");
        }
    }


    function findRecord(num){
        var hasRecord=false;
        $("#record ul li").each(function(){
            var record=$(this).find(".weui-row").find("div:first-child").find("span").text();
            if(record==num){
                alert(record);
                hasRecord=true;
            }
        });
        return hasRecord;
    }
})*/

/*
$(document).on("click","#record li button",function(){
    if($(this).hasClass("clicked")){
        $.alert("您今日已经送出宝贵的一票，请明日再来哦");
    }else{
        $.alert("投票成功！");
        $(this).addClass("clicked");
    }
})*/
