/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import licoreria.SingleConnection;

/**
 *
 * @author PC
 */
public class TableController implements Initializable{
    
    @FML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, String> col_nombre;
    @FXML
    private TableColumn<ModelTable, String> col_marca;
    @FXML
    private TableColumn<ModelTable, Float> col_precio;
    @FXML
    private TableColumn<ModelTable, Integer> col_cantidad;
    
     ObservableList<ModelTable> oblist=FXCollections.observableArrayList();
     
    @Override
    public void initialize (URL location, ResourceBundle resources){
            
            Connection con = SingleConnection.getInstance();
            
            ResultSet rs= con.createStatement().executeQuery("select * from producto");
            
            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("nombre"),rs.getString("marca"),rs.getFloat("precio"),rs.getInteger("cantidad")));
            }
            col_nombre.setCellvalueFactory(new PropertyValueFactory<>"nombre");
            col_marca.setCellvalueFactory(new PropertyValueFactory<>"marca");
            col_precio.setCellvalueFactory(new PropertyValueFactory<>"precio");
            col_cantidad.setCellvalueFactory(new PropertyValueFactory<>"cantidad");
            
            table.setItems(obList);
    }
    
}
