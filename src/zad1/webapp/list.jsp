<%@ page import="com.bookinventoryapp.model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gfbar
  Date: 28.05.2023
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<Book> books = (List<Book>) request.getAttribute("books");%>
<head>
    <title>Title</title>
</head>
<body>
<% if (books != null && !books.isEmpty()) { %>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
    </tr>
    <% for (Book b : books) {%>
    <tr>
        <td><%=b.getId()%>
        </td>
        <td><%=b.getTitle()%>
        </td>
        <td><%=b.getAuthor()%>
        </td>
        <td><%=b.getPrice()%>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/edit.jsp?id=<%=b.getId()%>">Edit</a>
            <a href="${pageContext.request.contextPath}/delete.jsp?id=<%=b.getId()%>">Delete</a>
        </td>
    </tr>
    <%}%>
</table>
<%}%>
<br>
<a href="create.jsp">Create Book</a>

</body>
</html>