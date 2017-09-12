<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/schema.css">
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

		<label for="category">カテゴリー</label>
		<label for="category">(10文字以下)</label><br />
		<c:if test="${ not empty sessionCategory }">
			<input type="text" name ="category" value="${sessionCategory}"><br />
		</c:if>

		<c:if test="${ empty sessionCategory }">
			<input type="text" name ="category"><br />
		</c:if>

		<c:remove var="sessionCategory" scope="session"/>

		<label for="subject">件名</label>
		<label for="subject">(30文字以下)</label><br />
		<c:if test="${ not empty sessionSubject }">
			<input type="text" name="subject" value="${sessionSubject}"  /><br />
		</c:if>

		<c:if test="${ empty sessionSubject }">
			<input type="text" name="subject" /><br />
		</c:if>
		<c:remove var="sessionSubject" scope="session"/>

		<label for="text">本文</label>
		<label for="text">(1000文字以下)</label><br />
		<c:if test="${ not empty sessionText }">
			<textarea name="text" cols="50" rows="10" class="tweet-box" maxlength="1000">${sessionText}</textarea><br />
		</c:if>
		<c:if test="${empty sessionText }">
			<textarea name="text" cols="50" rows="10" class="tweet-box" maxlength="1000"></textarea><br />
		</c:if>
		<c:remove var="sessionText" scope="session"/>
		<input type="submit" value="投稿">
		<a href="./">戻る</a>
	</form>
	</div>
</body>
</html>