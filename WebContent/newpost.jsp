<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
</head>
<body>
	<div class="form-area">

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

		<label for="category">カテゴリー</label><br />
		<input type="text" name ="category"><br />

		<label for="subject">件名</label><br />
		<input type="text" name="subject" /><br />

		<label for="text">本文</label><br />
		<textarea name="text" cols="100" rows="15" class="tweet-box" maxlength="1000"></textarea><br />
		<input type="submit" value="投稿">
		<a href="./">戻る</a>
	</form>
	</div>
</body>
</html>