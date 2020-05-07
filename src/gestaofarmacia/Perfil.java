package gestaofarmacia;

public class Perfil {
	private int id;
	private PermissaoSistema permissaoSistema;
	private Usuario usuario;
	
	public Perfil(int id, PermissaoSistema permissaoSistema, Usuario usuario) {
		super();
		this.id = id;
		this.permissaoSistema = permissaoSistema;
		this.usuario = usuario;
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
		return "Perfil [id=" + id + ", permissaoSistema=" + permissaoSistema + ", usuario=" + usuario + "]";
	}		
}
