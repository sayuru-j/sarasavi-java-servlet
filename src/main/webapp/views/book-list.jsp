<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Library Management - Books</title>
    <!-- Link to the external CSS file -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Library Book List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Category</th>
        <th>Availability</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.category}</td>
            <td>${book.isAvailable() ? "Available" : "Borrowed"}</td>
            <td>
                <a href="books?action=edit&id=${book.id}">Edit</a>
                <a href="books?action=delete&id=${book.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<a href="books?action=new">Add New Book</a>
</body>
</html>
