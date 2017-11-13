<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/theme/css/callCentre/tableOfOrders.css" var="tableOfOrdersCSS" />
    <spring:url value="/resources/theme/css/callCentre/infoAboutOrder.css" var="infoCSS" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${tableOfOrdersCSS}" rel="stylesheet" />
    <link href="${infoCSS}" rel="stylesheet" />

    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<div class="contentform">
    <div1>
        <h1>Список заказов</h1>
    </div1>
    <div class="scroll-window">
            <table class="table">
                <tr class="table-header">
                    <th class="cell">№</th>
                    <th class="cell">Откуда</th>
                    <th class="cell">Куда</th>
                    <th class="cell">Водитель</th>
                    <th class="cell">Статус</th>
                    <th><div></div></th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Проспект блюхера 11</td>
                    <td>Метро петроградская</td>
                    <td>Иванов И. И.</td>
                    <td>Выполняется</td>
                    <td>
                        <button style="background: #f44b42; color:white; border: 0" type="submit" class="deleteButton">Удалить</button>
                    </td>
                </tr>
                <tr>
                    <td id="numb">1</td>
                    <td>Проспект блюхера 11</td>
                    <td>Метро петроградская</td>
                    <td>Иванов И. И.</td>
                    <td>Выполняется</td>
                    <td>
                        <button style="background: #f44b42; color:white; border: 0" type="submit"  class="deleteButton">Удалить</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Проспект блюхера 11</td>
                    <td>Метро петроградская</td>
                    <td>Иванов И. И.</td>
                    <td>Выполняется</td>
                    <td>
                        <button style="background: #f44b42; color:white; border: 0" type="submit" class="deleteButton">Удалить</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Проспект блюхера 11</td>
                    <td>Метро петроградская</td>
                    <td>Иванов И. И.</td>
                    <td>Выполняется</td>
                    <td>
                        <button style="background: #f44b42; color:white; border: 0" type="submit" class="deleteButton">Удалить</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Проспект блюхера 11</td>
                    <td>Метро петроградская</td>
                    <td>Иванов И. И.</td>
                    <td>Выполняется</td>
                    <td>
                        <button style="background: #f44b42; color:white; border: 0" type="submit" class="deleteButton">Удалить</button>
                    </td>
                </tr>
            </table>
    </div>
</div>

</html>