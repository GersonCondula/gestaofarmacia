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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acronimo == null) ? 0 : acronimo.hashCode());
		result = prime * result + ((dataActualizacao == null) ? 0 : dataActualizacao.hashCode());
		result = prime * result + ((dataRegisto == null) ? 0 : dataRegisto.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identificacao other = (Identificacao) obj;
		if (acronimo == null) {
			if (other.acronimo != null)
				return false;
		} else if (!acronimo.equals(other.acronimo))
			return false;
		if (dataActualizacao == null) {
			if (other.dataActualizacao != null)
				return false;
		} else if (!dataActualizacao.equals(other.dataActualizacao))
			return false;
		if (dataRegisto == null) {
			if (other.dataRegisto != null)
				return false;
		} else if (!dataRegisto.equals(other.dataRegisto))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  "[Identificacao: "+ Validacao.delimitador + id + Validacao.delimitador + nome
				+ Validacao.delimitador + acronimo + Validacao.delimitador + status + Validacao.delimitador
				+ dataRegisto + Validacao.delimitador + dataActualizacao + Validacao.delimitador + "]";
	}	
}