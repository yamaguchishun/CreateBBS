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

	<c:if test="${ not empty errorcomments }">
		<div class="errors">
			<ul>
				<c:forEach items="${errorcomments}" var="errorcomment">
					<li><c:out value="${errorcomment}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorcomments" scope="session"/>
	</c:if>

	<c:if test="${ not empty errormanagements }">
		<div class="errormanagementss">
			<ul>
				<c:forEach items="${errormanagements}" var="errormanagement">
					<li><c:out value="${errormanagement}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errormanagements" scope="session"/>
	</c:if>

	<div class="main-contents">
		<a href="newpost">新規投稿</a><br />
		<a href="management">ユーザ管理</a><br />
		<a href="logout">ログアウト</a><br />
	</div>

	<label for="category">カテゴリー</label><br />
	<form action="index" method="get">
		<select name="category">
			<option value=""></option>
				<c:forEach items="${categorys}" var="category">
					<option value="${category.category}">${category.category}</option>
				</c:forEach>
		</select><br />

		<label for="startdate">開始日</label><br />
		<input type="date"  name="startdate" id="startdate" /><br />

		<label for="enddate">終了日</label><br />
		<input type="date" name="enddate" id="enddate"/> <br />
		<input type="submit" value="絞込"><br />
	</form>

		<c:forEach items="${posts}" var="post">
			<div class="posts">

				<label for="subject">件名</label><br />
				<input type="text" name="subject" readonly value="${post.subject}"><br />

				<label for="text">本文</label><br />
				<textarea name="text" cols="100" rows="10" class="tweet-box"
				maxlength="1000" readonly  align="left"><c:out value="${post.text}" /></textarea><br />

				<c:if test="${post.userId == user.id}">
					<label for="insertdate">投稿者：</label>
					<c:out value="${user.name}"/><br />
				</c:if>

				<div class="date">
					<label for="insertdate">投稿日時：</label>
					<fmt:formatDate value="${post.insertDate}"
						pattern="yyyy/MM/dd HH:mm:ss" /><br />
				</div>


				<form action="deletepost" method="post">
					<c:choose>
						<c:when test="${user.divisionId == 3}">
							<input type="hidden" name="postid" value="${post.id}" />
							<input type="submit" value="消去">
						</c:when>

						<c:when test="${user.divisionId == 4 && user.branchId == post.branchId}">
							<input type="hidden" name="postid" value="${post.id}" />
							<input type="submit" value="消去">
						</c:when>

						<c:when test="${post.userId == user.id}">
							<input type="hidden" name="postid" value="${post.id}" />
							<input type="submit" value="消去">
						</c:when>
					</c:choose>
				</form>

				<c:forEach items="${comments}" var="comment">
					<form action="deletecomment" method="post">
						<c:if test="${comment.postId == post.id}">
							<label for="text">投稿コメント</label>
							<br />
							<textarea name="postcomment" cols="50" rows="3" class="tweet-box"
							readonly><c:out value="${comment.text}" /></textarea><br />
							<label for="insertdate">投稿日時：</label>
							<fmt:formatDate value="${comment.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss" />
							<br />

							<c:choose>
								<c:when test="${user.divisionId == 3}">
										<input type="hidden" name="commentid" value="${comment.id}"/>
										<input type="submit" value="消去">
								</c:when>

								<c:when test="${user.divisionId == 4 && user.branchId == comment.branchId}">
										<input type="hidden" name="commentid" value="${comment.id}"/>
										<input type="submit" value="消去">
								</c:when>

								<c:when test="${comment.userId == user.id}">
									<input type="hidden" name="commentid" value="${comment.id}" />
									<input type="submit" value="消去">
								</c:when>
							</c:choose>

						</c:if>
					</form>
				</c:forEach>

				<form action="comment" method="post">
					<label for="text">新規コメント</label><br />
					<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500"></textarea><br />
					<input type="hidden" name="postid" value="${post.id}" />
					<input type="hidden" name="userid" value="${user.id}" />
					<input type="hidden" name="divisionid" value="${user.divisionId}" />
					<input type="hidden" name="branchid" value="${user.branchId}" />
					<input type="submit" value="投稿"><br />
				</form>
			</div>
		</c:forEach>
	</div>
</body>
</html>