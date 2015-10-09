<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html> 
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>
        <title>Miraflores Car Wash</title>
    </head>
    <body>
        <nav>
            <jsp:include page="/templates/banner.jsp"/>
        </nav>
        <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>" />
        <section class="container">
            <div class="wrapper">
                <spring:url value="/j_spring_security_check" var="goUrl" />
                <form:form class="form-signin" method="POST" action="${goUrl}" >
                    <h1 class="text-center">Bienvenido</h1>
                    <h2 class="form-signin-heading">Por favor Inicia Sesión</h2>
                    <c:if test="${not empty msg}">
                        <div class="alert alert-${css} alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <strong>${msg}</strong>
                        </div>
                    </c:if>
                    <input type="text" class="form-control" name="j_username" placeholder="Nombre de usuario" required="true" autofocus="" />
                    <input type="password" class="form-control" name="j_password" placeholder="Contraseña" required="true"/>
                    <label class="checkbox">
                        <input type="checkbox" value="remember-me" id="rememberMe" name="_spring_security_remember_me" /> Recuerdame
                    </label>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                </form:form>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
