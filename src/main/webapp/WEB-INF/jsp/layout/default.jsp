<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/5/15
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title><sitemesh:title/></title>

    <%--<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="${ctx}/css/weui.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/css/jquery-weui.min.css" type="text/css">

    <style>
        .col-center-block {
            width:auto;
            display: table;
            margin-left: auto;
            margin-right: auto;
        }
        .col-text-center{
            text-align: center;
        }
    </style>
    <sitemesh:head/>
</head>
<body>

    <script type="text/javascript" src="${ctx}/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-weui.min.js"></script>
    <%--<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>--%>
    <script>
        function GetUrlRelativePath()
        {
            var url = document.location.toString();
            var arrUrl = url.split("//");

            var start = arrUrl[1].indexOf("/");
            var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符

            return arrUrl[0]+"//"+window.location.host+"/"+relUrl.split("/")[1];
        }
    </script>
    <sitemesh:body/>
</body>
</html>
