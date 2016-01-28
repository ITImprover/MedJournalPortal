<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<title>Medical Journals Portal - Sign Up</title>
</head>
<body>
	<a href="index.jsp">Sign In</a>
	<c:if test="${!empty requestScope.errorMessage}">
        <p class="errorMessageText">${requestScope.errorMessage}</p>
    </c:if>
    <form action="register" method="POST">
        <table border="0">
            <tr><td>E-mail:</td><td><input type="text" name="email"/></td></tr>
            <tr><td>Journal Name:</td><td><input type="text" name="journalName"/></td></tr>
            <tr><td>password:</td><td><input type="password" name="password"/></td></tr>
            <tr><td>Retype password:</td><td><input type="password" name="passwordAgain"/></td></tr>
        </table>
        <input type="submit" value="Sign Up"/>
    </form>

</body>
</html>