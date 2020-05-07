package gestaofarmacia;

import java.time.LocalDateTime;

public class Usuario {	

	private int id;
	private Funcionario funcionario;    
	private boolean status;
	private LocalDateTime dataRegisto;
	private LocalDateTime dataActualizacao;

	public Usuario(int id, Funcionario funionario, boolean status,
			LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {	
		this.id = id;
		this.funcionario = funionario;		
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

	public int getId() {
		return id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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

	public void setDataActualizacao(LocalDateTime dataActualizacao) {
		this.dataActualizacao = dataActualizacao;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", Funionario=" + funcionario
				+ ", status=" + status
				+ ", dataRegisto=" + dataRegisto + ", dataActualizacao=" + dataActualizacao
				+ "]";
	}
}
