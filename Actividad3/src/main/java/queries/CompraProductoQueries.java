package queries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import entidades.Compra_Producto;

public class CompraProductoQueries {
	
	public static Compra_Producto ProductoID(int id, int unidades) {
		
		Compra_Producto compraProducto = null;
		try (Connection connection = Conexion.open()) {
			
			String query = "select * from producto WHERE id = ?";
			
			try (PreparedStatement ps = connection.prepareStatement(query)){
				ps.setInt(1, id);
				
				try (ResultSet rs = ps.executeQuery()){
					
					
					if(rs.next()) {
						 int id1 = rs.getInt("id");
						 compraProducto = new Compra_Producto (0, id1, unidades);
					}
					
					
					
				}
				
				
			}
			
		}	catch (SQLException e) {
			System.out.println("ERROR: problemas en la conexion SQL");
			e.printStackTrace();
		}
		
		return compraProducto;
	}
	
	public static boolean agregarProductoAlCarrito(int idCompra, int idProducto, int unidades) {
		boolean flag = false;
	    try (Connection connection = Conexion.open()) {
	    	
	        String query = "INSERT INTO compra_producto  VALUES (?, ?, ?)";
	        
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	        	
	            ps.setInt(1, idCompra);
	            ps.setInt(2, idProducto);
	            ps.setInt(3, unidades);
	            
	            ps.executeUpdate();
	            
	        }
	        
	    } catch (SQLException e) {
	    	
	        System.out.println("ERROR: problemas en la conexión SQL");
	        e.printStackTrace();
	    }
	    return flag;
	}
	
	public static boolean imprimirComprasCliente(int compra_id) {
	    
	    boolean flag = false;
	    double precioTotal = 0.0;
	    
	    // ruta del FP
	    //String rutaArchivo = "C:\\Users\\Alumno\\Desktop\\ticket.txt";
	    // C:\\Users\\usuario\\Desktop\\ticket.txt
	    String rutaArchivo = "ticket.txt";
	    
	    try {
	        File archivo = new File(rutaArchivo);
	        
	        System.out.println("Ruta de la ubicacion del ticket: " + archivo.getAbsolutePath());
	        
	        if (!archivo.exists()) {
	            archivo.createNewFile();
	        }
	        
	        try (Connection connection = Conexion.open();
	             BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
	            
	            String query = "select compra.id, producto.nombre, producto.precio, compra_producto.unidades "
	                         + "from compra, producto, compra_producto "
	                         + "where "
	                         + "compra.id = compra_producto.id_compra AND "
	                         + "compra_producto.id_producto = producto.id AND compra.id = ?";
	            
	            try (PreparedStatement ps = connection.prepareStatement(query)) {
	                
	                ps.setInt(1, compra_id);
	                
	                try (ResultSet rs = ps.executeQuery()) {
	                    int id = 1;
	                    
	                    writer.write("Ticket de compra:\n");
	                    
	                    while (rs.next()) {
	                        String nombreP = rs.getString("nombre");
	                        double precioP = rs.getDouble("precio");
	                        int unidades = rs.getInt("unidades");
	                        
	                        double precioTotalProducto = precioP * unidades;
	                        
	                        writer.write("\nID Compra: " + id + "\nProducto: " + nombreP + "\nPrecio: " + precioP + "\nUnidades: " + unidades + "\n");
	                        
	                        //precioTotal += precioP;
	                        precioTotal += precioTotalProducto;
	                        id++;
	                    }
	                    
	                    writer.write("\nPrecio Total: " + precioTotal + "\n");
	                }
	            }
	            
	            flag = true; 
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    
	    
	    return flag;
	}
}
