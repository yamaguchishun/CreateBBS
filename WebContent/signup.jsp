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
<title>新規登録</title>
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

<form action="signup" method="post"><br />

<div class="box">
	<strong>ログインID</strong><br />
	<label for="account">(半角英数字6～20文字)</label><br />
	<input name="account" value="${newUser.account}" id="input" /><br />


	<strong>パスワード</strong><br />
	<label for="password">(半角文字6～20文字)</label><br />
	<input name="password" type="password" id="password"/><br />


	<strong>パスワード(確認用)</strong><br />
	<label for="confirmation">(パスワードを再入力して下さい)</label><br />
	<input name="confirmation" type="password" id="input"/><br />

	<strong>名前</strong>
	<label for="name">(10文字以内)</label><br />
	<input name="name" value="${newUser.name}" id="input"/><br />


	<strong>支店名</strong><br />
	<c:if test="${ not empty newUser }">
		<select name="branch">
			<c:forEach items="${branches}" var="branch">
				<c:choose>
					<c:when test="${newUser.branchId == branch.id}">
						<option value="${branch.id}"selected>${branch.name}</option>
					</c:when>

					<c:when test="${newUser.branchId != branch.id}">
						<option value="${branch.id}">${branch.name}</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select><br />
	</c:if>

	<c:if test="${ empty newUser }">
		<select name="branch">
			<c:forEach items="${branches}" var="branch">
				<option value="${branch.id}">${branch.name}</option>
			</c:forEach>
		</select><br />
	</c:if>

	<strong>部署名</strong><br />
	<c:if test="${ not empty newUser }">
		<select name="division">
						<c:forEach items="${divisions}" var="division">
							<c:choose>
								<c:when test="${newUser.divisionId == division.id}">
									<option value="${division.id}"selected>${division.name}</option>
								</c:when>

					<c:when test="${newUser.divisionId != division.id}">
						<option value="${division.id}">${division.name}</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select><br />
	</c:if>

	<c:if test="${ empty newUser }">
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
	</div>

	<input type="submit" value="登録" /> <br />
	<a href="management">戻る</a>
	<c:remove var="newUser" scope="session"/>
</form>
</div>
</body>
</html>