package edu.cecar.controlador;

import edu.cecar.modelo.Dato;

import java.awt.geom.Rectangle2D;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *Clase que controla en scraping a los PDF
 *
 */
public class ScrapingPdf {

    public String obtenerTextoPdf(int pagina, int x, int y, int ancho, int alto, String rutaArchivo) throws IOException {

        PDDocument document = PDDocument.load(new File(rutaArchivo));

        PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
        Rectangle2D rectangulo = new java.awt.geom.Rectangle2D.Float(x, y, ancho, alto);
        textStripper.addRegion("region", rectangulo);

        PDPage docPage = document.getPage(pagina);

        textStripper.extractRegions(docPage);

        String contenido = textStripper.getTextForRegion("region");
        
        document.close();

        return contenido;

    }

    public List<Dato> obtenerDatosPdf(String rutaArchivo, int pagina) {

        int y = 300;

        List<Dato> datos = new ArrayList();
        
        System.out.println("------------------------------------------------------");
        
        try {
            for (int i = 0; i < 10; i++) {

                Dato dato = new Dato();
                
                String fecha = obtenerTextoPdf(0, 350, 220, 100, 20,rutaArchivo);
                String nombrePais = obtenerTextoPdf(pagina, -375, y, 550, 20, rutaArchivo);          
                String totalCasos = obtenerTextoPdf(pagina, 200, y, 100, 20, rutaArchivo);                
                String casosNuevos = obtenerTextoPdf(pagina, 250, y, 100, 20, rutaArchivo);                
                String muertos = obtenerTextoPdf(pagina, 350, y, 100, 20, rutaArchivo);                
                String muertosNuevos = obtenerTextoPdf(pagina, 430, y, 100, 20, rutaArchivo);
                
                nombrePais = nombrePais.replaceAll("\\s", "");
                nombrePais = nombrePais.replaceAll("[()]", "");
                totalCasos = totalCasos.replaceAll("\\s", "");
                casosNuevos = casosNuevos.replaceAll("\\s", "");
                muertos = muertos.replaceAll("\\s", "");
                muertosNuevos = muertosNuevos.replaceAll("\\s", "");

                
                System.out.println("Fecha = "+fecha.trim());
                System.out.println("Pais = "+nombrePais);                
                System.out.println("Casos Confirmados = "+totalCasos);                
                System.out.println("Casos Confirmados Nuevos = "+casosNuevos);                
                System.out.println("Total Muertes = "+muertos);                
                System.out.println("Total Nuevas Muertes = "+muertosNuevos);                
                System.out.println("");
                
                dato.setFecha(fecha);
                dato.setNombrePais(nombrePais);                
                dato.setCasosConfirmados(totalCasos);                
                dato.setCasosConfirmadosNuevos(casosNuevos);                
                dato.setTotalMuertes(muertos);                
                dato.setTotalNuevasMuertes(muertosNuevos);
                
                datos.add(dato);

                y += 20;

            }
            
            return datos;
            
        } catch (IOException ex) {
            Logger.getLogger(ScrapingPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
