<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/7/14
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <%--<title>找回密码</title>--%>
    <link rel="stylesheet" href="${ctx}/css/findPsd.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <div class="title">找回密码</div>
    </div>
    <div id="retrievePassword">
        <form action="${ctx}/member/findPsd" method="post">
            <div class="msg"><input type="text" placeholder=" 手机号码" name="mobile"></div>
            <div id="msgValidation" class="weui-row">
                <div  class="weui-col-65"><input type="text" placeholder=" 验证码" name="captcha"></div>
                <a href="javascript:;" class="weui_btn weui_btn_plain_warn weui-col-30">点击获取验证码</a>
            </div>
            <div class="msg"><input type="password" placeholder=" 设置新的密码" name="password"></div>
            <div class="msg"><input type="password" placeholder=" 确认新的密码" name="confirmpassword"></div>
            <button href="javascript:;" class="weui_btn weui_btn_warn">确认</button>
        </form>
        <span>${msg}</span>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/findPsd.js"></script>
</body>
</html>
