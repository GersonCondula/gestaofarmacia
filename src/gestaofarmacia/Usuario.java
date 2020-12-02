package gestaofarmacia;

import java.time.LocalDateTime;

public class Usuario {	

	private int id;
	private Funcionario funcionario;
	private String username;
	private String password;
	private boolean status;
	private LocalDateTime dataRegisto;
	private LocalDateTime dataActualizacao;

	
	public Usuario(int id, Funcionario funcionario, String password, boolean status,
			LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.username = geraUsername();
		this.password = password;
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}

	public String geraUsername() {
		String [] anArray = funcionario.getNome().split(" ");
		String username = null;
		for (int i = 0; i < anArray.length; i++) {
			username = anArray[0].charAt(0)+"".concat(anArray[anArray.length-1]);
		}
		return username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
