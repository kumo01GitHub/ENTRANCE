<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="common.HtmlUtil"%>
<%@ page import="me.AccountDTO"%>
<%@ page import="me.EmailDTO"%>
<c:import url="/WEB-INF/jsp/layout.jsp">
	<c:param name="title" value="EDITOR" />
	<c:param name="css" value="/Me/editor.css" />
	<c:param name="javascript" value="/Me/editor.js" />
	<c:param name="mainContents">
		<%
			ArrayList<EmailDTO> emails = (ArrayList<EmailDTO>) request.getAttribute("emails");
		%>
		<div id="wrapper_account">
			<div id="emailbar">
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
				<table id="email-table">
					<thead>
						<tr>
							<th></th>
							<th>NAME</th>
							<th>ADDRESS</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < emails.size(); i++) {
										EmailDTO email = emails.get(i);
						%>
						<tr>
							<td><form method="post"
									action="<%=request.getContextPath()%>/Me/editor">
									<input type="hidden" name="op"
										value="<%=request.getParameter("op")%>"> <input
										type="hidden" name="account_key"
										value="<%=request.getParameter("account_key")%>"> <input
										type="hidden" name="op_email" value="delete"> <input
										type="hidden" name="email_key" value="<%=email.getKey()%>">
									<input type="submit" value="delete" class="delete_submit">
								</form></td>
							<td><%=HtmlUtil.escape(email.getName())%></td>
							<td><%=HtmlUtil.escape(email.getAddress())%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<form method="post" action="<%=request.getContextPath()%>/Me/editor"
					id="email-form">
					<input type="hidden" name="op"
						value="<%=request.getParameter("op")%>"> <input
						type="hidden" name="account_key"
						value="<%=request.getParameter("account_key")%>"> <input
						type="hidden" name="op_email" value="insert">
					<p>
						NAME:<br> <input type="text" name="email_name">
					</p>
					<p>
						ADDRESS:<br> <input type="text" name="email_address">
					</p>
					<p>
						MEMO:<br> <input type="text" name="email_memo">
					</p>
					<input type="submit" value="registration">
				</form>
			</div>

			<form method="post" id="account-form"
				action="<%=request.getContextPath()%>/Me/view">
				<input type="hidden" name="op"
					value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("op"))%>">
				<input type="hidden" name="account_key"
					value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("account_key"))%>">
				<table id="account-table">
					<tbody>
						<tr>
							<td class="left">TITLE</td>
							<td><input type="text" name="account_title"
								value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("account_title"))%>"></td>
						</tr>
						<tr>
							<td class="left">URL</td>
							<td><input type="text" name="account_url"
								value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("account_url"))%>"></td>
						</tr>
						<tr>
							<td class="left">ID</td>
							<td><input type="text" name="account_id"
								value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("account_id"))%>"></td>
						</tr>
						<tr>
							<td class="left">PASSWORD</td>
							<td><input type="text" name="account_password"
								value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("account_password"))%>"></td>
						</tr>
						<tr>
							<td class="left">E-MAIL</td>
							<td><input type="text" name="account_email_text"
								value="<%=HtmlUtil.escapeQuot((String) request.getAttribute("account_email"))%>"><br>
								<select name="account_email_select">
									<%
										for (int i = 0; i < emails.size(); i++) {
													EmailDTO email = emails.get(i);
									%>
									<option
										value="<%=HtmlUtil.escapeQuot((String) email.getAddress())%>"><%=HtmlUtil.escape(email.getAddress())%></option>
									<%
										}
									%>
									<option value="other" selected>other</option>
							</select><input type="hidden" name="account_email"></td>
						</tr>
						<tr>
							<td class="left">MEMO</td>
							<td><textarea name="account_memo"><%=request.getAttribute("account_memo")%></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" name="account_registration"
					value="registration" id="account-registration">
			</form>
		</div>
	</c:param>
</c:import>