package gestaofarmacia;

import java.time.LocalDateTime;

public class Funcionario {
	private int  idFuncionario;
    private String  nomeFuncionario;
    private int  idIdentificacaoFuncionario;
    private int  nuitFunionario;
    private String  moradaFuncionario;
    private boolean  statusFuncionario;
    private LocalDateTime  dataRegistoFuncionario;
    private LocalDateTime  dataActualizacaoFuncionario;
    
	public Funcionario(int idFuncionario, String nomeFuncionario, int idIdentificacaoFuncionario, int nuitFunionario,
			String moradaFuncionario, boolean statusFuncionario, LocalDateTime dataRegistoFuncionario,
			LocalDateTime dataActualizacaoFuncionario) {	
		this.idFuncionario = idFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.idIdentificacaoFuncionario = idIdentificacaoFuncionario;
		this.nuitFunionario = nuitFunionario;
		this.moradaFuncionario = moradaFuncionario;
		this.statusFuncionario = statusFuncionario;
		this.dataRegistoFuncionario = dataRegistoFuncionario;
		this.dataActualizacaoFuncionario = dataActualizacaoFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public int getNuitFunionario() {
		return nuitFunionario;
	}

	public void setNuitFunionario(int nuitFunionario) {
		this.nuitFunionario = nuitFunionario;
	}

	public String getMoradaFuncionario() {
		return moradaFuncionario;
	}

	public void setMoradaFuncionario(String moradaFuncionario) {
		this.moradaFuncionario = moradaFuncionario;
	}

	public boolean isStatusFuncionario() {
		return statusFuncionario;
	}

	public void setStatusFuncionario(boolean statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public int getIdIdentificacaoFuncionario() {
		return idIdentificacaoFuncionario;
	}

	public void setDataActualizacaoFuncionario(LocalDateTime dataActualizacaoFuncionario) {
		this.dataActualizacaoFuncionario = dataActualizacaoFuncionario;
	}

	@Override
	public String toString() {
		return "Funcionario [idFuncionario=" + idFuncionario + ", nomeFuncionario=" + nomeFuncionario
				+ ", idIdentificacaoFuncionario=" + idIdentificacaoFuncionario + ", nuitFunionario=" + nuitFunionario
				+ ", moradaFuncionario=" + moradaFuncionario + ", statusFuncionario=" + statusFuncionario
				+ ", dataRegistoFuncionario=" + dataRegistoFuncionario + ", dataActualizacaoFuncionario="
				+ dataActualizacaoFuncionario + "]";
	}       
}
