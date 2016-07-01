/**
 * Created by Administrator on 2016/6/16.
 */
window.onload=function(){
    //ÇÐ»»tabÐ§¹û
    $("#tab .weui-col-50").click(function(){
        $(this).find("p").addClass("selected");
        $(this).siblings().find("p").removeClass("selected");
        $index=$(this).index();
        $("#activityList ul").eq($index).show().siblings().hide();
    })
}