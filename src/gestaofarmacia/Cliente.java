package gestaofarmacia;

public class Cliente {
	
	private int idCliente;
    private String nomeCliente;     
    private String moradaCliente;
	
    public Cliente(int idCliente, String nomeCliente, String moradaCliente) {
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.moradaCliente = moradaCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getMoradaCliente() {
		return moradaCliente;
	}

	public void setMoradaCliente(String moradaCliente) {
		this.moradaCliente = moradaCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", moradaCliente=" + moradaCliente
				+ "]";
	}
}
