<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.isLoggedIn}">
    <c:if test="${!empty requestScope.errorMessage}">
        <p class="errorMessageText">${requestScope.errorMessage}</p>
    </c:if>
    Sign in:
    <form action="login" method="POST">
        <table border="0">
            <tr><td>E-mail:</td><td><input type="text" name="email"/></td></tr>
            <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
        </table>
        <input type="submit"/>
    </form>
	<a href="registration.jsp">Sign Up</a>
</c:if>

<br/><br/>