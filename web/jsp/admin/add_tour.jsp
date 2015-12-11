<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
    <head>
        <title><fmt:message key="add_tour.title"/></title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <style>
            .demo select {
                border: 0 !important;  
                -webkit-appearance: none;  
                -moz-appearance: none; 
                background: #0088cc url(img/demo/select-arrow.png) no-repeat 90% center;
                width: 100px; 
                text-indent: 0.01px; 
                text-overflow: ""; 

                color: #FFF;
                border-radius: 15px;
                padding: 5px;
                box-shadow: inset 0 0 5px rgba(000,000,000, 0.5);
            }
            .demo select.balck {
                background-color: #000;
            }
            .demo select.option3 {
                border-radius: 10px 0;
            }
        </style>
    </head>
    <body>

        <%@include file="../../WEB-INF/jspf/header.jspf"%>


    <center> <h1><fmt:message key="add_tour.title"/></h1> </

        <center> 
            <script type="text/javascript">
                function activator() {
                    var rA = document.getElementById("rowsA");
                    var rowsA = rA.options[rA.selectedIndex].value;
                    var cA = document.getElementById("colsA");
                    var colsA = cA.options[cA.selectedIndex].value;
                    if (rowsA > 0 && colsA > 0) {
                        document.getElementById('Ok').disabled = false;
                    } else {
                        document.getElementById('Ok').disabled = true;
                    }
                }
            </script>
            <script type="text/javascript">
                function inputMatrixA() {
                    var rA = document.getElementById("rowsA");
                    var rowsA = rA.options[rA.selectedIndex].value;
                    var cA = document.getElementById("colsA");
                    var colsA = cA.options[cA.selectedIndex].value;
                    var inputA = "";
                    inputA += "<p>Матрица</p>";
                    inputA += "<table>";
                    for (var i = 0; i < rowsA; i++) {
                        inputA += "<tr>";
                        for (var j = 0; j < colsA; j++) {
                            inputA += "<td><input id=\"input\" size=5 type=\"number\" value=\"0\" required step=\"any\"/ name=\"mA" + i + j + "\"" + "/></td>";
                        }
                        inputA += "</tr>";
                    }
                    inputA += "</table>";
                    document.getElementById('matrixA').innerHTML = inputA;
                    activator();
                }
            </script>

            <div>
                <p class="title2">Введите данные матрицы.</p>
            </div>

            <form class="form" name="username" action="${pageContext.request.contextPath}/app?c=add_matrix&lang=${locale}" method="post">
                <!--Matrix id:<input id="cell" size="5" type="number" min="1" value="1" required="" step="1" name="idA">-->
                <select class="sign-up-select" id="rowsA" name="rowsA" onchange="inputMatrixA()">
                    <option value="0"></option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
                x <select  class="sign-up-select" id="colsA" name="colsA" onchange="inputMatrixA()">
                    <option value="0"></option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select></p>
                <br>
                <div id="matrixA"></div>
                <br>
                <input class="sign-up-button-m" type="submit" id="Ok" value="Готово" disabled="disabled"/>
                <input  type="hidden" name="command" value="add_matrix">
            </form>

       </center> 

        </center>

        <%@include file="../../WEB-INF/jspf/footer.jspf"%>
    </body>
</html>