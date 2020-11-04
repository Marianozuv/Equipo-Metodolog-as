package licoreria;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLInicioController implements Initializable{
    @FXML
    private TextField usuarioTf;
    @FXML
    private PasswordField contraseñaPf;
    @FXML
    private void iniciarSesion(MouseEvent event) throws SQLException, IOException{
        if (camposVacios()) {
            // allow user to decide between yes and no
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hay campos vacios", ButtonType.OK);
            // clicking X also means no
            ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
            if (ButtonType.OK.equals(result)) {}
        } else {
            if("user".equals(usuarioTf.getText())){
                if("user".equals(contraseñaPf.getText())){
                    System.out.println("Inicio de sesion exitoso");
                    Parent root; 
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("licoreria/Principal.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Bienvenido");
                    stage.setScene(new Scene(root, 650, 250));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    ((Node)(event.getSource())).getScene().getWindow().hide(); 
                }else{
                    // allow user to decide between yes and no
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Contraseña incorrecta", ButtonType.OK);
                    // clicking X also means no
                    ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
                    if (ButtonType.OK.equals(result)) {}
                }
            }else{
                // allow user to decide between yes and no
                Alert alert = new Alert(Alert.AlertType.WARNING, "Usuario incorrecto", ButtonType.OK);
                // clicking X also means no
                ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
                if (ButtonType.OK.equals(result)) {}
            }
        }  
    }
    private boolean camposVacios() {
        return (usuarioTf.getText().isEmpty() || contraseñaPf.getText().isEmpty());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
