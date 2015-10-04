
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
                <spring:url value="/Lavada/recargar.html" var="lavadaActionURL"/>
                <form:form action="${lavadaActionURL}" method="POST" modelAttribute="recargaForm"
                           id="recargaForm" name="recargaForm" class="form-horizontal">
                    <h2>Recarga de Lavadas</h2>
                    <form:hidden path="id" value="0" />
                    <form:hidden path="fechaRegistro" value="null" />
                    <form:hidden path="cliente" value="null" />
                    <form:hidden path="combopormodelo" value="null" />
                    <div class="form-group has-feedback">
                        <input type="hidden" id="idCliente" name="idCliente"/>
                        <label id="txtCliente" class="col-lg-3 control-label">Cliente</label>
                        <div class="col-lg-3 ">
                            <button id="btnSelModeloOpenModal" 
                                    type="button" 
                                    class="btn btn-default" 
                                    onclick="$('#myModalSelCliente').modal();">
                                Seleccionar
                            </button>
                            <br/>
                            <br/>
                            <table id="tablaCliente" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Cliente seleccionado</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tr>
                                    <td>Nombres</td>
                                    <td>------</td>
                                </tr>
                                <tr>
                                    <td>Apellidos</td>
                                    <td>------</td>
                                </tr>
                                <tr>
                                    <td>Dni</td>
                                    <td>------</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <label id="txtComboPorModelo" class="col-lg-3 control-label">Combo</label>
                        <input type="hidden" id="idCombo" name="idCombo"/>
                        <div class="col-lg-3 ">
                            <button id="btnSelComboPorModeloOpenModal" 
                                    type="button" 
                                    class="btn btn-default" >
                                Seleccionar
                            </button>
                            <br/>
                            <br/>
                            <table id="tablaCombo" class="table table-hover">
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
                                    <td>Modelo</td>
                                    <td>------</td>
                                </tr>
                                <tr>
                                    <td>Lavadas</td>
                                    <td>------</td>
                                </tr>
                                <tr>
                                    <td>Precio</td>
                                    <td>------</td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="form-group has-feedback">
                        <spring:url value="/Lavada/index.html" var="cancelURL" />
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-success left">Enviar</button>
                            <button type="reset" class="btn btn-info left">Limpiar</button>
                            <button type="button" class="btn btn-danger left" onclick="location.href = '${cancelURL}'">Cancelar</button>
                        </div>
                    </div>
                </form:form>
                <script type="text/javascript" src="<c:url value="/resources/js/formularios.js"/>"></script>
                <jsp:include page="../Reporte/selClienteModal.jsp"/>
                <jsp:include page="selComboPorModeloModal.jsp"/>
                <br/>
            </div>
            <script>
                                $('#btnSelCliente').click(function() {
                                    mostrarEnTablaCliente(posiblesDatosCliente);
                                });
                                $('#recargaForm').submit(function(event) {
                                    event.preventDefault();
                                    enviarFormulariosRecarga("${pageContext.servletContext.contextPath}")
                                });

            </script>
            <div id="myModalResult" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Resultado</h4>
                        </div>
                        <div class="modal-body">
                            <p id="textoResultado"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" 
                                    data-dismiss="modal">
                                Cerrar
                            </button>
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
