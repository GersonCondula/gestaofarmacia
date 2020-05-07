package gestaofarmacia;

import java.time.LocalDateTime;

public class LogSistema {
	
	private int  idLogSistema;
    private int  idUsuarioLogSistema;    
    private String  tipoLogSistema;
    private String  eventoLogSistema;        
    private LocalDateTime  dataRegistoLogSistema;
    
	public LogSistema(int idLogSistema, int idUsuarioLogSistema, String tipoLogSistema, String eventoLogSistema,
			LocalDateTime dataRegistoLogSistema) {	
		this.idLogSistema = idLogSistema;
		this.idUsuarioLogSistema = idUsuarioLogSistema;
		this.tipoLogSistema = tipoLogSistema;
		this.eventoLogSistema = eventoLogSistema;
		this.dataRegistoLogSistema = dataRegistoLogSistema;
	}

	public int getIdLogSistema() {
		return idLogSistema;
	}

	public int getIdUsuarioLogSistema() {
		return idUsuarioLogSistema;
	}

	public String getTipoLogSistema() {
		return tipoLogSistema;
	}

	public String getEventoLogSistema() {
		return eventoLogSistema;
	}

	@Override
	public String toString() {
		return "LogSistema [idLogSistema=" + idLogSistema + ", idUsuarioLogSistema=" + idUsuarioLogSistema
				+ ", tipoLogSistema=" + tipoLogSistema + ", eventoLogSistema=" + eventoLogSistema
				+ ", dataRegistoLogSistema=" + dataRegistoLogSistema + "]";
	}     
}
