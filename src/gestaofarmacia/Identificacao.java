package gestaofarmacia;

import java.time.LocalDateTime;

public class Identificacao {

	private int idIdentificacao;
	private String nomeIdentificacao;
	private String acronimoIdentificacao;    
	private boolean statusIdentificacao;
	private LocalDateTime dataRegistoIdentificacao;
	private LocalDateTime dataActualizacaoIdentificacaos;

	public Identificacao(int idIdentificacao, String nomeIdentificacao, String acronimoIdentificacao, boolean statusIdentificacao,
			LocalDateTime dataRegistoIdentificacao, LocalDateTime dataActualizacaoIdentificacaos) {		
		this.idIdentificacao = idIdentificacao;
		this.nomeIdentificacao = nomeIdentificacao;
		this.acronimoIdentificacao = acronimoIdentificacao;
		this.statusIdentificacao = statusIdentificacao;
		this.dataRegistoIdentificacao = dataRegistoIdentificacao;
		this.dataActualizacaoIdentificacaos = dataActualizacaoIdentificacaos;
	}

	public void setIdIdentificacao(int idIdentificacao) {
		this.idIdentificacao = idIdentificacao;
	}

	public void setDataRegistoIdentificacao(LocalDateTime dataRegistoIdentificacao) {
		this.dataRegistoIdentificacao = dataRegistoIdentificacao;
	}

	public String getAcronimoIdentificacao() {
		return acronimoIdentificacao;
	}

	public void setAcronimoIdentificacao(String acronimoIdentificacao) {
		this.acronimoIdentificacao = acronimoIdentificacao;
	}

	public LocalDateTime getDataRegistoIdentificacao() {
		return dataRegistoIdentificacao;
	}

	public LocalDateTime getDataActualizacaoIdentificacaos() {
		return dataActualizacaoIdentificacaos;
	}

	public int getIdIdentificacao() {
		return idIdentificacao;
	}	

	public String getNomeIdentificacao() {
		return nomeIdentificacao;
	}

	public void setNomeIdentificacao(String nomeIdentificacao) {
		this.nomeIdentificacao = nomeIdentificacao;
	}

	public boolean isStatusIdentificacao() {
		return statusIdentificacao;
	}

	public void setStatusIdentificacao(boolean statusIdentificacao) {
		this.statusIdentificacao = statusIdentificacao;
	}

	public void setDataActualizacaoIdentificacaos(LocalDateTime dataActualizacaoIdentificacaos) {
		this.dataActualizacaoIdentificacaos = dataActualizacaoIdentificacaos;
	}

	@Override
	public String toString() {
		return  "[Identificacao: "+ Validacao.delimitador + idIdentificacao + Validacao.delimitador + nomeIdentificacao
				+ Validacao.delimitador + acronimoIdentificacao + Validacao.delimitador + statusIdentificacao + Validacao.delimitador
				+ dataRegistoIdentificacao + Validacao.delimitador + dataActualizacaoIdentificacaos + Validacao.delimitador + "]";
	}	
}
