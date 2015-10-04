
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
                <h1>Todas los Lavadas</h1>
                <div class="col-lg-offset-10" >
                    <spring:url value="/Lavada/add.html" var="lavadaAddUrl" />
                    <button class="btn btn-default" onclick="location.href = '${lavadaAddUrl}'">Agregar Nuevo</button>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Fecha</th>
                            <th>Estado</th>
                            <th>Placa de Carro</th>
                            <th>Modelo de Carro</th>
                            <th>Cliente</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <c:forEach items="${lavadas}" var="lavada">
                        <tr>
                            <td>${lavada.id}</td>
                            <td>${lavada.fechaLavado}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${lavada.estado eq 'REA'}">
                                        Realizado
                                    </c:when>
                                    <c:otherwise>
                                        Pendiente de Entrega
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${lavada.carro.placa}</td>
                            <td>${lavada.carro.modelo.nombre}</td>
                            <td>${lavada.carro.cliente.apellidos} ${lavada.carro.cliente.nombres}</td>
                            <td>
                                <spring:url value="/Lavada/view.html?id=${lavada.id}" var="lavadaUrl" />
                                <button class="btn btn-info" onclick="location.href = '${lavadaUrl}'">Query</button>
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
