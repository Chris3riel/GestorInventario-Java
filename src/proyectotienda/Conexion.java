/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectotienda;


    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.Statement;
/**


/**
 *
 * @author chris
 */
public class Conexion {
    Connection con;
  public Conexion(){
    try {
    Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost/basetienda","root","");
    System.out.println("conectado");
    } catch (Exception e) {
    
    }
    }
  public Connection getConnection(){
  return con;
  }
}
