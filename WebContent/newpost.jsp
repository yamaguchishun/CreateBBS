<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
</head>
<body>
	<div class="form-area">
		<label for="category">カテゴリー</label><select name="category">
			<option value="サンプル1">サンプル1</option>
			<option value="サンプル2">サンプル2</option>
			<option value="サンプル3">サンプル3</option>
		</select>
	</div>
	<br /><form action="newpost" method="post">
		<label for="subject">件名</label><br />
		<textarea name="subject" cols="100" rows="1" class="tweet-box"
			maxlength="30"></textarea><br />

		<label for="text">本文</label><br />
		<textarea name="text" cols="100" rows="10" class="tweet-box"
			maxlength="1000"></textarea>
		<br />
		<input type="submit" value="投稿"> <a href="home">戻る</a>
	</form>
	</div>
</body>
</html>