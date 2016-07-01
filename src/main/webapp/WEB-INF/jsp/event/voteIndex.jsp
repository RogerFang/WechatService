<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/17
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>投票活动</title>--%>
    <link rel="stylesheet" href="${ctx}/css/voteIndex.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <p class="title">投票</p>
    </div>
    <div id="content">
        <div id="tab" class="weui-row weui-no-gutter">
            <div class="weui-col-33 selected">
                <div>全部</div>
            </div>
            <div class="weui-col-33">
                <div>排行榜</div>
            </div>
            <div class="weui-col-33">
                <div>投票记录</div>
            </div>
        </div>
        <div id="contentMain">
            <div id="all">
                <ul>
                    <c:forEach items="${eventSignList}" var="item">
                        <li>
                            <div class="photo">
                                <img src="http://herpink.cn:8080/pinknetpic/${item.eventSignPic}" style="display: none" onload="this.style.display=''">
                                <img src="${ctx}/${item.eventSignPic}" style="display: none" onload="this.style.display=''">
                                <div class="user">
                                    <img src="http://herpink.cn:8080/pinknetpic/${item.eventSignCreator.userPhoto}" style="display: none" onload="this.style.display=''">
                                    <img src="${ctx}/${item.eventSignCreator.text3}" style="display: none" onload="this.style.display=''">
                                    <span>${item.eventSignCreator.userName}</span>
                                    <span>LV${item.eventSignCreator.userLevel}</span>
                                </div>
                            </div>
                            <div class="vote weui-row weui-no-gutter">
                                <div class="weui-col-33">编号：<span>${item.eventNum}</span></div>
                                <div class="weui-col-33">票数：<span class="praise_${item.eventSignId}">${item.eventSignPraise}</span></div>
                                <div class="weui-col-33"><button onclick="voteForEventSign(${item.eventSignId});">支持他</button></div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div id="rank" style="display: none">
                <ul>
                    <c:forEach items="${eventSignRankList}" var="item">
                        <li class="weui-row weui-no-gutter">
                            <div class="weui-col-33">
                                <img src="http://herpink.cn:8080/pinknetpic/${item.eventSignCreator.userPhoto}" style="display: none" onload="this.style.display=''">
                                <img src="${ctx}/${item.eventSignCreator.text3}" style="display: none" onload="this.style.display=''">
                                <div>${item.eventSignCreator.userName}<p>LV${item.eventSignCreator.userLevel}</p></div>
                            </div>
                            <div class="weui-col-33">${item.eventNum}</div>
                            <div class="weui-col-33"><span class="praise_${item.eventSignId}">${item.eventSignPraise}</span>票</div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div id="record" style="display: none">
                <ul>
                    <c:forEach items="${eventSignRecordList}" var="item">
                        <li>
                            <div class="photo">
                                <img src="http://herpink.cn:8080/pinknetpic/${item.eventSignPic}" style="display: none" onload="this.style.display=''">
                                <img src="${ctx}/${item.eventSignPic}" style="display: none" onload="this.style.display=''">
                                <div class="user">
                                    <img src="http://herpink.cn:8080/pinknetpic/${item.eventSignCreator.userPhoto}" style="display: none" onload="this.style.display=''">
                                    <img src="${ctx}/${item.eventSignCreator.text3}" style="display: none" onload="this.style.display=''">
                                    <span>${item.eventSignCreator.userName}</span>
                                    <span>LV${item.eventSignCreator.userLevel}</span>
                                </div>
                            </div>
                            <div class="vote weui-row weui-no-gutter">
                                <div class="weui-col-33">编号：<span>${item.eventNum}</span></div>
                                <div class="weui-col-33">票数：<span class="praise_${item.eventSignId}">${item.eventSignPraise}</span></div>
                                <div class="weui-col-33"><button onclick="voteForEventSign(${item.eventSignId});">再次支持</button></div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/voteIndex.js" charset="gbk"></script>
<script>
    function voteForEventSign(eventSignId) {
        $.post(
                "${ctx}/eventinfo/vote/${eventId}/"+eventSignId,
                function(data){
                    $.alert(data.msg);
                    if (data.eventSignId != ""){
                        $(".praise_"+data.eventSignId).text(data.praiseCount);
                    }
                }
        );
    }
</script>
</body>
</html>
