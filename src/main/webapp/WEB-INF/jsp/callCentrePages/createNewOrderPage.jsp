<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<div class="wrapper">
    <div id="sidebar">
        <jsp:include page="menu.jsp"/>
    </div>
    <div class="main-content">
        <jsp:include page="informAboutOrder.jsp"/>
    </div>
</div>
</body>
</html>