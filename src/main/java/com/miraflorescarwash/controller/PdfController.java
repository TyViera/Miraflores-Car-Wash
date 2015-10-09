/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.miraflorescarwash.model.Carro;
import com.miraflorescarwash.model.Cliente;
import com.miraflorescarwash.model.ClienteReporte;
import com.miraflorescarwash.model.ComboPorModelo;
import com.miraflorescarwash.model.ComboReporte;
import com.miraflorescarwash.model.CreditoDisponibleCliente;
import com.miraflorescarwash.model.Lavada;
import com.miraflorescarwash.service.ClienteService;
import com.miraflorescarwash.service.ComboPorModeloService;
import com.miraflorescarwash.service.ComboService;
import com.miraflorescarwash.service.LavadaService;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ty
 */
@Controller
@RequestMapping("/Pdf")
public class PdfController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComboPorModeloService comboPorModeloService;

    @Autowired
    private ComboService comboService;

    @Autowired
    private LavadaService lavadaService;

    @RequestMapping(value = "/cliente.html", method = RequestMethod.GET)
    public void doGetPdfCliente(@RequestParam("id") Long id, HttpServletResponse response) {
        Cliente cliente;
        ByteArrayOutputStream baos;
        Document document;
        OutputStream os;

        cliente = clienteService.findById(id);
        if (cliente != null) {
            clienteService.iniciarRelacionesLazy(cliente);
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);
//                document.open();
                // Escribimos
                crearPdfCliente(document, cliente);
                // Cerramos
//                document.close();
                //No cerrar ni abrir porque se hace en el metodo arriba

                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Esccribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/credito.html", method = RequestMethod.GET)
    public void doGetPdfClienteCredito(@RequestParam("id") Long id, HttpServletResponse response) {
        List<CreditoDisponibleCliente> reporteCredito;
        ByteArrayOutputStream baos;
        Document document;
        OutputStream os;

        reporteCredito = clienteService.verCreditoDisponible(id);
        if (reporteCredito != null) {
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);
                // Escribimos
                crearPdfClienteCreditoDisponible(document, reporteCredito);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/combos.html", method = RequestMethod.GET)
    public void doGetPdfCombos(HttpServletResponse response) {
        List<ComboPorModelo> combos;
        ByteArrayOutputStream baos;
        Document document;
        OutputStream os;
        combos = comboPorModeloService.findAll();
        if (combos != null) {
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);
                // Escribimos
                crearPdfCombos(document, combos);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/combosReporte.html", method = RequestMethod.GET)
    public void doGetPdfCombosReporte(HttpServletResponse response) {
        PdfWriter writer;
        List<ComboReporte> combos;
        ByteArrayOutputStream baos;
        Document document;
        OutputStream os;

        combos = comboService.ComboReporte();
        if (combos != null) {
//            clienteService.iniciarRelacionesLazy(cliente);
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                writer = PdfWriter.getInstance(document, baos);
                crearPdfCombosReporte(document, combos, writer);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (IOException | DocumentException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/lavadasPendientes.html", method = RequestMethod.GET)
    public void doGetPdfLavadasPendientesReporte(HttpServletResponse response) {
        List<Lavada> pendientes;
        Document document;
        ByteArrayOutputStream baos;
        OutputStream os;

        pendientes = lavadaService.verLavadasPendientes();
        if (pendientes != null) {
//            clienteService.iniciarRelacionesLazy(cliente);
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);
                crearPdfLavadasPendientes(document, pendientes);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (IOException | DocumentException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/ventas.html", method = RequestMethod.GET)
    public void doGetPdfVentasReporte(@RequestParam("tiempo") String tiempo, HttpServletResponse response) {
        Document document;
        ByteArrayOutputStream baos;
        OutputStream os;
        JFreeChart grafico;
        PdfWriter writer;
        String inicio, fin;
        SimpleDateFormat format;
        Calendar c;
        int me;
        int n;

        c = Calendar.getInstance();
        n = 10;
        switch (tiempo) {
            case "d":
                format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
                fin = format.format(c.getTime());
                c.set(Calendar.DATE, c.get(Calendar.DATE) - (n - 1));
                inicio = format.format(c.getTime());
                me = Constantes.REPORTE_LAVADA_DIARIO;
                break;
            case "s":
                format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
                fin = format.format(c.getTime());
                c.set(Calendar.DATE, c.get(Calendar.DATE) - (n - 1) * 7);
                inicio = format.format(c.getTime());
                me = Constantes.REPORTE_LAVADA_SEMANAL;
                break;
            case "m":
                format = new SimpleDateFormat("MMMM 'de' yyyy");
                fin = format.format(c.getTime());
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) - (n - 1));
                inicio = format.format(c.getTime());
                me = Constantes.REPORTE_LAVADA_MENSUAL;
                break;
            default:
                fin = inicio = "";
                me = -1;
                break;
        }
        if (me >= 0) {
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                writer = PdfWriter.getInstance(document, baos);
                grafico = lavadaService.generarChartReporteVentas(me, n);
                crearPdfVentasReporte(document, grafico, writer, inicio, fin);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (IOException | DocumentException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/clientesFrecuentes.html", method = RequestMethod.GET)
    public void doGetPdfClienteFreqReporte(HttpServletResponse response) {
        List<ClienteReporte> reporteCompras;
        ByteArrayOutputStream baos;
        Document document;
        PdfWriter writer;
        OutputStream os;
        reporteCompras = clienteService.reporteCompras(false);
        if (reporteCompras != null) {
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                writer = PdfWriter.getInstance(document, baos);
                // Escribimos
                crearPdfClientesFreq(document, reporteCompras, writer);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @RequestMapping(value = "/clientesEvo.html", method = RequestMethod.GET)
    public void doGetPdfClienteEvolucionReporte(@RequestParam("id") Long id, HttpServletResponse response) {
        List<ClienteReporte> reporteCompras;
        ByteArrayOutputStream baos;
        Document document;
        PdfWriter writer;
        OutputStream os;
        reporteCompras = clienteService.reporteComprasPorMesPorCliente(id);
        if (reporteCompras != null) {
            try {
                document = new Document(PageSize.A4);
                baos = new ByteArrayOutputStream();
                writer = PdfWriter.getInstance(document, baos);
                // Escribimos
                crearPdfClientesEvolucion(document, reporteCompras, writer);
                // Cerramos
                // Hay que configurar las cabeceras para que
                //el navegador detecte que es un <strong>PDF</strong>
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                // Configuramos el content type
                response.setContentType("application/pdf");
                // Tamaño
                response.setContentLength(baos.size());
                // Escribir el ByteArrayOutputStream a el ServletOutputStream
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void crearPdfLavadasPendientes(Document doc, List<Lavada> lavadas) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        int i;
        JFreeChart chart;
        int w, h;
        int pos;
        pos = 0;
        w = h = 500;

        fuente = "arial";
        if (lavadas.isEmpty()) {
            return;
        }
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Lavadas Pendientes";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            tabla = new PdfPTable(3);

            frase = new Phrase("Id", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Fecha", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Placa de Carro", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Modelo de Carro", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Cliente", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            i = 1;

            for (Lavada lavada : lavadas) {

                if (i % 2 == 0) {
                    cell = new PdfPCell();
                    cell.setBackgroundColor(new BaseColor(244, 119, 119));

                    frase = new Phrase(lavada.getId() + "");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(lavada.getFechaLavado() + "");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(lavada.getCarro().getPlaca());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(lavada.getCarro().getModelo().getNombre());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(
                            lavada.getCarro().getCliente().getApellidos() + ", "
                            + lavada.getCarro().getCliente().getNombres()
                    );
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                } else {
                    tabla.addCell(lavada.getId() + "");
                    tabla.addCell(lavada.getFechaLavado() + "");
                    tabla.addCell(lavada.getCarro().getPlaca());
                    tabla.addCell(lavada.getCarro().getModelo().getNombre());
                    tabla.addCell(
                            lavada.getCarro().getCliente().getApellidos() + ", "
                            + lavada.getCarro().getCliente().getNombres()
                    );
                }
                i++;
            }
            doc.add(tabla);

            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfCliente(Document doc, Cliente cliente) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        int i;

        fuente = "arial";
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Cliente: " + cliente.getNombres();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            doc.add(Chunk.NEWLINE);

            txt = "Datos del Cliente";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 20, Font.UNDERLINE, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Id: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getId() + "";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Nombres: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getNombres();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Apellidos: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getApellidos();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Dni: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getDni();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Email: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getEmail();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Telefono: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getTelefono();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Carros:";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            tabla = new PdfPTable(4);
            frase = new Phrase("Id", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Modelo", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Marca", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Placa", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            i = 1;
            for (Carro carro : cliente.getCarros()) {

                if (i % 2 == 0) {
                    cell = new PdfPCell();
                    cell.setBackgroundColor(new BaseColor(244, 119, 119));

                    frase = new Phrase(carro.getId() + "");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(carro.getModelo().getNombre());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(carro.getMarca());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(carro.getPlaca());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);
                } else {
                    tabla.addCell(carro.getId() + "");
                    tabla.addCell(carro.getModelo().getNombre());
                    tabla.addCell(carro.getMarca());
                    tabla.addCell(carro.getPlaca());
                }
                i++;
            }
            doc.add(tabla);
            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfClienteCreditoDisponible(Document doc, List<CreditoDisponibleCliente> reporte) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        CreditoDisponibleCliente cliente;
        int i;

        fuente = "arial";
        if (reporte.isEmpty()) {
            return;
        }
        cliente = reporte.get(0);
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Cliente: " + cliente.getNombres();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            doc.add(Chunk.NEWLINE);

            txt = "Crédito Disponible";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 20, Font.UNDERLINE, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Nombres: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getNombres();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Apellidos: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getApellidos();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Dni: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getDni();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);

            txt = "Crédito Disponible:";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            doc.add(Chunk.NEWLINE);
            tabla = new PdfPTable(2);

            frase = new Phrase("Modelo", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Lavadas Disponibles", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            i = 1;
            for (CreditoDisponibleCliente cre : reporte) {

                if (i % 2 == 0) {
                    cell = new PdfPCell();
                    cell.setBackgroundColor(new BaseColor(244, 119, 119));

                    frase = new Phrase(cre.getModeloCarro());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(cre.getLavadas());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);
                } else {
                    tabla.addCell(cre.getModeloCarro());
                    tabla.addCell(cre.getLavadas() + "");
                }
                i++;
            }
            doc.add(tabla);
            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfCombos(Document doc, List<ComboPorModelo> combos) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        int i;

        fuente = "arial";
        if (combos.isEmpty()) {
            return;
        }
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Combos";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            doc.add(Chunk.NEWLINE);

            tabla = new PdfPTable(5);

            frase = new Phrase("Id", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Nombre", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Lavadas", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Modelo", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Precio", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            i = 1;

            for (ComboPorModelo combo : combos) {

                if (i % 2 == 0) {
                    cell = new PdfPCell();
                    cell.setBackgroundColor(new BaseColor(244, 119, 119));

                    frase = new Phrase(combo.getId());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(combo.getCombo().getNombre());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(combo.getCombo().getNumeroLavadas());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(combo.getModelo().getNombre());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase("S/. " + combo.getPrecio() + "0");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);
                } else {
                    tabla.addCell(combo.getId() + "");
                    tabla.addCell(combo.getCombo().getNombre());
                    tabla.addCell(combo.getCombo().getNumeroLavadas() + "");
                    tabla.addCell(combo.getModelo().getNombre());
                    tabla.addCell("S/. " + combo.getPrecio() + "0");
                }
                i++;
            }
            doc.add(tabla);
            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfCombosReporte(Document doc, List<ComboReporte> combos, PdfWriter writer) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        int i;
        JFreeChart chart;
        int w, h;
        int pos;
        pos = 0;
        w = h = 500;

        fuente = "arial";
        if (combos.isEmpty()) {
            return;
        }
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Reporte de Combos";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            tabla = new PdfPTable(3);

            frase = new Phrase("Id", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Nombre", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Dinero Recaudado", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            i = 1;

            for (ComboReporte combo : combos) {

                if (i % 2 == 0) {
                    cell = new PdfPCell();
                    cell.setBackgroundColor(new BaseColor(244, 119, 119));

                    frase = new Phrase(combo.getId() + "");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(combo.getNombre());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase("S/. " + combo.getCantidad() + "0");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                } else {
                    tabla.addCell(combo.getId() + "");
                    tabla.addCell(combo.getNombre());
                    tabla.addCell("S/. " + combo.getCantidad() + "0");
                }
                i++;
            }
            doc.add(tabla);
            doc.newPage();
            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            chart = comboService.generarChartReporte(combos);

            w = 500;
            h = 500;

            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(w, h);
            Graphics2D g2d = tp.createGraphics(w, h,
                    new DefaultFontMapper());
            Rectangle2D r2d = new Rectangle2D.Double(0, 0, w, h);
            chart.draw(g2d, r2d);
            cb.addTemplate(tp, 50, 250);
            g2d.dispose();
            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfVentasReporte(Document doc, JFreeChart chart, PdfWriter writer, String fechaInicio, String fechaFin) {
        Paragraph parrafo;
        Phrase frase;
        String txt;
        String fuente;
        int w, h;

        fuente = "arial";
        if (chart == null) {
            return;
        }
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Reporte de Ventas";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            doc.add(Chunk.NEWLINE);

            txt = "Inicio: ";
            frase = new Phrase(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            doc.add(frase);
            txt = fechaInicio;
            frase = new Phrase(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            doc.add(frase);
            doc.add(Chunk.NEWLINE);

            txt = "Fin: ";
            frase = new Phrase(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            doc.add(frase);
            txt = fechaFin;
            frase = new Phrase(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            doc.add(frase);

            w = 500;
            h = 500;

            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(w, h);
            Graphics2D g2d = tp.createGraphics(w, h,
                    new DefaultFontMapper());
            Rectangle2D r2d = new Rectangle2D.Double(0, 0, w, h);
            chart.draw(g2d, r2d);
            cb.addTemplate(tp, 50, 50);
            g2d.dispose();
            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfClientesFreq(Document doc, List<ClienteReporte> reporteCompras, PdfWriter writer) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        JFreeChart chart;
        int i, w, h;

        fuente = "arial";
        if (reporteCompras.isEmpty()) {
            return;
        }
        try {
            //

            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Clientes Frecuentes";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            doc.add(Chunk.NEWLINE);

            tabla = new PdfPTable(3);

            frase = new Phrase("Id", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Nombre", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            frase = new Phrase("Dinero", FontFactory.getFont(fuente, 12, Font.BOLD, BaseColor.WHITE));
            cell = new PdfPCell(frase);
            cell.setBackgroundColor(BaseColor.RED);
            tabla.addCell(cell);

            i = 1;

            for (ClienteReporte clienteReporte : reporteCompras) {

                if (i % 2 == 0) {
                    cell = new PdfPCell();
                    cell.setBackgroundColor(new BaseColor(244, 119, 119));

                    frase = new Phrase(clienteReporte.getId() + "");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase(clienteReporte.getApellido() + " " + clienteReporte.getNombre());
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                    frase = new Phrase("S/. " + clienteReporte.getTotal() + "0");
                    cell.setPhrase(frase);
                    tabla.addCell(cell);

                } else {
                    tabla.addCell(clienteReporte.getId() + "");
                    tabla.addCell(clienteReporte.getApellido() + " " + clienteReporte.getNombre());
                    tabla.addCell("S/. " + clienteReporte.getTotal() + "0");
                }
                i++;
            }
            doc.add(tabla);

            doc.newPage();
            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            chart = clienteService.generarChartReporte(reporteCompras);

            w = 500;
            h = 500;

            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(w, h);
            Graphics2D g2d = tp.createGraphics(w, h,
                    new DefaultFontMapper());
            Rectangle2D r2d = new Rectangle2D.Double(0, 0, w, h);
            chart.draw(g2d, r2d);
            cb.addTemplate(tp, 50, 250);
            g2d.dispose();
            doc.close();
        } catch (DocumentException ex) {

        }
    }

    private void crearPdfClientesEvolucion(Document doc, List<ClienteReporte> reporteCompras, PdfWriter writer) {
        Paragraph parrafo;
        PdfPTable tabla;
        String txt;
        PdfPCell cell;
        Phrase frase;
        String fuente;
        JFreeChart chart;
        ClienteReporte cliente;
        int i, w, h;

        fuente = "arial";
        if (reporteCompras.isEmpty()) {
            return;
        }
        try {
            //
            cliente = reporteCompras.get(0);
            // Se abre el documento.
            doc.open();

            txt = "Miraflores Car Wash";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 10, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);

            txt = "Evolución de Clientes ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 30, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_CENTER);

            doc.add(parrafo);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            txt = "Id: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getId() + "";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
//            doc.add(Chunk.NEWLINE);

            txt = "Nombres: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getNombre();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
//            doc.add(Chunk.NEWLINE);

            txt = "Apellidos: ";
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.BOLD, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
            txt = cliente.getApellido();
            parrafo = new Paragraph(txt, FontFactory.getFont(
                    fuente, 14, Font.NORMAL, BaseColor.BLACK
            ));
            parrafo.setAlignment(Element.ALIGN_LEFT);
            doc.add(parrafo);
//            doc.add(Chunk.NEWLINE);


            chart = clienteService.generarChartReporteMes(reporteCompras);

            w = 500;
            h = 500;

            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(w, h);
            Graphics2D g2d = tp.createGraphics(w, h,
                    new DefaultFontMapper());
            Rectangle2D r2d = new Rectangle2D.Double(0, 0, w, h);
            chart.draw(g2d, r2d);
            cb.addTemplate(tp, 50, 50);
            g2d.dispose();
            doc.close();
        } catch (DocumentException ex) {

        }
    }

}
