<%@ page import="com.bookinventoryapp.model.BookDao" %>
<%@ page import="com.bookinventoryapp.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: gfbar
  Date: 28.05.2023
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Book book = BookDao.getBookByID(Integer.parseInt(request.getParameter("id")));%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/BookControllerServlet" method="post">
    <input type="hidden" name="command" value="update">
    <input type="hidden" name="id" value="<%=book.getId()%>">
    Title: <input type="text" name="title" value="<%=book.getTitle()%>"><br>
    Author: <input type="text" name="author" value="<%=book.getAuthor()%>"><br>
    Price<input type="number" step="0.01" name="price" value="<%=book.getPrice()%>"><br>
    <input type="submit" value="Update Book">
</form>
</body>
</html>
