<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>新規投稿</title>
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

	<form action="newpost" method="post">
		<input type="hidden" name="userid" value="${user.id}" />
		<input type="hidden" name="divisionid" value="${user.divisionId}" />
		<input type="hidden" name="branchid" value="${user.branchId}" />

		<strong>カテゴリー</strong>
		<label for="category">(10文字以下)</label><br />
		<c:if test="${ not empty sessionCategory }">
			<input name ="category" value="${sessionCategory}" id="input" ><br />
		</c:if>

		<c:if test="${ empty sessionCategory }">
			<input name ="category" id="input" ><br />
		</c:if>

		<c:remove var="sessionCategory" scope="session"/>

		<strong>件名</strong>
		<label for="subject">(30文字以下)</label><br />
		<c:if test="${ not empty sessionSubject }">
			<input name="subject" value="${sessionSubject}" id="input"  /><br />
		</c:if>

		<c:if test="${ empty sessionSubject }">
			<input name="subject" id="input" /><br />
		</c:if>
		<c:remove var="sessionSubject" scope="session"/>

		<strong>本文</strong>
		<label for="text">(1000文字以下)</label><br />
		<c:if test="${ not empty sessionText }">
			<textarea name="text" cols="50" rows="10" class="tweet-box" maxlength="1000" wrap="hard" >${sessionText}</textarea><br />
		</c:if>
		<c:if test="${empty sessionText }">
			<textarea name="text" cols="50" rows="10" class="tweet-box" maxlength="1000" wrap="hard" ></textarea><br />
		</c:if>
		<c:remove var="sessionText" scope="session"/>
		<input type="submit" value="投稿"><br />
		<a href="./">戻る</a>
	</form>
	</div>
</body>
</html>