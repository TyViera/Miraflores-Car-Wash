
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="usuario" scope="request" class="com.miraflorescarwash.model.Usuario" />
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
                <h1 class="text-center">Usuario: <jsp:getProperty name="usuario" property="nombreCompleto" /></h1>
            </div>
            <hr/>
            <div class="container">
                <h3 class="text-left" style="text-decoration: underline;">Datos del usuario</h3>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Id</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="usuario" property="id" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Nombre Completo</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="usuario" property="nombreCompleto" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Nombre de Usuario</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="usuario" property="username" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Activo</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <c:choose>
                                    <c:when test="${usuario.enabled}" >
                                        Sí
                                    </c:when>
                                    <c:otherwise>
                                        No
                                    </c:otherwise>
                                </c:choose>
                            </p>
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
