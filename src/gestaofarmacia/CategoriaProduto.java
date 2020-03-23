package gestaofarmacia;

import java.time.LocalDateTime;

public class CategoriaProduto {
	
	private int idCategoriaProduto;
    private String nomeCategoriaProduto;       
    private boolean statusCategoriaProduto;
    private String observacoesCategoriaProduto;
    private LocalDateTime dataRegistoCategoriaProduto;
    private LocalDateTime dataActualizacaoCategoriaProduto;
    
	public CategoriaProduto(int idCategoriaProduto, String nomeCategoriaProduto, boolean statusCategoriaProduto,
			String observacoesCategoriaProduto, LocalDateTime dataRegistoCategoriaProduto,
			LocalDateTime dataActualizacaoCategoriaProduto) {	
		this.idCategoriaProduto = idCategoriaProduto;
		this.nomeCategoriaProduto = nomeCategoriaProduto;
		this.statusCategoriaProduto = statusCategoriaProduto;
		this.observacoesCategoriaProduto = observacoesCategoriaProduto;
		this.dataRegistoCategoriaProduto = dataRegistoCategoriaProduto;
		this.dataActualizacaoCategoriaProduto = dataActualizacaoCategoriaProduto;
	}

	public String getNomeCategoriaProduto() {
		return nomeCategoriaProduto;
	}

	public void setNomeCategoriaProduto(String nomeCategoriaProduto) {
		this.nomeCategoriaProduto = nomeCategoriaProduto;
	}

	public boolean isStatusCategoriaProduto() {
		return statusCategoriaProduto;
	}

	public void setStatusCategoriaProduto(boolean statusCategoriaProduto) {
		this.statusCategoriaProduto = statusCategoriaProduto;
	}

	public String getObservacoesCategoriaProduto() {
		return observacoesCategoriaProduto;
	}

	public void setObservacoesCategoriaProduto(String observacoesCategoriaProduto) {
		this.observacoesCategoriaProduto = observacoesCategoriaProduto;
	}

	public int getIdCategoriaProduto() {
		return idCategoriaProduto;
	}

	public void setDataActualizacaoCategoriaProduto(LocalDateTime dataActualizacaoCategoriaProduto) {
		this.dataActualizacaoCategoriaProduto = dataActualizacaoCategoriaProduto;
	}

	@Override
	public String toString() {
		return "CategoriaProduto [idCategoriaProduto=" + idCategoriaProduto + ", nomeCategoriaProduto="
				+ nomeCategoriaProduto + ", statusCategoriaProduto=" + statusCategoriaProduto
				+ ", observacoesCategoriaProduto=" + observacoesCategoriaProduto + ", dataRegistoCategoriaProduto="
				+ dataRegistoCategoriaProduto + ", dataActualizacaoCategoriaProduto=" + dataActualizacaoCategoriaProduto
				+ "]";
	}
}
