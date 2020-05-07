package gestaofarmacia;

import java.time.LocalDateTime;

public class Funcionario {
	private int  id;
	private String  nome;
	private Identificacao identificacao;
	private String numeroIdentidade;		
	private int nuit;
	private String morada;
	private boolean status;
	private LocalDateTime dataRegisto;
	private LocalDateTime dataActualizacao;	

	Funcionario (){
		
	}
	
	public Funcionario(int id, String nome, Identificacao identificacao, String numeroIdentidade, int nuit, String morada,
			boolean status, LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.identificacao = identificacao;
		this.numeroIdentidade = numeroIdentidade;
		this.nuit = nuit;
		this.morada = morada;
		this.status = status;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}
	
	public Identificacao getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Identificacao identificacao) {
		this.identificacao = identificacao;
	}

	public String getNumeroIdentidade() {
		return numeroIdentidade;
	}

	public void setNumeroIdentidade(String numeroIdentidade) {
		this.numeroIdentidade = numeroIdentidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeFuncionario) {
		this.nome = nomeFuncionario;
	}

	public int getNuit() {
		return nuit;
	}

	public void setNuit(int nuitFunionario) {
		this.nuit = nuitFunionario;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String moradaFuncionario) {
		this.morada = moradaFuncionario;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean statusFuncionario) {
		this.status = statusFuncionario;
	}

	public int getId() {
		return id;
	}

	public void setDataActualizacaoFuncionario(LocalDateTime dataActualizacao) {
		this.dataActualizacao = dataActualizacao;
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

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Funcionario [ID=" + id + ", Nome=" + nome
				+ ", Identidade=" + identificacao + ", nuit=" + nuit
				+ ", morada=" + morada + ", status=" + status
				+ ", dataRegisto=" + dataRegisto + ", dataActualizacao="
				+ dataActualizacao + "]";
	}     
}
