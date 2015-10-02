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
                <script>
                    var comboSel;
                    comboSel = "";
                </script>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Sel&hellip;</th>
                            <th>Nombre</th>
                            <th>Lavadas</th>
                            <th>Descripción</th>
                        </tr>
                    </thead>
                    <c:forEach items="${combos}" var="cb">
                        <tr>
                            <td>
                                <input type="radio" name="radio" 
                                       onclick="comboSel = this.value;
                        $('#btnSelCombo').attr('disabled', false)"
                                       value='{"id":"${cb.id}", "descripcion": "${cb.descripcion}", "nombre":"${cb.nombre}", "numeroLavadas":"${cb.numeroLavadas}"}' />
                            </td>
                            <td>${cb.nombre}</td>
                            <td>${cb.numeroLavadas}</td>
                            <td>${cb.descripcion}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" disabled="true" 
                        id="btnSelCombo" class="btn btn-primary" 
                        data-dismiss="modal" 
                        onclick="mostrarEnTablaComboSel(comboSel);">
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
                <script type="text/javascript" src="<c:url value="/resources/js/validador.js"/>"></script>
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