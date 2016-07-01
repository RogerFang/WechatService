<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/7
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>精确搜索</title>--%>
    <link rel="stylesheet" href="${ctx}/css/selectFriend.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <form action="${ctx}/member/findFriend" method="post">
        <div id="header">
            <p class="title"><span>精确搜索</span><button id="post" type="submit">完成</button></p>
        </div>
        <div id="content">
            <p>精确搜索条件，方便找到好友</p>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">年龄：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" value="不限" id="age" name="userAgeDesc"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">星座：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" value="不限" id="constellation" name="userStar"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">区域：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" value="不限" id="area" name="userArea"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">职业：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" value="不限"  id='job' name="userCareer"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">兴趣：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" value="不限" id='in' name="text4"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/js/selectFriend.js" charset="gbk"></script>
</body>
</html>
