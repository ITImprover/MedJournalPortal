<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<title>Medical Journals Portal - Journals which you subscribed</title>
</head>
<body>

    <jsp:include page="menu.jsp"/>
    <jsp:include page="login.jsp"/>
    Journals which you subscribed
    <c:if test="${!empty requestScope.errorMessage}">
        <p class="errorMessageText">${requestScope.errorMessage}</p>
    </c:if>
    <c:if test="${!empty sessionScope.journalName}">
        <%--c:forEach items="${requestScope.data}" var="item">
            ${item} <br/>
        </c:forEach--%>
    </c:if>

</body>
</html>