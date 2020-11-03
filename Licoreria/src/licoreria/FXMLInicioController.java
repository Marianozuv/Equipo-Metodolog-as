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
import javafx.scene.control.Button;
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
            System.out.println("Existe un campo vacio");
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
                    System.out.println("Contraseña incorrecta");
                }
            }else{
                System.out.println("Usuario incorrecto");
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
