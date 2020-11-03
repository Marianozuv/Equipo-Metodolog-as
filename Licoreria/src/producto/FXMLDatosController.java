package producto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLDatosController implements Initializable{
    public void agregar(MouseEvent event) throws IOException{
        
    }
    public void cancelar(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("licoreria/Principal.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Inventario");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}
