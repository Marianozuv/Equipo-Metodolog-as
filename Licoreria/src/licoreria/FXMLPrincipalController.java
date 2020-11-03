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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLPrincipalController implements Initializable{
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
