/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.group172301.matskevich.tour.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Sniazhana_Matskevich
 */
public class PDFCreator {
    
    public static JFreeChart generateBarChart(int init, int posle) {
       DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        
        dataSet.setValue(init, "Тесты", "Before");
        dataSet.setValue(posle, "Тесты", "After");

 
        JFreeChart chart = ChartFactory.createBarChart(
                "Tendention", "Tests", "Quantity",
                dataSet, PlotOrientation.VERTICAL, false, true, false);
 
        return chart;
    }
   
    public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName) {
    PdfWriter writer = null;
 
    Document document = new Document();
 
    try {
        writer = PdfWriter.getInstance(document, new FileOutputStream(
                fileName));
        document.open();
    Paragraph paragraph = new Paragraph();
    // We add one empty line
  
     paragraph = new Paragraph("Results:");
      paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(width, height);
        Graphics2D graphics2d = template.createGraphics(width, height,
                new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
                height);
 
        chart.draw(graphics2d, rectangle2d);
         
        graphics2d.dispose();
        contentByte.addTemplate(template, 150, 250);
 
    } catch (Exception e) {
        e.printStackTrace();
    }
    document.close();
}
    
    
}
