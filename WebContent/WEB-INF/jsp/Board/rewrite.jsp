<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="common.HtmlUtil"%>
<%@ page import="java.util.ArrayList"%>
<c:import url="/WEB-INF/jsp/layout.jsp">
	<c:param name="title" value="REWRITE" />
	<c:param name="css" value="/Board/rewrite.css" />
	<c:param name="javascript" value="/Board/rewrite.js" />
	<c:param name="mainContents">
		<form id="poster" method="post"
			action="<%=request.getContextPath()%>/Board/show">
			<input type="hidden" name="op" value="update"> <input
				type="hidden" name="panel_key"
				value="<%=request.getAttribute("panel_key")%>">
			<textarea name="panel_posting"><%=HtmlUtil.escape((String) request.getAttribute("panel_posting"))%></textarea>
			<br> <select name="panel_size">
				<option value="S">small</option>
				<option value="W">wide</option>
				<option value="L">large</option>
			</select> <input type="submit" value="post">
		</form>
	</c:param>
</c:import>