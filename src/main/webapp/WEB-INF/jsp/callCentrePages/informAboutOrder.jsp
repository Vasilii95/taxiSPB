<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/theme/css/callCentre/informAboutOrder.css" var="infoAboutOrderCSS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${infoAboutOrderCSS}" rel="stylesheet" /></head>
<body>

<form>
    <h1>Введите необходимую информацию :</h1>
    <div class="contentform">
        <div class="leftcontact">
            <div class="form-group">
                <p>Имя<span>*</span></p>
                <span class="icon-case"><i class="fa fa-male"></i></span>
                <input type="text" name="name" id="name" data-rule="required"/>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <p>Телефонный номер <span>*</span></p>
                <span class="icon-case"><i class="fa fa-phone"></i></span>
                <input type="text" name="prenom" id="prenom" data-rule="required"/>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <p>Время <span>*</span></p>
                <span class="icon-case"><i class="fa fa-clock-o"></i></span>
                <input type="email" name="time" id="time" data-rule="email"/>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <p>Дополнительные пожелания</p>
                <span class="icon-case"><i class="fa fa-comments-o"></i></span>
                <textarea name="message" rows="10"  id="message" data-rule="required"></textarea>
                <div class="validation"></div>
            </div>
        </div>

        <div class="rightcontact">

            <div class="form-group">
                <p>Откуда <span>*</span></p>
                <span class="icon-case"><i class="fa fa-map-marker"></i></span>
                <input type="text" name="from" id="from" data-rule="required"/>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <p>Куда <span>*</span></p>
                <span class="icon-case"><i class="fa fa-map-marker"></i></span>
                <input type="text" name="to" id="to" data-rule="maxlen:10"/>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <select id="soflow">
                    <option>Эконом</option>
                    <option>Базовый</option>
                    <option>Бизнесс</option>
                </select>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <h4>Информация по заказу </h4>
                <p id="price">Цена: </p>
                <p id="length">Длина пути: </p>
                <div class="validation"></div>
            </div>

            <div class="form-group">
                <button type="submit" id="getPrice" class="bouton-getinfo">Информация по заказу</button>
                <div class="validation"></div>
            </div>
        </div>
    </div>
    <button type="submit" id="createOrder" class="bouton-contact">Отправить заказ</button>
</form>
</body>
</html>
