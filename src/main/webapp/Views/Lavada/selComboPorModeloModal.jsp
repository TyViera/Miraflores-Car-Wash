<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var posibleDatosCPM;
</script>
<div id="myModalSelComboPorModelo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <table id="events-table-selCombo" 
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
                            <th data-field="idCombo" data-visible="false">IdCombo</th>
                            <th data-field="nombre">Nombre</th>
                            <th data-field="modelo">Modelo</th>
                            <th data-field="lavadas">Lavadas</th>
                            <th data-field="precio" data-formatter="precioFormatter">Precio</th>
                        </tr>
                    </thead>
                    <c:forEach items="${combos}" var="cli">
                        <tr>
                            <td></td>
                            <td>${cli.id}</td>
                            <td>${cli.combo.id}</td>
                            <td>${cli.combo.nombre}</td>
                            <td>${cli.modelo.nombre}</td>
                            <td>${cli.combo.numeroLavadas}</td>
                            <td>${cli.precio}</td>
                        </tr>
                    </c:forEach>
                </table>
                <script>
                    function precioFormatter(value, row) {
                        return 'S/. ' + value + '0';
                    }
                    $(function() {
                        $('#btnSelComboPorModeloOpenModal').click(function() {
                            $('#myModalSelComboPorModelo').modal();
                            $('#events-table-selCombo').bootstrapTable({
                            }).on('dbl-click-row.bs.table', function(e, row, $element) {
                                $('#idCombo').attr('value', row.id);
                                $('#btnSelComboPorModelo').attr('disabled', false);
                                posibleDatosCPM = {
                                    nombre: row.nombre,
                                    modelo: row.modelo,
                                    lavadas: row.lavadas,
                                    precio: row.precio
                                };
                            }).on('check.bs.table', function(e, row) {
                                $('#idCombo').attr('value', row.id);
                                $('#btnSelComboPorModelo').attr('disabled', false);
                                posibleDatosCPM = {
                                    nombre: row.nombre,
                                    modelo: row.modelo,
                                    lavadas: row.lavadas,
                                    precio: row.precio
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
                        id="btnSelComboPorModelo" class="btn btn-primary" 
                        data-dismiss="modal"
                        onclick="mostrarEnTablaComboPorModelo(posibleDatosCPM)">
                    Seleccionar
                </button>
            </div>
        </div>
    </div>
</div>