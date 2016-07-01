<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>

<div class="page">
	<ul>
		 <% if (page.hasPreviousPage()){%>
               	<a href="?query=${query}&page=1&sortType=${sortType}">&lt;&lt;</a>
                <a href="?query=${query}&page=${current-1}&sortType=${sortType}">&lt; 上一页</a>
         <%}else{%>
                <!-- <a href="#">&lt;&lt;</a>
                <a href="#">&lt; 上一页</a> -->
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <a href="?query=${query}&page=${i}&sortType=${sortType}" class="on">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="?query=${query}&page=${i}&sortType=${sortType}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<a href="?query=${query}&page=${current+1}&sortType=${sortType}">下一页&gt;</a>
                <a href="?query=${query}&page=${page.totalPages}&sortType=${sortType}">&gt;&gt;</a>
         <%}else{%>
                <!-- <a href="#">下一页&gt;</a>
                <a href="#">&gt;&gt;</a> -->
         <%} %>

	</ul>
</div>

