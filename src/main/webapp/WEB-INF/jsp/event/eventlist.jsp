<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/7
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>粉蜜活动</title>--%>
    <link rel="stylesheet" href="${ctx}/css/activityIndex.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title"><span>粉蜜活动</span><a href="${ctx}/eventinfo/release" id="post"></a></p>
    </div>

    <div class="bd">
        <!--<a href="javascript:;" class="weui_btn weui_btn_primary">点击展现searchBar</a>-->
        <div class="weui_search_bar" id="search_bar">
            <form id="conditionForm" class="weui_search_outer" action="${ctx}/eventinfo/list" method="post">
                <div class="weui_search_inner">
                    <i class="weui_icon_search"></i>
                    <input type="search" name="query" class="weui_search_input" id="search_input" value="${query}" placeholder="搜索" required/>
                    <a href="javascript:" class="weui_icon_clear" id="search_clear"></a>
                </div>
                <label for="search_input" class="weui_search_text" id="search_text">
                    <i class="weui_icon_search"></i>
                    <span>搜索</span>
                    <%--<button type="submit">搜索</button>--%>
                </label>

                <input type="hidden" name="eventCls">
                <input type="hidden" name="eventArea">
                <input type="hidden" name="eventSort">
                <input type="hidden" name="eventPay">
                <button type="submit" style="display: none;">提交</button>
            </form>
            <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a>
        </div>
    </div>

    <div id="category" class="weui-row weui-no-gutter">
        <div class="weui-col-25">
            <div>
                <span><span class="type">类型</span><i></i></span>
            </div>
        </div>
        <div class="weui-col-25">
            <div>
                <span><span class="type">地区</span><i></i></span>
            </div>
        </div>
        <div class="weui-col-25">
            <div>
                <span><span class="type">综合</span><i></i></span>
            </div>
        </div>
        <div class="weui-col-25 noborder">
            <div>
                <span><span class="type">筛选</span><i></i></span>
            </div>
        </div>

        <div id="activitySelect">
            <div class="weui_cells weui_cells_radio" style="border-top: none;display: none;">
                <label class="weui_cell weui_check_label" for="x1" style="border-top: none;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p class="selected">类型</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" class="weui_check" name="radio1" id="x1" value="" checked="checked">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="cls_7" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>女学馆</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio1" class="weui_check" id="cls_7" value="7">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="cls_8" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>美人计</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio1" class="weui_check" id="cls_8" value="8">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="cls_9" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>粉妈帮</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio1" class="weui_check" id="cls_9" value="9">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="cls_10">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>粉红club</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio1" class="weui_check" id="cls_10" value="10">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
            </div>

            <div class="weui_cells weui_cells_radio" style="border-top: none;display: none;">
                <label class="weui_cell weui_check_label" for="x6" style="border-top: none;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p class="selected">地区</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" class="weui_check" name="radio2" id="x6" value="" checked="checked">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="x7" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>洪山区</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio2" class="weui_check" id="x7">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="x8" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>汉阳区</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio2" class="weui_check" id="x8">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="x9" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>武昌区</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio2" class="weui_check" id="x9">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="x10">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>江夏区</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio2" class="weui_check" id="x10">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
            </div>

            <div class="weui_cells weui_cells_radio" style="border-top: none;display: none;">
                <label class="weui_cell weui_check_label" for="x11" style="border-top: none;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p class="selected">综合</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" class="weui_check" name="radio3" id="x11" value="" checked="checked">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="sort_0" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>人气最高</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio3" class="weui_check" id="sort_0"value="0">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="sort_1" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>最近更新</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio3" class="weui_check" id="sort_1" value="1">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <%--<label class="weui_cell weui_check_label" for="x14" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>距离最近</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio3" class="weui_check" id="x14" checked="checked">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>--%>
            </div>

            <div class="weui_cells weui_cells_radio" style="border-top: none;display: none;">
                <label class="weui_cell weui_check_label" for="x15" style="border-top: none;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p class="selected">筛选</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" class="weui_check" name="radio4" id="x15" value="" checked="checked">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="pay_0" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>免费</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio4" class="weui_check" id="pay_0" value="0">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
                <label class="weui_cell weui_check_label" for="pay_1" style="border-top: 0px solid transparent;">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>收费</p>
                    </div>
                    <div class="weui_cell_ft">
                        <input type="radio" name="radio4" class="weui_check" id="pay_1" value="1">
                        <span class="weui_icon_checked"></span>
                    </div>
                </label>
            </div>
        </div>
    </div>
    <div style="clear: both;height: 1px"></div>

    <!--隐藏的表单用于获取选中的类别-->
    <%--<form id="conditionForm" action="${ctx}/eventinfo/list" method="post" style="display: none">
        <input type="text" name="eventCls">
        <input type="text" name="eventArea">
        <input type="text" name="eventSort">
        <input type="text" name="eventPay">
        <button type="submit">提交</button>
    </form>--%>

    <div id="content">
        <ul id="activityList">
            <c:forEach items="${eiPage.content}" var="ei">
                <li>
                    <a href="${ctx}/eventinfo/detail/${ei.id}" style="margin-top: 0">
                        <div class="activityTime">
                            <c:choose>
                                <c:when test="${ei.beginIntervalDays > 0}">
                                    <p>还有<span>${ei.beginIntervalDays}</span>天开始</p>
                                </c:when>
                                <c:when test="${ei.endIntervalDays > 0}">
                                    <p>还有<span>${ei.endIntervalDays}</span>天结束</p>
                                </c:when>
                            </c:choose>
                            <%--<p>还有<span>9</span>天开始</p>--%>
                            <p>${ei.beginMonth}月,${ei.beginDayOfWeek}</p>
                            <p>${ei.beginDayOfMonth}</p>
                            <%--<p><span><fmt:formatDate value="${ei.eventBegintime}" pattern="yyyy-MM-dd" /></span></p>--%>
                        </div>
                        <div class="activityImage">
                            <img src="http://herpink.cn:8080/pinknetpic/${ei.descPic}" style="display: none" onload="this.style.display=''">
                            <img src="${ctx}/${ei.descPic}" style="display: none" onload="this.style.display=''">
                        </div>
                        <div class="activityInfo">
                            <p>${ei.eventTitle}</p>
                            <p>结束时间：<span>${ei.formatEndTime}</span></p>
                            <p>发起人：<span>${ei.eventCreator.userName}</span></p>
                            <p>
                                <span>${ei.eventMemberCount}人已参加</span>
                                <span>
                                    <c:choose>
                                        <c:when test="${ei.text2 eq 0}">
                                            免费
                                        </c:when>
                                        <c:otherwise>
                                            收费
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </p>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <input type="hidden" name="page" value="${page}">
    </div>
</div>
<%--<div class="weui-infinite-scroll">
    <div class="infinite-preloader"></div>
    Herpink正在为您加载... <!-- 文案，可以自行修改 -->
</div>--%>
<script type="text/javascript" src="${ctx}/js/activityIndex.js" charset="gbk"></script>
<script>
    $(document).ready(function(){
        var $condition1 = "${eventCls}";
        var $condition2 = "";
        var $condition3 = "${eventSort}";
        var $condition4 = "${eventPay}";
//        alert($condition1+" "+$condition3+" "+$condition4);
        if($condition1!==""){
            $("#"+$condition1).attr("checked","checked");
            $("input[name='eventCls']").val($("#"+$condition1).val());
            var $text1=$("#"+$condition1).parent("div").prev().find("p").text();
            $("#category>div").eq(0).find("div").find("span").find("span").text($text1);
        }
        if($condition2!==""){
            $("#"+$condition2).attr("checked","checked");
            $("input[name='eventArea']").val($("#"+$condition2).val());
            var $text2=$("#"+$condition2).parent("div").prev().find("p").text();
            $("#category>div").eq(1).find("div").find("span").find("span").text($text2);

        }
        if($condition3!==""){
            $("#"+$condition3).attr("checked","checked");
            $("input[name='eventSort']").val($("#"+$condition3).val());
            var $text3=$("#"+$condition3).parent("div").prev().find("p").text();
            $("#category>div").eq(2).find("div").find("span").find("span").text($text3);

        }
        if($condition4!==""){
            $("#"+$condition4).attr("checked","checked");
            $("input[name='eventPay']").val($("#"+$condition4).val());
            var $text4=$("#"+$condition4).parent("div").prev().find("p").text();
            $("#category>div").eq(3).find("div").find("span").find("span").text($text4);
        }
    })
</script>
</body>
</html>
