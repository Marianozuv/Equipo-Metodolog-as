package producto;

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

public class FXMLConsultarController implements Initializable{
    @FXML
    public void agregar(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("producto/DatosProducto.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Agregar un producto");
        stage.setScene(new Scene(root, 600, 375));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    @FXML
    public void regresar(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("licoreria/Principal.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Bienvenido");
        stage.setScene(new Scene(root, 650, 250));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
