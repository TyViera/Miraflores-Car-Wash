
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
                <h1>Todas los Carros</h1>
                <div class="col-lg-offset-10" >
                    <spring:url value="/Carro/add.html" var="carroAddUrl" />
                    <button class="btn btn-default" onclick="location.href = '${carroAddUrl}'">Agregar Nuevo</button>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Modelo</th>
                            <th>Marca</th>
                            <th>Placa</th>
                            <th>Dueño</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <c:forEach items="${carros}" var="carro">
                        <tr>
                            <td>${carro.id}</td>
                            <td>${carro.modelo.nombre}</td>
                            <td>${carro.marca}</td>
                            <td>${carro.placa}</td>
                            <td>${carro.cliente.nombres} ${carro.cliente.apellidos}</td>
                            <td>
                                <spring:url value="/Carro/view.html?id=${carro.id}" var="carroUrl" />
                                <spring:url value="/Carro/${carro.id}/delete.html" var="deleteUrl" /> 
                                <spring:url value="/Carro/update.html?id=${carro.id}" var="updateUrl" />

                                <button class="btn btn-info" onclick="location.href = '${carroUrl}'">Query</button>
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
