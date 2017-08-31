<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ管理</title>
</head>
<body>

<div class="managements">
		<c:forEach items="${users}" var="user">
			<div class="management">
				<form action="edit" method="get">
					<input type="hidden" name="userid" value="${user.id}" />
					<table border="1">
						<tr>
    						<th>名前</th>
    						<th>アカウント名</th>
    						<th>支店名</th>
    						<th>部署名</th>
    						<th>パスワード</th>
    						<th>ステータス</th>
    						<th>登録日</th>
    						<th>更新日</th>
 						</tr>
						<tr>
    						<td><c:out value="${user.name}" /></td>
    						<td><c:out value="${user.account}" /></td>
    						<td><c:out value="${user.branchID}" /></td>
    						<td><c:out value="${user.divisionID}" /></td>
    						<td>****</td>
    						<td><c:out value="${user.divisionID}" /></td>
    						<td><c:out value="${user.insertDate}" /></td>
    						<td><c:out value="${user.updateDate}" /></td>
  						</tr>
					</table>
				<input type="submit" value="編集" /> <br />
				</form>
			</div>
		</c:forEach>
		<a href="home">戻る</a>
	</div>

</body>
</html>