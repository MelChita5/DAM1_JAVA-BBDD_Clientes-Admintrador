package entidades;

public class Cliente {
	
	private int cli_id;
	private String cli_nombre;
	
	public Cliente(int cli_id, String cli_nombre) {
		this.cli_id = cli_id;
		this.cli_nombre = cli_nombre;
	}

	public int getCli_id() {
		return cli_id;
	}

	public void setCli_id(int cli_id) {
		this.cli_id = cli_id;
	}

	public String getCli_nombre() {
		return cli_nombre;
	}

	public void setCli_nombre(String cli_nombre) {
		this.cli_nombre = cli_nombre;
	}
	
	@Override
	public String toString() {
		return "ID: " + cli_id +"\nNombre: " + cli_nombre + "\n";
	}
	
	
}
