
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>

        <title>Miraflores Car Wash</title>


        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapValidator.min.css" />" />
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrapValidator.min.js" />" ></script>

        <script type="text/javascript" src="<c:url value="/resources/js/validador.js"/>"></script>

    </head>
    <body>
        <nav>
            <jsp:include page="/templates/menu.jsp"/>
            <jsp:include page="/templates/banner.jsp"/>
        </nav>
        <section>
            <jsp:include page="/templates/header.jsp"/>
            <div class="container">
                <c:choose>
                    <c:when test="${usuarioForm.id == null || usuarioForm.id == 0}">
                        <spring:url value="/Usuario/add.html" var="usuarioActionURL"/>
                    </c:when>
                    <c:otherwise>
                        <spring:url value="/Usuario/update.html" var="usuarioActionURL"/>
                    </c:otherwise>
                </c:choose>
                <sec:authentication property="principal.username" var="actualUsuario"/>
                <form:form action="${usuarioActionURL}" method="POST" modelAttribute="usuarioForm"
                           id="usuarioForm" name="usuarioForm" class="form-horizontal">
                    <h2>Formularios de Usuarios</h2>
                    <form:hidden path="id" />
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="nombreCompleto" class="col-lg-3 control-label" >Nombre completo</form:label>
                            <div class="col-lg-3 ">
                            <form:input path="nombreCompleto" class="form-control" placeholder="Introduce tu Nombre real" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="username" class="col-lg-3 control-label" >Nombre de Usuario</form:label>
                            <div class="col-lg-3 ">
                            <c:choose>
                                <c:when test="${not(usuarioForm.username eq actualUsuario)}">
                                    <form:input path="username" class="form-control" placeholder="Introduce tu Nombre de Usuario" />
                                </c:when>
                                <c:otherwise>
                                    <form:input path="username" class="form-control" disabled="true"/>
                                </c:otherwise>
                            </c:choose>                            
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${usuarioActionURL.contains('/Usuario/add.html')}">
                            <div class="form-group has-feedback">
                                <!--<label for="nombres">Nombres</label>-->
                                <form:label path="password" class="col-lg-3 control-label" >Contraseña </form:label>
                                    <div class="col-lg-3 ">
                                    <form:password path="password" class="form-control" placeholder="Introduce la contraseña" />
                                </div>
                            </div>
                            <div class="form-group has-feedback">
                                <!--<label for="nombres">Nombres</label>-->
                                <label class="col-lg-3 control-label" >Confirma Contraseña</label>
                                <div class="col-lg-3 ">
                                    <input type="password" name="confirmPassword" class="form-control" placeholder="Confirma la Contraseña" />
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form:hidden path="password" />
                        </c:otherwise>
                    </c:choose>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="enabled" class="col-lg-3 control-label">Activo</form:label>
                            <div class="col-lg-3">
                            <c:choose>
                                <c:when test="${usuarioActionURL.contains('/Usuario/add.html')}">
                                    <form:checkbox path="enabled" checked="true" />
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${not(usuarioForm.username eq actualUsuario)}">
                                            <form:checkbox path="enabled" />
                                        </c:when>
                                        <c:otherwise>
                                            <form:checkbox path="enabled" disabled="true" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <%--<form:hidden path="roles" value="1" />--%>
                    <div class="form-group has-feedback">
                        <spring:url value="/Usuario/index.html" var="cancelURL" />
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-success left">Enviar</button>
                            <button type="reset" class="btn btn-info left">Limpiar</button>
                            <button type="button" class="btn btn-danger left" onclick="location.href = '${cancelURL}'">Cancelar</button>
                        </div>
                    </div>
                </form:form>
                <br/>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
