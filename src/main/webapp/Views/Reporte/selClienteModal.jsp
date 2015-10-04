<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModalSelCliente" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <table id="events-table-selCliente" 
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
                            <th data-field="nombres">Nombres</th>
                            <th data-field="apellidos">Apellidos</th>
                            <th data-field="dni">Dni</th>
                            <th data-field="telefono">Telefono</th>
                        </tr>
                    </thead>
                    <c:forEach items="${clientes}" var="cli">
                        <tr>
                            <td><span onclick="alert('haber');">aksdnaksn</span></td>
                            <td>${cli.id}</td>
                            <td>${cli.nombres}</td>
                            <td>${cli.apellidos}</td>
                            <td>${cli.dni}</td>
                            <td>${cli.telefono}</td>
                        </tr>
                    </c:forEach>
                </table>
                <script>
                    var posibleData;
                    var posiblesDatosCliente;
                    posibleData = '';
                    $(function() {
                        $('#txtCliente').next().click(function() {
                            $('#events-table-selCliente').bootstrapTable({
                            }).on('dbl-click-row.bs.table', function(e, row, $element) {
                                $('#idCliente').attr('value', row.id);
                                posibleData = row.apellidos + ', ' + row.nombres;
                                $('#btnSelCliente').attr('disabled', false);
                                posiblesDatosCliente = {
                                    id : row.id,
                                    nombres : row.nombres,
                                    apellidos : row.apellidos,
                                    dni : row.dni,
                                    telefono : row.telefono
                                };
                            }).on('check.bs.table', function(e, row) {
                                $('#idCliente').attr('value', row.id);
                                posibleData = row.apellidos + ', ' + row.nombres;
                                $('#btnSelCliente').attr('disabled', false);
                                posiblesDatosCliente = {
                                    id : row.id,
                                    nombres : row.nombres,
                                    apellidos : row.apellidos,
                                    dni : row.dni,
                                    telefono : row.telefono
                                };
                            });
                        });
                    });
                </script>
            </div>
            <br/>
            <br/>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" 
                        data-dismiss="modal">
                    Cerrar
                </button>
                <button type="button" disabled="true" 
                        id="btnSelCliente" class="btn btn-primary" 
                        data-dismiss="modal"
                        onclick="$('#txtCliente').attr('value', posibleData);">
                    Seleccionar
                </button>
            </div>
        </div>
    </div>
</div>