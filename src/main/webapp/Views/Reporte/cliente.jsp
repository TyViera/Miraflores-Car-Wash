
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="rep" scope="request" class="com.miraflorescarwash.model.ClienteReporte" />
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
            <script type="text/javascript" src="<c:url value="/resources/js/Chart.js"/>"></script>
            <script type="text/javascript">
                var datosGrafico;
                datosGrafico = {
                    numeros:
                            [<c:forEach items="${clientes}" var="cli">${cli.total},</c:forEach>],
                    etiquetas:
                            [<c:forEach items="${clientes}" var="cli">"${cli.id}",</c:forEach>]
                };
//                console.log(JSON.stringify(datosGrafico));
                </script>
                <div class="container">
                    <h1>${mensaje}</h1>
                <c:if test="${mensaje eq 'Clientes frecuentes'}" >
                    <button class="btn btn-danger" 
                            onclick="exportPDFReporteClientesFreq('${pageContext.servletContext.contextPath}');">
                        Exportar a PDF
                    </button>
                </c:if>
                <table width="100%">
                    <tr>
                        <td width="50%">
                            <table class="table table-hover" height="300">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Dinero</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${clientes}" var="cli" >
                                    <tr>
                                        <td>${cli.id}</td>
                                        <td>${cli.apellido}, ${cli.nombre}</td>
                                        <td>S/. ${cli.total}0</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td width="10%">

                        </td>
                        <td width="40%">
                            <canvas id="myChart" height="300" width="${clientes.size()*50}">
                            </canvas> 
                        </td>
                    </tr>
                </table>
                <script type="text/javascript">
                    var ctx = $("#myChart").get(0).getContext("2d");
                    var data1 = {
                        labels: datosGrafico.etiquetas,
                        datasets: [
                            {
                                label: "Clientes frecuentes",
                                fillColor: "rgba(77,140,182,0.5)",
                                strokeColor: "rgba(220,220,220,1)",
                                pointColor: "rgba(220,220,220,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(220,220,220,1)",
                                data: datosGrafico.numeros
                            }
                        ]
                    };
                    var myLineChart = new Chart(ctx).Bar(data1, {
                        barShowStroke: false
                    });
                </script>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
