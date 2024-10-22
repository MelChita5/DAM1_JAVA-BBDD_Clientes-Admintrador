package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import entidades.Compra;

public class CompraQueries {
	
	public static boolean InsertarCompra(Compra newCompra) {
		
		boolean flag = false;
		
		try (Connection connection = Conexion.open()) {
			String query = "INSERT INTO compra VALUES (?, ?, ?)";
			
			try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				
				ps.setInt(1, newCompra.getCom_id());
				ps.setString(2, newCompra.getCom_concepto());
				ps.setInt(3, newCompra.getCom_cli_id());
				
				int nRows = ps.executeUpdate();
	            flag = nRows == 1;
	            
	            
	            if (flag) {
	                System.out.println("Insert exitoso.");
	            } else {
	                System.out.println("Fallo en el Insert");
	            }
	            
	            
	            ResultSet generatedKeys = ps.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					
					int newActorKey = generatedKeys.getInt(1);
					
					System.out.println("\nID de la compra: " + newActorKey + "\n");
				}

				
			}
			
		} catch (SQLException e) {
			System.err.println("ERROR: ");
			e.getMessage();
			e.printStackTrace();
		}
		
		return flag;
	}
}
