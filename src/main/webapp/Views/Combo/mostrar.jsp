
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="combo" scope="request" class="com.miraflorescarwash.model.ComboPorModelo" />
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
                <h1 class="text-center">Combo: ${combo.combo.nombre}</h1>
            </div>
            <hr/>
            <div class="container">
                <h3 class="text-left" style="text-decoration: underline;">Datos del combo</h3>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Id</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <jsp:getProperty name="combo" property="id" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Nombre</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                ${combo.combo.nombre}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Lavadas</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                ${combo.combo.numeroLavadas}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Descripción</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                ${combo.combo.descripcion}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Modelo de Autos</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                ${combo.modelo.nombre}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Precio</label>
                        <div class="col-lg-10">
                            <p class="form-control-static">
                                S/. <jsp:getProperty name="combo" property="precio" />0
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
