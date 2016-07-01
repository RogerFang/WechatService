<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>【话题】女人悄悄话-粉蜜话题-herpink粉红网</title>
</head>

<body>
	<div class="ui_mainbox wrap">
		<div class="cf"></div>
		<!-- 
		<div class="mt20 active_user">
			<div class="tit">
				<h2>活跃用户</h2>
			</div>
			<div class="cnt">
				
			</div>
		</div> -->
		<div class="mt10">
			<div class="herpink_title">
				<h2>
					<span>Her beauty</span> 她美丽 <a href="${ctx}/topic/list?topictype=1"><span
						style="font-size: 15px;">(更多)</span></a>
				</h2>
			</div>
			<div class="ui_herpink_content mt20 cf">
				<div class="imgbox fl">
					<div class="recom">
						<a href="${ctx}/topic/detail?id=${top11.id}" class="img"><img style="max-height:390px"
							src="http://herpink.cn:8080/pinknetpic/${top11.descPic}"></a> <a
							href="${ctx}/topic/detail?id=${top11.id}" class="txt">${top11.shortTitle }</a>
					</div>
					<div class="list">
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top21.id}"><img
									src="http://herpink.cn:8080/pinknetpic/${top21.descPic}"></a>
							</dt>
							<dd>
								<h4>${top21.shortTitle }</h4>
								<p>${top21.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top21.id}">查看更多</a>
							</dd>
						</dl>
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top31.id}"><img
									src="http://herpink.cn:8080/pinknetpic/${top31.descPic}"></a>
							</dt>
							<dd>
								<h4>${top31.shortTitle }</h4>
								<p>${top31.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top31.id}">查看更多</a>
							</dd>
						</dl>
					</div>
				</div>
				<div class="hotbox fr">
					<div class="tit">
						<h2>
							原创热点 <span>Hot topic</span>
						</h2>
					</div>
					<div class="cnt">
						<c:forEach items="${item1}" var="var">
							<dl>
								<dt>
									<a href="${ctx}/topic/detail?id=${var.id}">${var.shortTitle }</a>
								</dt>
								<dd>
									${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>
								</dd>
							</dl>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="mt10">
			<div class="herpink_title">
				<h2>
					<span>Her bady</span> 她宝贝<a href="${ctx}/topic/list?topictype=2"><span
						style="font-size: 15px;">(更多)</span></a>
				</h2>
			</div>
			<div class="ui_herpink_content mt20 cf">
				<div class="imgbox fr">
					<div class="recom">
						<a href="${ctx}/topic/detail?id=${top12.id}" class="img"><img style="max-height:390px"
							src="http://herpink.cn:8080/pinknetpic/${top12.descPic}"></a> <a
							href="${ctx}/topic/detail?id=${top12.id}" class="txt">${top12.shortTitle }</a>
					</div>
					<div class="list">
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top22.id}"><img
									src="http://herpink.cn:8080/pinknetpic/${top22.descPic}"></a>
							</dt>
							<dd>
								<h4>${top22.shortTitle }</h4>
								<p>${top22.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top22.id}">查看更多</a>
							</dd>
						</dl>
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top32.id}"><img
									src="http://herpink.cn:8080/pinknetpic/${top32.descPic}"></a>
							</dt>
							<dd>
								<h4>${top32.shortTitle }</h4>
								<p>${top32.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top32.id}">查看更多</a>
							</dd>
						</dl>
					</div>
				</div>
				<div class="hotbox fl">
					<div class="tit">
						<h2>
							原创热点 <span>Hot topic</span>
						</h2>
					</div>
					<div class="cnt">
						<c:forEach items="${item2}" var="var">
							<dl>
								<dt>
									<a href="${ctx}/topic/detail?id=${var.id}">${var.shortTitle }</a>
								</dt>
								<dd>
									${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>
								</dd>
							</dl>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="mt10">
			<div class="herpink_title">
				<h2>
					<span>Her love</span> 她心情<a href="${ctx}/topic/list?topictype=6"><span
						style="font-size: 15px;">(更多)</span></a>
				</h2>
			</div>
			<div class="ui_herpink_content mt20 cf">
				<div class="imgbox fl">
					<div class="recom">
						<a href="${ctx}/topic/detail?id=${top16.id}" class="img"><img style="max-height:390px"
							src="http://herpink.cn:8080/pinknetpic/${top16.descPic}"></a> <a
							href="${ctx}/topic/detail?id=${top16.id}" class="txt">${top16.shortTitle }</a>
					</div>
					<div class="list">
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top26.id}"><img style="max-height:188px"
									src="http://herpink.cn:8080/pinknetpic/${top26.descPic}"></a>
							</dt>
							<dd>
								<h4>${top26.shortTitle }</h4>
								<p>${top26.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top26.id}">查看更多</a>
							</dd>
						</dl>
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top36.id}"><img style="max-height:188px"
									src="http://herpink.cn:8080/pinknetpic/${top36.descPic}"></a>
							</dt>
							<dd>
								<h4>${top36.shortTitle }</h4>
								<p>${top36.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top36.id}">查看更多</a>
							</dd>
						</dl>
					</div>
				</div>
				<div class="hotbox fr">
					<div class="tit">
						<h2>
							原创热点 <span>Hot topic</span>
						</h2>
					</div>
					<div class="cnt">
						<c:forEach items="${item6}" var="var">
							<dl>
								<dt>
									<a href="${ctx}/topic/detail?id=${var.id}">${var.shortTitle }</a>
								</dt>
								<dd>
									${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>
								</dd>
							</dl>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="mt10">
			<div class="herpink_title">
				<h2>
					<span>Her life</span> 她生活<a href="${ctx}/topic/list?topictype=5"><span
						style="font-size: 15px;">(更多)</span></a>
				</h2>
			</div>
			<div class="ui_herpink_content mt20 cf">
				<div class="imgbox fr">
					<div class="recom">
						<a href="${ctx}/topic/detail?id=${top15.id}" class="img"><img
							src="http://herpink.cn:8080/pinknetpic/${top15.descPic}"></a> <a
							href="${ctx}/topic/detail?id=${top15.id}" class="txt">${top15.shortTitle }</a>
					</div>
					<div class="list">
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top25.id}"><img
									src="http://herpink.cn:8080/pinknetpic/${top25.descPic}"></a>
							</dt>
							<dd>
								<h4>${top25.shortTitle }</h4>
								<p>${top25.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top25.id}">查看更多</a>
							</dd>
						</dl>
						<dl>
							<dt>
								<a href="${ctx}/topic/detail?id=${top35.id}"><img
									src="http://herpink.cn:8080/pinknetpic/${top35.descPic}"></a>
							</dt>
							<dd>
								<h4>${top35.shortTitle }</h4>
								<p>${top35.shortContent }</p>
								<a href="${ctx}/topic/detail?id=${top35.id}">查看更多</a>
							</dd>
						</dl>
					</div>
				</div>
				<div class="hotbox fl">
					<div class="tit">
						<h2>
							原创热点 <span>Hot topic</span>
						</h2>
					</div>
					<div class="cnt">
						<c:forEach items="${item5}" var="var">
							<dl>
								<dt>
									<a href="${ctx}/topic/detail?id=${var.id}">${var.shortTitle }</a>
								</dt>
								<dd>
									${var.shortContent}<a href="${ctx}/topic/detail?id=${var.id}">阅读全文</a>
								</dd>
							</dl>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
