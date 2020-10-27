package licoreria;
import java.sql.*;
public class SingleConnection {
    private static Connection con = null;
    private SingleConnection(){}
    public static Connection getInstance(){
        try{
            if(con==null){
                String bd = "Licoreria";
                String login ="root";
                String password ="";
                String url="jdbc:mysql://localhost/" +bd;
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(url,login,password);
                System.out.println("Conectado");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}
