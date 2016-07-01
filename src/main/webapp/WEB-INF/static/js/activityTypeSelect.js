/**
 * Created by Administrator on 2016/6/9.
 */
$(document).ready(function(){
    //每个版块高度为页面的一半
    $("a").height(($(window).height()-$("#header").height()-10)/2);
    //每个版块内背景图标和文字居中
    $("a div").css({"margin-top":($("a").height()-$("a div").height()-$("a p").height())/2});
})