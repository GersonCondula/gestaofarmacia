package gestaofarmacia;

import java.time.LocalDateTime;

public class PermissaoSistema {
	
	private int idPermissaoSistema;
    private String nomePermissaoSistema;    
    private boolean statusPermissaoSistema;
    private LocalDateTime dataRegistoPermissaoSistema;
    private LocalDateTime dataActualizacaoPermissaoSistema;
    
	public PermissaoSistema(int idPermissaoSistema, String nomePermissaoSistema, boolean statusPermissaoSistema,
			LocalDateTime dataRegistoPermissaoSistema, LocalDateTime dataActualizacaoPermissaoSistema) {	
		this.idPermissaoSistema = idPermissaoSistema;
		this.nomePermissaoSistema = nomePermissaoSistema;
		this.statusPermissaoSistema = statusPermissaoSistema;
		this.dataRegistoPermissaoSistema = dataRegistoPermissaoSistema;
		this.dataActualizacaoPermissaoSistema = dataActualizacaoPermissaoSistema;
	}

	public String getNomePermissaoSistema() {
		return nomePermissaoSistema;
	}

	public void setNomePermissaoSistema(String nomePermissaoSistema) {
		this.nomePermissaoSistema = nomePermissaoSistema;
	}

	public boolean isStatusPermissaoSistema() {
		return statusPermissaoSistema;
	}

	public void setStatusPermissaoSistema(boolean statusPermissaoSistema) {
		this.statusPermissaoSistema = statusPermissaoSistema;
	}

	public int getIdPermissaoSistema() {
		return idPermissaoSistema;
	}

	public void setDataActualizacaoPermissaoSistema(LocalDateTime dataActualizacaoPermissaoSistema) {
		this.dataActualizacaoPermissaoSistema = dataActualizacaoPermissaoSistema;
	}

	@Override
	public String toString() {
		return "PermissaoSistema [idPermissaoSistema=" + idPermissaoSistema + ", nomePermissaoSistema="
				+ nomePermissaoSistema + ", statusPermissaoSistema=" + statusPermissaoSistema
				+ ", dataRegistoPermissaoSistema=" + dataRegistoPermissaoSistema + ", dataActualizacaoPermissaoSistema="
				+ dataActualizacaoPermissaoSistema + "]";
	}           
}
