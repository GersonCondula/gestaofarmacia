package gestaofarmacia;

import java.time.LocalDateTime;

public class ItemVenda {
	private int id;
    private Venda venda;
    private Produto produto;
    private int quantidade;    
    private LocalDateTime dataActualizacao;
	public ItemVenda() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemVenda(int id, Venda venda, Produto produto, int quantidade, LocalDateTime dataActualizacao) {
		super();
		this.id = id;
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.dataActualizacao = dataActualizacao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public LocalDateTime getDataActualizacao() {
		return dataActualizacao;
	}
	public void setDataActualizacao(LocalDateTime dataActualizacao) {
		this.dataActualizacao = dataActualizacao;
	}
	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", venda=" + venda + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", dataActualizacao=" + dataActualizacao + "]";
	}
	
}
