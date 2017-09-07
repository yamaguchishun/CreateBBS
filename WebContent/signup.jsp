<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<label for="name">名前</label>
	<input name="name" value="${newUser.name}" id="name"/><br />

	<label for="account">ログインID</label>
	<input name="account" value="${newUser.account}" id="account" /><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="password">パスワード(確認用)</label>
	<input name="confirmation" type="password" id="confirmation"/> <br />

	<label for="division">部署名</label>
		<select name="division">
			<c:forEach items="${divisions}" var="division">
				<option value="${division.id}">${division.name}</option>
			</c:forEach>
		</select><br />

	<label for="branch">支社名</label>
		<select name="branch">
			<c:forEach items="${branches}" var="branch">
				<option value="${branch.id}">${branch.name}</option>
			</c:forEach>
	</select><br />
	<input type="submit" value="登録" /> <br />
	<a href="management">戻る</a>
</form>
</div>
</body>
</html>