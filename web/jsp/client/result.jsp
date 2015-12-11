<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>
<%@ taglib prefix="ex" uri="/WEB-INF/printer.tld" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="admin.manager.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.js"></script>

</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>

<div class="content">
    <center>
   <c:if test="${not empty result}">
                <ex:printMatrix matrix="${result}"/>
            </c:if>
            <div class="center_coment">
                ${error}
            </div>
    </center> 
                <form class="form" name="username" action="${pageContext.request.contextPath}/app?c=manager&lang=${locale}" method="post">

                    <center>      <input class="sign-up-button-m" type="submit" id="Ok" value="Готово"/> </center>
                <input  type="hidden" name="command" value="count">
            </form>

            
            
</div>
<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>