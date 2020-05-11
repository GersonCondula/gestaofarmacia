package gestaofarmacia;

import java.time.LocalDateTime;

public class Produto {
	
	private int id;
    private Fornecedor fornecedor;    
    private CategoriaProduto categoriaProduto;
    private String nome; 
    private String marca;
    private String descricao;
    private int quantidade;        
    private double preco;
    private boolean status;
    private LocalDateTime dataValidade;
    private LocalDateTime dataRegisto;
    private LocalDateTime dataActualizacao;
    
	public Produto(int id, Fornecedor fornecedor, CategoriaProduto categoriaProduto, String nome, String marca,
			String descricao, int quantidade, double preco, boolean status, LocalDateTime dataValidade,
			LocalDateTime dataRegisto, LocalDateTime dataActualizacao) {
		super();
		this.id = id;
		this.fornecedor = fornecedor;
		this.categoriaProduto = categoriaProduto;
		this.nome = nome;
		this.marca = marca;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.status = status;
		this.dataValidade = dataValidade;
		this.dataRegisto = dataRegisto;
		this.dataActualizacao = dataActualizacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDateTime dataValidade) {
		this.dataValidade = dataValidade;
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", fornecedor=" + fornecedor + ", categoriaProduto=" + categoriaProduto + ", nome="
				+ nome + ", marca=" + marca + ", descricao=" + descricao + ", quantidade=" + quantidade + ", preco="
				+ preco + ", status=" + status + ", dataValidade=" + dataValidade + ", dataRegisto=" + dataRegisto
				+ ", dataActualizacao=" + dataActualizacao + "]";
	}
    	   
}
