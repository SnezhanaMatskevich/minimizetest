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

        <script type="text/javascript">
            $(document).ready(function() {
                $('.paid').click(function() {
                    var paid = $(this).is(":checked");
                    var link = "app?c=orders&id=" + $(this).attr('data-id') + "&paid=" + paid;
                    window.location.href = link;
                });
            });
        </script>
        <script type="text/javascript" id="js">$(document).ready(function() {
                $("listToBeFiltered").tablesorter({
                    sortList: [[0, 0], [2, 0]]
                });
            });</script> 

    </head>
    <body>

        <%@include file="../../WEB-INF/jspf/header.jspf"%>


        <div class="content">
            <h1><fmt:message key="orders.title"/></h1>

            <center> <lable for="searchField"><fmt:message key="header.search.form"/></lable> <input type="text" id="dgSearchField" style="width:400" ></center>


            <br><br>
            <table id="listToBeFiltered" style="width: 100%">
                <thead>
                    <tr>
                        <th><fmt:message key="orders.id"/></th>
                        <th><fmt:message key="orders.tourname"/></th>
                        <th><fmt:message key="order.prepare.total"/></th>
                        <th><fmt:message key="orders.date"/></th>
                        <th><fmt:message key="orders.paid"/></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.tour.tourname}</td>
                            <td><fmt:formatNumber maxFractionDigits="1" type="number"  groupingUsed="false" value="${order.amount}"/> USD</td>
                            <td><fmt:formatDate value="${order.dateTime}" pattern="d.M.Y H:m"/> </td>
                            <td><input data-id="${order.id}" data-paid=${not empty order.paid} class="paid" type="checkbox" <c:if test="${order.paid}">checked="true"</c:if>/> </td>
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