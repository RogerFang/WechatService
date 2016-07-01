<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/13
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>搜蜜</title>--%>
    <link rel="stylesheet" href="${ctx}/css/seekFriend.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title"><span>搜蜜</span><a href="${ctx}/member/findFriend"></a></p>
    </div>
    <div id="content">
        <p>您可能感兴趣的人</p>
        <c:forEach items="${auPage.content}" var="user">
            <ul>
                <li class="weui-row weui-no-gutter">
                    <div class="weui-col-85">
                        <a href="#">
                            <img src="http://herpink.cn:8080/pinknetpic/${user.userPhoto}" style="display: none" onload="this.style.display=''">
                            <img src="${ctx}/${user.text3}" style="display: none" onload="this.style.display=''">
                            <c:if test="${user.userPhoto eq null and user.text3 eq null}">
                                <img src="${ctx}/img/defaultHerpink.png" style="display: none" onload="this.style.display=''">
                            </c:if>
                            <div>
                                <p>${user.userName}</p>
                                <p>LV ${user.userLevel}</p>
                            </div>
                        </a>
                    </div>
                </li>
            </ul>
        </c:forEach>
    </div>
</div>
<script src="${ctx}/js/seekFriend.js" type="text/javascript" charset="gbk"></script>
</body>
</html>
