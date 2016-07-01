<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/16
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="decorator" content="default">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title></title>--%>
    <link rel="stylesheet" href="${ctx}/css/activityTypeSelect.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">发起活动</p>
    </div>
    <div id="content">
        <div class="weui-row weui-no-gutter">
            <a class="weui-col-50" href="${ctx}/eventinfo/${baseHref}/7">
                <div></div>
                <p>女学馆</p>
            </a>
            <a class="weui-col-50" href="${ctx}/eventinfo/${baseHref}/8">
                <div></div>
                <p>美人计</p>
            </a>
        </div>
        <div class="weui-row weui-no-gutter">
            <a class="weui-col-50" href="${ctx}/eventinfo/${baseHref}/9">
                <div></div>
                <p>粉妈帮</p>
            </a>
            <a class="weui-col-50" href="${ctx}/eventinfo/${baseHref}/10">
                <div></div>
                <p>粉红club</p>
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/activityTypeSelect.js"></script>
</body>
</html>
