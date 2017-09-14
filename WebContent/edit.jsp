<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/styles.css">
<link rel="stylesheet" type="text/css" href="./css/backgrounds.css">
<link rel="stylesheet" type="text/css" href="./css/buttons.css">
<link rel="stylesheet" type="text/css" href="./css/forms.css">
<link rel="stylesheet" type="text/css" href="./css/tables.css">
<link rel="stylesheet" type="text/css" href="./css/breadcrumbs.css">
<link rel="stylesheet" type="text/css" href="./css/responsive.css">
<link rel="stylesheet" type="text/css" href="./css/workless.css">
<link rel="stylesheet" type="text/css" href="./css/plugins.css">
<link rel="stylesheet" type="text/css" href="./css/helpers.css">
<link rel="stylesheet" type="text/css" href="./css/alerts.css">
<link rel="stylesheet" type="text/css" href="./css/pagination.css">
<link rel="stylesheet" type="text/css" href="./css/font.css">
<link rel="stylesheet" type="text/css" href="./css/scaffolding.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ編集</title>
</head>
<body>

<div class="main-contents">
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

		<form action="edit" method="post">
			<strong>ログインID</strong>
			<label for="account">(半角英数字6～20文字)</label><br />
			<input name="account" value="${user.account}" id="input" /><br />

			<strong>パスワード</strong>
			<label for="password">(半角文字6～20文字)</label><br />
			<input type="password" name="password" id="password"/><br />

			<Strong>パスワード(確認用)</strong>
			<label for="confirmation">(パスワードを再入力して下さい)</label><br />
			<input type="password" name="confirmation" id="confirmation"/><br />

			<input type="hidden" name="userid" value="${user.id}" />

			<strong>名前</strong>
			<label for="name">(10文字以内)</label><br />
			<input name="name" value="${user.name}"id="input"/><br />

			<strong>支店名</strong><br />
			<c:if test="${user.id == sessionUser.id }">
				<select name="branch">
					<c:forEach items="${branches}" var="branch">
						<c:if test="${user.branchId == branch.id}">
							<option value="${branch.id}" >${branch.name}</option>
						</c:if>
					</c:forEach>
				</select><br />
			</c:if>

			<c:if test="${user.id != sessionUser.id }">
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
			</c:if>

			<strong>部署/役職名</strong><br />
			<c:if test="${user.id == sessionUser.id }">
				<select name="division">
					<c:forEach items="${divisions}" var="division">
						<c:if test="${user.divisionId == division.id}">
							<option value="${division.id}"selected>${division.name}</option>
						</c:if>
					</c:forEach>
				</select><br />
			</c:if>

			<c:if test="${user.id != sessionUser.id }">
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
			</c:if>

			<c:remove var="sessionUser" scope="session"/>
			<input type="submit" value="更新" /> <br />
		</form>
		<a href="management">戻る</a>
</div>

</body>
</html>