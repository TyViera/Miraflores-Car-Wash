
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="rep" scope="request" class="com.miraflorescarwash.model.ClienteReporte" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>
        <title>Miraflores Car Wash</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.css"/>"/>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>

        <script type="text/javascript" src="<c:url value="/resources/js/Chart.js"/>"></script>

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
                <h1>Crédito disponible de los Clientes</h1>
                <spring:url value="/Reporte/cliente.html" var="url" />
                <form action="${url}" method="POST" id="formSelClienteEvo" name="formSelClienteEvo" class="form-inline">
                    <div class="form-group has-feedback" id="divError">
                        <input type="hidden" name="tipo" value="cre"/>
                        <input type="hidden" id="id" name="id"/>
                        <label id="clienteSel" class="control-label" >Cliente Seleccionado: </label>
                        <input id="txtCliente" class="form-control" type="text" name="txtCliente" readonly="true" placeholder="Cliente a Buscar" />
                        <button type="button" class="btn btn-default" 
                                onclick="$('#myModal').modal();
                                        $('#enviarForm').removeAttr('disabled');
                                        $('#divError').removeClass('has-error');
                                        $('#txtCliente').data('bootstrapValidator.error').html('').hide()">
                            Seleccionar...
                        </button>
                        <button type="submit" id="enviarForm" class="btn btn-primary">
                            Buscar <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </form>
                <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body">
                                <table id="events-table" 
                                       data-toggle="table" data-cache="false" 
                                       data-height="299" data-click-to-select="true" 
                                       data-show-refresh="true" data-show-toggle="true"
                                       data-show-columns="true" data-search="true" 
                                       data-select-item-name="toolbar1"
                                       data-select-item-name="radioName">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-radio="true"></th>
                                            <th data-field="id">Id</th>
                                            <th data-field="nombres">Nombres</th>
                                            <th data-field="apellidos">Apellidos</th>
                                            <th data-field="dni">Dni</th>
                                            <th data-field="telefono">Telefono</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${clientes}" var="cli">
                                        <tr>
                                            <td><span onclick="alert('haber');">aksdnaksn</span></td>
                                            <td>${cli.id}</td>
                                            <td>${cli.nombres}</td>
                                            <td>${cli.apellidos}</td>
                                            <td>${cli.dni}</td>
                                            <td>${cli.telefono}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <script>
                                    var posibleData;
                                    posibleData = '';
                                    $(function() {
                                        $('#txtCliente').next().click(function() {
                                            var $result = $('#events-result');
                                            $('#events-table').bootstrapTable({
                                            }).on('dbl-click-row.bs.table', function(e, row, $element) {
                                                $('#id').attr('value', row.id);
                                                posibleData = row.apellidos + ', ' + row.nombres;
                                                $('#btnSelModelo').attr('disabled', false);
                                            }).on('check.bs.table', function(e, row) {
                                                $('#id').attr('value', row.id);
                                                posibleData = row.apellidos + ', ' + row.nombres;
                                                $('#btnSelModelo').attr('disabled', false);
                                            });
                                        });
                                    });
                                </script>
                            </div>
                            <br/>
                            <br/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" 
                                        data-dismiss="modal">
                                    Cerrar
                                </button>
                                <button type="button" disabled="true" 
                                        id="btnSelModelo" class="btn btn-primary" 
                                        data-dismiss="modal"
                                        onclick="$('#txtCliente').attr('value', posibleData);">
                                    Seleccionar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
                <c:choose>
                    <c:when test="${credito == null}">
                        <div style="height: 150px;">
                            <h2 class="text-center">Sin datos</h2>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${credito.size() != 0}">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Nombres</label>
                                        <div class="col-lg-10">
                                            <p class="form-control-static">
                                                ${credito.get(0).nombres}
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Apellidos</label>
                                        <div class="col-lg-10">
                                            <p class="form-control-static">
                                                ${credito.get(0).apellidos}
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Crédito disponible</label>
                                        <div class="col-lg-10">
                                            <div class="form-control-static">
                                                <br/>
                                                <table class="table table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Modelo</th>
                                                            <th>Lavadas Disponibles</th>
                                                        </tr>
                                                    </thead>
                                                    <c:forEach items="${credito}" var="cre">
                                                        <tr>
                                                            <td>${cre.modeloCarro}</td>
                                                            <td>${cre.lavadas}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger" role="alert">
                                    <h2 class="text-center">El cliente no dispone de Crédito</h2>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
