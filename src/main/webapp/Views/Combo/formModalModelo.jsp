<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="modalSelModelo" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Seleccionar Modelo</h4>
            </div>
            <div class="modal-body">
                <script>
                    var modeloSel;
                    modeloSel = "";
                </script>

                <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.css"/>"/>
                <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
                <table class="table table-hover" >
                    <thead>
                        <tr>
                            <th data-field="state" data-radio="true">Seleccionar</th>
                            <th data-field="nombre" data-align="left">Nombre</th>
                        </tr>
                    </thead>
                    <c:forEach items="${modelos}" var="mode">
                        <tr>
                            <td>
                                <input type="radio" name="radio" 
                                       onclick="modeloSel = this.value;
                        $('#btnSelModelo').attr('disabled', false)"
                                       value='{"id":"${mode.id}", "nombre": "${mode.nombre}"}' />
                            </td>
                            <td>${mode.nombre}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
            <br/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" disabled="true" 
                        id="btnSelModelo" class="btn btn-primary" 
                        data-dismiss="modal" 
                        onclick="mostrarEnTablaModeloSel(modeloSel);">
                    Seleccionar
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modalAddModelo" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Agregar Modelo</h4>
            </div>
            <div class="modal-body">
<%--                <script type="text/javascript" src="<c:url value="/resources/js/formularios.js"/>"></script>--%>
                <script type="text/javascript" src="<c:url value="/resources/js/validador.js"/>"></script>
                <spring:url value="/Combo/addmodelo.html" var ="actionFormModeloAdd"/>
                <form id="modeloAddForm" name="modeloAddForm" class="form-horizontal">
                    <input type="hidden" id="idModeloAdd" name="id" value="0" />
                    <div class="form-group has-feedback">
                        <label class="col-lg-3 control-label">Nombre</label>
                        <div class="col-lg-8 ">
                            <input type="text" id="nombreModeloAdd" name="nombre" class="form-control" placeholder="Introduce el Nombre"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" id="btnAddModelo" class="btn btn-primary" 
                        data-dismiss="modal" 
                        onclick="enviarFormularioModelo('${actionFormModeloAdd}');">
                    Agregar
                </button>
            </div>
        </div>
    </div>
</div>