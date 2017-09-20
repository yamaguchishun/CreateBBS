<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/styles.css">
<link rel="stylesheet" type="text/css" href="./css/backgrounds.css">
<link rel="stylesheet" type="text/css" href="./css/buttons.css">
<link rel="stylesheet" type="text/css" href="./css/forms.css">y
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
		<c:if test="${sessionUser.divisionId == 1 }">
		<a href="management">ユーザ管理</a><br />
		</c:if>
		<a href="logout">ログアウト</a><br /><br />
	</div>


	<form action="./" method="get">
		<em>カテゴリー</em><br />
		<select name="category">
			<option value="">--こちらから選択して下さい--</option>
				<c:forEach items="${categorys}" var="category">
					<c:if test="${category.category == sessionCategory}">
						<option value="${category.category}"selected><c:out value="${category.category}"/></option>
					</c:if>
					<c:if test="${category.category != sessionCategory}">
						<option value="${category.category}"><c:out value="${category.category}"/></option>
					</c:if>
				</c:forEach>
			<c:remove var="sessionCategory" scope="session"/>
		</select><br />


		<em>開始日</em><br />
		<c:if test="${ not empty startdates }">
			<input type="date"  value="${startdates}"  name="startdate" id="startdate" /><br />
		</c:if>

		<c:if test="${ empty startdates }">
			<input type="date"   name="startdate" id="startdate" /><br />
		</c:if>
		<c:remove var="startdates" scope="session"/>

		<em>終了日</em><br />
		<c:if test="${ not empty enddates }">
			<input type="date"  value="${enddates}"  name="enddate" id="enddate" /><br />
		</c:if>

		<c:if test="${ empty enddates }">
			<div class="enddate">
				<input type="date"   name="enddate" id="enddate" /><br />
			</div>
		</c:if>

		<c:remove var="enddates" scope="session"/>
		<input type="submit" value="絞込"><br /><br />
	</form>

	<c:forEach items="${posts}" var="post">
		<div class="posts">
			<div class="text">
				<strong>カテゴリー</strong><br />
				<input value="${post.category}" id="subject" /><br />

				<strong>件名</strong><br />
				<input value="${post.subject}" id="subject" /><br />

				<strong>本文</strong><br />
				<textarea cols="50" rows="3" maxlength="1000" >${post.text}</textarea><br />

				<c:forEach items="${users}" var="user">
					<c:if test="${post.userId == user.id}">
						<em>投稿者：${user.name}</em>
					</c:if>
				</c:forEach>

				<div class="date">
					<em>投稿日時：${post.insertDate}</em><br />
				</div>
			</div>

				<form action="deletepost" method="post"onClick="return check()">
					<c:choose>
						<c:when test="${sessionUser.divisionId == 2}">
							<input type="hidden" name="postid" value="${post.id}"/>
							<input type="submit" value="メッセージ削除">
						</c:when>

						<c:when test="${sessionUser.divisionId == 3 && sessionUser.branchId == post.branchId}">
							<input type="hidden" name="postid" value="${post.id}"/>
							<input type="submit" value="メッセージ削除">
						</c:when>

						<c:when test="${post.userId == sessionUser.id}">
							<input type="hidden" name="postid" value="${post.id}"/>
							<input type="submit" value="メッセージ削除">
						</c:when>
					</c:choose>
				</form>
		</div>



				<c:forEach items="${comments}" var="comment">
						<c:if test="${comment.postId == post.id}">
							<br /><strong>投稿コメント</strong><br />


							<textarea cols="50" rows="3" maxlength="500" >${comment.text}</textarea><br />

							<c:forEach items="${users}" var="user">
								<c:if test="${comment.userId == user.id }">
									<em>投稿者名：${user.name }</em><br />
								</c:if>
							</c:forEach>

							<em>投稿日時：${comment.insertDate}</em><br />

							<form action="deletecomment" method="post" onClick="return check()">
							<c:choose>
								<c:when test="${sessionUser.divisionId == 2}">
									<input type="hidden" name="commentid" value="${comment.id}"/>
									<input type="submit" value="コメント削除"><br />
								</c:when>

								<c:when test="${sessionUser.divisionId == 3 && sessionUser.branchId == comment.branchId}">
									<input type="hidden" name="commentid" value="${comment.id}"/>
									<input type="submit" value="コメント削除"><br />
								</c:when>

								<c:when test="${comment.userId == sessionUser.id}">
									<input type="hidden" name="commentid" value="${comment.id}" />
									<input type="submit" value="コメント削除"><br />
								</c:when>
							</c:choose>
							</form>
						</c:if>

				</c:forEach>

				<br />
				<form action="comment" method="post">
					<strong>新規コメント</strong><br />
					<c:if test = "${not empty sessionComment}">
						<c:if test = "${post.id == sessionPostid}">
							<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500" wrap="hard">${sessionComment}</textarea><br />
						</c:if>
					</c:if>

					<c:if test = "${not empty sessionComment}">
						<c:if test = "${post.id != sessionPostid}">
							<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500" wrap="hard"></textarea><br />
						</c:if>
					</c:if>

					<c:if test = "${empty sessionComment}">
						<textarea name="comment" cols="50" rows="3" class="tweet-box" maxlength="500" wrap="hard"></textarea><br />
					</c:if>

					<input type="hidden" name="postid" value="${post.id}"/>
					<input type="hidden" name="userid" value="${sessionUser.id}"/>
					<input type="hidden" name="divisionid" value="${sessionUser.divisionId}"/>
					<input type="hidden" name="branchid" value="${sessionUser.branchId}"/>
					<input type="submit" value="コメント投稿"><br /><br />
				</form>
			</c:forEach>
		<c:remove var="sessionComment" scope="session"/>
		<c:remove var="sessionPostid" scope="session"/>
	</div>
</body>
</html>