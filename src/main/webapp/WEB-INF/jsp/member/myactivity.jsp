<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/17
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>我的活动</title>--%>
    <link rel="stylesheet" href="${ctx}/css/userActivityList.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">我的活动</p>
    </div>
    <div id="content">
        <div id="tab" class="weui-row weui-no-gutter">
            <div class="weui-col-50"><a href="#"><p class="selected">我发起的</p></a></div>
            <div class="weui-col-50"><a href="#"><p>我参与的</p></a></div>
        </div>
        <div id="activityList">
            <ul class="activityList">
                <c:forEach items="${eventInfoList}" var="ei">
                    <li>
                        <div class="activityInfo">
                            <a href="#" class="weui-row weui-no-gutter">
                                <div class="weui-col-25">
                                    <img src="http://herpink.cn:8080/pinknetpic/${ei.descPic}" style="display: none" onload="this.style.display=''">
                                    <img src="${ctx}/${ei.descPic}" style="display: none" onload="this.style.display=''">
                                </div>
                                <div class="weui-col-60">
                                    <h4>${ei.eventTitle}</h4>
                                    <p>活动时间:<span><fmt:formatDate value="${ei.eventBegintime}" pattern="yyyy-MM-dd" /></span>~<span><fmt:formatDate value="${ei.eventEndtime}" pattern="yyyy-MM-dd" /></span></p>
                                    <p>发起人：${ei.eventCreator.userName}</p>
                                </div>
                                <c:if test="${ei.eventEndtime >= today and ei.eventProcess == 1}">
                                    <div class="weui-col-15 isinprocess">
                                        进行中
                                    </div>
                                </c:if>
                                <c:if test="${ei.eventEndtime < today and ei.eventProcess == 1}">
                                    <div class="weui-col-15 isover">
                                        已结束
                                    </div>
                                </c:if>
                                <c:if test="${ei.eventProcess == 0}">
                                    <div class="weui-col-15 isoncheck">
                                        待审核
                                    </div>
                                </c:if>
                                <c:if test="${ei.eventProcess == 2}">
                                    <div class="weui-col-15 isoncheck">
                                        未通过
                                    </div>
                                </c:if>

                            </a>
                        </div>
                        <div class="activityDetail weui-row weui-no-gutter">
                            <div class="weui-col-50"><a href="${ctx}/eventinfo/detail/${ei.id}">活动详情</a></div>
                            <div class="weui-col-50"><a href="${ctx}/member/enrollDetail/${ei.id}">报名详情</a></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <ul class="activityList" style="display: none">
                <c:forEach items="${eventInfoSignList}" var="eis">
                    <li>
                        <div class="activityInfo">
                            <a href="#" class="weui-row weui-no-gutter">
                                <div class="weui-col-25">
                                    <img src="http://herpink.cn:8080/pinknetpic/${eis.descPic}" style="display: none" onload="this.style.display=''">
                                    <img src="${ctx}/${eis.descPic}" style="display: none" onload="this.style.display=''">
                                </div>
                                <div class="weui-col-60">
                                    <h4>${eis.eventTitle}</h4>
                                    <p>活动时间:<span><fmt:formatDate value="${eis.eventBegintime}" pattern="yyyy-MM-dd" /></span>~<span><fmt:formatDate value="${eis.eventEndtime}" pattern="yyyy-MM-dd" /></span></p>
                                    <p>发起人：${eis.eventCreator.userName}</p>
                                </div>
                                <c:if test="${eis.eventEndtime >= today and eis.eventProcess == 1}">
                                    <div class="weui-col-15 isinprocess">
                                        进行中
                                    </div>
                                </c:if>
                                <c:if test="${eis.eventEndtime < today and eis.eventProcess == 1}">
                                    <div class="weui-col-15 isover">
                                        已结束
                                    </div>
                                </c:if>
                            </a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/userActivityList.js"></script>
</body>
</html>
