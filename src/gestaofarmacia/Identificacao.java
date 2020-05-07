package gestaofarmacia;

import java.time.LocalDateTime;

public class Identificacao {

	private int id;
	private String nome;
	private String acronimo;    
	private boolean status;
	private LocalDateTime dataRegisto;
	private LocalDateTime dataActualizacao;

	public Identificacao(int id, String nome, String acronimo, boolean status,
			LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {		
		this.id = id;
		this.nome = nome;
		this.acronimo = acronimo;
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDataRegisto(LocalDateTime dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public LocalDateTime getDataRegisto() {
		return dataRegisto;
	}

	public LocalDateTime getDataActualizacao() {
		return dataActualizacao;
	}

	public int getId() {
		return id;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setDataActualizacao(LocalDateTime dataActualizacao) {
		this.dataActualizacao = dataActualizacao;
	}

	@Override
	public String toString() {
		return  "[Identificacao: "+ Validacao.delimitador + id + Validacao.delimitador + nome
				+ Validacao.delimitador + acronimo + Validacao.delimitador + status + Validacao.delimitador
				+ dataRegisto + Validacao.delimitador + dataActualizacao + Validacao.delimitador + "]";
	}	
}