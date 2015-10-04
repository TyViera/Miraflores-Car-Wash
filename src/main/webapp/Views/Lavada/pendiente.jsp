
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
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.css"/>"/>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table-translate.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/formularios.js"/>"></script>
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
                <h1>Lavadas Pendientes</h1>
                <table id="events-table" 
                       data-toggle="table" data-cache="false" 
                       data-height="499" 
                       data-sort-name="fecha" data-sort-order="desc"
                       data-pagination="true"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-search="true" 
                       data-select-item-name="toolbar1"
                       >
                    <thead>
                        <tr>
                            <th  data-field="id" data-sortable="true">Id</th>
                            <th data-field="fecha" data-sortable="true">Fecha</th>
                            <th data-field="estado" data-sortable="true">Estado</th>
                            <th data-field="placa" data-sortable="true">Placa de Carro</th>
                            <th data-field="modelo" data-sortable="true">Modelo de Carro</th>
                            <th data-field="cliente" data-sortable="true">Cliente</th>
                            <th data-field="accion">Acción</th>
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
                                <button class="btn btn-success" 
                                        onclick="entregar(${lavada.id}, ${lavada.carro.cliente.telefono});">
                                    Entregar
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <script>
                function entregar(id, tel) {
                    $('#telModal').val(tel);
                    $('#id').val(id);
                    $('#myModal').modal();
                }

            </script>
            <div id="myModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Entregar Carro</h4>
                        </div>
                        <div id="modalBody" class="modal-body">
                            <form class="form-horizontal">
                                <input type="hidden" id="id" name="id" />
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Enviar SMS</label>
                                    <div class="col-lg-3 ">
                                        <input type="checkbox" class="form-control" id="chkEnviarSMS" name="sms"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Telefono</label>
                                    <div class="col-lg-3">
                                        <input type="text" id="telModal" class="form-control" readonly="true"/>
                                    </div>
                                </div>
                            </form>
                            <br/>
                            <br/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="button" id="btnOK" class="btn btn-primary" 
                                    data-dismiss="modal" onclick="sendValId('${pageContext.servletContext.contextPath}');">
                                Entregar
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
