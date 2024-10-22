package entidades;

public class Compra {
	
	private int com_id;
	private String com_concepto;
	private int com_cli_id;
	
	public Compra(int com_id, String com_concepto, int com_cli_id) {
		this.com_id = com_id;
		this.com_concepto = com_concepto;
		this.com_cli_id = com_cli_id;
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getCom_concepto() {
		return com_concepto;
	}

	public void setCom_concepto(String com_concepto) {
		this.com_concepto = com_concepto;
	}

	public int getCom_cli_id() {
		return com_cli_id;
	}

	public void setCom_cli_id(int com_cli_id) {
		this.com_cli_id = com_cli_id;
	}
	
	
}
