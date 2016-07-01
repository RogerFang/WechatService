<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/5/15
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>会员登录</title>
    <link rel="stylesheet" href="${ctx}/css/login.css" type="text/css">
    <style>
        .weui_btn_dialog.primary{
            color:#f24166;
            font-family:weui;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">登录一次即可绑定微信</p>
    </div>
    <div id="content">
        <form action="${ctx}/member/login" method="post">
            <div><input type="text" placeholder=" 手机号" name="mobile"></div>
            <div><input type="password" placeholder=" 密码" name="password"></div>
            <button href="javascript:;" class="weui_btn weui_btn_warn">登录</button>
        </form>
        <div id="info">
            <a href="${ctx}/member/register" style="color: #F24166;">注册Herpink会员</a>
            <!--<a href="#" style="color: #F24166;">找回密码</a>-->
        </div>
        <span>${msg}</span>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/login.js"></script>
</body>
</html>
