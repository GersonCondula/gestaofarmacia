package gestaofarmacia;

import java.time.LocalDateTime;

public class Produto {
	
	private int idProduto;
    private int idFornecedorProduto;
    private int idUsuarioProduto;
    private int idProdutoCategoria;
    private String nomeProduto; 
    private String marcaProduto;
    private int quantidadeProduto;
    private boolean statusProduto;
    private String observacoesProduto;
    private double precoVendaProduto;
    private LocalDateTime dataValidadeProduto;
    private LocalDateTime dataRegistoProduto;
    private LocalDateTime dataActualizacaoProduto;
    
	public Produto(int idProduto, int idFornecedorProduto, int idUsuarioProduto, int idProdutoCategoria,
			String nomeProduto, String marcaProduto, int quantidadeProduto, boolean statusProduto,
			String observacoesProduto, double precoVendaProduto, LocalDateTime dataValidadeProduto,
			LocalDateTime dataRegistoProduto, LocalDateTime dataActualizacaoProduto) {	
		this.idProduto = idProduto;
		this.idFornecedorProduto = idFornecedorProduto;
		this.idUsuarioProduto = idUsuarioProduto;
		this.idProdutoCategoria = idProdutoCategoria;
		this.nomeProduto = nomeProduto;
		this.marcaProduto = marcaProduto;
		this.quantidadeProduto = quantidadeProduto;
		this.statusProduto = statusProduto;
		this.observacoesProduto = observacoesProduto;
		this.precoVendaProduto = precoVendaProduto;
		this.dataValidadeProduto = dataValidadeProduto;
		this.dataRegistoProduto = dataRegistoProduto;
		this.dataActualizacaoProduto = dataActualizacaoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarcaProduto() {
		return marcaProduto;
	}

	public void setMarcaProduto(String marcaProduto) {
		this.marcaProduto = marcaProduto;
	}

	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public boolean isStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(boolean statusProduto) {
		this.statusProduto = statusProduto;
	}

	public String getObservacoesProduto() {
		return observacoesProduto;
	}

	public void setObservacoesProduto(String observacoesProduto) {
		this.observacoesProduto = observacoesProduto;
	}

	public double getPrecoVendaProduto() {
		return precoVendaProduto;
	}

	public void setPrecoVendaProduto(double precoVendaProduto) {
		this.precoVendaProduto = precoVendaProduto;
	}

	public LocalDateTime getDataValidadeProduto() {
		return dataValidadeProduto;
	}

	public void setDataValidadeProduto(LocalDateTime dataValidadeProduto) {
		this.dataValidadeProduto = dataValidadeProduto;
	}

	public LocalDateTime getDataActualizacaoProduto() {
		return dataActualizacaoProduto;
	}

	public void setDataActualizacaoProduto(LocalDateTime dataActualizacaoProduto) {
		this.dataActualizacaoProduto = dataActualizacaoProduto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public int getIdFornecedorProduto() {
		return idFornecedorProduto;
	}

	public int getIdUsuarioProduto() {
		return idUsuarioProduto;
	}

	public int getIdProdutoCategoria() {
		return idProdutoCategoria;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", idFornecedorProduto=" + idFornecedorProduto
				+ ", idUsuarioProduto=" + idUsuarioProduto + ", idProdutoCategoria=" + idProdutoCategoria
				+ ", nomeProduto=" + nomeProduto + ", marcaProduto=" + marcaProduto + ", quantidadeProduto="
				+ quantidadeProduto + ", statusProduto=" + statusProduto + ", observacoesProduto=" + observacoesProduto
				+ ", precoVendaProduto=" + precoVendaProduto + ", dataValidadeProduto=" + dataValidadeProduto
				+ ", dataRegistoProduto=" + dataRegistoProduto + ", dataActualizacaoProduto=" + dataActualizacaoProduto
				+ "]";
	}
}
