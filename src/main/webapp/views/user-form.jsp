<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user != null ? "Edit User" : "Add User"}</title>
</head>
<body>
<h1>${user != null ? "Edit User" : "Add New User"}</h1>
<form action="UserController" method="post">
    <input type="hidden" name="action" value="${user != null ? 'update' : 'insert'}"/>
    <input type="hidden" name="id" value="${user != null ? user.id : ''}"/>

    <label for="name">Name:</label>
    <input type="text" name="name" value="${user != null ? user.name : ''}" required/><br/>

    <label for="email">Email:</label>
    <input type="email" name="email" value="${user != null ? user.email : ''}" required/><br/>

    <label for="role">Role:</label>
    <select name="role">
        <option value="Student" ${user != null && user.role == 'Student' ? 'selected' : ''}>Student</option>
        <option value="Staff" ${user != null && user.role == 'Staff' ? 'selected' : ''}>Staff</option>
    </select><br/>

    <label for="courseOrDepartment">Course/Department:</label>
    <input type="text" name="courseOrDepartment" value="${user instanceof Student ? user.course : user.department}" required/><br/>

    <input type="submit" value="${user != null ? 'Update User' : 'Add User'}"/>
</form>
<br/>
<a href="UserController">Back to User List</a>
</body>
</html>
