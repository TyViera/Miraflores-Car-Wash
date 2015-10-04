function post(path, params, method) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}

$('#carroForm').submit(function(event) {
    var datos = new Object();
    var id;

    event.preventDefault();

    if ($('#id').val() === '') {
        id = 0;
    } else {
        id = $('#id').val();
    }

    datos = {
        id: id,
        placa: $('#placa').val(),
        marca: $('#marca').val(),
        modelo: {
            id: $('#modelo').val()
        },
        cliente: {
            id: $('#cliente').val()
        }
    };
    console.log("%%%%%%" + JSON.stringify(datos));
//    console.log($('#url').val());
    $.ajax({
        url: $('#url').val(),
        type: 'POST',
        data: JSON.stringify(datos),
        contentType: 'application/json',
        error: function(jqXHR, textStatus, errorThrown) {
            $('#textoModal').html("Ocurrió algun error con los datos");
            $('#myModal').modal();
        }, success: function(data, textStatus, jqXHR) {
//            alert("success");
            switch (data) {
                case "OK":
                    $('#textoModal').html("Operación realizada correctamente");
                    $('#myModal').modal();
                    setTimeout(function() {
                        window.location.href = $('#baseUrl').html() + "/Carro/index.html";
                    }, 1000);
                    break;
                case "PR":
                    $('#textoModal').html("Ya existe un auto registrado con esta placa");
                    $('#myModal').modal();
                    break;
                case "ER":
                default :
                    $('#textoModal').html("Ocurrió algun error con los datos");
                    $('#myModal').modal();
                    break;
            }
        }
    });
});

function abrirModalSeleccionarCombo() {
    $('#modalSelCombo').modal();
}

function abrirModalSeleccionarModelo() {
    $('#modalSelModelo').modal();
}

function abrirModalAddModelo() {
    $('#modalAddModelo').modal();
}

function abrirModalAddCombo() {
    $('#modalAddCombo').modal();
}

function mostrarEnTablaComboSel(comboSel) {
    var nuevoContenido;

//    console.log(comboSel);
    var objeto = JSON.parse(comboSel);

//    console.log(typeof objeto);
    nuevoContenido = "<thead>\n";
    nuevoContenido += " <tr>\n";
    nuevoContenido += "     <th>Combo seleccionado</th>\n";
    nuevoContenido += "     <th></th>\n";
    nuevoContenido += " </tr>\n";
    nuevoContenido += "</thead>\n";
    nuevoContenido += "<tbody>\n";
    nuevoContenido += "<tr>\n";
    nuevoContenido += " <td>Nombre</td>\n";
    nuevoContenido += " <td>" + objeto.nombre + "</td>\n";
    nuevoContenido += "</tr>\n";
    nuevoContenido += "<tr>\n";
    nuevoContenido += " <td>Lavadas</td>\n";
    nuevoContenido += " <td>" + objeto.numeroLavadas + "</td>\n";
    nuevoContenido += "</tr>\n";
    nuevoContenido += "</tbody>\n";
    $('#tablaCombo').html(nuevoContenido);
    $('#comboHidden').attr("value", JSON.stringify(objeto));
}

function mostrarEnTablaModeloSel(modeloSel) {
    var nuevoContenido;

    console.log(modeloSel);
    var objeto = JSON.parse(modeloSel);

    console.log(objeto);
    nuevoContenido = "<thead>\n";
    nuevoContenido += " <tr>\n";
    nuevoContenido += "     <th>Combo seleccionado</th>\n";
    nuevoContenido += "     <th></th>\n";
    nuevoContenido += " </tr>\n";
    nuevoContenido += "</thead>\n";
    nuevoContenido += "<tbody>\n";
    nuevoContenido += "<tr>\n";
    nuevoContenido += " <td>Nombre</td>\n";
    nuevoContenido += " <td>" + objeto.nombre + "</td>\n";
    nuevoContenido += "</tr>\n";
    nuevoContenido += "</tbody>\n";
    $('#tablaModelo').html(nuevoContenido);
    $('#modeloHidden').attr("value", JSON.stringify(objeto));
}

function enviarFormularioModelo(url) {
    var modeloSel;

    modeloSel = {
        id: $('#idModeloAdd').val(),
        nombre: $('#nombreModeloAdd').val()
    };
    $.ajax({
        url: url,
        type: 'POST',
        data: JSON.stringify(modeloSel),
        contentType: 'application/json',
        success: function(data, textStatus, jqXHR) {
            modeloSel.id = data;
            mostrarEnTablaModeloSel(JSON.stringify(modeloSel));
        }

    });

}

function enviarFormularioCombo(url) {
    var comboSel;
    comboSel = {
        id: $('#idComboAdd').val(),
        nombre: $('#nombreComboAdd').val(),
        numeroLavadas: $('#numeroLavadasComboAdd').val(),
        descripcion: $('#descripcionComboAdd').val()
    };
    $.ajax({
        url: url,
        type: 'POST',
        data: JSON.stringify(comboSel),
        contentType: 'application/json',
        success: function(data, textStatus, jqXHR) {
            comboSel.id = data;
            mostrarEnTablaComboSel(JSON.stringify(comboSel));
        }

    });
}

$('#comboForm').submit(function() {
    var datos;
    var id;
    console.log("1");
    if ($('#id').val() === '') {
        id = 0;
    } else {
        id = $('#id').val();
    }
    datos = {
        id: id,
        combo: JSON.parse($('#comboHidden').val()),
        modelo: JSON.parse($('#modeloHidden').val()),
        precio: $('#precio').val()
    };
    console.log(datos);
    console.log(" A la URL " + $('#urlCombo').html());
    $.ajax({
        url: $('#urlCombo').html(),
        type: 'POST',
        data: JSON.stringify(datos),
        contentType: 'application/json',
        success: function(data, textStatus, jqXHR) {
//            alert("todo ok");
        }
    });
    console.log("enviarems " + JSON.stringify(datos));
});

function enviarDatosLavada(idCarro, baseURL) {
    var estado;
    var ff;
    var datos;
    var urlDest;

    ff = $('#estadoVal');

    if (ff.is(':checked')) {
        estado = "PDT";
        datos = {
            id: $('#id').val(),
            fechaLavado: new Date(),
            estado: "PDT",
            carro: {
                id: idCarro
            },
            objetosEnCustodia: objetosCustodia
        };
    } else {
        estado = "REA";
        datos = {
            id: $('#id').val(),
            fechaLavado: new Date(),
            estado: "REA",
            carro: {
                id: idCarro
            }
        };
    }
    console.log(JSON.stringify(datos));

    urlDest = baseURL + "/Lavada/add.html";

    $.ajax({
        url: urlDest,
        type: 'POST',
        data: JSON.stringify(datos),
        dataType: 'html',
        contentType: 'application/json',
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("error");
        }, complete: function(jqXHR, textStatus) {
            console.log("complete");
        }, success: function(data, textStatus, jqXHR) {
            var txt;
            var claseAgregar;
            var claseQuitar;
            var todoOK;
            console.log("suc");
            console.log(data);
            urlDest = baseURL + "/Lavada/index.html";

            todoOK = false;
            switch (data) {
                case "OK":
                    txt = "Datos Agregados Correctamente";
                    claseAgregar = "alert-success";
                    claseQuitar = "alert-danger";
                    todoOK = true;
                    break;
                case "SC":
                    txt = "Usted no dispone de Cŕedito suficiente.";
                    claseAgregar = "alert-danger";
                    claseQuitar = "alert-success";
                    break;
                case "ER":
                    txt = "La placa no coincide con ninguna ";
                    txt += "registrada en nuestras bases de datos. ";
                    txt += "Por favor ingrese otra";
                    claseAgregar = "alert-danger";
                    claseQuitar = "alert-success";
                    break;
                default:
                    txt = "La placa no coincide con ninguna ";
                    txt += "registrada en nuestras bases de datos. ";
                    txt += "Por favor ingrese otra";
                    claseAgregar = "alert-danger";
                    claseQuitar = "alert-success";
                    break;
            }

            $('#textoModal').html(txt);
            $('#myModal').modal();

            if (todoOK) {
                setTimeout(function() {
                    window.location.href = urlDest;
                }, 1000);
            }
        }
    });
    //formulario.submit();
    console.log("a la url " + urlDest);
    console.log(JSON.stringify(datos));
}

function sendValId(urlBase) {
    var datos;
    datos = {
        id: $('#id').val()
    };
    $.ajax({
        data: JSON.stringify(datos),
        type: 'POST',
        url: urlBase + "/Lavada/marcarRealizada.html",
        contentType: 'application/json',
        success: function(data, textStatus, jqXHR) {
            var html;
            var redir;
            html = "<div>";
            html += "<p>";
            if (data === "OK") {
                html += "Actualización Correcta";
                redir = true;
            } else {
                html += "Ocurrió un error, por favor intentelo otra vez";
                redir = false;
            }
            html += "</p>";
            html += "</div>";
            $('#modalBody').html(html);
            $('#btnOK').hide();
            $('#myModal').modal();
            if (redir) {
                setTimeout(function() {
                    window.location.href = urlBase + "/Lavada/pendientes.html";
                }, 1000);
            }
            console.log(data);
        }
    });
}

function mostrarEnTablaComboPorModelo(datos) {
    var nuevoHTML;
    nuevoHTML = "<thead>";
    nuevoHTML += "  <tr>";
    nuevoHTML += "      <th>Combo seleccionado</th>";
    nuevoHTML += "      <th></th>";
    nuevoHTML += "  </tr>";
    nuevoHTML += "</thead>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Nombre</td>";
    nuevoHTML += "  <td>" + datos.nombre + "</td>";
    nuevoHTML += "</tr>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Modelo</td>";
    nuevoHTML += "  <td>" + datos.modelo + "</td>";
    nuevoHTML += "</tr>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Lavadas</td>";
    nuevoHTML += "  <td>" + datos.lavadas + "</td>";
    nuevoHTML += "</tr>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Precio</td>";
    nuevoHTML += "  <td>S/. " + datos.precio + "0</td>";
    nuevoHTML += "</tr>";
    $('#tablaCombo').html(nuevoHTML);
}

function mostrarEnTablaCliente(datos) {
    var nuevoHTML;
    nuevoHTML = "<thead>";
    nuevoHTML += "  <tr>";
    nuevoHTML += "      <th>Cliente seleccionado</th>";
    nuevoHTML += "      <th></th>";
    nuevoHTML += "  </tr>";
    nuevoHTML += "</thead>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Nombres</td>";
    nuevoHTML += "  <td>" + datos.nombres + "</td>";
    nuevoHTML += "</tr>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Apellidos</td>";
    nuevoHTML += "  <td>" + datos.apellidos + "</td>";
    nuevoHTML += "</tr>";
    nuevoHTML += "<tr>";
    nuevoHTML += "  <td>Dni</td>";
    nuevoHTML += "  <td>" + datos.dni + "</td>";
    nuevoHTML += "</tr>";
    $('#tablaCliente').html(nuevoHTML);
}

function enviarFormulariosRecarga(urlBase) {
    var datos;
    var id;
    id = $('#id').val();
    datos = {
        id: id,
        fechaRegistro: new Date(),
        cliente: {
            id: $('#idCliente').val()
        },
        combopormodelo: {
            id: $('#idCombo').val()
        }
    };
    console.log(JSON.stringify(datos));
    $.ajax({
        url: urlBase + "/Lavada/recargar.html",
        type: 'POST',
        data: JSON.stringify(datos),
        contentType: 'application/json',
        success: function(data, textStatus, jqXHR) {
            console.log(data);
            switch (data) {
                case "OK":
                    $('#textoResultado').html("¡Compra Correcta!");
                    $('#myModalResult').modal();
                    setTimeout(function() {
                        window.location.href = urlBase + "/Lavada/index.html";
                    }, 1000);
                    break;
                case "ER":
                default:
                    $('#textoResultado').html("¡Ocurrió un error! Por favor intentelo otra vez");
                    $('#myModalResult').modal();
                    break;
            }
        }
    });
}
