<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="modalSelCombo" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Seleccionar Combo</h4>
            </div>
            <div class="modal-body">
                <table id="events-table-combo" 
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
                            <th data-field="lavadas">Lavadas</th>
                            <th data-field="descripcion">Descripción</th>
                        </tr>
                    </thead>
                    <c:forEach items="${combos}" var="cb">
                        <tr>
                            <td></td>
                            <td>${cb.id}</td>
                            <td>${cb.nombre}</td>
                            <td>${cb.numeroLavadas}</td>
                            <td>${cb.descripcion}</td>
                        </tr>
                    </c:forEach>
                </table>
                <script>
                    var posibleData;
                    posibleData = '';
                    $('#btnSelComboOpenModal').click(function() {
                        $('#events-table-combo').bootstrapTable({
                        }).on('dbl-click-row.bs.table', function(e, row, $element) {
//                            $('#id').attr('value', row.id);
                            posibleData = {
                                id: row.id,
                                nombre: row.nombre,
                                numeroLavadas: row.lavadas,
                                descripcion: row.descripcion
                            };
                            $('#btnSelCombo').removeAttr('disabled');
                        }).on('check.bs.table', function(e, row) {
//                            $('#id').attr('value', row.id);
                            posibleData = {
                                id: row.id,
                                nombre: row.nombre,
                                numeroLavadas: row.lavadas,
                                descripcion: row.descripcion
                            };
//                        $('#btnSelCombo').removeAttr('disabled');
                            $('#btnSelCombo').removeAttr('disabled')
                        });
                    });
                </script>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" id="btnSelCombo" class="btn btn-primary" 
                        data-dismiss="modal" disabled="true"
                        onclick="mostrarEnTablaComboSel(JSON.stringify(posibleData));">
                    Seleccionar
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modalAddCombo" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Agregar Combo</h4>
            </div>
            <div class="modal-body">
                <%--                <script type="text/javascript" src="<c:url value="/resources/js/formularios.js"/>"></script>--%>
                <!--<script type="text/javascript" src="<c:url value="/resources/js/validador.js"/>"></script>-->
                <spring:url value="/Combo/addcombo.html" var ="actionFormComboAdd"/>
                <form id="comboAddForm" name="comboAddForm" class="form-horizontal">
                    <input type="hidden" id="idComboAdd" name="id" value="0" />
                    <div class="form-group has-feedback">
                        <label class="col-lg-3 control-label">Nombre</label>
                        <div class="col-lg-8 ">
                            <input type="text" id="nombreComboAdd" name="nombre" class="form-control" placeholder="Introduce el Nombre"/>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label class="col-lg-3 control-label">Lavadas</label>
                        <div class="col-lg-8 ">
                            <input type="text" id="numeroLavadasComboAdd" name="numeroLavadas" class="form-control" placeholder="Introduce el Número de Lavadas"/>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label class="col-lg-3 control-label">Descipción</label>
                        <div class="col-lg-8 ">
                            <textarea id="descripcionComboAdd" name="descripcion"
                                      class="form-control" 
                                      placeholder="Introduce la descripción"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" id="btnAddCombo" class="btn btn-primary" 
                        data-dismiss="modal" 
                        onclick="enviarFormularioCombo('${actionFormComboAdd}');">
                    Agregar
                </button>
            </div>
        </div>
    </div>
</div>