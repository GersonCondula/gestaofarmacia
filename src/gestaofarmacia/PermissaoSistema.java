package gestaofarmacia;

import java.time.LocalDateTime;

public class PermissaoSistema {
	
	private int id;
    private String nome;    
    private boolean status;
    private LocalDateTime dataRegisto;
    private LocalDateTime dataActualizacao;
    
	public PermissaoSistema(int id, String nome, boolean status,
			LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {	
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
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

	public LocalDateTime getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(LocalDateTime dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public LocalDateTime getDataActualizacao() {
		return dataActualizacao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setDataActualizacao(LocalDateTime dataActualizacao) {
		this.dataActualizacao = dataActualizacao;
	}

	@Override
	public String toString() {
		return "Permissao Sistema [id=" + id + ", nome="
				+ nome + ", status=" + status
				+ ", dataRegisto=" + dataRegisto + ", dataActualizacao="
				+ dataActualizacao + "]";
	}           
}
