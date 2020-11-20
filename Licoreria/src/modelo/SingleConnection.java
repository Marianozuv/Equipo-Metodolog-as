package modelo;

import java.sql.*;

public class SingleConnection {
    
    private SingleConnection(){}
    
    public static Connection getInstance(){
        Connection con = null;
        try{
            if(con==null){
                String bd = "Licoreria";
                String login ="root";
                String password ="";
                String url="jdbc:mysql://localhost/" +bd;
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(url,login,password);
                //System.out.println("Conectado");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
}