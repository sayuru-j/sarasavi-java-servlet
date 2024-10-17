<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${book != null ? "Edit Book" : "Add Book"}</title>
</head>
<body>
<h1>${book != null ? "Edit Book" : "Add New Book"}</h1>
<form action="BookController" method="post">
    <input type="hidden" name="action" value="${book != null ? 'update' : 'insert'}"/>
    <input type="hidden" name="id" value="${book != null ? book.id : ''}"/>

    <label for="title">Title:</label>
    <input type="text" name="title" value="${book != null ? book.title : ''}" required/><br/>

    <label for="author">Author:</label>
    <input type="text" name="author" value="${book != null ? book.author : ''}" required/><br/>

    <label for="category">Category:</label>
    <input type="text" name="category" value="${book != null ? book.category : ''}" required/><br/>

    <label for="isAvailable">Available:</label>
    <input type="checkbox" name="isAvailable" ${book != null && book.isAvailable ? 'checked' : ''}/><br/>

    <input type="submit" value="${book != null ? 'Update Book' : 'Add Book'}"/>
</form>
<br/>
<a href="BookController">Back to Book List</a>
</body>
</html>
