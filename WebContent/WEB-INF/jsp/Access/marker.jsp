<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="common.HtmlUtil"%>
<%@ page import="access.BookmarkCategoryDTO"%>
<%@ page import="access.BookmarkDTO"%>
<c:import url="/WEB-INF/jsp/layout.jsp">
	<c:param name="title" value="MARKER" />
	<c:param name="css" value="/Access/marker.css" />
	<c:param name="javascript" value="/Access/marker.js" />
	<c:param name="mainContents">
		<%
			ArrayList<String> msgs = (ArrayList<String>) request.getAttribute("msgs");
		%>
		<ul id="msg-list">
			<%
				for (int i = 0; i < msgs.size(); i++) {
			%>
			<li><%=msgs.get(i)%></li>
			<%
				}
			%>
		</ul>
		<form method="post"
			action="<%=request.getContextPath()%>/Access/marker"
			id="category-register">
			<input type="hidden" name="op_category" value="insert">New&nbsp;Category:&nbsp;&nbsp;<input
				type="text" name="category_name"> <input type="submit"
				value="registration">
		</form>
		<%
			ArrayList<BookmarkCategoryDTO> categories = (ArrayList<BookmarkCategoryDTO>) request
							.getAttribute("categories");
		%>
		<ul id="category-list">
			<%
				for (int i = 0; i < categories.size(); i++) {
							BookmarkCategoryDTO category = categories.get(i);
			%>
			<li class="category">
				<form method="post"
					action="<%=request.getContextPath()%>/Access/marker"
					class="category-form">
					<input type="hidden" name="op_category" value="delete"><input
						type="hidden" name="category_key" value="<%=category.getKey()%>">
					<input type="submit" class="delete_submit" value="delete">
				</form> <a class="category-header"><%=HtmlUtil.escape(category.getName())%></a>
				<ul class="bookmark-list">
					<%
						ArrayList<BookmarkDTO> bookmarks = (ArrayList<BookmarkDTO>) request
											.getAttribute("bookmarks" + category.getKey());
									for (int j = 0; j < bookmarks.size(); j++) {
										BookmarkDTO bookmark = bookmarks.get(j);
					%>
					<li class="bookmark">
						<form method="post"
							action="<%=request.getContextPath()%>/Access/marker"
							class="bookmark-form">
							<input type="hidden" name="op_bookmark" value="delete"> <input
								type="hidden" name="bookmark_key" value="<%=bookmark.getKey()%>">
							<input type="submit" class="delete_submit" value="delete">
						</form> <a class="bookmark-header"><%=HtmlUtil.escape(bookmark.getTitle())%></a>
					</li>
					<%
						}
					%>
					<li class="bookmark">
						<form method="post"
							action="<%=request.getContextPath()%>/Access/marker"
							class="bookmark-register">
							<input type="hidden" name="op_bookmark" value="insert">
							TITLE:&nbsp;&nbsp;<input type="text" name="bookmark_title">
							URL:&nbsp;&nbsp;<input type="text" name="bookmark_url"> <input
								type="hidden" name="bookmark_category"
								value="<%=category.getKey()%>"> <input type="submit"
								value="registration">
						</form>
					</li>
				</ul>
			</li>
			<%
				}
			%>
		</ul>
	</c:param>
</c:import>