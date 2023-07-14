<%--
  Created by IntelliJ IDEA.
  User: gfbar
  Date: 28.05.2023
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/BookControllerServlet" method="post">
    <input type="hidden" name="command" value="create">
    Title: <input type="text" name="title" value="${book.title}"><br>
    Author: <input type="text" name="author" value="${book.author}"><br>
    Price<input type="number" step="0.01" name="price" value="${book.price}"><br>
    <input type="submit" value="Add Book">
</form>

</body>
</html>
