package producto;
import java.sql.*;
import licoreria.SingleConnection;
public class CRUDProducto {
    //"abro" una conexion a la base de datos, aunque por el single solo es una en si, por ello no lo cierro, porque esa conexion sirve para todo
    Connection con = SingleConnection.getInstance();
    public void nuevoProducto(String nombre, String marca, float precio, int cantidad){
        try {
            PreparedStatement pstm; 
            //abro un preparedSatatement y con la conexion hago el insert a la base de datos
            pstm = con.prepareStatement("insert into producto(nombre,marca,precio,cantidad) values(?,?,?,?)");
                pstm.setString(1,nombre);
                pstm.setString(2,marca);
                pstm.setFloat(3,precio);
                pstm.setInt(4,cantidad);
                pstm.execute();
                //cierro el pstm para evitar gasto de recursos
                pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
