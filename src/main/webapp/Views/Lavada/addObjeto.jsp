<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="modalAddObjeto" class="modal fade" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Lavada De Carro - Objetos en Guantera</h4>
            </div>
            <div class="modal-body">
                <form id="formObjetosCustodia" name="formObjetosCustodia" class="form-horizontal">
                    <div class="form-group has-feedback">
                        <label class="col-lg-3 control-label">Nombre</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" id="nombreOBJ" name="nombre" placeholder="Ingrese el Nombre del Objeto" />
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label class="col-lg-3 control-label">Cantidad</label>
                        <div class="col-lg-8">
                            <input type="text" id="cantOBJ" name="cantidad" class="form-control" value="1" placeholder="Ingrese la cantidad de Objeto en el carro" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="hi();">
                    Agregar
                </button>
            </div>
            <script>

                function hi() {
                    var addHTML;
                    var obj;
                    obj = {
                        nombre : $('#nombreOBJ').val(),
                        cantidad : $('#cantOBJ').val()
                    };
                    objetosCustodia.push(obj);
                    addHTML = "<tr>";
                    addHTML += "    <td>";
                    addHTML += obj.nombre;
                    addHTML += "    </td>";
                    addHTML += "    <td>";
                    addHTML +=obj.cantidad;
                    addHTML += "    </td>";
                    addHTML += "</tr>";
                    $('#tablaObjetos').html($('#tablaObjetos').html() + addHTML);
                    $('#nombreOBJ').val("");
                    $('#cantOBJ').val("1");
                }
            </script>
        </div>
    </div>
</div>