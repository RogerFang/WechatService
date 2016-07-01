<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/21
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>报名详情</title>--%>
    <link rel="stylesheet" href="${ctx}/css/enrollmentDetail.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">报名详情</p>
        <p class="enrollmentCount"><span>${eventInfo.eventMemberCount}</span>人已报名</p>
    </div>
    <div id="content">
        <ul id="enrollmentList">
            <c:forEach items="${eventSignList}" var="es">
                <li>
                    <div class="userInfo">
                        <div class="userHeader">
                            <img src="http://herpink.cn:8080/pinknetpic/${es.eventSignCreator.userPhoto}" style="display: none" onload="this.style.display=''">
                            <img src="${ctx}/${es.eventSignCreator.text3}" style="display: none" onload="this.style.display=''">
                        </div>
                        <div class="userInfoDetail">
                            <h4>${es.eventSignCreator.userName}</h4>
                            <p>报名时间：<fmt:formatDate value="${es.eventSignCreatetime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                            <%--<p>免费报名</p>--%>
                        </div>
                        <c:if test="${es.eventSignProcess eq 1}">
                            <div class="userCondition pass">已通过</div>
                        </c:if>
                        <c:if test="${es.eventSignProcess eq 2}">
                            <div class="userCondition check">审核中</div>
                        </c:if>
                        <c:if test="${es.eventSignProcess eq 3}">
                            <div class="userCondition refuse">拒绝</div>
                        </c:if>
                        <c:if test="${es.eventSignProcess eq -1}">
                            <div class="userCondition quit">退出活动</div>
                        </c:if>
                        <c:if test="${es.eventSignProcess eq -1}">
                            <div class="userCondition delete">剔出活动</div>
                        </c:if>
                    </div>
                    <div class="userPhone">
                        联系方式：<a>${es.eventSignCreator.mobile}</a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
