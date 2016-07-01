<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/14
  Time: 20:07
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
    <link rel="stylesheet" href="${ctx}/css/launchOnlineActivity.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <form action="${ctx}/eventinfo/releaseOnline" method="post" enctype="multipart/form-data">
        <input type="hidden" name="picTag" value="${picTag}">
        <input type="hidden" name="voteTag" value="${voteTag}">
        <input type="hidden" name="eventCls" value="${eventCls}">
        <div id="header">
            <p class="title"><span>发起活动</span><button id="post" type="submit">确定</button></p>
        </div>
        <div id="content">
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">活动主题：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" placeholder="来一个响亮的主题吧" name="eventTitle"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">开始时间：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" placeholder="选择开始时间"  id='begintime' name="eventBegintime"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">结束时间：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" placeholder="选择结束时间" id='endtime' name="eventEndtime"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">人数限制：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="number" pattern="[0-9]*" placeholder="请填写活动限制人数" name="limitNum"/>
                    </div>
                </div>
                <div class="weui_cell" id="textArea">
                    <div class="weui_cell_hd"><label class="weui_label">活动详情：</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <textarea class="weui_textarea" placeholder="请简单描述一下您的活动流程" rows="3" name="eventContent"></textarea>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <div class="weui_uploader">
                            <div class="weui_uploader_hd weui_cell">
                                <div class="weui_cell_bd weui_cell_primary">图片上传</div>
                                <div class="weui_cell_ft" id="photoNum"><span>0</span>/5</div>
                            </div>
                            <div class="weui_uploader_bd">
                                <ul class="weui_uploader_files">

                                </ul>
                                <div class="weui_uploader_input_wrp">
                                    <input name="multipartFiles" class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/js/launchOnlineActivity.js" charset="gbk"></script>
</body>
</html>
