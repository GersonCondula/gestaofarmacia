package gestaofarmacia;

public class Perfil {
	private int id;
	private PermissaoSistema permissaoSistema;
	private Usuario usuario;
	
	public Perfil(int id, Usuario usuario, PermissaoSistema permissaoSistema) {
		super();
		this.id = id;		
		this.usuario = usuario;
		this.permissaoSistema = permissaoSistema;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PermissaoSistema getPermissaoSistema() {
		return permissaoSistema;
	}

	public void setPermissaoSistema(PermissaoSistema permissaoSistema) {
		this.permissaoSistema = permissaoSistema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", usuario=" + usuario + " permissaoSistema=" + permissaoSistema + "]";
	}		
}
