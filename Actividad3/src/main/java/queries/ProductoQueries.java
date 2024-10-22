package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Producto;

public class ProductoQueries {
	
	//Organizado ascendentemente por precio.
	public static ArrayList<Producto> getPrecioAscendente() {
		
		ArrayList<Producto> productos = new ArrayList<>();
		
		try (Connection connection = Conexion.open()) {
			
			String query = "select * from producto order by precio asc";
			
			try (PreparedStatement ps = connection.prepareStatement(query)){
				
				try (ResultSet rs = ps.executeQuery()){
					
					int id = 1;
					
					while (rs.next()) {
						
						
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");
						
						Producto newProducto = new Producto(id, nombre, precio);
						
						System.out.println(newProducto);
						productos.add(newProducto);
						id++;
					}
					
				}
				
				
			}
			
		}	catch (SQLException e) {
			System.out.println("ERROR: problemas en la conexion SQL");
			e.printStackTrace();
		}
		
		
		
		return productos;
		
	}
	
	//Organizado descendentemente por precio.
	
	
	public static ArrayList<Producto> getPrecioDescen() {
			
			ArrayList<Producto> productos = new ArrayList<>();
			
			try (Connection connection = Conexion.open()) {
				
				String query = "select * from producto order by precio desc";
				
				try (PreparedStatement ps = connection.prepareStatement(query)){
					
					try (ResultSet rs = ps.executeQuery()){
						
						int id = 1;
						
						while (rs.next()) {
							
							
							String nombre = rs.getString("nombre");
							double precio = rs.getDouble("precio");
							
							Producto newProducto = new Producto(id, nombre, precio);
							
							System.out.println(newProducto);
							productos.add(newProducto);
							
							id++;
						}
						
					}
					
					
				}
				
			}	catch (SQLException e) {
				System.out.println("ERROR: problemas en la conexion SQL");
				e.printStackTrace();
			}
			
			
			
			return productos;
			
		}
	
	
	// Organizado alfab�ticamente por nombre. 
	
	public static ArrayList<Producto> getPrecioAlfabetico() {
		
		ArrayList<Producto> productos = new ArrayList<>();
		
		try (Connection connection = Conexion.open()) {
			
			String query = "select * from producto order by nombre";
			
			try (PreparedStatement ps = connection.prepareStatement(query)){
				
				try (ResultSet rs = ps.executeQuery()){
					
					int id = 1;
					
					while (rs.next()) {
						
						
						String nombre = rs.getString("nombre");
						double precio = rs.getDouble("precio");
						
						Producto newProducto = new Producto(id, nombre, precio);
						
						System.out.println(newProducto);
						productos.add(newProducto);
						id++;
					}
					
				}
				
				
			}
			
		}	catch (SQLException e) {
			System.out.println("ERROR: problemas en la conexion SQL");
			e.printStackTrace();
		}
		
		
		
		return productos;
		
	}
	
	 
	
	// Reducir los resultados a aquellos productos que contengan una cadena determinada en su nombre.(FILTRO)
	
	public static ArrayList<Producto> getPrecioFiltro(String filtro) {
			
			ArrayList<Producto> productos = new ArrayList<>();
			
			try (Connection connection = Conexion.open()) {
				
				String query = "select * from producto where nombre like ?";
				
				try (PreparedStatement ps = connection.prepareStatement(query)){
					
					ps.setString(1, "%" + filtro + "%");
					
					try (ResultSet rs = ps.executeQuery()){
						
						int id = 1;
						
						while (rs.next()) {
							
							
							String nombre = rs.getString("nombre");
							double precio = rs.getDouble("precio");
							
							Producto newProducto = new Producto(id, nombre, precio);
							
							System.out.println(newProducto);
							productos.add(newProducto);
							
							id++;
						}
						
					}
					
					
				}
				
			}	catch (SQLException e) {
				System.out.println("ERROR: problemas en la conexion SQL");
				e.printStackTrace();
			}
			
			
			
			return productos;
			
	}
	
	public static ArrayList<Producto> getMostrarProductos() {
		
	    ArrayList<Producto> productos = new ArrayList<>();
	    
	    String query = "SELECT * FROM producto";
	    

	    try (Connection connection = Conexion.open();
	    		
	         PreparedStatement ps = connection.prepareStatement(query);
	    		
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	        	
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            double precio = rs.getDouble("precio");

	            Producto producto = new Producto(id, nombre, precio);
	            
	            System.out.println(producto);
	            productos.add(producto);
	            
	        }

	    } catch (SQLException e) {
	    	
	        System.out.println("ERROR: problemas en la conexión SQL");
	        e.printStackTrace();
	    }

	    return productos;
	}
	

	// Dar de alta un nuevo producto.
	
	public static boolean InsertProducto(Producto newProducto) {
		
		boolean flag = false;
		
		try (Connection connection = Conexion.open()) {
			String query = "INSERT INTO producto VALUES (?, ?, ?)";
			
			try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				
				ps.setInt(1, newProducto.getProd_id());
				ps.setString(2, newProducto.getProd_nombre());
				ps.setDouble(3, newProducto.getProd_precio());
				
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
					
					System.out.println("Clave del producto insertado: " + newActorKey);
				}
				
			}
			
		} catch (SQLException e) {
			System.err.println("ERROR: ");
			e.getMessage();
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	public static boolean ModificarProducto(Producto updateProducto) {
			
			boolean flag = false;
			
			try (Connection connection = Conexion.open()) {
				String query = "update producto set nombre = ?, precio = ? where id = ?";
				
				try (PreparedStatement ps = connection.prepareStatement(query)) {
					
					ps.setString(1, updateProducto.getProd_nombre());
					ps.setDouble(2, updateProducto.getProd_precio());
					ps.setInt(3, updateProducto.getProd_id());
					
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
	
	
	public static boolean DeleteProducto(int id) {
		
		boolean flag = false;
		
		try (Connection connection = Conexion.open()) {
			String query = "delete from producto where id = ?";
			
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
