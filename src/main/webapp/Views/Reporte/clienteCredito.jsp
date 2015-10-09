
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
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table-translate.js"/>"></script>

        <script type="text/javascript" src="<c:url value="/resources/js/Chart.js"/>"></script>

        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapValidator.min.css" />" />
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrapValidator.min.js" />" ></script>
        <script type="text/javascript" src="<c:url value="/resources/js/validador.js"/>"></script>
        
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
                <h1>Crédito disponible de los Clientes</h1>
                <spring:url value="/Reporte/cliente.html" var="url" />
                <form action="${url}" method="POST" id="formSelClienteEvo" name="formSelClienteEvo" class="form-inline">
                    <div class="form-group has-feedback" id="divError">
                        <input type="hidden" name="tipo" value="cre"/>
                        <input type="hidden" id="idCliente" name="id"/>
                        <label id="clienteSel" class="control-label" >Cliente Seleccionado: </label>
                        <input id="txtCliente" class="form-control" type="text" name="txtCliente" readonly="true" placeholder="Cliente a Buscar" />
                        <button type="button" class="btn btn-default" 
                                onclick="$('#myModalSelCliente').modal();
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
                <jsp:include page="selClienteModal.jsp"/>
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
                                <button class="btn btn-danger" 
                                        onclick="exportPDFCreditoCliente('${pageContext.servletContext.contextPath}',${credito.get(0).id})">
                                    Exportar a PDF
                                </button>
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
