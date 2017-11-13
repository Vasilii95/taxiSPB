<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/theme/css/callCentre/signInStyle.css" var="styleCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${styleCSS}" rel="stylesheet" />
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form">
            <input type="text" placeholder="логин.."/>
            <input type="password" placeholder="пароль.."/>
            <button>Вход</button>
        </form>
    </div>
</div>
</body>
</html>
