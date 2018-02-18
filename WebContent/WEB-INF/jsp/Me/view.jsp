<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="common.HtmlUtil"%>
<%@ page import="me.AccountDTO"%>
<c:import url="/WEB-INF/jsp/layout.jsp">
	<c:param name="title" value="VIEW" />
	<c:param name="css" value="/Me/view.css" />
	<c:param name="javascript" value="/Me/view.js" />
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
		<div id="control">
			<button id="edit-mode">&gt;&gt;&nbsp;&nbsp;&nbsp;edit</button>
			<button id="new-account"
				onclick="location.href='<%=request.getContextPath()%>/Me/editor'">
				&gt;&gt;&nbsp;&nbsp;&nbsp;new&nbsp;account</a>
			</button>
		</div>
		<table id="view-table">
			<thead>
				<tr>
					<th class="edit-column"></th>
					<th>TITLE</th>
					<th>ID</th>
					<th>PASSWORD</th>
					<th>E-MAIL</th>
					<th>MEMO</th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<AccountDTO> accounts = (ArrayList<AccountDTO>) request.getAttribute("accounts");
							for (int i = 0; i < accounts.size(); i++) {
								AccountDTO account = accounts.get(i);
				%>
				<tr>
					<td class="edit-column">
						<form method="post" action="<%=request.getContextPath()%>/Me/view"
							class="edit-form">
							<input type="hidden" name="account_key"
								value="<%=account.getKey()%>"><input type="hidden"
								name="op" value="delete"><input type="submit"
								class="delete_submit" value="delete">
						</form>
						<form method="post"
							action="<%=request.getContextPath()%>/Me/editor"
							class="edit-form">
							<input type="hidden" name="account_key"
								value="<%=account.getKey()%>"><input type="hidden"
								name="op" value="update"><input type="submit"
								class="update_submit" value="update">
						</form>
					</td>
					<td><a href="<%=HtmlUtil.escapeQuot(account.getUrl())%>"><%=HtmlUtil.escape(account.getTitle())%></a></td>
					<td><%=HtmlUtil.escape(account.getId())%></td>
					<td><%=HtmlUtil.escape(account.getPassword())%></td>
					<td><%=HtmlUtil.escape(account.getEmail())%></td>
					<td><%=HtmlUtil.escape(account.getMemo())%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</c:param>
</c:import>