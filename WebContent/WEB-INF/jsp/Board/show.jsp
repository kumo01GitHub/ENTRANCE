<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="common.HtmlUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="board.Panel"%>
<%@ page import="board.ShowTable"%>
<c:import url="/WEB-INF/jsp/layout.jsp">
	<c:param name="title" value="SHOW" />
	<c:param name="css" value="/Board/show.css" />
	<c:param name="javascript" value="/Board/show.js" />
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
		<form id="poster" method="post"
			action="<%=request.getContextPath()%>/Board/show">
			<input type="hidden" name="op" value="insert">
			<textarea name="panel_posting"></textarea>
			<br> <select name="panel_size">
				<option value="S">small</option>
				<option value="W">wide</option>
				<option value="L">large</option>
			</select> <input type="submit" value="post">
		</form>
		<table id="show-table">
			<thead>
				<tr>
					<%
						for (int i = 0; i < ShowTable.WIDTH; i++) {
					%><th></th>
					<%
						}
					%>
				</tr>
			</thead>
			<tbody>
				<%
					ShowTable showTable = (ShowTable) request.getAttribute("showTable");
							for (int i = 0; i < showTable.getHeight(); i++) {
								ArrayList<Panel> row = showTable.getRow(i);
				%>
				<tr>
					<%
						for (int j = 0; j < row.size(); j++) {
										Panel panel = row.get(j);
					%>
					<td class="tile" colspan="<%=panel.getWidth()%>"
						rowspan="<%=panel.getHeight()%>">
						<form class="panel-form" method="post"
							action="<%=request.getContextPath()%>/Board/show">
							<input type="hidden" name="op" value="delete"> <input
								type="hidden" name="panel_key" value="<%=panel.getKey()%>">
							<input type="submit" class="delete_submit" value="">
						</form>
						<form class="panel-form" method="post"
							action="<%=request.getContextPath()%>/Board/rewrite">
							<input type="hidden" name="op" value="update"> <input
								type="hidden" name="panel_key" value="<%=panel.getKey()%>">
							<input type="submit" class="update_submit" value="">
						</form>
						<p class="posting"><%=HtmlUtil.escape(panel.getPosting())%></p>
					</td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</c:param>
</c:import>