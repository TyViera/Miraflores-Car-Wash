
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
                <h1>Reportes</h1>
                <hr/>
                <div class="jumbotron container-fluid" style="background-color: rgba(155,220,50,0.3);">
                    <div class="row">
                        <div class="col-md-6">
                            <h2>Ventas</h2>
                            <p class="text-justify" >
                                Con el reporte de Ventas, usted puede ver la información 
                                de las ventas y entradas de dinero del negocio en un determinado
                                tiempo, el cual puede ser diario, semanal o mensual.
                            </p>
                            <spring:url value="/Reporte/ventas.html?tiempo=dia" var="url" />
                            <button type="button" class="btn btn-default"
                                    onclick="location.hre f = '${url}'
                                                    ;">
                                Ir a Ventas
                            </button>
                        </div>
                        <div class="col-md-6">
                            <img alt="Ventas" src="<c:url value="/resources/img/20607-NSPEU7.jpg"/>"
                                 height="300" width="450" style="border-radius: 10px;" />

                        </div>
                    </div>
                </div>
                <div class="jumbotron container-fluid" style="background-color: rgba(155,220,50,0.3);">
                    <div class="row">
                        <div class="col-md-6">
                            <h2>Clientes</h2>
                            <p class="text-justify" >
                                Con el reporte de Clientes, usted puede ver quienes son los clientes
                                que más gastan en el negocio, también puede ver la evolución mensual
                                de gastos de un cliente determinado en el negocio de la misma manera 
                                saber cuanto crédito tiene disponible un cliente para futuras 
                                lavadas.
                            </p>
                            <spring:url value="/Reporte/cliente.html?tipo=fre" var="url" />
                            <button type="button" class="btn btn-default"
                                    onclick="location.href = '${url}';">
                                Ir a Clientes
                            </button>
                        </div>
                        <div class="col-md-6">
                            <img alt="Ventas" src="<c:url value="/resources/img/7 graph.jpg"/>"
                                 height="300" width="450" style="border-radius: 10px;" />

                        </div>
                    </div>
                </div>
                <div class="jumbotron container-fluid" style="background-color: rgba(155,220,50,0.3);">
                    <div class="row">
                        <div class="col-md-6">
                            <h2>Combos</h2>
                            <p class="text-justify" >
                                Con el reporte de Combos, usted puede ver la información 
                                de que tanto se venden los combos.
                            </p>
                            <spring:url value="/Reporte/combos.html" var="url" />
                            <button type="button" class="btn btn-default"
                                    onclick="location.href = '${url}';">
                                Ir a Combos
                            </button>
                        </div>
                        <div class="col-md-6">
                            <img alt="Ventas" src="<c:url value="/resources/img/4-150-01.jpg"/>"
                                 height="300" width="450" style="border-radius: 10px;" />

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
