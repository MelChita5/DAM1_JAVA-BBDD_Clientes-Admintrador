package entidades;


public class Compra_Producto {
	
	private int cp_com_id;
	private int cp_prod_id;
	private int cp_unidades;
	
	public Compra_Producto(int cp_com_id, int cp_prod_id, int cp_unidades) {
		this.cp_com_id = cp_com_id;
		this.cp_prod_id = cp_prod_id;
		this.cp_unidades = cp_unidades;
	}
	
	public int getCp_com_id() {
		return cp_com_id;
	}
	public void setCp_com_id(int cp_com_id) {
		this.cp_com_id = cp_com_id;
	}
	public int getCp_prod_id() {
		return cp_prod_id;
	}
	public void setCp_prod_id(int cp_prod_id) {
		this.cp_prod_id = cp_prod_id;
	}
	public int getCp_unidades() {
		return cp_unidades;
	}
	public void setCp_unidades(int cp_unidades) {
		this.cp_unidades = cp_unidades;
	}
	
	
}
