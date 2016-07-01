<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<html>
<head>
	<meta name="decorator" content="default">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>【话题】女人悄悄话-粉蜜话题-herpink粉红网</title>

	<script>
		function commentAjax() {
			$("#commentForm").ajaxSubmit({
				success:function (data) {
					if (data.flag == 1){
						$("#commentText").val("");
						alert("感谢您的评论");
					}
				}
			});
		}
	</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<h2>
				${topicCls.clsTitle}
			</h2>
			${topicInfo.topicCreatetime }
		</div>

		<p>
			${topicInfo.topicContent }
		</p>
		<div class="container">
			<ul>
				<c:forEach items="${picList }" var="item">
					<li class="list-unstyled"><img class="img-responsive center-block" style="max-height:755px;max-width:755px" src="http://herpink.cn:8080/pinknetpic/${item}"></li>
				</c:forEach>
			</ul>
		</div>
		<h2>
			热门评论<span>&nbsp;[${ciPage.totalElements}]</span>
		</h2>
		<div class="row-fluid">
			<div class="span12">
				<c:if test="${ciPage.totalElements eq 0}">
					<h4>暂无评论，做第一个发评论的人吧！</h4>
				</c:if>
				<c:forEach items="${ciPage.content}" var="ci">
					<div class="media">
						<img class="pull-left" src="http://herpink.cn:8080/pinknetpic/${ci.commentCreator.userPhoto}">
						<div class="media-body">
							<h4>${ci.commentCreator.userName}
								&nbsp;&nbsp;
								<fmt:formatDate value="${ci.commentCreatetime }"
												pattern="yyyy-MM-dd HH:mm" />
							</h4>
							<p>${ci.commentContent }</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<div class="form">
			<form id="commentForm" action="${ctx}/topic/comment" method="post">
				<input type="hidden" name="infoId" value="${id}">
				<textarea class="form-control" id="commentText" name="commentContent"></textarea>
				<button class="btn btn-primary form-control" type="button" onclick="commentAjax();">发表评论</button>
			</form>
		</div>
	</div>

	<%--<div class="ui_mainbox wrap">--%>
		<%--<div class="cf">--%>
			<%--<div class="herpink_main">--%>
				<%--<div class="herpink_title">--%>
					<%--<h2>--%>
						<%--<span>Her baby</span> 她宝贝--%>
					<%--</h2>--%>
				<%--</div>--%>
				<%--<div class="herpink_cont love_detail">--%>

					<%--<div class="time">${topicInfo.topicCreatetime }</div>--%>
					<%--<div class="text">--%>
						<%--${topicInfo.topicContent }--%>
						<%--<c:forEach items="${picList }" var="item">--%>
							<%--<p></p>--%>
							<%--<center>--%>
								<%--<img style="max-height:755px;max-width:755px" src="http://herpink.cn:8080/pinknetpic/${item}">--%>
							<%--</center>--%>
							<%--<p></p>--%>
						<%--</c:forEach>--%>
					<%--</div>--%>

				<%--</div>--%>
				<%--<div class="ui_hot_comment">--%>
					<%--<div class="tit">--%>
						<%--<h2>--%>
							<%--热门评论<span>&nbsp;[${ciPage.totalElements}]</span>--%>
						<%--</h2>--%>
					<%--</div>--%>
					<%--<div class="list">--%>
						<%--<c:if test="${ciPage.totalElements eq 0}">--%>
							<%--<h4>暂无评论，做第一个发评论的人吧！</h4>--%>
						<%--</c:if>--%>
						<%--<ul>--%>
							<%--<c:forEach items="${ciPage.content}" var="ci">--%>
								<%--<li><a href="" class="img"><img--%>
										<%--src="http://herpink.cn:8080/pinknetpic/${ci.commentCreator.userPhoto}"></a>--%>
									<%--<h4>${ci.commentCreator.userName}--%>
										<%--&nbsp;&nbsp;--%>
										<%--<fmt:formatDate value="${ci.commentCreatetime }"--%>
											<%--pattern="yyyy-MM-dd HH:mm" />--%>
									<%--</h4>--%>
									<%--<p>${ci.commentContent }</p></li>--%>
							<%--</c:forEach>--%>
						<%--</ul>--%>
					<%--</div>--%>

					<%--<div class="page">--%>
						<%--&lt;%&ndash;<tags:paginationwithid page="${ciPage}" paginationSize="10" />&ndash;%&gt;--%>
					<%--</div>--%>
					<%--<div class="form">--%>
						<%--<form id="commentForm" action="${ctx}/topic/comment" method="post">--%>
							<%--<input type="hidden" name="infoId" value="${id}">--%>
							<%--<textarea id="commentText" name="commentContent"></textarea>--%>
							<%--<button type="button" onclick="commentAjax();">发表评论</button>--%>
						<%--</form>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div class="herpink_side">--%>
				<%--<div class="side_her_love mt20">--%>
					<%--<div class="tit">--%>
						<%--<a href="${ctx}/topic/list?topictype=6">查看更多&gt;&gt;</a>--%>
						<%--<h2>--%>
							<%--<span>Her love</span> 她心情--%>
						<%--</h2>--%>
					<%--</div>--%>
					<%--<div class="cnt">--%>
						<%--<c:forEach items="${item6}" var="var">--%>
							<%--<dl>--%>
								<%--<dd>--%>
									<%--${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>--%>
								<%--</dd>--%>
							<%--</dl>--%>
						<%--</c:forEach>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="side_her_love mt20">--%>
					<%--<div class="tit">--%>
						<%--<a href="${ctx}/topic/list?topictype=1">查看更多&gt;&gt;</a>--%>
						<%--<h2>--%>
							<%--<span>Her beauty</span> 她美丽--%>
						<%--</h2>--%>
					<%--</div>--%>
					<%--<div class="cnt">--%>
						<%--<c:forEach items="${item1}" var="var">--%>
							<%--<dl>--%>
								<%----%>
								<%--<dd>--%>
									<%--${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>--%>
								<%--</dd>--%>
							<%--</dl>--%>
						<%--</c:forEach>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="side_her_love mt20">--%>
					<%--<div class="tit">--%>
						<%--<a href="${ctx}/topic/list?topictype=5">查看更多&gt;&gt;</a>--%>
						<%--<h2>--%>
							<%--<span>Her life</span> 她生活--%>
						<%--</h2>--%>
					<%--</div>--%>
					<%--<div class="cnt">--%>
						<%--<c:forEach items="${item5}" var="var">--%>
							<%--<dl>--%>
								<%----%>
								<%--<dd>--%>
									<%--${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>--%>
								<%--</dd>--%>
							<%--</dl>--%>
						<%--</c:forEach>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="side_her_love mt20">--%>
					<%--<div class="tit">--%>
						<%--<a href="${ctx}/topic/list?topictype=2">查看更多&gt;&gt;</a>--%>
						<%--<h2>--%>
							<%--<span>Her life</span> 她宝贝--%>
						<%--</h2>--%>
					<%--</div>--%>
					<%--<div class="cnt">--%>
						<%--<c:forEach items="${item2}" var="var">--%>
							<%--<dl>--%>
								<%----%>
								<%--<dd>--%>
									<%--${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>--%>
								<%--</dd>--%>
							<%--</dl>--%>
						<%--</c:forEach>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="mt20 side_download">--%>
					<%--<span><img src="${ctx }/static/images/tcode.jpg"></span>--%>
					<%--<p>想参与更多姐妹话题交流，请下载</p>--%>
					<%--<p>“最可信赖的纯女性社交平台Herpink“</p>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
</body>
</html>
