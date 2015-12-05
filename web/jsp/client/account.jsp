<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
    <head>
        <title><fmt:message key="account.title"/></title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>

        <%@include file="../../WEB-INF/jspf/header.jspf"%>


        <div class="content">
            <h1>Расчёт </h1>
            



            <table class="order-table">
                <tbody>

                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>
                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>
                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>
                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>
                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>
                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>
                    <tr>
                        <td> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/> <input class="table-input" type="text" required/></td>
                    </tr>

                </tbody>
            </table>

   
   <center> <a class="btn" href="app?c=order&lang=${locale}"><fmt:message key="form_submit"/></a> </center>
        </div>

        <%@include file="../../WEB-INF/jspf/footer.jspf"%>
    </body>
</html>