<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Management - Users</title>
</head>
<body>
<h1>Library User List</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Course/Department</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user instanceof Student ? user.course : user.department}</td>
            <td>
                <a href="UserController?action=edit&id=${user.id}">Edit</a>
                <a href="UserController?action=delete&id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<a href="UserController?action=new">Add New User</a>
</body>
</html>
