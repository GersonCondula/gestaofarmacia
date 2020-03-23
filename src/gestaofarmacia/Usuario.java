package gestaofarmacia;

import java.time.LocalDateTime;

public class Usuario {	
	
	private int  idUsuario;
    private int  idFunionarioUsuario;
    private int  idPermissaoSistemaUsuario;
    private boolean  statusUsuario;
    private LocalDateTime  dataRegistoUsuario;
    private LocalDateTime  dataActualizacaoUsuario;
	
    public Usuario(int idUsuario, int idFunionarioUsuario, int idPermissaoSistemaUsuario, boolean statusUsuario,
			LocalDateTime dataRegistoUsuario, LocalDateTime dataActualizacaoUsuario) {	
		this.idUsuario = idUsuario;
		this.idFunionarioUsuario = idFunionarioUsuario;
		this.idPermissaoSistemaUsuario = idPermissaoSistemaUsuario;
		this.statusUsuario = statusUsuario;
		this.dataRegistoUsuario = dataRegistoUsuario;
		this.dataActualizacaoUsuario = dataActualizacaoUsuario;
	}

	public boolean isStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(boolean statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getIdFunionarioUsuario() {
		return idFunionarioUsuario;
	}

	public int getIdPermissaoSistemaUsuario() {
		return idPermissaoSistemaUsuario;
	}

	public void setDataActualizacaoUsuario(LocalDateTime dataActualizacaoUsuario) {
		this.dataActualizacaoUsuario = dataActualizacaoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", idFunionarioUsuario=" + idFunionarioUsuario
				+ ", idPermissaoSistemaUsuario=" + idPermissaoSistemaUsuario + ", statusUsuario=" + statusUsuario
				+ ", dataRegistoUsuario=" + dataRegistoUsuario + ", dataActualizacaoUsuario=" + dataActualizacaoUsuario
				+ "]";
	}
}
