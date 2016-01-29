<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty sessionScope.userId}">
    <c:if test="${!empty sessionScope.journalName}">
        <a href="uploadIssue.jsp">Upload issue</a> |
    </c:if>
	<a href="logout">Sigh out</a>
</c:if>
