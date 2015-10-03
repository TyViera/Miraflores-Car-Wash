
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

        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.css"/>"/>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table-translate.js"/>"></script>

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
                    <c:when test="${comboForm.id == null || comboForm.id == 0}">
                        <spring:url value="/Combo/add.html" var="comboActionURL"/>
                    </c:when>
                    <c:otherwise>
                        <spring:url value="/Combo/update.html" var="comboActionURL"/>
                    </c:otherwise>
                </c:choose>
                <label class="hidden" id="urlCombo" >${comboActionURL}</label>                
                <form:form action="${comboActionURL}" method="POST" modelAttribute="comboForm"
                           id="comboForm" name="comboForm" class="form-horizontal">
                    <h2>Formularios de Combos</h2>
                    <form:hidden path="id" />
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="combo" class="col-lg-3 control-label" >
                            Combo
                        </form:label>
                        <input type="hidden" id="comboHidden" name="comboHidden" />
                        <div class="col-lg-3 ">
                            <button id="btnSelComboOpenModal" type="button" class="btn btn-default" onclick="abrirModalSeleccionarCombo();">Seleccionar</button>
                            <button type="button" class="btn btn-default" onclick="abrirModalAddCombo();">Agregar</button>
                            <br/>
                            <br/>
                            <table id="tablaCombo" class="table table-hover">
                                <c:choose>
                                    <c:when test="${comboForm.combo eq null}">
                                        <thead>
                                            <tr>
                                                <th>Combo seleccionado</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tr>
                                            <td>Nombre</td>
                                            <td>------</td>
                                        </tr>
                                        <tr>
                                            <td>Lavadas</td>
                                            <td>------</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${comboForm.combo.nombre}</td>
                                        <script>
                                            var cadjson = '{';
                                            cadjson += '"id":"${comboForm.combo.id}",';
                                            cadjson += '"descripcion": "${comboForm.combo.descripcion}",';
                                            cadjson += '"nombre":"${comboForm.combo.nombre}",';
                                            cadjson += '"numeroLavadas":"${comboForm.combo.numeroLavadas}"';
                                            cadjson += '}';
                                            mostrarEnTablaComboSel(cadjson);
                                        </script>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <form:label path="modelo" class="col-lg-3 control-label" >
                            Modelo de Carro
                        </form:label>
                        <input type="hidden" id="modeloHidden" name="modeloHidden" />
                        <div class="col-lg-3 ">
                            <button id="btnSelModeloOpenModal" type="button" class="btn btn-default" onclick="abrirModalSeleccionarModelo();">Seleccionar</button>
                            <button type="button" class="btn btn-default" onclick="abrirModalAddModelo();">Agregar</button>
                            <br/>
                            <br/>
                            <table id="tablaModelo" class="table table-hover">
                                <c:choose>
                                    <c:when test="${comboForm.modelo eq null}">
                                        <thead>
                                            <tr>
                                                <th>Modelo seleccionado</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tr>
                                            <td>Nombre</td>
                                            <td>------</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${comboForm.modelo.nombre}</td>
                                        <script>
                                            cadjson = '{';
                                            cadjson += '"id":"${comboForm.modelo.id}",';
                                            cadjson += '"nombre": "${comboForm.modelo.nombre}"';
                                            cadjson += '}';
                                            mostrarEnTablaModeloSel(cadjson);
                                        </script>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <form:label path="precio" class="col-lg-3 control-label" >
                            Precio
                        </form:label>
                        <div class="col-lg-3 ">
                            <form:input path="precio" id="precio" class="form-control" placeholder="Introduce el Precio"/>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <spring:url value="/Combo/index.html" var="cancelURL" />
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-success left">Enviar</button>
                            <button type="reset" class="btn btn-info left">Limpiar</button>
                            <button type="button" class="btn btn-danger left" onclick="location.href = '${cancelURL}'">Cancelar</button>
                        </div>
                    </div>
                </form:form>
                <script type="text/javascript" src="<c:url value="/resources/js/formularios.js"/>"></script>
                <jsp:include page="formModalCombo.jsp" />
                <jsp:include page="formModalModelo.jsp" />
                <br/>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
