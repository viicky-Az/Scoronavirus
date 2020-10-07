package edu.cecar.scrapingPdf.test;

import edu.cecar.controlador.ScrapingPdf;
import edu.cecar.modelo.Dato;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 *
 */
public class AppTest {

    @Test
    public void scrapingTestPdfColombia() {

        String fechaEsperada = "12 August 2020";
        String paisEsperado = "Colombia";
        int casosEsperado = 397623;
        int casosNuevosEsperado = 10142;
        int muertesEsperada = 13154;
        int muertesNuevasEsperada = 312;

        ScrapingPdf sp = new ScrapingPdf();

        List<Dato> datos = sp.obtenerDatosPdf("archivos/20200812-covid-19-sitrep-205.pdf", 8);

        String fechaObtenida = datos.get(0).getFecha();
        String paisObtenido = datos.get(0).getNombrePais();
        int casosObtenido = datos.get(0).getCasosConfirmados();
        int casosNuevosObtenido = datos.get(0).getCasosConfirmadosNuevos();
        int muertesObtenido = datos.get(0).getTotalMuertes();
        int muertesNuevasObtenido = datos.get(0).getTotalNuevasMuertes();

        assertEquals("La fecha no corresponde", fechaEsperada, fechaObtenida);
        assertEquals("El pais no corresponde", paisEsperado, paisObtenido);
        assertEquals("El total de casos no corresponde", casosEsperado, casosObtenido);
        assertEquals("El total de casos nuevos no corresponde", casosNuevosEsperado, casosNuevosObtenido);
        assertEquals("El total de muertes no corresponde", muertesEsperada, muertesObtenido);
        assertEquals("El total de muertes nuevas no corresponde", muertesNuevasEsperada, muertesNuevasObtenido);
    }

}
