<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <spring:url value="/resources/theme/css/callCentre/infoAboutOrderStyle.css" var="infoAboutOrderCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${infoAboutOrderCSS}" rel="stylesheet" />
</head>
<form>
    <h1>Поиск :</h1>
    <div class="contentform">
        <div class="leftcontact">
            <div class="form-group">
                <p>Имя<span>*</span></p>
                <span class="icon-case"><i class="fa fa-male"></i></span>
                <input type="text" name="name" id="name" data-rule="required"/>
                <div class="validation"></div>
            </div>
        </div>
        <div class="rightcontact">
            <div class="form-group">
                <button type="submit" id="getPrice" class="bouton-getinfo">Найти</button>
                <div class="validation"></div>
            </div>
        </div>
    </div>
</form>
</html>
