<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ編集</title>
</head>
<body>

<div class="editss">
		<c:forEach items="${users}" var="user">
			<div class="edit">
				<form action="edit" method="post">
					<label for="name">名前</label>
					<input name="name" value="${user.name}" id="name"/><br />

					<label for="account">アカウント名</label>
					<input name="account" value="${user.account}" id="account" /><br />

					<label for="password">パスワード</label>
					<input name="password" value="${user.password}" id="password"/> <br />

					<label for="branch">支店名</label>
					<input name="branch" value="${user.branchID}" id="branch"/> <br />

					<label for="division">部署</label>
					<input name="division" value="${user.divisionID}" id="division"/> <br />
					<input type="submit" value="更新" /> <br />
				</form>
			</div>
		</c:forEach>
		<a href="./">戻る</a>
	</div>

</body>
</html>