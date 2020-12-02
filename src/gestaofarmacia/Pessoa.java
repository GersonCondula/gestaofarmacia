package gestaofarmacia;

public class Pessoa {
	protected int id;
	protected String nome;
	protected String morada;
	protected Identificacao identificacao;
	protected String numeroIdentidade;		
	protected int nuit;
	
	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructor para o fornecedor
	public Pessoa(int id, String nome, String morada, int nuit) {
		super();
		this.id = id;
		this.nome = nome;
		this.morada = morada;
		this.nuit = nuit;
	}
	
	// Constructor para o funcionario
	public Pessoa(int id, String nome, String morada, Identificacao identificacao, String numeroIdentidade, int nuit) {
		super();
		this.id = id;
		this.nome = nome;
		this.morada = morada;
		this.identificacao = identificacao;
		this.numeroIdentidade = numeroIdentidade;
		this.nuit = nuit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
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

	public int getNuit() {
		return nuit;
	}

	public void setNuit(int nuit) {
		this.nuit = nuit;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", morada=" + morada + "\n, identificacao=" + identificacao
				+ ", numeroIdentidade=" + numeroIdentidade + ", nuit=" + nuit + "]";
	}
}
