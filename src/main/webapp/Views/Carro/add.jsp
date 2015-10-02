
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
                    <c:when test="${carroForm.id == null || carroForm.id == 0}">
                        <spring:url value="/Carro/add.html" var="carroActionURL"/>
                    </c:when>
                    <c:otherwise>
                        <spring:url value="/Carro/update.html" var="carroActionURL"/>
                    </c:otherwise>
                </c:choose>
                <label class="hidden" id="url">${carroActionURL}</label>
                <label class="hidden" id="baseUrl">${pageContext.servletContext.contextPath}</label>
                <form:form action="${carroActionURL}" method="POST" modelAttribute="carroForm"
                           id="carroForm" name="carroForm" class="form-horizontal">
                    <h2>Formularios de Carros</h2>
                    <form:hidden path="id" id="id" />
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="marca" class="col-lg-3 control-label" >Marca</form:label>
                            <div class="col-lg-3 ">
                            <form:input id="marca" path="marca" class="form-control" placeholder="Introduce la Marca del Auto" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="placa" class="col-lg-3 control-label">Placa</form:label>
                            <div class="col-lg-3">
                            <form:input id="placa" path="placa" class="form-control" placeholder="Introduce la del Placa" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="modelo" class="col-lg-3 control-label">Modelo</form:label>
                            <div class="col-lg-3">
                            <form:select path="modelo" id = "modelo" class="form-control">
                                <c:forEach items="${modelosCarro}" var="modelo">
                                    <form:option value="${modelo.id}">${modelo.nombre}</form:option>
                                </c:forEach>
                            </form:select>    
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="cliente" class="col-lg-3 control-label">Cliente Dueño</form:label>
                            <div class="col-lg-3">
                            <c:choose>
                                <c:when test="${carroForm.cliente == null}">
                                    <form:select path="cliente" id="cliente" class="form-control">
                                        <c:forEach items="${clientes}" var="cli">
                                            <form:option value="${cli.id}">${cli.apellidos} ${cli.nombres}</form:option>
                                        </c:forEach>
                                    </form:select>    
                                </c:when>
                                <c:otherwise>
                                    <p class="form-control-static">${carroForm.cliente.apellidos} ${carroForm.cliente.nombres}</p>
                                    <input id="cliente" type="hidden" value="${carroForm.cliente.id}" />
                                    <%--<form:hidden path="cliente" />--%>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <spring:url value="/Carro/index.html" var="cancelURL" />
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-success left">Enviar</button>
                            <button type="reset" class="btn btn-info left">Limpiar</button>
                            <button type="button" class="btn btn-danger left" onclick="location.href = '${cancelURL}'">Cancelar</button>
                            <script src="<c:url value="/resources/js/formularios.js"/>" ></script>
                        </div>
                    </div>
                </form:form>
                <br/>
            </div>
            <div id="myModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Carro</h4>
                        </div>
                        <div class="modal-body">
                            <p id="textoModal"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
