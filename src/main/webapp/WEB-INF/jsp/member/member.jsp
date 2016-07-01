<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/4
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="${ctx}/css/userIndex.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">我的</p>
    </div>
    <div id="content">
        <a href="${ctx}/member/uploadPhoto">

            <c:choose>
                <c:when test="${not empty user.text3}">
                    <img src="${ctx}/${user.text3}">
                </c:when>
                <c:when test="${not empty user.userPhoto}">
                    <img src="http://herpink.cn:8080/pinknetpic/${user.userPhoto}">
                </c:when>
                <c:otherwise>
                    <img src="${ctx}/img/userdefault.png">
                </c:otherwise>
            </c:choose>
        </a>
        <ul>
            <li><a href="${ctx}/member/username">昵称<span>${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span></a></li>
            <li><a href="#">我的身份<span>LV${user.userLevel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span></a></li>
            <li><a href="#">我的粉红币<span>${user.pinknetCoin}个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span></a></li>
            <li><a href="${ctx}/member/completeInfo">个人信息<span>完善&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span></a></li>
            <li><a href="${ctx}/member/myactivity">我的活动<span>></span></a></li>
            <li><a href="${ctx}/member/mytopic">我的话题<span>></span></a></li>
            <li><a href="${ctx}/member/myfriend">我的好友<span>></span></a></li>
        </ul>
    </div>
</div>
</body>
</html>
