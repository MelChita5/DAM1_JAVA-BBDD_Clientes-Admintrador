package main;

import java.util.Scanner;

import entidades.Cliente;
import entidades.Producto;
import queries.ClienteQueries;
import queries.ProductoQueries;

public class MainAdmin {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
		boolean continuar = true;

		while (continuar) {
			
			try {
				
				System.out.println("------------------------------------\n"
									+ "Bienvenido Administrador\n"
									+ "------------------------------------\n"
									+ "Elige una opcion\n"
									+ "1.-  Dar de alta un nuevo Cliente\n"  
									+ "2.-  Dar de alta un nuevo Producto\n"
									+ "3.-  Modificar los datos de un Cliente\n"
									+ "4.-  Modificar los datos de un Producto\n"
									+ "5.-  Eliminar un Cliente por su ID\n"
									+ "6.-  Eliminar un Producto por su ID\n");
				
				int opcion = scanner.nextInt();
				scanner.nextLine();
				
				switch(opcion) {
				
				
				case 1:
					System.out.println("Ingrese el Nombre: ");
					String ins_nombreC = scanner.nextLine();
					
					ClienteQueries.InsertCliente(new Cliente(0, ins_nombreC));
					
					break;
					
				case 2:
					System.out.println("Ingrese el Nombre: ");
					String ins_nombreP = scanner.nextLine();
					
					System.out.println("Ingrese el Precio: ");
					double ins_precioP = scanner.nextDouble();
					
					ProductoQueries.InsertProducto(new Producto(0, ins_nombreP, ins_precioP));
					
					break;
					
				case 3:
					System.out.println("Ingrese el ID del cliente a modificar: ");
					int mod_idC = scanner.nextInt();
					
					scanner.nextLine();
					
					System.out.println("Ingrese el Nombre a modificar: ");
					String mod_nombreC = scanner.nextLine();
					
					ClienteQueries.ModificarCliente(new Cliente(mod_idC, mod_nombreC));
					
					break;
					
				case 4:
					System.out.println("Ingrese el ID del producto a modificar: ");
					int mod_idP = scanner.nextInt();
					
					scanner.nextLine();
					
					System.out.println("Ingrese el Nombre a modificar: ");
					String mod_nombreP = scanner.nextLine();
					
					System.out.println("Ingrese el Precio a modificar: ");
					double mod_precioP = scanner.nextDouble();
					
					ProductoQueries.ModificarProducto(new Producto(mod_idP, mod_nombreP, mod_precioP));
					break;
					
				case 5:
					System.out.println("Ingrese el ID del cliente a eliminar: ");
					int cli_id = scanner.nextInt();
					
					ClienteQueries.DeleteCliente(cli_id);
					break;
					
				case 6:
					System.out.println("Ingrese el ID del producto a eliminar: ");
					int prod_id = scanner.nextInt();
					
					ProductoQueries.DeleteProducto(prod_id);
					break;
					
				default:
					System.out.println("ERROR: Opcion desonocida");
					continuar = false;
					break;
				}

		} catch (Exception e) {
            System.out.println("ERROR: Ocurrió un error al procesar la opción. Por favor, intenta de nuevo.");
            scanner.nextLine(); 
        }
		
		
		
		
		}
		
		scanner.close();
	}
}
