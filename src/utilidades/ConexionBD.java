package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
	/**
	 * coneccion con la base de datos
	 * @return
	 */
	public static Connection getConnection() {
        try {
        	Class.forName("org.hsqldb.jdbcDriver");  
        	Connection con = DriverManager.getConnection(  
        			//esta Linea es muy importante porque hay que especificar de forma 
        			//correcta la ubicacion de la base de datos con su respectiva contraseña y
        			//nombre de usuario
                    "jdbc:hsqldb:file:sources/MillonarioFinal/BDMillonario", "Millonario", "123");//folder:::user::pasw  
            
        	//Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname",
            //        "root", "dbpass");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
 /**
  * Al monento que se deja de usar el metodo se debe de quitar la conección
  * @param con
  */
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
