<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/16
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>活动报名</title>--%>
    <link rel="stylesheet" href="${ctx}/css/joinOfflineActivity.css" type="text/css">

</head>
<body>
<div id="wrapper">
    <form id="joinForm" action="${ctx}/eventinfo/joinNotVote/${eventId}" method="post">
        <div id="header">
            <p class="title"><span>活动报名</span><button id="post" type="button" onclick="submitForm();">提交</button></p>
        </div>
        <div id="content">
            <p>为了方便活动时能及时与您联系请填写以下信息</p>
            <label for="phone">手机号码</label>
            <input type="number" id="phone" name="mobile" placeholder="请输入手机号码">
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/js/joinOfflineActivity.js" charset="gbk"></script>

<script>
    function submitForm() {
        $("#joinForm").ajaxSubmit({
            success:function(data){
                $.alert(data.msg);
            }
        });
    }
</script>
</body>
</html>
