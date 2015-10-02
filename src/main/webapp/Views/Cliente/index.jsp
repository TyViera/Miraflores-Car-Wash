
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                <h1>Todas los Clientes</h1>
                <div class="col-lg-offset-10" >
                    <spring:url value="/Cliente/add.html" var="clienteAddUrl" />
                    <button class="btn btn-default" onclick="location.href = '${clienteAddUrl}'">Agregar Nuevo</button>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Dni</th>
                            <th>Email</th>
                            <th>Telefono</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <c:forEach items="${clientes}" var="cliente">
                        <tr>
                            <td>${cliente.id}</td>
                            <td>${cliente.nombres}</td>
                            <td>${cliente.apellidos}</td>
                            <td>${cliente.dni}</td>
                            <td>${cliente.email}</td>
                            <td>${cliente.telefono}</td>
                            <td>
                                <spring:url value="/Cliente/view.html?id=${cliente.id}" var="clienteUrl" />
                                <spring:url value="/Cliente/${cliente.id}/delete.html" var="deleteUrl" /> 
                                <spring:url value="/Cliente/update.html?id=${cliente.id}" var="updateUrl" />

                                <button class="btn btn-info" onclick="location.href = '${clienteUrl}'">Query</button>
                                <button class="btn btn-primary" onclick="location.href = '${updateUrl}'">Update</button>
                                <button class="btn btn-danger" onclick="this.disabled = true;
                                        post('${deleteUrl}')">Delete</button>
                                <script src="<c:url value="/resources/js/formularios.js"/>" ></script>
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
