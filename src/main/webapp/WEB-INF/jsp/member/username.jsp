<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/23
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>我的昵称</title>--%>
    <link rel="stylesheet" href="${ctx}/css/username.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <form action="${ctx}/member/username" method="post">
        <div id="header">
            <p class="title"><span>我的昵称</span><button id="post" type="submit">提交</button></p>
        </div>
        <div id="content">
            <p>为了让大家记住你，给自己取一个好听的昵称吧</p>
            <label for="username">设置昵称</label>
            <input type="text" id="username" name="username" placeholder="请设置个人昵称" value="${user.userName}">
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/js/username.js" charset="gbk"></script>
</body>
</html>
