<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>【话题】女人悄悄话-粉蜜话题-herpink粉红网</title>
</head>

<body>
	<div class="ui_mainbox wrap">
		<div class="cf">
			<div class="activity_main">
				
				<div class="activity_list">
					<div class="list">
						<c:forEach items="${tiPage.content}" var="topic">
							<dl>
								<dt>
									<a href="${ctx}/topic/detail?id=${topic.id}"><img
										src="http://herpink.cn:8080/pinknetpic/${topic.descPic}"></a>
								</dt>
								<dd>
									<h4>
										<a href="${ctx}/topic/detail?id=${topic.id}">${topic.topicTitle}
										</a>
									</h4>
									<p>
										${topic.mediumContent} <a
											href="${ctx}/topic/detail?id=${topic.id}">阅读全文</a>
									</p>
									
								</dd>
							</dl>
						</c:forEach>
					</div>
					<%--<tags:paginationwithtype page="${tiPage}" paginationSize="10"/>--%>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>
