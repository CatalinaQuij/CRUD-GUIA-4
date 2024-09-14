/*
 * DAO - Data Access Object - Objeto de Acceso a Datos relacionado con la tabla usuario
 */
package Modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioDAO {
    private Conexion conex;
    private Connection connection;
    private Statement stmt;
    private String consulta; 
    public usuarioDAO(){  // método constructor establecer conexión 
        conex = new Conexion();
        connection= conex.getConexion();
    }
    public String listar_Usuarios(){ // método listar usuarios 
      consulta = "";
      String cc, name;
        try {
            stmt = connection.createStatement(); 
            ResultSet a = stmt.executeQuery("SELECT * FROM usuario");
            while (a.next()) {
                cc = a.getString("Cedula");
                name = a.getString("Nombre");
                consulta = consulta + cc + " " + name + "\n";
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consulta;
    }
    public ResultSet listar2_Usuarios(){ // método listar usuarios 
      ResultSet usuarios= null;   
      consulta = "";
      String cc, name;
        try {
            stmt = connection.createStatement(); 
            usuarios = stmt.executeQuery("SELECT * FROM usuario");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
     public String consultarUsuario(String ced){  // método consultar por céudla 
      consulta = "";
      String cc, name;
        try {
            stmt = connection.createStatement(); 
            ResultSet a = stmt.executeQuery("SELECT * FROM usuario WHERE cedula = "+ced);
                cc = a.getString("Cedula");
                name = a.getString("Nombre");
                consulta = consulta + cc + " " + name + "\n";

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consulta;
    }
      public String modificarUsuario(String ced, String nombreApp){ // método modificar nombre por céudla
      consulta = "";
      String cc, name;
        try {
            //UPDATE `bdprueba`.`usuario` SET `Nombre`='Sandra Rincon' WHERE  `Cedula`='111111';
            Statement s = connection.createStatement();
            s.executeUpdate("UPDATE bdprueba.usuario SET  nombre='"+ nombreApp + "' WHERE cedula='"+ced+"';");
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consulta;
    }
     
    public void registrarUsuario(String cc, String name){
        try {

            Statement s = connection.createStatement();
            s.executeUpdate("INSERT INTO bdprueba.usuario(cedula, nombre) VALUES ('"+ cc+"', '"+name+"');");
        } catch (SQLException e) {
            System.out.println("Excepción SQL al insertar en tabla: " + e.getMessage());
        }
    }
    public void cerrar(){
       this.conex.closeConexion(connection);
    }

    public String eliminar(String ced) {
       consulta = "";
      String cc, name;
        try {
            stmt = connection.createStatement(); 
            
            Statement s = connection.createStatement();
            s.executeUpdate("DELETE FROM usuario WHERE cedula ="+ced+";");
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consulta;
    }
}
