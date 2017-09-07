<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ編集</title>
</head>
<body>

<div class="edit">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>

		<c:forEach items="${users}" var="user">
			<form action="edit" method="post">
				<input type="hidden" name="userid" value="${user.id}" />
				<label for="name">名前</label>
				<input name="name" value="${user.name}" id="name"/><br />

				<label for="account">ログインID</label>
				<input name="account" value="${user.account}" id="account" /><br />

				<label for="password">パスワード</label>
				<input type="password" name="password" id="password"/><br />

				<label for="confirmation">パスワード(確認用)</label>
				<input type="password" name="confirmation" id="confirmation"/> <br />

				<label for="branch">支社名</label>
				<select name="branch">
					<c:forEach items="${branches}" var="branch">
						<c:choose>
							<c:when test="${user.branchId == branch.id}">
								<option value="${branch.id}"selected>${branch.name}</option>
							</c:when>

							<c:when test="${user.branchId != branch.id}">
								<option value="${branch.id}">${branch.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select><br />

				<label for="division">部署名</label>
				<select name="division">
					<c:forEach items="${divisions}" var="division">
						<c:choose>
							<c:when test="${user.divisionId == division.id}">
								<option value="${division.id}"selected>${division.name}</option>
							</c:when>

							<c:when test="${user.divisionId != division.id}">
								<option value="${division.id}">${division.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select><br />

				<input type="submit" value="更新" /> <br />
			</form>
		</c:forEach>

	<a href="management">戻る</a>
</div>

</body>
</html>