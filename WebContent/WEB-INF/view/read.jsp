<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
List<Member> list=(List<Member>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名簿</title>
</head>
<body>
<p><a href="/meibo/Create">新規登録</a></p>
<p><a href="/meibo/ReadCSV">csvから読み込む</a>&nbsp;<a href="/meibo/Write">csvで出力</a>
<%if(list.size()>0){%>
<table border="1">
<%for(Member m:list){ %>
<tr>
<td><%=m.getId() %></td>
<td><%=m.getName() %></td>
<td><%=m.getKana() %></td>
<td><%=m.getEmail() %></td>
<td><%=m.getAge() %></td>
<td><a href="/meibo/Update?id=<%=m.getId()%>">更新</a></td>
<td><a href="/meibo/Delete?id=<%=m.getId()%>" onclick="return confirm('id=<%=m.getId()%>を削除してよろしいですか？')">削除</a></td>
</tr>
<%} %>
</table>
<%} %>
</body>
</html>