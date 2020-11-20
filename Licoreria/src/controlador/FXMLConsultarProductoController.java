package controlador;

import modelo.CRUDProducto;
import modelo.Producto;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;//
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;//
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.SingleConnection;

public class FXMLConsultarProductoController implements Initializable{
    
    public static Producto aux= new Producto();
    @FXML
    private TextField buscarProductoTf;
    @FXML
    private TableView<Producto> table;
    @FXML
    private TableColumn<Producto, String> col_nombre;
    @FXML
    private TableColumn<Producto, String> col_marca;
    @FXML
    private TableColumn<Producto, Float> col_precio;
    @FXML
    private TableColumn<Producto, Integer> col_cantidad;
    ObservableList<Producto> oblist=FXCollections.observableArrayList();
    @FXML
    private Button ventaProductoBtn;
            
    @FXML
    public void agregar(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("vista/DatosProducto.fxml"));
        Stage stage = new Stage();
        stage.setTitle(" DatosProducto ");
        stage.setScene(new Scene(root, 600, 375));
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image("/vista/imagenes/vintage.png"));
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    
    @FXML
    public void editar(MouseEvent event) throws IOException{
        try{
            Producto sele= table.getSelectionModel().getSelectedItem();
            aux.setNombre(sele.getNombre());
            aux.setMarca(sele.getMarca());
            aux.setCantidad(sele.getCantidad());
            aux.setPrecio(sele.getPrecio());
            Parent root; 
            root = FXMLLoader.load(getClass().getClassLoader().getResource("vista/EditarProducto.fxml"));
            Stage stage = new Stage();
            stage.setTitle(" DatosProducto ");
            System.out.println(table.getSelectionModel().getSelectedItem().getNombre());
            stage.setScene(new Scene(root, 600, 375));
            stage.show();
            stage.setResizable(false);
            stage.getIcons().add(new Image("/vista/imagenes/vintage.png"));
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }catch(Exception e){
            Alert aler = new Alert(Alert.AlertType.INFORMATION, "Selecciona un elemento a modificar", ButtonType.OK);
            aler.showAndWait();
        }
    }
    
    @FXML
    public void regresar(MouseEvent event) throws IOException{
        Parent root; 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("vista/Principal.fxml"));
        Stage stage = new Stage();
        stage.setTitle(" Principal ");
        stage.setScene(new Scene(root, 650, 600));
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image("/vista/imagenes/vintage.png"));
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    
    @FXML
    public void consultar(MouseEvent event){
        Connection con = SingleConnection.getInstance();
        ResultSet rs;
        try {
            this.table.getItems().clear();
            String textToSearch = buscarProductoTf.getText();
            rs = con.createStatement().executeQuery("select * from producto where nombre like '%"+textToSearch+"%'");
            while (rs.next()){
                oblist.add(new Producto(rs.getString("nombre"),rs.getString("marca"),rs.getFloat("precio"),rs.getInt("cantidad")));
            }
            col_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            col_marca.setCellValueFactory(new PropertyValueFactory("marca"));
            col_precio.setCellValueFactory(new PropertyValueFactory("precio"));
            col_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
            table.setItems(oblist);
        }catch (SQLException ex) {
            Logger.getLogger(FXMLConsultarController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(!con.isClosed()){
                    con.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUDProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void eliminar(MouseEvent event){
        Connection con = SingleConnection.getInstance();
        try {
            Producto rowSelected = table.getSelectionModel().getSelectedItem();
            String selec = table.getSelectionModel().getSelectedItem().getNombre();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que lo quiere eliminar?", ButtonType.YES, ButtonType.NO);
            // clicking X also means no
            ButtonType resul = alert.showAndWait().orElse(ButtonType.YES);
            if(ButtonType.YES.equals(resul)) {
                PreparedStatement pstm; 
                pstm = con.prepareStatement("delete from producto where nombre='"+selec+"'");
                pstm.execute();
                this.table.getItems().remove(rowSelected);
                pstm.close();
                Alert aler2 = new Alert(Alert.AlertType.INFORMATION, "¡Eliminación exitosa!", ButtonType.OK);
                aler2.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLConsultarController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            Alert aler = new Alert(Alert.AlertType.INFORMATION, "Selecciona un elemento a eliminar", ButtonType.OK);
            aler.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ventaProductoBtn.setDisable(true);
        Connection con = SingleConnection.getInstance();
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery("select * from producto");
            while (rs.next()){
                if(rs.getInt("cantidad") > 0){
                    oblist.add(new Producto(rs.getString("nombre"),rs.getString("marca"),rs.getFloat("precio"),rs.getInt("cantidad")));
                }else{
                    oblist.add(new Producto(rs.getString("nombre"),rs.getString("marca"),rs.getFloat("precio"),rs.getInt("cantidad")));
                }  
            }
            col_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            col_marca.setCellValueFactory(new PropertyValueFactory("marca"));
            col_precio.setCellValueFactory(new PropertyValueFactory("precio"));
            col_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
            table.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLConsultarController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}