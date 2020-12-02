package gestaofarmacia;

import java.time.LocalDateTime;

public class Venda {
	
	private int id; 
    private Usuario usuario;
    private LocalDateTime dataRegistoVenda;
    private LocalDateTime dataActualizacaoVenda;
    
	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venda(int id, Usuario usuario, LocalDateTime dataRegistoVenda,
			LocalDateTime dataActualizacaoVenda) {
		super();
		this.id = id;		
		this.usuario = usuario;
		this.dataRegistoVenda = dataRegistoVenda;
		this.dataActualizacaoVenda = dataActualizacaoVenda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataRegistoVenda() {
		return dataRegistoVenda;
	}

	public void setDataRegistoVenda(LocalDateTime dataRegistoVenda) {
		this.dataRegistoVenda = dataRegistoVenda;
	}

	public LocalDateTime getDataActualizacaoVenda() {
		return dataActualizacaoVenda;
	}

	public void setDataActualizacaoVenda(LocalDateTime dataActualizacaoVenda) {
		this.dataActualizacaoVenda = dataActualizacaoVenda;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", usuario=" + usuario + ", dataRegistoVenda=" + dataRegistoVenda
				+ ", dataActualizacaoVenda=" + dataActualizacaoVenda + "]";
	}
}
