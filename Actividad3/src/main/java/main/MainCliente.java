package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.Compra;
import entidades.Compra_Producto;
import queries.ClienteQueries;
import queries.CompraProductoQueries;
import queries.CompraQueries;
import queries.ProductoQueries;

public class MainCliente {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		// nunca habia dado tanto en algo
		// mis lagrimas estan en este trabajo
		// gracias por todo
		
		ArrayList<Compra_Producto> compraProducto = new ArrayList<>();
		
		int opcion = 0;
		
		 while (opcion != 4) {
	            try {
	                System.out.println("------------------------------------\n"
	                        + "Bienvenido Cliente\n"
	                        + "------------------------------------\n"
	                        + "Elige una opcion\n"
	                        + "1.-  Ver catalogo de productos\n"
	                        + "2.-  Seleccionar productos\n"
	                        + "3.-  Efectuar ticket de compra\n"
	                        + "4.-  Salir");

	                opcion = scanner.nextInt();
	                scanner.nextLine();

	                switch (opcion) {
	                    case 1:
	                    	
	                        try {
	                        	
	                            System.out.println("------------------------------------\n"
	                                    + "Ver catalogo de productos >>\n"
	                                    + "------------------------------------\n"
	                                    + "Elige una opcion\n"
	                                    + "1.-  Organizado ascendentemente por precio\n"
	                                    + "2.-  Organizado descendentemente por precio\n"
	                                    + "3.-  Organizado alfabeticamente por nombre\n"
	                                    + "4.-  Filtro general\n");

	                            int opcion2 = scanner.nextInt();

	                            switch (opcion2) {
	                                case 1:
	                                    ProductoQueries.getPrecioAscendente();
	                                    break;
	                                case 2:
	                                    ProductoQueries.getPrecioDescen();
	                                    break;
	                                case 3:
	                                    ProductoQueries.getPrecioAlfabetico();
	                                    break;
	                                case 4:
	                                	
	                                    System.out.println("Ingrese una cadena: ");
	                                    scanner.nextLine();
	                                    
	                                    String cadena = scanner.nextLine();
	                                    ProductoQueries.getPrecioFiltro(cadena);
	                                    
	                                    break;
	                                default:
	                                    System.out.println("ERROR: Opcion desconocida");
	                                    break;
	                            }
	                            
	                            
	                        } catch (InputMismatchException e) {
	                        	
	                            System.out.println("Error: Ingrese un número válido para la opción.");
	                            scanner.next(); 
	                        }
	                        break;

	                    case 2:
	                        ProductoQueries.getMostrarProductos();

	                        int idProducto = 0;
	                        int unidades = 0;

	                        try {
	                        	
	                            System.out.println("Ingrese el ID del producto que desea agregar al carrito: ");
	                            idProducto = scanner.nextInt();

	                            System.out.println("Ingrese la cantidad de unidades: ");
	                            unidades = scanner.nextInt();

	                            Compra_Producto compraP = CompraProductoQueries.ProductoID(idProducto, unidades);

	                            if (compraP != null) {
	                                compraProducto.add(compraP);
	                                System.out.println("Producto agregado al carrito correctamente.");
	                            } else {
	                                System.out.println("El producto no pudo ser agregado al carrito.");
	                            }
	                            
	                            
	                        } catch (InputMismatchException e) {
	                        	
	                            System.out.println("Error: Ingrese un numero valido para el ID del producto o para la cantidad de unidades.");
	                            scanner.next(); 
	                        }
	                        break;

	                    case 3:
	                        try {
	                            ClienteQueries.getMostrarClientes();

	                            int cli_id = 0;
	                            String com_concepto = "";
	                            int cmp_id = 0;

	                            System.out.println("Introduce el id del cliente: ");
	                            cli_id = scanner.nextInt();

	                            scanner.nextLine();
	                            System.out.println("Agrega el concepto de su compra: ");
	                            com_concepto = scanner.nextLine();

	                            CompraQueries.InsertarCompra(new Compra(0, com_concepto, cli_id));

	                            System.out.println("Introduce el Id de la compra: ");
	                            cmp_id = scanner.nextInt();

	                            for (Compra_Producto comProd : compraProducto) {
	                                int id = comProd.getCp_prod_id();
	                                int unidades1 = comProd.getCp_unidades();

	                                CompraProductoQueries.agregarProductoAlCarrito(cmp_id, id, unidades1);
	                            }

	                            if (CompraProductoQueries.imprimirComprasCliente(cmp_id)) {
	                                System.out.println("El ticket ha sido generado");
	                            } else {
	                                System.out.println("Hubo un problema al generar el ticket.");
	                            }
	                            
	                        } catch (InputMismatchException e) {
	                        	
	                            System.out.println("Error: Ingrese un número válido para el ID del cliente o para el ID de la compra.");
	                            scanner.next(); 
	                            
	                        }
	                        
	                        break;

	                    case 4:
	                        System.out.println("Hasta luego...");
	                        break;

	                    default:
	                        System.out.println("ERROR: Opcion desconocida.");
	                        break;

	                }
	            } catch (InputMismatchException e) {
	            	
	                System.out.println("Error: Ingrese un valor válido.");
	                scanner.next(); 
	                
	           } catch (Exception e) {
	        	   
	                System.out.println("Error: " + e.getMessage());
	           }
	       }
		 scanner.close();
	}

}
