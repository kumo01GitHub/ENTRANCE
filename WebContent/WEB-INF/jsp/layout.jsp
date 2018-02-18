<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="common.HtmlUtil"%>
<%@ page import="access.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/layout/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>${param.css}">
<title>ENTRANCE | ${param.title}</title>
</head>
<body>
	<!-- header -->
	<div id="header">
		<h1 id="title">${param.title}</h1>
	</div>
	<!-- contents -->
	<div id="contents">
		<!-- left column -->
		<div id="left-column">
			<ul id="contentsbar">
				<li class="contents-tab"><a class="contents_anchor"
					href="<%=request.getContextPath()%>/Board/show">&gt;&gt;&nbsp;&nbsp;&nbsp;Board</a></li>
				<li class="contents-tab"><a class="contents_anchor"
					href="<%=request.getContextPath()%>/Me/view">&gt;&gt;&nbsp;&nbsp;&nbsp;Me</a></li>
				<li class="contents-tab"><a class="contents_anchor"
					href="<%=request.getContextPath()%>/Access/marker">&gt;&gt;&nbsp;&nbsp;&nbsp;Access</a></li>
			</ul>
			<ul id="accessbar">
				<%
					BookmarkCategoryDAO bcDao = new BookmarkCategoryDAO();
					BookmarkDAO bDao = new BookmarkDAO();
					ArrayList<BookmarkCategoryDTO> bcList = bcDao.get();
					for (int i = 0; i < bcList.size(); i++) {
						BookmarkCategoryDTO bc = bcList.get(i);
				%>
				<li class="bookmark-category"><a
					class="bookmark-category_header"><%=HtmlUtil.escape(bc.getName())%></a>
					<ul class="bookmark-list">
						<%
							ArrayList<BookmarkDTO> bList = bDao.getByCategory(bc);
								for (int j = 0; j < bList.size(); j++) {
									BookmarkDTO b = bList.get(j);
						%>
						<li class="bookmark"><a class="bookmark_anchor"
							href="<%=HtmlUtil.escapeQuot(b.getUrl())%>"><%=HtmlUtil.escape(b.getTitle())%></a></li>
						<%
							}
						%>
						<li class="bookmark"><a class="bookmark_anchor"
							href="<%=request.getContextPath()%>/Access/marker">new&nbsp;bookmark...</a></li>
					</ul></li>
				<%
					}
				%>
			</ul>
		</div>
		<!-- main contents -->
		<div id="wrapper_main">
			<div id="main-contents">${param.mainContents}</div>
		</div>
	</div>
	<!-- footer -->
	<div id="footer">
		<div id="copyright">&copy;&nbsp;2018&nbsp;TAICHI&nbsp;SATO</div>
	</div>
	<!-- javascript -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/layout/leftColumn.js"></script>
	<script src="<%=request.getContextPath()%>${param.javascript}"></script>
</body>
</html>