
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="lavada" scope="request" class="com.miraflorescarwash.model.Lavada" />
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
                <h1 class="text-center">Lavada: <jsp:getProperty name="lavada" property="id" /></h1>
            </div>
            <hr/>
            <div class="container">
                <h3 class="text-left" style="text-decoration: underline;">Datos del lavada</h3>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Id</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="lavada" property="id" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Cliente</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                ${lavada.carro.cliente.apellidos}, ${lavada.carro.cliente.nombres}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Fecha</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="lavada" property="fechaLavado" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Estado</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <c:choose>
                                    <c:when test="${lavada.estado eq 'REA'}">
                                        Realizado
                                    </c:when>
                                    <c:otherwise>
                                        Pendiente de Entrega
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Carro</label>
                        <br/>
                        <div class="col-lg-10">
                            <div class="form-control-static">
                                <table class="table table-hover">
                                    <tr>
                                        <td>Modelo</td>
                                        <td>${lavada.carro.modelo.nombre}</td>
                                    </tr>
                                    <tr>
                                        <td>Marca</td>
                                        <td>${lavada.carro.marca}</td>
                                    </tr>
                                    <tr>
                                        <td>Placa</td>
                                        <td>${lavada.carro.placa}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Objetos en Custodia</label>
                        <div class="col-lg-10">
                            <div class="form-control-static">
                                <table class="table table-hover">
                                    <c:choose>
                                        <c:when test="${lavada.objetosEnCustodia == null || lavada.objetosEnCustodia.isEmpty()}">
                                            <tr>
                                                <td>
                                                    <p class="text-center form-control-static">No hay resultados</p>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Objeto</th>
                                                    <th>Cantidad</th>
                                                </tr>
                                            </thead>
                                            <c:forEach items="${lavada.objetosEnCustodia}" var="obj">
                                                <tr>
                                                    <td>${obj.id}</td>
                                                    <td>${obj.nombre}</td>
                                                    <td>${obj.cantidad}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
