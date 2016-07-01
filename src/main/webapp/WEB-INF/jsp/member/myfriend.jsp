<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/23
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>我的好友</title>--%>
    <link rel="stylesheet" href="${ctx}/css/userFriend.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">我的好友</p>
    </div>
    <div id="content">
        <ul>
            <c:forEach items="${userFriendList}" var="friend">
                <li>
                    <div class="friendImg">
                        <img src="http://herpink.cn:8080/pinknetpic/${friend.userFriend.userPhoto}" style="display: none" onload="this.style.display=''">
                        <img src="${ctx}/${friend.userFriend.text3}" style="display: none" onload="this.style.display=''">
                    </div>
                    <div class="friendInfo">
                        <p>${friend.userFriend.userName}&nbsp;&nbsp;<span>LV${friend.userFriend.userLevel}</span></p>
                        <p>${friend.userFriend.userSign}</p>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
