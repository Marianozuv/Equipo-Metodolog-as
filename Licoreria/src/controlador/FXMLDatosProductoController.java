package controlador;

import modelo.CRUDProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class FXMLDatosProductoController implements Initializable{
    @FXML
    private TextField nombreProductoTf;
    @FXML
    private TextField cantidadProductoTf;
    @FXML
    private TextField precioProductoTf;
    @FXML
    private TextField marcaProductoTf;
    
    @FXML
    public void agregar(MouseEvent event) throws IOException{
        if(camposVacios()){
            // allow user to decide between yes and no
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hay campos vacios", ButtonType.OK);
            // clicking X also means no
            ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
            if (ButtonType.OK.equals(result)) {}
        }else{    
            String nombre = nombreProductoTf.getText();
            String marca = marcaProductoTf.getText();
            float precio = Float.parseFloat(precioProductoTf.getText());
            int cantidad = Integer.valueOf(cantidadProductoTf.getText());
            CRUDProducto agre = new CRUDProducto();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Realmente desea guardar?", ButtonType.YES, ButtonType.NO);
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.YES.equals(result)) {
                agre.nuevoProducto(nombre, marca, precio, cantidad);
                Alert aler = new Alert(Alert.AlertType.INFORMATION, "¡Registro exitoso!", ButtonType.OK);
                ButtonType resul = aler.showAndWait().orElse(ButtonType.OK);
                if (ButtonType.OK.equals(resul)) {
                    limpiar();
                    Parent root; 
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("vista/ConsultarProductos.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(" ConsultarProductos ");
                    stage.setScene(new Scene(root, 900, 600));
                    stage.show();
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("/vista/imagenes/vintage.png"));
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
            }
        }
    }
    
    @FXML
    public void cancelar(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("vista/ConsultarProductos.fxml"));
        Stage stage = new Stage();
        stage.setTitle(" ConsultarProductos ");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image("/vista/imagenes/vintage.png"));
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void limpiar(){
        nombreProductoTf.setText("");
        cantidadProductoTf.setText("");
        precioProductoTf.setText("");
        marcaProductoTf.setText("");
    }
    
    public boolean camposVacios(){
        return (nombreProductoTf.getText().isEmpty() || cantidadProductoTf.getText().isEmpty() || precioProductoTf.getText().isEmpty() || marcaProductoTf.getText().isEmpty());
    }
    
    @FXML
    private void filtrarPrecio(KeyEvent evento) {
        char caracter = evento.getCharacter().charAt(0);
        if (Character.isDigit(caracter) || caracter == '.') {
            if (precioProductoTf.getText().split("\\.").length < 2 || Character.isDigit(caracter)) {
            } else {
                evento.consume();
            }
        } else {
            evento.consume();
        }
    }
    
    @FXML
    private void filtrarCantidad(KeyEvent evento) {
        char caracter = evento.getCharacter().charAt(0);
        if (Character.isDigit(caracter) || caracter == '.') {
        } else {
            evento.consume();
        }
    }
}