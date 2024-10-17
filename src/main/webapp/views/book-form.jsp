<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${book != null ? "Edit Book" : "Add Book"}</title>
    <!-- Link to the external CSS file -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>${book != null ? "Edit Book" : "Add New Book"}</h1>
<form action="books" method="post">
    <!-- Action to handle either insert or update -->
    <input type="hidden" name="action" value="${book != null ? 'update' : 'insert'}"/>
    <input type="hidden" name="id" value="${book != null ? book.id : ''}"/>

    <!-- Title input -->
    <label for="title">Title:</label>
    <input type="text" name="title" value="${book != null ? book.title : ''}" required/><br/>

    <!-- Author input -->
    <label for="author">Author:</label>
    <input type="text" name="author" value="${book != null ? book.author : ''}" required/><br/>

    <!-- Category input -->
    <label for="category">Category:</label>
    <input type="text" name="category" value="${book != null ? book.category : ''}" required/><br/>

    <!-- Checkbox for Available -->
    <label for="isAvailable">Available:</label>
    <input type="checkbox" name="isAvailable" value="true" ${book != null && book.isAvailable() ? 'checked' : ''}/><br/>

    <!-- Submit button -->
    <input type="submit" value="${book != null ? 'Update Book' : 'Add Book'}"/>
</form>
<br/>
<a href="books?action=list">Back to Book List</a>
</body>
</html>
