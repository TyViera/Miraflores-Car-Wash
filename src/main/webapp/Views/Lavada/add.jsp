
<%@page import="com.miraflorescarwash.service.CarroService"%>
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

        <script type="text/javascript" src="<c:url value="/resources/js/formularios.js"/>"></script>
        <script>
            var objetosCustodia;
            objetosCustodia = [];
        </script>
    </head>
    <body>
        <div id="contenido">
            <nav>
                <jsp:include page="/templates/menu.jsp"/>
                <jsp:include page="/templates/banner.jsp"/>
            </nav>
            <section>
                <jsp:include page="/templates/header.jsp"/>
                <div id="myModal" class="modal fade" >
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Lavada De Carro</h4>
                            </div>
                            <div class="modal-body">
                                <div id="divTextomodal">
                                    <p id="textoModal">
                                    </p>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <spring:url value="/Lavada/add.html" var="urlForm"/>
                    <form:form id="lavadaForm" name="lavadaForm" action="${urlForm}" method="POST" modelAttribute="lavadaForm" class="form-horizontal">
                        <h2>Formularios de Lavadas</h2>
                        <form:hidden path="id" id="id" value="0" />
                        <form:hidden path="fechaLavado" value="null" />
                        <form:hidden path="carro" value="null" />
                        <form:hidden path="estado" value="" />
                        <div class="form-group has-feedback">
                            <label class="col-lg-3 control-label">Placa Auto</label>
                            <div class="col-lg-3 ">
                                <input type="text" name="placa" id="placa" class="form-control" placeholder="Ingrese la placa del carro"/>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-lg-3 control-label">Dejar Carro en Custodia</label>
                            <div class="col-lg-3">
                                <input type="checkbox" id="estadoVal" value="1" />
                            </div>
                        </div>
                        <div id="divTablaCustodia" class="form-group has-feedback hidden">
                            <label class="col-lg-3 control-label">Objetos en Custodia</label>
                            <div class="col-lg-3">
                                <button type="button" class="btn btn-default"
                                        onclick="$('#modalAddObjeto').modal();">
                                    Agregar Objeto
                                </button>
                                <table id="tablaObjetos" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Objeto</th>
                                            <th>Cantidad</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <spring:url value="/Lavada/index.html" var="cancelURL" />
                            <div class="col-lg-9 col-lg-offset-3">
                                <button id="btnAddLavada" type="submit" class="btn btn-success left">Enviar</button>
                                <button type="reset" class="btn btn-info left">Limpiar</button>
                                <button type="button" class="btn btn-danger left" onclick="location.href = '${cancelURL}'">Cancelar</button>
                            </div>
                        </div>
                    </form:form>
                    <jsp:include page="addObjeto.jsp"/>
                    <script>
                        $('#estadoVal').change(function(event) {
                            if ($('#estadoVal').is(':checked')) {
                                $('#divTablaCustodia').removeClass("hidden");
                            } else {
                                $('#divTablaCustodia').addClass("hidden");
                            }
                        });
                        $('#lavadaForm').submit(function(event) {
//                            alert("asda");
                            alert("los daots " + JSON.stringify(objetosCustodia));
                            var placa = $('#placa').val();
                            var baseURL;
                            var url;
                            var idCarro = 0;
                            baseURL = "${pageContext.servletContext.contextPath}";
                            url = baseURL + "/Lavada/buscarAuto.html";
                            event.preventDefault();
                            $.ajax({
                                data: placa,
                                url: url,
                                contentType: 'text/html',
                                type: 'POST',
                                error: function(jqXHR, textStatus, errorThrown) {
                                    var txt;
                                    txt = "La placa no coincide con ninguna ";
                                    txt += "registrada en nuestras bases de datos. ";
                                    txt += "Por favor ingrese otra";
                                    $('#textoModal').html(txt);
                                    $('#myModal').modal();
                                }, success: function(data, textStatus, jqXHR) {
                                    if (data !== null && data !== "" && data !== "0") {
                                        idCarro = data;
                                        enviarDatosLavada(idCarro, baseURL);
                                    } else {
                                        var txt;
                                        idCarro = 0;
                                        txt = "La placa no coincide con ninguna ";
                                        txt += "registrada en nuestras bases de datos. ";
                                        txt += "Por favor ingrese otra";
                                        $('#textoModal').html(txt);
                                        $('#myModal').modal();
                                        //$('#lavadaForm').submit();
                                    }
                                }
                            });
                        });
                    </script>
                    <br/>
                </div>
            </section>
            <footer>
                <jsp:include page="/templates/footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
