package gestaofarmacia;

import java.time.LocalDateTime;

public class Fornecedor {	

	private int  id;
    private String  nome;     
    private String  morada;        
    private int  nuit;
    private boolean  status;
    private LocalDateTime  dataRegisto;
    private LocalDateTime  dataActualizacao;
    
	public Fornecedor(int id, String nome, String morada, int nuit, boolean status,
			LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.morada = morada;
		this.nuit = nuit;		
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
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
		return "Fornecedor [id=" + id + ", nome=" + nome + ", morada=" + morada + ", nuit=" + nuit + ", status=" + status + ", dataRegisto=" + dataRegisto + ", dataActualizacao="
				+ dataActualizacao + "]";
	}       
}
