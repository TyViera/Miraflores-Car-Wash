<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="modalSelModelo" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Seleccionar Modelo</h4>
            </div>
            <div class="modal-body">
                <table id="events-table-modelo" 
                       data-toggle="table" data-cache="false" 
                       data-height="299" data-click-to-select="true" 
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-search="true" 
                       data-select-item-name="toolbar1"
                       data-select-item-name="radioName">
                    <thead>
                        <tr>
                            <th data-field="state" data-radio="true"></th>
                            <th data-field="id">Id</th>
                            <th data-field="nombre">Nombre</th>
                        </tr>
                    </thead>
                    <c:forEach items="${modelos}" var="mode">
                        <tr>
                            <td></td>
                            <td>${mode.id}</td>
                            <td>${mode.nombre}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
                <br/>
                <script>
                    $('#btnSelModeloOpenModal').click(function() {
                        var posibleData;
                        posibleData = '';
                        $('#events-table-modelo').bootstrapTable({
                        }).on('dbl-click-row.bs.table', function(e, row, $element) {
//                            $('#id').attr('value', row.id);
                            posibleData = {
                                id: row.id,
                                nombre: row.nombre
                            };
                            $('#btnSelModelo').removeAttr('disabled');
                        }).on('check.bs.table', function(e, row) {
//                            $('#id').attr('value', row.id);
                            posibleData = {
                                id: row.id,
                                nombre: row.nombre
                            };
//                        $('#btnSelCombo').removeAttr('disabled');
                            $('#btnSelModelo').removeAttr('disabled')
                        });
                    });
                </script>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" id="btnSelModelo" class="btn btn-primary" 
                        data-dismiss="modal" disabled="true" 
                        onclick="mostrarEnTablaModeloSel(JSON.stringify(posibleData));">
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