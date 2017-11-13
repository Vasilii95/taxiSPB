<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/theme/css/clients/menu.css" var="menuCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${menuCSS}" rel="stylesheet" />
</head>
<nav>
    <a href="#" class="menu-trigger">Menu</a>
    <ul>
        <li><a href="#">Новый заказ</a></li>
        <li><a href="#">О компании</a></li>
        <li><a href="#">Контакты</a></li>
        <li><a href="#">Услуги</a></li>
    </ul>
</nav>
</html>
