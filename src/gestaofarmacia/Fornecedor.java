package gestaofarmacia;

import java.time.LocalDateTime;

public class Fornecedor {	

	private int  idFornecedor;
    private String  nomeFornecedor;     
    private String  moradaFornecedor;
    private int  nuitFornecedor;
    private String  tipoPessoaFornecedor;
    private boolean  statusFornecedor;
    private LocalDateTime  dataRegistoFornecedor;
    private LocalDateTime  dataActualizacaoFornecedor;
    
	public Fornecedor(int idFornecedor, String nomeFornecedor, String moradaFornecedor, int nuitFornecedor,
			String tipoPessoaFornecedor, boolean statusFornecedor, LocalDateTime dataRegistoFornecedor,
			LocalDateTime dataActualizacaoFornecedor) {	
		this.idFornecedor = idFornecedor;
		this.nomeFornecedor = nomeFornecedor;
		this.moradaFornecedor = moradaFornecedor;
		this.nuitFornecedor = nuitFornecedor;
		this.tipoPessoaFornecedor = tipoPessoaFornecedor;
		this.statusFornecedor = statusFornecedor;
		this.dataRegistoFornecedor = dataRegistoFornecedor;
		this.dataActualizacaoFornecedor = dataActualizacaoFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getMoradaFornecedor() {
		return moradaFornecedor;
	}

	public void setMoradaFornecedor(String moradaFornecedor) {
		this.moradaFornecedor = moradaFornecedor;
	}

	public int getNuitFornecedor() {
		return nuitFornecedor;
	}

	public void setNuitFornecedor(int nuitFornecedor) {
		this.nuitFornecedor = nuitFornecedor;
	}

	public String getTipoPessoaFornecedor() {
		return tipoPessoaFornecedor;
	}

	public void setTipoPessoaFornecedor(String tipoPessoaFornecedor) {
		this.tipoPessoaFornecedor = tipoPessoaFornecedor;
	}

	public boolean isStatusFornecedor() {
		return statusFornecedor;
	}

	public void setStatusFornecedor(boolean statusFornecedor) {
		this.statusFornecedor = statusFornecedor;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setDataActualizacaoFornecedor(LocalDateTime dataActualizacaoFornecedor) {
		this.dataActualizacaoFornecedor = dataActualizacaoFornecedor;
	}

	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nomeFornecedor=" + nomeFornecedor + ", moradaFornecedor="
				+ moradaFornecedor + ", nuitFornecedor=" + nuitFornecedor + ", tipoPessoaFornecedor="
				+ tipoPessoaFornecedor + ", statusFornecedor=" + statusFornecedor + ", dataRegistoFornecedor="
				+ dataRegistoFornecedor + ", dataActualizacaoFornecedor=" + dataActualizacaoFornecedor + "]";
	}       
}
