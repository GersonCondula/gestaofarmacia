package gestaofarmacia;

import java.time.LocalDateTime;

public class Conta {

	private int idConta;
    private String nomeConta;
    private double numeroConta;
    private boolean statusConta;
    private LocalDateTime dataRegistoConta;
    private LocalDateTime dataActualizacaoConta;
    
	public Conta(int idConta, String nomeConta, double numeroConta, boolean statusConta, LocalDateTime dataRegistoConta,
			LocalDateTime dataActualizacaoConta) {	
		this.idConta = idConta;
		this.nomeConta = nomeConta;
		this.numeroConta = numeroConta;
		this.statusConta = statusConta;
		this.dataRegistoConta = dataRegistoConta;
		this.dataActualizacaoConta = dataActualizacaoConta;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public double getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(double numeroConta) {
		this.numeroConta = numeroConta;
	}

	public boolean isStatusConta() {
		return statusConta;
	}

	public void setStatusConta(boolean statusConta) {
		this.statusConta = statusConta;
	}

	public int getIdConta() {
		return idConta;
	}

	public LocalDateTime getDataActualizacaoConta() {
		return dataActualizacaoConta;
	}

	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", nomeConta=" + nomeConta + ", numeroConta=" + numeroConta
				+ ", statusConta=" + statusConta + ", dataRegistoConta=" + dataRegistoConta + ", dataActualizacaoConta="
				+ dataActualizacaoConta + "]";
	}
}
