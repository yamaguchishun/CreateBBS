<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<div class="main-contents">
	<div class="link">
		<br /><a href="signup">新規登録</a><br /><br />
	</div>

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

	<div class="view">
		<table class="u-full-width">
			<thead>
				<tr>
    				<th>アカウント名</th>
    				<th>名前</th>
    				<th>支店名</th>
    				<th>部署/役職名</th>
    				<th>ユーザ編集</th>
    				<th>アカウント停止/復活</th>
 				</tr>
 			</thead>

 				<c:forEach items="${users}" var="user">
 					<tbody>
						<tr>
    						<td><input readonly name="account" value="${user.account}" id="output" /></td>
    						<td><input readonly name="name" value="${user.name}" id="output" /></td>

							<c:forEach items="${branches}" var="branch">
								<c:if test="${user.branchId== branch.id}">
    								<td><input readonly name="branch" value="${branch.name}" id="output" /></td>
    							</c:if>
    						</c:forEach>

    						<c:forEach items="${divisions}" var="division">
								<c:if test="${user.divisionId== division.id}">
    								<td><input readonly name="division" value="${division.name}" id="output" /></td>
    							</c:if>
    						</c:forEach>

    					<td><form action="edit" method="get"><input type="submit" value="編集"/>
    					<input type="hidden" name="userid" value="${user.id}" /></form></td>
							<td>
    							<form action="status" method="post" onSubmit="return check()">
    								<input type="hidden" name="userid" value="${user.id}">
									<input type="hidden" name="isworking" value="${user.isWorking}">

									<c:if test="${user.id != sessionUser.id}">
										<c:if test="${user.isWorking == 0}">
											<button class="stop" type="submit"name="status">停止</button>
										</c:if>
									</c:if>

									<c:if test="${user.id == sessionUser.id}">
										<c:if test="${user.isWorking == 0}">
											<input type="submit"name="status" disabled value="-"/>
										</c:if>
									</c:if>

									<c:if test="${user.isWorking == 1}">
										<button class="start" type="submit"name="status">復活</button>
									</c:if>
								</form>
							</td>
    					</tr>
    				</tbody>
    			</c:forEach>
			</table>
			</div>
		</div>
		<a href="./">戻る</a>
	</body>
</html>