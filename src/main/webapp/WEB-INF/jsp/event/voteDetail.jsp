<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/13
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>活动详情</title>--%>
    <link rel="stylesheet" href="${ctx}/css/onlineActivityDetail.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">${event.eventTitle }</p>
    </div>
    <div id="content">
        <div id="activityImage">
            <img src="http://herpink.cn:8080/pinknetpic/${event.descPic}" style="display: none" onload="this.style.display=''">
            <img src="${ctx}/${event.descPic}" style="display: none" onload="this.style.display=''">
        </div>
        <div id="activityMain">
            <div id="activityInfo">
                <p>活动开始时间：<span><fmt:formatDate
                        value="${event.eventBegintime }" pattern="yyyy-MM-dd HH:mm" /></span></p>
                <p>活动结束时间：<span><fmt:formatDate value="${event.eventEndtime }"
                                                pattern="yyyy-MM-dd HH:mm" /></span></p>
                <p>活动费用：<span>${event.text2}元/人</span></p>
                <p>活动发起人：<span>${event.eventCreator.userName }</span></p>
                <p>活动地点：<span>${event.eventPlace}</span></p>
            </div>
            <div id="activityJoin">
                <p><span>${event.eventMemberCount }</span>人已参加</p>
            </div>
        </div>
        <div id="activityDetail">
            <h4>活动详情：</h4>
            <p>
                ${event.eventContent}
            </p>
        </div>
        <div id="commentArea">
            <p></p>
            <c:if test="${ciPage.totalElements eq 0}">
                <h4>暂无评论，做第一个发评论的人吧！</h4>
            </c:if>
            <ul>
                <c:forEach items="${ciPage.content}" var="ci">
                    <li>
                        <div class="comment">
                            <img src="http://herpink.cn:8080/pinknetpic/${ci.commentCreator.userPhoto}" style="display: none" onload="this.style.display=''">
                            <img src="${ctx}/${ci.commentCreator.text3}" style="display: none" onload="this.style.display=''">
                            <div>
                                <p><span>${ci.commentCreator.userName}</span><span><fmt:formatDate value="${ci.commentCreatetime }"
                                                                                                   pattern="yyyy-MM-dd HH:mm" /></span></p>
                                <p>${ci.commentContent }</p>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div id="inputArea">
                <!--<form>-->
                <%--<input type="text" placeholder="写下您的评论：">--%>
                <%--<button>写评论</button>--%>
                <!--</form>-->
                <form id="commentForm" action="${ctx}/eventinfo/comment" method="post">
                    <input type="hidden" name="infoId" value="${event.id}">
                    <input type="text" id="commentContent" name="commentContent" placeholder="请输入您的评论">
                    <button class="btn btn-primary form-control" type="button" onclick="commentAjax();">写评论</button>
                </form>
            </div>
        </div>
        <div id="activitySelected">
            <div id="activitySelectedHeader">
                <span>精选活动</span>
                <%--<a href="#">更多></a>--%>
            </div>
            <ul id="activityList">
                <c:forEach items="${eiPage.content}" var="ei">
                    <li>
                        <a href="${ctx}/eventinfo/detail/${ei.id}" style="margin-top: 0;">
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
                                <p>结束时间：<span><fmt:formatDate value="${ei.eventEndtime}" pattern="yyyy-MM-dd" /></span></p>
                                <p>发起人：<span>${ei.eventCreator.userName}</span></p>
                                <p>
                                    <span>${ei.eventMemberCount}</span>人已参加
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
        </div>
    </div>
    <c:if test="${event.eventEndtime >= today and event.eventProcess == 1}">
        <div id="footer" class="weui-row weui-no-gutter">
            <div class="weui-col-25">
                <div class="open"><a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.kaiyitech.ipink">打开Herpink</a></div>
            </div>
            <div class="weui-col-25">
                <div class="post"><a href="${ctx}/eventinfo/release">我要发活动</a></div>
            </div>
            <div class="weui-col-25">
                <div class="vote"><a href="${ctx}/eventinfo/vote/${event.id}">我要投票</a></div>
            </div>
            <div class="weui-col-25">
                <div class="join"><a href="${joinUrl}" onclick="return checkJoin();">我要报名</a></div>
            </div>
        </div>
    </c:if>
</div>
<script src="${ctx}/js/activityDetail.js" type="text/javascript" charset="gbk"></script>
<script>
    function commentAjax() {
        if ($("#commentContent").val() == ""){
            alert("评论不能为空!");
            return false;
        }

        $("#commentForm").ajaxSubmit({
            success:function (data) {
                if (data.flag == 1){
                    // $("#commentText").val("");
                    // alert("感谢您的评论");
                    location.reload(true);
                }else if (data.flag == -1){
                    alert("请先到【会员】进行注册!");
                }
            }
        });
    }

    // 判断该用户是否可以报名参加活动
    function checkJoin() {
        var result = false;
        $.ajax({
            url:"${ctx}/eventinfo/checkJoin/"+${event.id},
            type:"POST",
            async:false,
            success:function (data) {
                if (data.success){
                    result = true;
                }else{
                    $.alert(data.msg)
                    result = false;
                }
            }
        });
        return result;
    }
</script>
</body>
</html>
