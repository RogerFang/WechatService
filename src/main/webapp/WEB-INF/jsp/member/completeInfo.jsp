<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/4
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>完善资料</title>--%>
    <link rel="stylesheet" href="${ctx}/css/userInfoFillin.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <form action="${ctx}/member/completeInfo" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <div id="header">
            <p class="title"><span>完善资料</span><button id="post" type="submit">完成</button></p>
        </div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label" for="age">年龄</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="age" name="userAgeDesc" value="${user.userAgeDesc}" class="weui_input" type="text" placeholder="年龄分段，消除代沟" readonly="" >
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label" for="constellation">星座</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="constellation" name="userStar" value="${user.userStar}" class="weui_input" type="text" placeholder="选择星座，寻找闺蜜" readonly="" >
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label" for="area">区域</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="area" name="userArea" value="${user.userArea}" class="weui_input" type="text" placeholder="精确地区，发现友邻" readonly="" >
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label" for="job">职业</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="job" name="userCareer" value="${user.userCareer}" class="weui_input" type="text" placeholder="填写职业，找到同行" readonly="" >
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label" for="in">兴趣</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="in" name="text4" value="${user.text4}" class="weui_input" type="text" placeholder="点击修改兴趣" readonly="" >
                </div>
            </div>
        </div>
    </form>
    <div class="info error" style="display: none">
        <p class="weui_toast_content"></p>
    </div>
    <div class="info success" style="display: none">
        <p class="weui_toast_content">完善资料成功</p>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/userInfoFillin.js" charset="gbk"></script>
</body>
</html>
