<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/3/26
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <title>Herpink APP下载</title>
</head>
<body>
    <div style="margin: 0 auto;">
        <div style="background: rgba(255, 255, 255, 0.2) url('${ctx}/img/test11.jpg') no-repeat scroll 0 0 / cover;height: 250px;position: relative;">
            <img src="${ctx}img/logo.png" style="position: absolute;left: 0;top: 0;">
            <!--<div style="margin-top: 30px;position: absolute;bottom: 0;left: 2%;width: 96%;">-->
            <!--<h2 style="text-align: center;color:#f69;font-weight:900;font-size: 28px">第二届</h2>-->
            <!--<h2 style="text-align: center;color:#f69;font-weight:900;font-size: 28px">粉红江城魅力女人</h2>-->
            <!--<h3 style="text-align: center;color: #f69;font-weight:900;font-size: 24px">报名即拿奖</h3>-->
            <!--</div>-->
        </div>
        <div class="download">
            <p>Herpink</p>
            <p>可信赖的纯女性社交平台</p>
            <p>扫描下方二维码下载APP</p>
            <img src="${ctx}/img/erwei.png">
            <a href="#">下载Herpink APP了解更多信息</a>
        </div>
    </div>
</body>
</html>
