<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="students.Credentials" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Details</title>
</head>
<body>

<h2>Your Details</h2>

<p>ID: 25250</p>
<p>First Name:Ishimwe</p>
<p>Last Name: patience</p>

<h2>Insert New Credentials</h2>

<form action="index" method="post">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required><br><br>
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>
    <input type="hidden" name="submitAction" value="insert">  
    <button type="submit">Insert</button>
</form>

</body>
</html>