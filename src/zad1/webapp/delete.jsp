<%@ page import="com.bookinventoryapp.model.BookDao" %>
<%@ page import="com.bookinventoryapp.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: gfbar
  Date: 28.05.2023
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Book book = BookDao.getBookByID(Integer.parseInt(request.getParameter("id")));%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Are you sure you want to delete this book?</h3>
<p>ID: <%=book.getId()%>
</p>
<p>Title: <%=book.getTitle()%>
</p>
<p>Author: <%=book.getAuthor()%>
</p>
<p>Price: <%=book.getPrice()%>
</p>
<form action="${pageContext.request.contextPath}/BookControllerServlet" method="post">
    <input type="hidden" name="command" value="delete">
    <input type="hidden" name="id" value="<%=book.getId()%>">
    <input type="submit" value="Delete Book">
</form>

</body>
</html>
