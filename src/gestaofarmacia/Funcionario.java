package gestaofarmacia;

import java.time.LocalDateTime;

public class Funcionario extends Pessoa{
	
	private boolean status;
	private LocalDateTime dataRegisto;
	private LocalDateTime dataActualizacao;
	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Funcionario(int id, String nome, Identificacao identificacao, String numeroIdentidade, int nuit, String morada,boolean status, LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {
		super(id, nome, morada, identificacao, numeroIdentidade, nuit);
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LocalDateTime getDataRegisto() {
		return dataRegisto;
	}
	public void setDataRegisto(LocalDateTime dataRegisto) {
		this.dataRegisto = dataRegisto;
	}
	public LocalDateTime getDataActualizacao() {
		return dataActualizacao;
	}
	public void setDataActualizacao(LocalDateTime dataActualizacao) {
		this.dataActualizacao = dataActualizacao;
	}
	@Override
	public String toString() {
		return super.toString() + "\n" + "Funcionario [status=" + status + ", dataRegisto=" + dataRegisto + ", dataActualizacao="
				+ dataActualizacao + "]";
	}	
}
