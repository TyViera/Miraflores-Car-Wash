//Info del Cliente
function exportPDFIText(urlbase, idCliente) {
    window.open(urlbase + "/Pdf/cliente.html?id=" + idCliente);
}

function exportPDFCreditoCliente(urlbase, idCliente) {
    window.open(urlbase + "/Pdf/credito.html?id=" + idCliente);
}

function exportPDFCombos(urlbase) {
    window.open(urlbase + "/Pdf/combos.html");
}

function exportPDFReporteCombos(urlbase) {
    window.open(urlbase + "/Pdf/combosReporte.html");
}

function exportPDFReporteLavadasPendientes(urlbase) {
    window.open(urlbase + "/Pdf/lavadasPendientes.html");
}

function exportPDFReporteVentas(urlbase, tiempo) {
    window.open(urlbase + "/Pdf/ventas.html?tiempo=" + tiempo);
}

function exportPDFReporteClientesFreq(urlbase) {
    window.open(urlbase + "/Pdf/clientesFrecuentes.html");
}

function exportPDFReporteClienteEvo(urlbase, idCliente) {
    window.open(urlbase + "/Pdf/clientesEvo.html?id=" + idCliente);
}