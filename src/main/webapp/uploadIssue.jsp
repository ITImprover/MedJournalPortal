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
    <jsp:include page="login.jsp"/>
    <jsp:include page="menu.jsp"/>
    <c:if test="${!empty sessionScope.userId}">
        <c:if test="${!empty requestScope.errorMessage}">
            <p class="errorMessageText">${requestScope.errorMessage}</p>
        </c:if>
        <form action="upload" method="POST" enctype="multipart/form-data">
            Choose issue (PDF file): <input type="file" name="issue"/> <input type="submit" value="Upload"/>
        </form>
    </c:if>
</body>
</html>