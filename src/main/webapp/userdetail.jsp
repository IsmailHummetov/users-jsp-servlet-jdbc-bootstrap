<%@ page import="com.example.resumeweb.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05-Jun-24
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detailed information</title>
    <script src="https://kit.fontawesome.com/aec1554bc2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<%
    User u = (User) request.getAttribute("user");
%>

<div style="margin: 50px">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Firstname</th>
            <th scope="col">Lastname</th>
            <th scope="col">E-mail</th>
            <th scope="col">Phone</th>
            <th scope="col">Profile Description</th>
            <th scope="col">Birth Date</th>
            <th scope="col">Birth Place</th>
            <th scope="col">Nationality</th>
            <th scope="col">Address</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            </th>
            <td><%=u.getFirstname() == null ? "N/A" : u.getFirstname()%>
            </td>
            <td><%=u.getLastname() == null ? "N/A" : u.getLastname()%>
            </td>
            <td><%=u.getEmail() == null ? "N/A" : u.getEmail()%>
            </td>
            <td><%=u.getPhone() == null ? "N/A" : u.getPhone()%>
            </td>
            <td><%=u.getProfileDescription() == null ? "N/A" : u.getProfileDescription()%>
            </td>
            <td><%=u.getBirthDate() == null ? "N/A" : u.getBirthDate()%>
            </td>
            <td><%=u.getbirthPlace().getBirthPlace() == null ? "N/A" : u.getbirthPlace().getBirthPlace()%>
            </td>
            <td><%=u.getNationality().getNationality() == null ? "N/A" : u.getNationality().getNationality()%>
            </td>
            <td><%=u.getAddress() == null ? "N/A" : u.getAddress()%>
            </td>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>
