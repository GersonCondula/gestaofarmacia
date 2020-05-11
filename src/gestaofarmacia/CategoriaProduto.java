package gestaofarmacia;

import java.time.LocalDateTime;

public class CategoriaProduto {
	
	private int id;
    private String nome;   
    private String descricao;
    private boolean status;    
    private LocalDateTime dataRegisto;
    private LocalDateTime dataActualizacao;
    
	public CategoriaProduto(int id, String nome, String descricao, 
			boolean status, LocalDateTime dataRegisto,
			LocalDateTime dataActualizacao) {	
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		return "CategoriaProduto [id=" + id + ", nome=" + nome + ", status=" + status + ", descricao=" + descricao
				+ ", dataRegisto=" + dataRegisto + ", dataActualizacao=" + dataActualizacao + "]";
	}
}
