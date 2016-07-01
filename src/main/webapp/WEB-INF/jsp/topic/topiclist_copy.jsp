<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default">
	<title>【话题】女人悄悄话-粉蜜话题-herpink粉红网</title>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-937523">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#panel-beauty" data-toggle="tab">她美丽</a>
						</li>
						<li>
							<a href="#panel-baby" data-toggle="tab">她宝贝</a>
						</li>
						<li>
							<a href="#panel-feel" data-toggle="tab">她心情</a>
						</li>
						<li>
							<a href="#panel-life" data-toggle="tab">她生活</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-beauty">
							<p>

							</p>
						</div>
						<div class="tab-pane" id="panel-baby">
							<p>

							</p>
						</div>
						<div class="tab-pane" id="panel-feel">
							<p>

							</p>
						</div>
						<div class="tab-pane" id="panel-life">
							<p>

							</p>
						</div>
					</div>
				</div>
				<ul class="thumbnails">
					<c:forEach items="${tiPage.content}" var="topic">
						<li class="list-unstyled span4">
							<div class="thumbnail">
								<a href="${ctx}/topic/detail?id=${topic.id}"><img
										src="http://herpink.cn:8080/pinknetpic/${topic.descPic}"></a>
								<div class="caption">
									<div class="row-fluid">
										<div class="span12">
											<div class="media">
												<img class="pull-left" src="http://herpink.cn:8080/pinknetpic/${topic.topicCreator.userPhoto}">
												<div class="media-body">
													<h4 class="media-heading">
														发布人：${topic.topicCreator.userName}
													</h4>
													发布时间：<fmt:formatDate value="${topic.topicCreatetime}"
																	pattern="yyyy-MM-dd HH:mm" />
												</div>
											</div>
										</div>
									</div>
									<p>${topic.mediumContent}
									</p>
									<p class="col-center-block">
										<a class="btn btn-primary" href="${ctx}/topic/detail?id=${topic.id}">阅读全文</a>
									</p>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<%--<div class="container">--%>
		<%--<ul class="list-group">--%>
			<%--<c:forEach items="${tiPage.content}" var="topic">--%>
				<%--<li class="list-group-item">--%>
					<%--<img src="http://herpink.cn:8080/pinknetpic/${topic.topicCreator.userPhoto}">--%>
						<%--${topic.topicCreator.userName}--%>
					<%--&nbsp;&nbsp;--%>
					<%--<fmt:formatDate value="${topic.topicCreatetime}"--%>
									<%--pattern="yyyy-MM-dd HH:mm" />--%>
					<%--<div >--%>
						<%--<a href="${ctx}/topic/detail?id=${topic.id}"><img--%>
								<%--src="http://herpink.cn:8080/pinknetpic/${topic.descPic}"></a>--%>
					<%--</div>--%>
					<%--<div >--%>
						<%--<h4>--%>
							<%--<a href="${ctx}/topic/detail?id=${topic.id}">${topic.topicTitle}</a>--%>
						<%--</h4>--%>
						<%--<p>--%>
								<%--${topic.mediumContent}<a href="${ctx}/topic/detail?id=${topic.id}">阅读全文</a>--%>
						<%--</p>--%>
					<%--</div>--%>
				<%--</li>--%>
			<%--</c:forEach>--%>
		<%--</ul>--%>
	<%--</div>--%>
</body>
</html>
