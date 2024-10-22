package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Cliente;

public class ClienteQueries {
	
	
		public static ArrayList<Cliente> getMostrarClientes() {
			
			ArrayList<Cliente> clientes = new ArrayList<>();
			
			try (Connection connection = Conexion.open()) {
				
				String query = "select * from cliente";
				
				try (PreparedStatement ps = connection.prepareStatement(query)){
					
					try (ResultSet rs = ps.executeQuery()){
						
						while (rs.next()) {
							
							int id = rs.getInt("id");
							String nombre = rs.getString("nombre");
							
							Cliente newCliente = new Cliente(id, nombre);
							System.out.println(newCliente);
							
							clientes.add(newCliente);
							
						}
						
					}
					
					
				}
				
			}	catch (SQLException e) {
				System.out.println("ERROR: problemas en la conexion SQL");
				e.printStackTrace();
			}
			
			
			
			return clientes;
			
		}
		
		// Dar de alta un nuevo cliente
		public static boolean InsertCliente(Cliente newCliente) {
				
				boolean flag = false;
				
				try (Connection connection = Conexion.open()) {
					String query = "INSERT INTO cliente VALUES (?, ?)";
					
					try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
						
						ps.setInt(1, newCliente.getCli_id());
						ps.setString(2, newCliente.getCli_nombre());
						
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
							
							System.out.println("Clave del cliente insertado: " + newActorKey);
						}

						
					}
					
				} catch (SQLException e) {
					System.err.println("ERROR: ");
					e.getMessage();
					e.printStackTrace();
				}
				
				return flag;
			}
		// Modificar los datos de un cliente.
		
		
		public static boolean ModificarCliente(Cliente updateCliente) {
				
				boolean flag = false;
				
				try (Connection connection = Conexion.open()) {
					String query = "update cliente set nombre = ? where id = ?";
					
					try (PreparedStatement ps = connection.prepareStatement(query)) {
						
						ps.setString(1, updateCliente.getCli_nombre());
						ps.setInt(2, updateCliente.getCli_id());
						
						int nRows = ps.executeUpdate();
						
						flag = nRows == 1;
						
						if (flag) {
			                System.out.println("Update exitoso.");
			            } else {
			                System.out.println("Fallo en el Update");
			            }
						
					
						
					}
					
				} catch (SQLException e) {
					System.err.println("ERROR: ");
					e.getMessage();
					e.printStackTrace();
				}
				
				return flag;
			}
		
		
		
			
		public static boolean DeleteCliente(int id) {
				
				boolean flag = false;
				
				try (Connection connection = Conexion.open()) {
					String query = "delete from cliente where id = ?";
					
					try (PreparedStatement ps = connection.prepareStatement(query)) {
						
						ps.setInt(1, id);
						
						
						int nRows = ps.executeUpdate();
						
						flag = nRows == 1;
						
						if (flag) {
			                System.out.println("Delete exitoso.");
			            } else {
			                System.out.println("Fallo en el Delete");
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