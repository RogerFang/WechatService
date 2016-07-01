<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/4
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>我的话题</title>--%>
    <link rel="stylesheet" href="${ctx}/css/userTopic.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">${topicInfos.size()}个话题</p>
    </div>
    <div id="content">
        <ul>
            <c:forEach items="${topicInfos}" var="topicInfo">
                <li>
                    <a href="${ctx}/topic/detail?id=${topicInfo.id}">
                        <p>${topicInfo.mediumContent}</p>
                        <p><fmt:formatDate value="${topicInfo.topicCreatetime}" pattern="yyyy-MM-dd HH:mm" /></p>
                        <p>已有<span>${topicInfo.topicCommentCount}</span>条评论&nbsp;&nbsp;已有<span></span>${topicInfo.topicPraiseCount}人飞吻</p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
