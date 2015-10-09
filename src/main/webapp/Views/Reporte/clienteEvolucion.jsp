
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
        <!--To PDF-->
        <script type="text/javascript" src="<c:url value="/resources/js/pdf/basic.js"/>"></script>
    </head>
    <body>
        <nav>
            <jsp:include page="/templates/menu.jsp"/>
            <jsp:include page="/templates/banner.jsp"/>
        </nav>
        <section>
            <jsp:include page="/templates/header.jsp"/>
            <script type="text/javascript">
                var datosGrafico;
                datosGrafico = {
                    numeros:
                            [<c:forEach items="${clientesReporte}" var="cli">${cli.total},</c:forEach>],
                    etiquetas:
                            [<c:forEach items="${etiquetas}" var="et">"${et}",</c:forEach>]
                };
                console.log(JSON.stringify(datosGrafico));
                </script>
                <div class="container">
                    <h1>Evolución de los Clientes</h1>
                <spring:url value="/Reporte/cliente.html" var="url" />
                <form action="${url}" method="POST" id="formSelClienteEvo" name="formSelClienteEvo" class="form-inline">
                    <div class="form-group has-feedback" id="divError">
                        <input type="hidden" name="tipo" value="evo"/>
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
                <c:if test="${not empty clientesReporte}">
                    <div class="container">
                        <h4>Cliente: ${clientesReporte.get(0).apellido}, ${clientesReporte.get(0).nombre}</h4>
                        <button class="btn btn-danger" 
                                onclick="exportPDFReporteClienteEvo(
                                            '${pageContext.servletContext.contextPath}',
                                            '${clientesReporte.get(0).id}')">
                            Generar PDF
                        </button>
                    </div>
                </c:if>
                <canvas id="myChart" width="1000" height="300">

                </canvas>
                <script type="text/javascript">
                    var ctx = $("#myChart").get(0).getContext("2d");
                    var data1 = {
                        labels: datosGrafico.etiquetas,
                        datasets: [
                            {
                                label: "Clientes frecuentes",
                                fillColor: "rgba(77,140,182,0.2)",
                                strokeColor: "rgba(220,220,220,1)",
                                pointColor: "rgba(120,120,220,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(220,220,220,1)",
                                data: datosGrafico.numeros
                            }
                        ]
                    };
                </script>
                <c:if test="${not empty clientesReporte}">
                    <script type="text/javascript">
                        var myLineChart = new Chart(ctx).Line(data1, {
                            bezierCurve: false
                        });
                    </script>
                </c:if>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
