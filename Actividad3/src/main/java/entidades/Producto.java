package entidades;

public class Producto {
	
	private int prod_id;
	private String prod_nombre;
	private double prod_precio;
	
	public Producto(int prod_id, String prod_nombre, double prod_precio) {
		this.prod_id = prod_id;
		this.prod_nombre = prod_nombre;
		this.prod_precio = prod_precio;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_nombre() {
		return prod_nombre;
	}

	public void setProd_nombre(String prod_nombre) {
		this.prod_nombre = prod_nombre;
	}

	public double getProd_precio() {
		return prod_precio;
	}

	public void setProd_precio(double prod_precio) {
		this.prod_precio = prod_precio;
	}

	@Override
	public String toString() {
		return prod_id +".-\nNombre: " + prod_nombre + "\nPrecio: " + prod_precio + "\n";
	}
	
	
}
