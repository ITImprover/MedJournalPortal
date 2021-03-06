<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<title>Medical Journals Portal - Issues of your journal</title>
</head>
<body>

    <jsp:include page="menu.jsp"/>
    <jsp:include page="login.jsp"/>
    <h1>Issues of your journal "${sessionScope.journalName}"</h1>
    <c:if test="${!empty requestScope.errorMessage}">
        <p class="errorMessageText">${requestScope.errorMessage}</p>
    </c:if>
    <c:if test="${!empty sessionScope.journalName}">
        <table border="0">
            <c:forEach items="${requestScope.issues}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td><a href="deleteIssue?id=${item.id}"
                                onclick="return confirm('Do you want delete issue `${item.name}`?')">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</body>
</html>