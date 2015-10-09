
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
                <h1>Reporte de Combos</h1>
                <spring:url value="/Reporte/cliente.html" var="url" />
                <hr/>
                <%
                    double total;
                    java.util.List<com.miraflorescarwash.model.ComboReporte> lista;

                    lista = (java.util.List) request.getAttribute("combosReporte");
                    total = 0;
                    for (com.miraflorescarwash.model.ComboReporte r : lista) {
                        total += r.getCantidad();
                    }
                %>
                <h3>Total Recaudado: S/. <%=total%>0</h3>
                <div class="container">
                    <h4>${clientesReporte.get(0).apellido}, ${clientesReporte.get(0).nombre}</h4>
                </div>
                <script type="text/javascript">
                    var data1;
                    data1 = [
                    <c:forEach items="${combosReporte}" var="cb">
                        {
                            value: ${cb.cantidad},
                            color: colorAleatorio(),
//                            highlight: colorAleatorio(),
                            label: "${cb.nombre}"
                        },
                    </c:forEach>
                    ];
                    console.log(JSON.stringify(data1));
                    function aleatorio(inferior, superior) {
                        var numPosibilidades;
                        var aleat;
                        numPosibilidades = superior - inferior;
                        aleat = Math.random() * numPosibilidades;
                        aleat = Math.floor(aleat);
                        return parseInt(inferior) + aleat;
                    }
                    function colorAleatorio() {
                        var color_aleatorio;
                        var hexadecimal;
                        var posarray;
                        hexadecimal = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F");
                        color_aleatorio = "#";
                        for (i = 0; i < 6; i++) {
                            posarray = aleatorio(0, hexadecimal.length)
                            color_aleatorio += hexadecimal[posarray]
                        }
                        return color_aleatorio;
                    }
                </script>
                <div class="container">
                    <button class="btn btn-danger"
                            onclick="exportPDFReporteCombos('${pageContext.servletContext.contextPath}');">
                        Exportar a PDF
                    </button>
                    <table width="100%">
                        <tr>
                            <td width="70%">
                                <table class="table table-hover" height="300">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th>Dinero Recuadado</th>
                                            <th>Porcentaje Ventas</th>
                                            <th>Color</th>
                                        </tr>
                                    </thead>
                                    <%
                                        int i;
                                        i = 0;
                                    %>
                                    <c:forEach items="${combosReporte}" var="cb" >
                                        <tr>
                                            <td>${cb.id}</td>
                                            <td>${cb.nombre}</td>
                                            <td>S/. ${cb.cantidad}0</td>
                                            <td>
                                                <script>
                                                    var porc;
                                                    porc = ${cb.cantidad} /<%=total%>;
                                                    porc = Math.round(porc * 10000) / 10000;
                                                    porc = porc * 100;
                                                    document.write(porc + "%");
                                                </script>
                                            </td>
                                            <td>
                                                <canvas
                                                    id="cv_${cb.id}" style="max-height: 50px;"></canvas>
                                                <script>
                                                    var a = data1[<%=i%>].color;
                                                    var cuad;
                                                    cuad = 50;
                                                    var miDibujo = $('#cv_${cb.id}').get(0).getContext("2d");
                                                    miDibujo.rect(cuad, cuad, cuad, cuad);
                                                    miDibujo.fillStyle = a;
                                                    miDibujo.fill();
                                                </script>
                                            </td>
                                        </tr>
                                        <%
                                            i++;
                                        %>
                                    </c:forEach>
                                </table>
                            </td>
                            <td width="10%">

                            </td>
                            <td width="20%">
                                <h4><b>Gráfico</b></h4>
                                <canvas id="myChart" height="300">
                                </canvas>
                            </td>
                        </tr>
                    </table>
                </div>
                <c:if test="${not empty combosReporte}">
                    <script type="text/javascript">
                        var ctx = $("#myChart").get(0).getContext("2d");
                        var myLineChart = new Chart(ctx).Pie(data1, {
                            animateScale: true
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
