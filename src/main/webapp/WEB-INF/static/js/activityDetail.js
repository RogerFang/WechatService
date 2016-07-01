/**
 * Created by Administrator on 2016/6/7.
 */
$(document).ready(function(){
    //位置居中效果
    $marginTop=($("#activityInfo").height()-$("#activityJoin p").height()-18)/2;
    $("#activityJoin p").css({"margin-top":$marginTop});

    // var $user="寒寒妈";//用户名
    // var $time=getCurrentTIme();//当前时间

    //页面加载时判断已有评论条数,根据评论数目在评论区上方写入不同提示文字
    // var $commentNum=$("#commentArea ul li").length;
    // if($commentNum==0){
    //     $("#commentArea>p").text("暂时还没有人评论哦，快来抢沙发吧");
    // }else{
    //     $("#commentArea>p").text("共有"+$commentNum+"条评论");
    // }

    //添加评论效果
    // $("#inputArea button").click(function(){
    //     $content=$("#inputArea input").val();
    //     if($content.length==0){
    //         $.alert("回复内容不能为空");
    //     }else{
    //         var $comment=$("<li><div class='comment'><img><div><p><span></span><span></span></p><p></p></div></div>");//评论节点
    //         $("#commentArea ul").append($comment);
    //         $("#commentArea ul li:last-child .comment div p:last-child").text($content);
    //         $("#commentArea ul li:last-child .comment div p:first-child span:first-child").text($user);
    //         $("#commentArea ul li:last-child .comment div p:first-child span:last-child").text($time);
    //         $("#commentArea ul li img").attr("src","img/userHeader.jpg");
    //         $("#inputArea input").val("");
    //         $commentNum++;
    //         $("#commentArea>p").text("共有"+$commentNum+"条评论");
    //         //$(window).scrollTop($("#content").height());
    //     }
    // })

    //添加回复效果
    // $(document).on("click",".reply button",function(){
    //     $replyContent=$(this).prev("input").val();
    //     if($replyContent.length==0){
    //         $.alert("回复内容不能为空");
    //     }else{
    //         var $comment=$("<li><div class='comment'><img><div><p><span></span><span></span></p><p></p></div></div>");//回复节点
    //         $replyName=$(this).prev("input").attr("placeholder").slice(0,-1);
    //         if(countNum($replyName,"回复")>1){
    //             var $fulluserName=$user+$replyName.substring(0,$replyName.indexOf("回复",$replyName.indexOf("回复")+1));
    //         }else{
    //             var $fulluserName=$user+$replyName;
    //         }
    //         $parent=$(this).parents("li");
    //         $comment.insertAfter($parent);
    //         $parent.next("li").find(".comment").find("div").find("p:first-child").find("span:first-child").text($fulluserName);
    //         $parent.next("li").find(".comment").find("div").find("p:first-child").find("span:last-child").text($time);
    //         $parent.next("li").find(".comment").find("div").find("p:last-child").text($replyContent);
    //         $parent.next("li").find("img").attr("src","img/userHeader.jpg");
    //         $(this).prev("input").val("");
    //         $parent.find(".reply").remove();
    //         $commentNum++;
    //         $("#commentArea>p").text("共有"+$commentNum+"条评论");
    //         //$(window).scrollTop($("#content").height());
    //     }
    // })

    //点击已有评论添加回复框效果
    $(document).on("click",".comment",function(){
        //判断评论下是否有回复框，如果没有则添加，如果已有则删除
        if($(this).siblings(".reply").length==0){
            //创建评论框节点
            $reply=$("<div class='reply'><input type='text'><button>回复</button></div>");
            //将回复框插入到该条评论之后
            $reply.insertAfter($(this));
            //获取被回复者的用户名
            $name=$(this).find("div").find("p").find("span:first-child").text();
            if(countNum($name,"回复")==1){
                var $username=$name.substring(0,$name.indexOf("回复"));
            }else{
                var $username=$name;
            }
            //输入框中显示回复信息
            $(this).siblings().find("input[type='text']").attr("placeholder","回复"+$username+":");
            //移除其他评论下的回复框
            $(this).parent().siblings().find(".reply").remove();
        }else{
            $(this).siblings(".reply").remove();
        }
    })

})

//获取当前评论时间
function getCurrentTIme(){
    var currentTime=new Date();
    var month=currentTime.getMonth()+1;//月份从0开始计
    var day=currentTime.getDate();
    var hour=currentTime.getHours();
    var minute=currentTime.getMinutes();
    var time=month.toString()+"-"+day.toString()+" "+hour.toString()+":"+minute.toString();//将日期转换为对应的字符串
    return time;
}

//统计回复次数，避免累积回复
function countNum(str,word){
    var positions=new Array();
    var pos=str.indexOf(word);

    while(pos>-1){
        positions.push(pos);
        pos=str.indexOf(word,pos+1);
    }

    return positions.length;
}