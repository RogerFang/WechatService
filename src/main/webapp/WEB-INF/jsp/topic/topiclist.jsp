<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default">
	<%--<title>女人悄悄话-粉蜜话题</title>--%>
	<link href="${ctx}/css/topicList.css" rel="stylesheet" type="text/css">

</head>

<body>
<div id="wrapper">
		<div id="header">
			<p class="title"><span>粉蜜话题</span><a href="${ctx}/topic/release" style="background: url('${ctx}/img/write.png') no-repeat center;"></a></p>
		</div>
		<ul id="content">
			<c:forEach items="${tiPage.content}" var="topic">
				<li class="topic">
					<div class="topicHeader">
						<img class="topicUserHead" src="http://herpink.cn:8080/pinknetpic/${topic.topicCreator.userPhoto}" style="display: none" onload="this.style.display=''">
						<img class="topicUserHead" src="${ctx}/${topic.topicCreator.text3}" style="display: none" onload="this.style.display=''">
						<%--<img class="topicUserHead" src="${ctx}/img/defaultHerpink.png" style="display: none" onload="this.style.display=''">--%>
						<div class="topicUserInfo">
							<p>${topic.topicCreator.userName}<span>LV${topic.topicCreator.userLevel}</span></p>
							<p><fmt:formatDate value="${topic.topicCreatetime}" pattern="yyyy-MM-dd HH:mm" /></p>
						</div>
					</div>
					<a href="${ctx}/topic/detail?id=${topic.id}" style="margin-top: 0;">
						<div class="topicContent">
							<p>${topic.mediumContent}</p>
							<%--<c:if test="${not empty topic.descPic}">
								<img src="http://herpink.cn:8080/pinknetpic/${topic.descPic}">
							</c:if>
							<c:if test="${not empty topic.text4PicDesc}">
								<img src="${ctx}/${topic.text4PicDesc}">
							</c:if>--%>
							<img src="http://herpink.cn:8080/pinknetpic/${topic.descPic}" style="display: none" onload="this.style.display=''">
							<img src="${ctx}/${topic.text4PicDesc}" style="display: none" onload="this.style.display=''">
						</div>
					</a>
					<div class="topicFooter weui-row">
						<span class="weui-col-50"><a href="${ctx}/topic/detail?id=${topic.id}">评论(${topic.topicCommentCount})</a></span>
						<span class="weui-col-50"><a id="praise-${topic.id}" onclick="praise(${topic.id})">${topic.topicPraiseCount}</a></span>
					</div>
				</li>
			</c:forEach>
		</ul>
		<input type="hidden" name="page" value="${page}">
	</div>

<script>
	function praise(topicId) {
		$.post(
			"${ctx}/topic/praise/"+topicId,
			function(data){
				if (data.flag == 1){
					$("#praise-"+topicId).html(data.praiseCount);
				}else if (data.flag == 0){
					$.alert("您已点过赞啦!");
				}else if (data.flag == -1){
					$.alert("请先到【会员】进行注册!");
				}
			}
		);
	}
</script>
<script>
	$(document).ready(function() {
		var loading=false;
		$(window).scroll(function(){
			if ($(this).scrollTop() + this.innerHeight >= $(document).height() && $(this).scrollTop() > 0){
				if(loading) return;
				loading = true;
				setTimeout(function() {
					var $page = $("input[name='page']");
					var $pageNumber = parseInt($page.val())+1;
					$.ajax({
						type: "POST",
						url: "loadList",
						data: {"page":$pageNumber},
						dataType: "json",
						success: function (data) {
							if (data.length > 0){
								$.each(data, function (i, topic) {
									var basepath = GetUrlRelativePath();

									var topicinfo =
										'<li class="topic">' +
										'<div class="topicHeader">' +
										'<img class="topicUserHead" src="http://herpink.cn:8080/pinknetpic/'+topic.topicCreator.userPhoto+'" style="display: none" onload="this.style.display=\'\'">' +
										'<img class="topicUserHead" src="'+basepath+'/'+topic.topicCreator.text3+'" style="display: none" onload="this.style.display=\'\'">'+
										'<div class="topicUserInfo">' +
										'<p>'+ topic.topicCreator.userName + '<span>LV'+topic.topicCreator.userLevel+'</span></p>' +
										'<p>'+topic.formatTopicCreatetime+'</p>' +
										'</div>' +
										'</div>' +
										'<a href="detail?id='+topic.id+'" style="margin-top: 0;">' +
										'<div class="topicContent">' +
										'<p>'+topic.mediumContent+'</p>' +
										'<img src="http://herpink.cn:8080/pinknetpic/'+topic.descPic+'" style="display: none" onload="this.style.display=\'\'">' +
										'<img src="'+basepath+'/'+topic.text4PicDesc+'" style="display: none" onload="this.style.display=\'\'">' +
										'</div>' +
										'</a>' +
										'<div class="topicFooter weui-row">' +
										'<span class="weui-col-50"><a href="detail?id='+topic.id+'">评论('+topic.topicCommentCount+')</a></span>' +
										'<span class="weui-col-50"><a id="praise-'+topic.id+'" onclick="praise('+topic.id+')">'+topic.topicPraiseCount+'</a></span>' +
										'</div>' +
										'</li>';
									$("#content").append(topicinfo);
								});

								$page.val($pageNumber);
							}
						}
					});
					loading = false;
				}, 100);   //模拟延迟
			}
		})
	});


</script>
</body>
</html>
