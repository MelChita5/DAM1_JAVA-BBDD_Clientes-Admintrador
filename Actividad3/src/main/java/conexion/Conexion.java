package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tienda";
	
	private static final String USUARIO = "root";
	
	private static final String PASSWORD = "AlumnoIFP";
	//private static final String PASSWORD = "alexandra";
	
	public static Connection open() throws SQLException {
		Properties props= new Properties();
		
		props.setProperty("user", USUARIO);
		props.setProperty("password", PASSWORD);
		return DriverManager.getConnection(URL, props);
	}

}
