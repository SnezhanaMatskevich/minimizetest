<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
    <head>
        <title><fmt:message key="signup.page.title"/></title>
        <link rel="stylesheet" href="css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    </head>
    <body>

        <%@include file="../WEB-INF/jspf/header.jspf"%>

        <div class="content">

            <h1><fmt:message key="signup.page.title"/> </h1>
            <p><fmt:message key="auth.page.message"/> </p>

            <form method="post" id="loginForm">

                <input type="hidden" name="command" value="user"/>
                <div class="field"><br>
                    <label for="login"><fmt:message key="auth.page.login_form.login"/> :</label>
                    <br>
                    <input id="login" type="text" name="login" pattern ='^[a-zA-Z]+$' placeholder =<fmt:message key="auth.page.login_form.login"/> required/>
                </div>
                <div class="field">
                    <label for="password"><fmt:message key="auth.page.login_form.password"/>:</label>
                    <br>
                    <input id="password" type="password" pattern = '^[a-zA-Z0-9]+$'placeholder= <fmt:message key="auth.page.login_form.password"/> name="password" required/>
                </div>
                <div class="field">
                    <label for="firstName"><fmt:message key="signup.page.login_form.firstname"/> :</label>
                    <br>
                    <input id="firstName" type="text"pattern ='^[а-яА-ЯёЁa-zA-Z]+$' placeholder=<fmt:message key="signup.page.login_form.firstname"/> name="firstName"required/>
                </div>
                <div class="field">
                    <label for="lastName"><fmt:message key="signup.page.login_form.lastname"/> :</label>
                    <br>
                    <input id="lastName" type="text" pattern ='^[а-яА-ЯёЁa-zA-Z]+$' placeholder=<fmt:message key="signup.page.login_form.lastname"/> name="lastName"required/>
                </div>
                <label for="dateOfBirth"><fmt:message key="signup.page.login_form.dateofbirth"/> :</label>
                <br>
                <input id="dateOfBirth" type="date"  name="dateOfBirth" min="1890-01-01"/>
                <div class="field">
                    <label for="email"><fmt:message key="signup.page.login_form.email"/> :</label>
                    <br>
                    <input id="email" type="email" placeholder=<fmt:message key="signup.page.login_form.email"/> name="email"required/>
                </div>
                <div class="field">
                    <label for="fullAdress"><fmt:message key="signup.page.login_form.fulladress"/> :</label>
                    <br>
                    <input id="fullAdress" type="text" placeholder=<fmt:message key="signup.page.login_form.fulladress"/> name="fullAdress"required />
                </div>
              
                <input type="submit" value="<fmt:message key="signup.page.login_form.submit"/>"/>
            </form>

        </div>

        <%@include file="../WEB-INF/jspf/footer.jspf"%>
    </body>
</html>