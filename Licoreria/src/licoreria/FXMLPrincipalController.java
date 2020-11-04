package licoreria;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import producto.ReporteProducto;

public class FXMLPrincipalController implements Initializable{
    ReporteProducto gpdf = new ReporteProducto();
    @FXML
    public void crearReporteInven(MouseEvent event){
        System.out.println("He sido pulsado");
        gpdf.crear();
        // allow user to decide between yes and no
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea abrir el reporte?", ButtonType.YES, ButtonType.NO);
        // clicking X also means no
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            gpdf.abrirPDF();
        }
    }
    @FXML
    public void irProducto(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("producto/ConsultarProductos.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Inventario");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    @FXML
    public void irEmpleado(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("producto/ConsultarEmpleados.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Control de empleados");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    @FXML
    public void cerrarSesion(MouseEvent event) throws IOException{
        System.out.println("Cerrando Sesion");
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("licoreria/IniciarSesion.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Inicia sesion");
        stage.setScene(new Scene(root, 500, 250));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
        System.out.println("Se ha cerrado sesion");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
