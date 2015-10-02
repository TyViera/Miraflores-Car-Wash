
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="usuario" scope="request" class="com.miraflorescarwash.model.Usuario" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>
        <title>Miraflores Car Wash</title>
    </head>
    <body>
        <nav>
            <jsp:include page="/templates/menu.jsp"/>
            <jsp:include page="/templates/banner.jsp"/>
        </nav>
        <section>
            <jsp:include page="/templates/header.jsp"/>
            <div class="container">
                <c:if test="${not empty msg}">
                    <div class="alert alert-${css} alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <strong>${msg}</strong>
                    </div>
                </c:if>
                <h1>Todas los Usuarios</h1>
                <div class="col-lg-offset-10" >
                    <spring:url value="/Usuario/add.html" var="usuarioAddUrl" />
                    <button class="btn btn-default" onclick="location.href = '${usuarioAddUrl}'">Agregar Nuevo</button>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre Completo</th>
                            <th>Username</th>
                            <th>Activo</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <sec:authentication property="principal.username" var="actualUsuario"/>
                    <c:forEach items="${usuarios}" var="usuario">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nombreCompleto}</td>
                            <td>${usuario.username}</td>
                            <td>
                                <input type="checkbox" disabled="true" 
                                       <c:if test="${usuario.enabled}" >checked</c:if> />
                                </td>
                                <td>
                                <spring:url value="/Usuario/view.html?id=${usuario.id}" var="usuarioUrl" />
                                <spring:url value="/Usuario/${usuario.id}/delete.html" var="deleteUrl" /> 
                                <spring:url value="/Usuario/update.html?id=${usuario.id}" var="updateUrl" />

                                <button class="btn btn-info" onclick="location.href = '${usuarioUrl}'">Query</button>
                                <button class="btn btn-primary" onclick="location.href = '${updateUrl}'">Update</button>
                                <c:if test="${not(usuario.username eq actualUsuario)}">
                                    <button class="btn btn-danger" onclick="this.disabled = true;
                                            post('${deleteUrl}')">Delete</button>
                                    <script src="<c:url value="/resources/js/formularios.js"/>" ></script>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
