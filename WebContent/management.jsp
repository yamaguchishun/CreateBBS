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

<script type="text/javascript">
<!--

function check(){
	if(window.confirm('変更を行います。よろしいですか？')){
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
<a href="signup">新規登録</a>
<a href="./">戻る</a>

<div class="management">

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

		<c:forEach items="${users}" var="user">
				<table border="1">
					<tr>
    					<th>名前</th>
    					<th>アカウント名</th>
    					<th>支店名</th>
    					<th>部署名</th>
    					<th>ユーザ編集</th>
    					<th>アカウント停止/復活</th>
 					</tr>
					<tr>
    					<td><input type=text readonly name="name" value="${user.name}" /></td>
    					<td><input type=text readonly name="account" value="${user.account}" /></td>

						<c:forEach items="${branches}" var="branch">
							<c:if test="${user.branchId== branch.id}">
    							<td><input type=text readonly name="branch" value="${branch.name}" /></td>
    						</c:if>
    					</c:forEach>

    					<c:forEach items="${divisions}" var="division">
							<c:if test="${user.divisionId== division.id}">
    							<td><input type=text readonly name="branch" value="${division.name}" /></td>
    						</c:if>
    					</c:forEach>

						<c:if test="${user.id == sessionUser.id}">
    						<td><form action="edit" method="get"><input type="submit" value="編集" disabled/>
    						<input type="hidden" name="userid" value="${user.id}" /></form></td>
    					</c:if>

    					<c:if test="${user.id != sessionUser.id}">
    						<td><form action="edit" method="get"><input type="submit" value="編集"/>
    						<input type="hidden" name="userid" value="${user.id}" /></form></td>
    					</c:if>

    					<td><form action="status" method="post" onSubmit="return check()">
    						<input type="hidden" name="userid" value="${user.id}">
							<input type="hidden" name="isworking" value="${user.isWorking}">

							<c:if test="${user.id != sessionUser.id}">
								<c:if test="${user.isWorking == 0}">
									<input type="submit"name="status" value="停止"/>
								</c:if>
							</c:if>

							<c:if test="${user.id == sessionUser.id}">
								<c:if test="${user.isWorking == 0}">
									<input type="submit"name="status" disabled value="停止"/>
								</c:if>
							</c:if>

							<c:if test="${user.isWorking == 1}">
								<input type="submit"name="status" value="復活"/>
							</c:if>
							</form>
    					</td>
    				</tr>
				</table>
		</c:forEach>
	</div>
</body>
</html>