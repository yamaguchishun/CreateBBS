<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
</head>
<body>

	<div class="messages">

	<div class="main-contents">
		<a href="newpost">新規投稿</a><br />
		<a href="management">ユーザ管理</a><br />
		<a href="logout">ログアウト</a><br /><br /><br />
	</div>

		<c:forEach items="${messages}" var="message">
			<div class="message">
				<form action="home" method="post">
					<label for="subject">件名</label><br />
					<textarea name="subject" cols="30" rows="1" class="tweet-box"
						maxlength="30" readonly>
						<c:out value="${message.subject}"  />
					</textarea><br />

					<label for="text">本文</label><br />
					<textarea name="text" cols="100" rows="10" class="tweet-box"
						maxlength="1000" readonly>
						<c:out value="${message.text}" />
					</textarea><br />

					<input type="hidden" name="postid" value="${message.id}" />

					<div class="date">
						<label for="insertdate">投稿日時：</label>
						<fmt:formatDate value="${message.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss" /><br />
					</div>

					<c:forEach items="${comments}" var="comments">
						<c:if test="${comments.postID == message.id}">
							<label for="text">投稿コメント</label><br />
							<textarea name="postcomment" cols="50" rows="3" class="tweet-box"
							maxlength="500" readonly>
								<c:out value="${comments.text}" />
							</textarea><br />

							<label for="insertdate">投稿日時：</label>
							<fmt:formatDate value="${comments.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss" /><br /><br />
						</c:if>
					</c:forEach>

					<label for="text">新規コメント</label><br />
					<textarea name="comment" cols="50" rows="3" class="tweet-box"
							maxlength="500">
					</textarea><br />
					<input type="submit" value="投稿"><br /><br /><br />
				</form>
			</div>
		</c:forEach>
	</div>

	<br />

</body>
</html>