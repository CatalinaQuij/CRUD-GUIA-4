package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author olroa
 */
public class Conexion {
Connection connection;
    public Conexion() {
       
    }
    /**
     * Abre la conexión con la base de datos
     * retorna la conexion abierta.
     * @param 
     */
    public Connection getConexion() {
         try {
            // localhost - puerto de la base de datos / nombre base de datos // usuario y contraseña.  
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bdprueba", "root", "");
            //jdbc:derby://localhost:1527/POO
             //Statement stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
             System.out.println(" Error en la conexión ");
        }
        return connection; 
    }
    /**
     * Cierra la conexión con la base de datos
     * @param conn objeto de la clase conn
     */
    public void closeConexion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }

    }
}
