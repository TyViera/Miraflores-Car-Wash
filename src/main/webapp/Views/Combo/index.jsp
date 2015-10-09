
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
        <!-- To PDF -->
        <script type="text/javascript" src="<c:url value="/resources/js/pdf/basic.js"/>"></script>
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
                <h1>Todas los Combos</h1>
                <div class="col-lg-offset-9" >
                    <spring:url value="/Combo/add.html" var="comboAddUrl" />
                    <button class="btn btn-default" onclick="location.href = '${comboAddUrl}'">Agregar Nuevo</button>
                    <button class="btn btn-danger" onclick="exportPDFCombos('${pageContext.servletContext.contextPath}')">
                        Exportar a PDF
                    </button>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Lavadas</th>
                            <th>Modelo de Autos</th>
                            <th>Precio</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <c:forEach items="${combos}" var="combo">
                        <tr>
                            <td>${combo.id}</td>
                            <td>${combo.combo.nombre}</td>
                            <td>${combo.combo.numeroLavadas}</td>
                            <td>${combo.modelo.nombre}</td>
                            <td>S/. ${combo.precio}0</td>
                            <td>
                                <spring:url value="/Combo/view.html?id=${combo.id}" var="comboUrl" />
                                <spring:url value="/Combo/${combo.id}/delete.html" var="deleteUrl" /> 
                                <spring:url value="/Combo/update.html?id=${combo.id}" var="updateUrl" />

                                <button class="btn btn-info" onclick="location.href = '${comboUrl}'">Query</button>
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
