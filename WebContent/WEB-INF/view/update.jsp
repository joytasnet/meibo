<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
Member member=(Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名簿|更新</title>
</head>
<body>
<form action="/meibo/Update" method="post">
名前:<input type="text" name="name" value="<%=member.getName()%>"><br>
かな:<input type="text" name="kana" value="<%=member.getKana()%>"><br>
email:<input type="email" name="email" value="<%=member.getEmail()%>"><br>
年齢:<input type="number" name="age" value="<%=member.getAge()%>"><br>
<input type="hidden" name="id" value="<%=member.getId() %>">
<button type="submit">更新</button>
</form>
</body>
</html>