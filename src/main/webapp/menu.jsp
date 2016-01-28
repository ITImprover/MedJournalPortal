<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty sessionScope.isLoggedIn}">
    <a href="uploadIssue.jsp">Upload issue</a> |
	<a href="logout">Sigh out</a> |
</c:if>

<br/><br/>