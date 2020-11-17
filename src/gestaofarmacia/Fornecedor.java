package gestaofarmacia;

import java.time.LocalDateTime;

public class Fornecedor extends Pessoa{	
	       
    private boolean  status;
    private LocalDateTime  dataRegisto;
    private LocalDateTime  dataActualizacao;
	public Fornecedor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fornecedor(int id, String nome, String morada, int nuit, boolean status, LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {
		super(id, nome, morada, nuit);
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}
	public int getNuit() {
		return nuit;
	}
	public void setNuit(int nuit) {
		this.nuit = nuit;
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
		return "Fornecedor [nuit=" + nuit + ", status=" + status + ", dataRegisto=" + dataRegisto
				+ ", dataActualizacao=" + dataActualizacao + "]";
	}
}
