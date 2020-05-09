package gestaofarmacia;

public class PermissaoSistema {
	
	private int id;
    private String nome;       
    
	public PermissaoSistema(int id, String nome) {	
		this.id = id;
		this.nome = nome;		
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Permissao Sistema [id=" + id + ", nome=" + nome + "]";
	}           
}
