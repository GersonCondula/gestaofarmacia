package gestaofarmacia;

import java.time.LocalDateTime;

public class Venda {
	
	private int idVenda;
    private int idClienteVenda;
    private int idUsuarioVenda;
    private int quantidadeItensVenda;
    private double valorPagoVenda;
    private LocalDateTime dataRegistoVenda;
    private LocalDateTime dataActualizacaoVenda;
    
	public Venda(int idVenda, int idClienteVenda, int idUsuarioVenda, int quantidadeItensVenda, double valorPagoVenda,
			LocalDateTime dataRegistoVenda, LocalDateTime dataActualizacaoVenda) {	
		this.idVenda = idVenda;
		this.idClienteVenda = idClienteVenda;
		this.idUsuarioVenda = idUsuarioVenda;
		this.quantidadeItensVenda = quantidadeItensVenda;
		this.valorPagoVenda = valorPagoVenda;
		this.dataRegistoVenda = dataRegistoVenda;
		this.dataActualizacaoVenda = dataActualizacaoVenda;
	}
	
	public int getQuantidadeItensVenda() {
		return quantidadeItensVenda;
	}

	public void setQuantidadeItensVenda(int quantidadeItensVenda) {
		this.quantidadeItensVenda = quantidadeItensVenda;
	}

	public double getValorPagoVenda() {
		return valorPagoVenda;
	}

	public void setValorPagoVenda(double valorPagoVenda) {
		this.valorPagoVenda = valorPagoVenda;
	}

	public LocalDateTime getDataActualizacaoVenda() {
		return dataActualizacaoVenda;
	}

	public void setDataActualizacaoVenda(LocalDateTime dataActualizacaoVenda) {
		this.dataActualizacaoVenda = dataActualizacaoVenda;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public int getIdClienteVenda() {
		return idClienteVenda;
	}

	public int getIdUsuarioVenda() {
		return idUsuarioVenda;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", idClienteVenda=" + idClienteVenda + ", idUsuarioVenda=" + idUsuarioVenda
				+ ", quantidadeItensVenda=" + quantidadeItensVenda + ", valorPagoVenda=" + valorPagoVenda
				+ ", dataRegistoVenda=" + dataRegistoVenda + ", dataActualizacaoVenda=" + dataActualizacaoVenda + "]";
	}
}
