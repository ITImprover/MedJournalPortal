<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty sessionScope.isLoggedIn}">
	<a href="do?action=my_habits">мои привычки</a> |
        <a href="add_habit.jsp">создать привычку</a> |
        <a href="search_users_form.jsp">Найти друзей</a> |
	<a href="do?action=logout">выход</a> |
        <a href="do?action=drop_account">удалить аккаунт</a>
</c:if>

<br/><br/>