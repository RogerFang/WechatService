<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<html>
<head>
	<meta name="decorator" content="default">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<%--<title>【话题】女人悄悄话-粉蜜话题-herpink粉红网</title>--%>

	<link href="${ctx}/css/topicDetail.css" rel="stylesheet" type="text/css">

	<script>
		function commentAjax() {
			if ($("#commentContent").val() == ""){
				$.alert("评论不能为空!");
				return false;
			}

			$("#commentForm").ajaxSubmit({
				success:function (data) {
					if (data.flag == 1){
						// $("#commentText").val("");
						// alert("感谢您的评论");
						location.reload(true);
					}else if (data.flag == -1){
						$.alert("请先到【会员】进行注册!");
					}
				}
			});
		}
	</script>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<p class="title"><a href="#"></a><span>${topicCls.clsTitle}</span></p>
		</div>
		<div id="content">
			<div class="topic">
				<div class="topicHeader">
					<img class="topicUserHead" src="http://herpink.cn:8080/pinknetpic/${topicCreator.userPhoto}" style="display: none" onload="this.style.display=''">
					<img class="topicUserHead" src="${ctx}/${topicCreator.text3}" style="display: none" onload="this.style.display=''">
					<%--<c:if test="${topicCreator.userPhoto eq null and topicCreator.text3 eq null}">--%>
						<%--<img class="topicUserHead" src="${ctx}/img/defaultHerpink.png" style="display: none" onload="this.style.display=''">--%>
					<%--</c:if>--%>
					<div class="topicUserInfo">
						<p>${topicCreator.userName}<span>LV${topicCreator.userLevel}</span></p>
						<p><fmt:formatDate value="${topicInfo.topicCreatetime }" pattern="yyyy-MM-dd HH:mm" /></p>
					</div>
				</div>
				<div class="topicContent">
					<p>${topicInfo.topicContent }</p>
					<c:forEach items="${picList }" var="item">
						<img src="http://herpink.cn:8080/pinknetpic/${item}">
					</c:forEach>
					<c:forEach items="${text4PicList }" var="item">
						<img src="${ctx}/${item}">
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="commentList">
			<ul>
				<c:if test="${ciPage.totalElements eq 0}">
					<h4>暂无评论，做第一个发评论的人吧！</h4>
				</c:if>
				<c:forEach items="${ciPage.content}" var="ci">
					<li class="comment list-unstyled">
						<div class="commentHeader">
							<img class="commentUserHead" src="http://herpink.cn:8080/pinknetpic/${ci.commentCreator.userPhoto}" style="display: none" onload="this.style.display=''">
							<img class="commentUserHead" src="${ctx}/${ci.commentCreator.text3}" style="display: none" onload="this.style.display=''">
							<%--<c:if test="${ci.commentCreator.userPhoto eq null and ci.commentCreator.text3 eq null}">--%>
								<%--<img class="commentUserHead" src="${ctx}/img/defaultHerpink.png" style="display: none" onload="this.style.display=''">--%>
							<%--</c:if>--%>
							<div class="commentUserInfo">
								<p>${ci.commentCreator.userName}</p>
								<p><fmt:formatDate value="${ci.commentCreatetime }" pattern="yyyy-MM-dd HH:mm" /></p>
							</div>
							<%--<a href="#">回复</a>--%>
						</div>
						<div class="commentDetail">
							${ci.commentContent }
							<c:if test="${empty ci.commentContent}">
								&nbsp;
							</c:if>
						</div>
					</li>
				</c:forEach>

			</ul>
		</div>
	</div>

	<div id="comment">
		<form id="commentForm" action="${ctx}/topic/comment" method="post">
			<input type="hidden" name="infoId" value="${id}">
			<textarea id="commentContent" name="commentContent" class="weui_textarea" placeholder=" 请输入评论"></textarea>
			<button class="btn btn-primary form-control" type="button" onclick="commentAjax();">发表评论</button>
		</form>
	</div>
</body>
</html>
