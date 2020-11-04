package producto;

import java.util.Date;
import licoreria.SingleConnection;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.sql.*;
import java.io.*;
import java.util.Calendar;
import java.util.logging.*;

public class ReporteProducto {
    Connection conn = SingleConnection.getInstance();
    String resultado,titulo;
    Document docu;
    PdfWriter escribir;
    String strRotuloPDF = "Reporte de inventario";
    String consulta = "select * from producto";
    String nombre;
    
    public void crear(){
        try{
            Calendar c1 = Calendar.getInstance();
            String dia = Integer.toString(c1.get(Calendar.DATE));
            String mes = Integer.toString(c1.get(Calendar.MONTH));
            String annio = Integer.toString(c1.get(Calendar.YEAR));
            nombre = "Reporte de inventario "+dia+"-"+mes+"-"+annio+".pdf";
            docu = new Document(PageSize.LETTER.rotate());
            escribir = PdfWriter.getInstance(docu,new FileOutputStream(nombre));
            docu.open();
            agregarMetaDatos(docu);
            agregarContenido(docu);
            docu.close();
            System.out.println("Se ha generado el repoorte");
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    private void agregarContenido(Document document) throws DocumentException{
        Paragraph ParrafoHoja = new Paragraph();
        agregarLineasBlancas(ParrafoHoja, 1);
        ParrafoHoja.add(new Paragraph(strRotuloPDF.toUpperCase ()) );
        agregarLineasBlancas(ParrafoHoja, 1);
        agregarTabla(ParrafoHoja);
        agregarLineasBlancas(ParrafoHoja, 2);
        docu.add(ParrafoHoja);
    }
    private void agregarTabla(Paragraph parrafo)throws BadElementException{
        float anchosFilas[] = {2.5f,1.5f,1f,1f };
        PdfPTable tabla = new PdfPTable(anchosFilas);
        String rotulosColumnas[] = {"Nombre del producto","Marca","Precio","Cantidad"};
        tabla.setWidthPercentage(100);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell = new PdfPCell(new Paragraph("Tabla: Inventario"));
        cell.setColspan(9);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(cell);
        try{
            for(int i=0; i<rotulosColumnas.length; i++){
                cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(cell);
            }
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=st.executeQuery(consulta);
            try {
                while(rs.next()){
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getString("nombre"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getString("marca"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getFloat("precio"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cantidad"))));
                    tabla.addCell(cell);
                } 
            } catch (SQLException ex) {
                Logger.getLogger(ReporteProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(Exception e){
            System.out.println("Excepcion al ejecutar CONSULTA!!!");
            e.printStackTrace();
        }
        parrafo.add(tabla);
    }
    private static void agregarLineasBlancas(Paragraph parrafo, int nLineas){
        for(int i=0;i<nLineas;i++){
            parrafo.add(new Paragraph (" "));
        }
    }
    private static void agregarMetaDatos(Document docu){
        docu.addTitle("Reporte de inventario");
        docu.addSubject("Usando iText y MySql");
        docu.addKeywords("Java,PDF,iText");
        docu.addAuthor("");
        
    }
    public void abrirPDF(){
          try{
              Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+nombre);
          }catch(IOException e){
              
          }
    }
}
