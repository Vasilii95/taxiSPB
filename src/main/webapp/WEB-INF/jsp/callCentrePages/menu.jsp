<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
    <spring:url value="/resources/theme/css/callCentre/menuStyle.css" var="styleCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${styleCSS}" rel="stylesheet" />
</head>
<div class="area"></div><nav class="main-menu">
    <ul>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-road fa-2x"></i>
                <span class="nav-text">Новый заказ</span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">Список заказов</span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-search fa-2x"></i>
                <span class="nav-text">Поиск</span>
            </a>
        </li>
    </ul>
    <ul class="logout">
        <li>
            <a href="#">
                <i class="fa fa-power-off fa-2x"></i>
                <span class="nav-text">Выход</span>
            </a>
        </li>
    </ul>
</nav>
<div class="area"></div><nav class="main-menu">
    <ul>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-road fa-2x"></i>
                <span class="nav-text">Новый заказ</span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">Список заказов</span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-search fa-2x"></i>
                <span class="nav-text">Поиск</span>
            </a>
        </li>
    </ul>
    <ul class="logout">
        <li>
            <a href="#">
                <i class="fa fa-power-off fa-2x"></i>
                <span class="nav-text">Выход</span>
            </a>
        </li>
    </ul>
</nav>
</html>
