<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/5/27
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <%--<title>粉红网</title>--%>

    <link href="${ctx}/css/topicRelease.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="wrapper">
        <form id="releaseForm" action="${ctx}/topic/release" method="post" enctype="multipart/form-data">
            <div id="header">
                <p class="title"><a href="#"></a>发话题<button type="submit" id="post">发表</button></p>
            </div>
            <div id="content">
                <div id="topicSelect">
                    <input type="hidden" value="" id="topicClsId" name="topicClsId">
                    <span>类型:</span><div>选择话题类型</div>
                </div>
                <div class="weui_cells weui_cells_radio" id="topicTypeSelect" style="border-top: none;display: none">
                    <label class="weui_cell weui_check_label" for="x11" style="border-top: none;">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p title="1">Her beauty 她美丽</p>
                        </div>
                        <div class="weui_cell_ft">
                            <input type="radio" class="weui_check" name="radio1" id="x11">
                            <span class="weui_icon_checked"></span>
                        </div>
                    </label>
                    <label class="weui_cell weui_check_label" for="x12" style="border-top: 0px solid transparent;">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p title="2">Her baby 她宝贝</p>
                        </div>
                        <div class="weui_cell_ft">
                            <input type="radio" name="radio1" class="weui_check" id="x12" checked="checked">
                            <span class="weui_icon_checked"></span>
                        </div>
                    </label>
                    <label class="weui_cell weui_check_label" for="x13" style="border-top: 0px solid transparent;">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p title="6">Her love 她心情</p>
                        </div>
                        <div class="weui_cell_ft">
                            <input type="radio" name="radio1" class="weui_check" id="x13" checked="checked">
                            <span class="weui_icon_checked"></span>
                        </div>
                    </label>
                    <label class="weui_cell weui_check_label" for="x14" style="border-top: 0px solid transparent;">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p title="5">Her life 她生活</p>
                        </div>
                        <div class="weui_cell_ft">
                            <input type="radio" name="radio1" class="weui_check" id="x14" checked="checked">
                            <span class="weui_icon_checked"></span>
                        </div>
                    </label>
                </div>
                <div class="weui_cells weui_cells_form" id="commentArea">
                    <div class="weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">
                            <textarea class="weui_textarea" placeholder="这一刻的想法..." rows="3" name="topicContent"></textarea>
                            <div class="weui_textarea_counter"><span>0</span>/5000</div>
                        </div>
                    </div>
                </div>
                <p>(话题内容不能超过5000字)</p>
                <div class="weui_cells weui_cells_form" id="photoBd">
                    <div class="weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">
                            <div class="weui_uploader">
                                <div class="weui_uploader_hd weui_cell">
                                    <div class="weui_cell_bd weui_cell_primary">图片上传</div>
                                    <div class="weui_cell_ft" id="photoNum"><span>0</span>/9</div>
                                </div>
                                <div class="weui_uploader_bd">
                                    <ul class="weui_uploader_files">

                                    </ul>
                                    <div class="weui_uploader_input_wrp">
                                        <input name="multipartFiles" class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <div class="info error" style="display: none">
            <p class="weui_toast_content"></p>
        </div>
        <div class="info success" style="display: none">
            <p class="weui_toast_content">发布成功</p>
        </div>
    </div>

    <script type="text/javascript" src="${ctx}/js/topicRelease.js" charset="gbk"></script>
</body>
</html>
