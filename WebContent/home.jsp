<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<script type="text/javascript">
<!--

function check(){
	if(window.confirm('実行します。よろしいですか？')){
		return true;
	}
	else{
		window.alert('キャンセルされました');
		return false;
	}
}

// -->
</script>

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

	<div class="main-contents">
		<a href="newpost">新規投稿</a><br />
		<a href="management">ユーザ管理</a><br />
		<a href="logout">ログアウト</a><br />
	</div>


	<form action="./" method="get">
		<label for="category">カテゴリー</label><br />
		<select name="category">
			<option value="">全カテゴリー</option>
				<c:forEach items="${categorys}" var="category">
					<c:if test="${category.category == sessionCategory}">
						<option value="${category.category}"selected>${category.category}</option>
					</c:if>
					<c:if test="${category.category != sessionCategory}">
						<option value="${category.category}">${category.category}</option>
					</c:if>
				</c:forEach>
				<c:remove var="sessionCategory" scope="session"/>
		</select><br />


		<label for="startdate">開始日</label><br />
		<c:if test="${ not empty startdates }">
			<div class="startdate">
				<input type="date"  value="${startdates}"  name="startdate" id="startdate" /><br />
			</div>
		</c:if>

		<c:if test="${ empty startdates }">
			<div class="startdate">
				<input type="date"   name="startdate" id="startdate" /><br />
			</div>
		</c:if>
		<c:remove var="startdates" scope="session"/>

		<label for="enddate">終了日</label><br />
		<c:if test="${ not empty enddates }">
			<div class="enddate">
				<input type="date"  value="${enddates}"  name="enddate" id="enddate" /><br />
			</div>
		</c:if>

		<c:if test="${ empty enddates }">
			<div class="enddate">
				<input type="date"   name="enddate" id="enddate" /><br />
			</div>
		</c:if>

		<c:remove var="enddates" scope="session"/>
		<input type="submit" value="絞込"><br />
	</form>

	<c:forEach items="${posts}" var="post">
		<div class="posts">

			<label for="subject">件名</label><br />
			<c:out value="${post.subject}"/><br />

			<label for="text">本文</label><br />
			<c:forEach items="${fn:split(post.text,'
')}" var="splitpost">
				<c:out value="${splitpost}" /><br />
			</c:forEach>

			<c:if test="${post.userId == user.id}">
				<label for="insertdate">投稿者：</label>
				<c:out value="${user.name}"/><br />
			</c:if>

				<div class="date">
					<label for="insertdate">投稿日時：</label>
					<fmt:formatDate value="${post.insertDate}"
						pattern="yyyy/MM/dd HH:mm:ss" /><br />
				</div>

				<form action="deletepost" method="post"onClick="return check()">
					<c:choose>
						<c:when test="${user.divisionId == 2}">
							<input type="hidden" name="postid" value="${post.id}"/>
							<input type="submit" value="消去">
						</c:when>

						<c:when test="${user.divisionId == 3 && user.branchId == post.branchId}">
							<input type="hidden" name="postid" value="${post.id}"/>
							<input type="submit" value="消去">
						</c:when>

						<c:when test="${post.userId == user.id}">
							<input type="hidden" name="postid" value="${post.id}"/>
							<input type="submit" value="消去">
						</c:when>
					</c:choose>
				</form>

				<c:forEach items="${comments}" var="comment">
					<form action="deletecomment" method="post" onClick="return check()">
						<c:if test="${comment.postId == post.id}">
							<label for="text">投稿コメント</label><br />
							<c:out value="${comment.text}" /><br />
							<label for="insertdate">投稿日時：</label>
							<fmt:formatDate value="${comment.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss"/><br />

							<c:choose>
								<c:when test="${user.divisionId == 2}">
										<input type="hidden" name="commentid" value="${comment.id}"/>
										<input type="submit" value="消去">
								</c:when>

								<c:when test="${user.divisionId == 3 && user.branchId == comment.branchId}">
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
					<c:out value="${sessionPostid}" /><br />
					<c:if test = "${not empty sessionComment}">
						<c:if test = "${post.id == sessionPostid}">
							<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500">${sessionComment}</textarea><br />
						</c:if>
					</c:if>

					<c:if test = "${not empty sessionComment}">
						<c:if test = "${post.id != sessionPostid}">
							<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500"></textarea><br />
						</c:if>
					</c:if>

					<c:if test = "${empty sessionComment}">
						<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500"></textarea><br />
					</c:if>

					<input type="hidden" name="postid" value="${post.id}"/>
					<input type="hidden" name="userid" value="${user.id}"/>
					<input type="hidden" name="divisionid" value="${user.divisionId}"/>
					<input type="hidden" name="branchid" value="${user.branchId}"/>
					<input type="submit" value="投稿"><br />

				</form>
			</div>
		</c:forEach>
		<c:remove var="sessionComment" scope="session"/>
		<c:remove var="sessionPostid" scope="session"/>
	</div>
</body>
</html>