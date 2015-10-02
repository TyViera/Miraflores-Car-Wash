
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="cliente" scope="request" class="com.miraflorescarwash.model.Cliente" />
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
                <h1 class="text-center">Cliente: <jsp:getProperty name="cliente" property="nombres" /></h1>
            </div>
            <hr/>
            <div class="container">
                <h3 class="text-left" style="text-decoration: underline;">Datos del cliente</h3>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Id</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="cliente" property="id" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Nombres</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="cliente" property="nombres" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Apellidos</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="cliente" property="apellidos" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Dni</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="cliente" property="dni" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Email</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="cliente" property="email" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Telefono</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="cliente" property="telefono" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Autos</label>
                        <div class="col-lg-10">
                            <div class="form-control-static">
                                <table class="table table-hover">
                                    <c:choose>
                                        <c:when test="${cliente.carros == null || cliente.carros.isEmpty()}">
                                            <tr>
                                                <td>
                                                    <p class="form-control-static">No hay resultados</p>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Modelo</th>
                                                    <th>Marca</th>
                                                    <th>Placa</th>
                                                </tr>
                                            </thead>
                                            <c:forEach items="${cliente.carros}" var="carro">
                                                <tr>
                                                    <td>${carro.id}</td>
                                                    <td>${carro.modelo.nombre}</td>
                                                    <td>${carro.marca}</td>
                                                    <td>${carro.placa}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                    <tr>
                                        <td>
                                            <spring:url value="/Carro/add.html?cliente=${cliente.id}" var="urlAddAuto" />
                                            <button type="button" class="btn btn-info" 
                                                    onclick="location.href = '${urlAddAuto}'">
                                                Agregar Nuevo Auto
                                            </button>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
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
