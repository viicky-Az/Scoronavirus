package edu.cecar.controlador;

import edu.cecar.modelo.Dato;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

/**
 *Clase que controla la creacion del documento de EXCEL
 * 
 */
public class ControladorApachePoi {
    
    public void crearGraficaExcel(List<Dato> lista1,List<Dato> lista2,List<Dato> lista3,List<Dato> lista4) throws FileNotFoundException, IOException{
        
        Workbook wb = new XSSFWorkbook();
        
        for (int i = 0; i < lista1.size(); i++) {

            Sheet sheet = wb.createSheet(lista2.get(i).getNombrePais());

            Row row;
            Cell cell;

            row = sheet.createRow(0);
            row.createCell(0);
            row.createCell(1).setCellValue("Total confirmed cases ");
            row.createCell(2).setCellValue("Total confirmed* new cases");
            row.createCell(3).setCellValue("Total deaths");
            row.createCell(4).setCellValue("Total new deaths");
            //Fila1
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue(lista1.get(i).getFecha());
            cell = row.createCell(1);
            cell.setCellValue(lista1.get(i).getCasosConfirmados());
            cell = row.createCell(2);
            cell.setCellValue(lista1.get(i).getCasosConfirmadosNuevos());
            cell = row.createCell(3);
            cell.setCellValue(lista1.get(i).getTotalMuertes());
            cell = row.createCell(4);
            cell.setCellValue(lista1.get(i).getTotalNuevasMuertes());
            //Fila2
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellValue(lista2.get(i).getFecha());
            cell = row.createCell(1);
            cell.setCellValue(lista2.get(i).getCasosConfirmados());
            cell = row.createCell(2);
            cell.setCellValue(lista2.get(i).getCasosConfirmadosNuevos());
            cell = row.createCell(3);
            cell.setCellValue(lista2.get(i).getTotalMuertes());
            cell = row.createCell(4);
            cell.setCellValue(lista2.get(i).getTotalNuevasMuertes());
            //Fila3
            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellValue(lista3.get(i).getFecha());
            cell = row.createCell(1);
            cell.setCellValue(lista3.get(i).getCasosConfirmados());
            cell = row.createCell(2);
            cell.setCellValue(lista3.get(i).getCasosConfirmadosNuevos());
            cell = row.createCell(3);
            cell.setCellValue(lista3.get(i).getTotalMuertes());
            cell = row.createCell(4);
            cell.setCellValue(lista3.get(i).getTotalNuevasMuertes());
            //Fila4
            row = sheet.createRow(4);
            cell = row.createCell(0);
            cell.setCellValue(lista4.get(i).getFecha());
            cell = row.createCell(1);
            cell.setCellValue(lista4.get(i).getCasosConfirmados());
            cell = row.createCell(2);
            cell.setCellValue(lista4.get(i).getCasosConfirmadosNuevos());
            cell = row.createCell(3);
            cell.setCellValue(lista4.get(i).getTotalMuertes());
            cell = row.createCell(4);
            cell.setCellValue(lista4.get(i).getTotalNuevasMuertes());

            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
            ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 8, 20);

            XSSFChart chart = drawing.createChart(anchor);

            CTChart ctChart = ((XSSFChart) chart).getCTChart();
            CTPlotArea ctPlotArea = ctChart.getPlotArea();
            CTBar3DChart ctBarChart = ctPlotArea.addNewBar3DChart();
            CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
            ctBoolean.setVal(true);
            ctBarChart.addNewBarDir().setVal(STBarDir.COL);

            for (int r = 2; r < 6; r++) {
                CTBarSer ctBarSer = ctBarChart.addNewSer();
                CTSerTx ctSerTx = ctBarSer.addNewTx();
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                ctStrRef.setF(lista2.get(i).getNombrePais()+"!$A$" + r);
                ctBarSer.addNewIdx().setVal(r - 2);
                CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                ctStrRef.setF(lista2.get(i).getNombrePais()+"!$B$1:$E$1");
                CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                ctNumRef.setF(lista2.get(i).getNombrePais()+"!$B$" + r + ":$E$" + r);

                //at least the border lines in Libreoffice Calc ;-)
                ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{0, 0, 0});

            }

            //telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);

            //cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewDelete().setVal(false);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); //id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAx.addNewDelete().setVal(false);
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //legend
            CTLegend ctLegend = ctChart.addNewLegend();
            ctLegend.addNewLegendPos().setVal(STLegendPos.B);
            ctLegend.addNewOverlay().setVal(false);

            //System.out.println(ctChart);

        }
            FileOutputStream fileOut = new FileOutputStream("archivos/EstadisticasCovid.xlsx");
            wb.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Documento Generado con exito");
    }

}
