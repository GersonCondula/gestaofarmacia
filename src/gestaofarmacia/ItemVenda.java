package gestaofarmacia;

import java.time.LocalDateTime;

public class ItemVenda {
	private int idItemVenda;
    private int idVendaItemVenda;
    private int idProdutoItemVenda;               
    private LocalDateTime dataActualizacaoItemVenda;
    
	public ItemVenda(int idItemVenda, int idVendaItemVenda, int idProdutoItemVenda,
			LocalDateTime dataActualizacaoItemVenda) {			
		this.idItemVenda = idItemVenda;
		this.idVendaItemVenda = idVendaItemVenda;
		this.idProdutoItemVenda = idProdutoItemVenda;
		this.dataActualizacaoItemVenda = dataActualizacaoItemVenda;
	}

	public LocalDateTime getDataActualizacaoItemVenda() {
		return dataActualizacaoItemVenda;
	}

	public void setDataActualizacaoItemVenda(LocalDateTime dataActualizacaoItemVenda) {
		this.dataActualizacaoItemVenda = dataActualizacaoItemVenda;
	}

	public int getIdItemVenda() {
		return idItemVenda;
	}

	public int getIdVendaItemVenda() {
		return idVendaItemVenda;
	}

	public int getIdProdutoItemVenda() {
		return idProdutoItemVenda;
	}

	@Override
	public String toString() {
		return "ItemVenda [idItemVenda=" + idItemVenda + ", idVendaItemVenda=" + idVendaItemVenda
				+ ", idProdutoItemVenda=" + idProdutoItemVenda + ", dataActualizacaoItemVenda="
				+ dataActualizacaoItemVenda + "]";
	}
}
