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
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.tablesorter.min.js"></script> 
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/mootools.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/dg-filter.js"></script> 

    </script>
    <script type="text/javascript" id="js">$(document).ready(function () {
            $("listToBeFiltered").tablesorter({
                sortList: [[0, 0], [2, 0]]
            });
        });</script> 

</head>
<body>

    <%@include file="../../WEB-INF/jspf/header.jspf"%>


    <div class="content">
        <h1><fmt:message key="orders.title"/></h1>

        <center> <lable for="searchField"><fmt:message key="header.search.form"/></lable> <input class="sign-up-input" type="text" id="dgSearchField" style="width:400" ></center>
   
        
        
        <br><br>
        <table id="listToBeFiltered" style="width: 100%">
            <thead>
                <tr>
                    <th>Номер</th>
                    <th>Имя</th>             
                    <th>Использовано</th>
                    <th>Улучшения</th>

                </tr>
            </thead>

            <tbody>
                <c:forEach items="${reports}" var="reports">
                    <tr>
                        <td>${reports.id}</td>
                        <td>${reports.report}</td>
                        <td><input  class="paid" type="checkbox" <c:if test="true">checked="true"</c:if>/> </td>
                        <td>${reports.perc}%</td>
                <form class="form" name="username" action="${pageContext.request.contextPath}/app?c=download&id=${reports.id}&lang=${locale}" method="post">
                    <input type="hidden" name="command" value="download">
                    <td>   <input  class="table-button" name="submit" type="submit" value="Скачать"/> </td>
                </form>

                </tr>

                               

                </c:forEach>
                </tbody>
        </table>
        <script type="text/javascript">
            var filter = new DG.Filter({
                filterField: $('dgSearchField'),
                filterEl: $('listToBeFiltered'),
            });
        </script> 
    </div>

    <%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>