<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名簿|新規登録</title>
</head>
<body>
<form action="/meibo/Create" method="post">
名前:<input type="text" name="name"><br>
かな:<input type="text" name="kana"><br>
email:<input type="email" name="email"><br>
年齢:<input type="number" name="age"><br>
<button type="submit">登録</button>
</form>
</body>
</html>