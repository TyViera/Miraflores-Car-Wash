
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>
        <title>Miraflores Car Wash</title>
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
                            [<c:forEach items="${data}" var="dinero">${dinero},</c:forEach>],
                    etiquetas:
                            [<c:forEach items="${etiq}" var="et">"${et}",</c:forEach>]
                };
                console.log(JSON.stringify(datosGrafico));
                </script>
                <%
                    double total;
                    java.util.List<Double> lista;
                    
                    lista = (java.util.List)request.getAttribute("data");
                    total = 0;
                    for(Double r : lista){
                        total += r;
                    }
                %>
                <div class="container">
                    <h1>Ventas por ${tiempo}</h1>
                <div class="container">
                    <h4><b>Inicio:</b> ${tiempoInicio}</h4>
                    <h4><b>Fin:</b> ${tiempoFin}</h4>
                    <h4><b>Total Recaudado:</b> S/. <%=total%>0</h4>
                </div>
                <canvas id="myChart" class="center-block" width="1000" height="400">

                </canvas> 
                <script type="text/javascript">
                    var ctx = $("#myChart").get(0).getContext("2d");
                    var data1 = {
                        labels: datosGrafico.etiquetas,
                        datasets: [
                            {
                                label: "Ventas por ${tiempo}",
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
                    var myLineChart = new Chart(ctx).Line(data1, {
                        bezierCurve: false
                    });
                </script>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
