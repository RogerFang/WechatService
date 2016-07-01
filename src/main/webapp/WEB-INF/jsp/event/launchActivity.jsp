<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/14
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>发起活动</title>--%>
    <link rel="stylesheet" href="${ctx}/css/launchActivity.css" type="text/css">
</head>
<body>
<div id="content">
    <form action="${ctx}/eventinfo/release" method="post">
        <div id="header">
            <p class="title"><span>发起活动</span><button type="submit">下一步</button></p>
        </div>
        <h4>请选择活动类型：</h4>
        <div class="radioclass">
                <span class="on">
                    <input type="radio" name="activity" id="offline" checked="checked" value="0"/>
                </span>
            <label for="offline">线下</label>
        </div>
        <div class="radioclass">
                <span>
                    <input type="radio" name="activity" id="online" value="1"/>
                </span>
            <label for="online">线上</label>
        </div>
        <div class="checkclass">
                <span class="on">
                    <input type="checkbox" name="picTag" id="upload" value="1"/>
                </span>
            <label for="upload">上传图片</label>
        </div>
        <div class="checkclass">
                <span>
                    <input type="checkbox" name="voteTag" id="vote" value="1"/>
                </span>
            <label for="vote">投票</label>
        </div>
    </form>
    <p>温馨提示：herpink普通用户目前发布的活动均自动默认为公益免费活动。如您发起的活动涉及收费，请联系027-87883800.</p>
</div>
<script type="text/javascript" src="${ctx}/js/launchActivity.js"></script>
</body>
</html>
