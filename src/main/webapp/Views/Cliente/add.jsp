
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>

        <title>Miraflores Car Wash</title>


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
                <div id="myModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Carro</h4>
                            </div>
                            <div class="modal-body">
                                <p>${err}</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty err}">
                    <script type="text/javascript">
                        $('#myModal').modal();
                    </script>
                </c:if>
                <c:choose>
                    <c:when test="${clienteForm.id == null || clienteForm.id == 0}">
                        <spring:url value="/Cliente/add.html" var="clienteActionURL"/>
                    </c:when>
                    <c:otherwise>
                        <spring:url value="/Cliente/update.html" var="clienteActionURL"/>
                    </c:otherwise>
                </c:choose>

                <form:form action="${clienteActionURL}" method="POST" modelAttribute="clienteForm"
                           id="clienteForm" name="clienteForm" class="form-horizontal">
                    <h2>Formularios de Clientes</h2>
                    <form:hidden path="id" />
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="nombres" class="col-lg-3 control-label" >Nombres</form:label>
                            <div class="col-lg-3 ">
                            <form:input path="nombres" class="form-control" placeholder="Introduce tus Nombres" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="apellidos" class="col-lg-3 control-label">Apellidos</form:label>
                            <div class="col-lg-3">
                            <form:input path="apellidos" class="form-control" placeholder="Introduce tus Apellidos" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="dni" class="col-lg-3 control-label">Dni</form:label>
                            <div class="col-lg-3">
                            <form:input path="dni" class="form-control" placeholder="Introduce tu Dni" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="email" class="col-lg-3 control-label" >Email</form:label>
                            <div class="col-lg-3">
                            <form:input path="email" class="form-control" placeholder="Introduce tu Email"/>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <!--<label for="nombres">Nombres</label>-->
                        <form:label path="telefono" class="col-lg-3 control-label">Telefono</form:label>
                            <div class="col-lg-3">
                            <form:input path="telefono" class="form-control" placeholder="Introduce tu Telefono"/>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <spring:url value="/Cliente/index.html" var="cancelURL" />
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-success left">Enviar</button>
                            <button type="reset" class="btn btn-info left">Limpiar</button>
                            <button type="button" class="btn btn-danger left" onclick="location.href = '${cancelURL}'">Cancelar</button>
                        </div>
                    </div>
                </form:form>
                <br/>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
