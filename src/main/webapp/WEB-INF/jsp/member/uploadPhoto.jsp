<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/4
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>上传头像</title>--%>
    <link rel="stylesheet" href="${ctx}/css/uploadUserHeaderPhoto.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title"><span>上传头像</span>
            <%--<button id="post">跳过</button>--%>
        </p>
    </div>
    <div id="content">
        <div id="view"></div>
        <p>据说美女们都喜欢上传自己美美的<span>头像</span>哦~</p>
        <form action="${ctx}/member/uploadPhoto" method="post" enctype="multipart/form-data">
            <div class="weui-row">
                <div class="weui-col-50">
                <span id="chooseBtn">
                    选择图片
                    <input id="file" type="file" name="file">
                </span>
                </div>
                <div class="weui-col-50">
                <span id="clipBtn">
                    截取上传
                    <button type="submit"></button>
                </span>
                </div>
            </div>
        </form>
    </div>
    <div id="clipArea" style="display: none"></div>
</div>
<script type="text/javascript" src="${ctx}/js/hammer.js"></script>
<script type="text/javascript" src="${ctx}/js/iscroll-zoom.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.photoClip.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadUserHeaderPhoto.js"></script>
</body>
</html>
