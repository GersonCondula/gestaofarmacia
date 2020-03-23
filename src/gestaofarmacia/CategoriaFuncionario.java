package gestaofarmacia;

import java.time.LocalDateTime;

public class CategoriaFuncionario {

	private int  idCategoriaFuncionario;
    private String  nomeCategoriraFuncionario; 
    private boolean  statusCategoriraFuncionario; 
    private LocalDateTime  dataRegistoCategoriraFuncionario; 
    private LocalDateTime  dataActualizacaoCategoriraFuncionario;
    
	public CategoriaFuncionario(int idCategoriaFuncionario, String nomeCategoriraFuncionario,
			boolean statusCategoriraFuncionario, LocalDateTime dataRegistoCategoriraFuncionario,
			LocalDateTime dataActualizacaoCategoriraFuncionario) {	
		this.idCategoriaFuncionario = idCategoriaFuncionario;
		this.nomeCategoriraFuncionario = nomeCategoriraFuncionario;
		this.statusCategoriraFuncionario = statusCategoriraFuncionario;
		this.dataRegistoCategoriraFuncionario = dataRegistoCategoriraFuncionario;
		this.dataActualizacaoCategoriraFuncionario = dataActualizacaoCategoriraFuncionario;
	}

	public int getIdCategoriaFuncionario() {
		return idCategoriaFuncionario;
	}
	
	public String getNomeCategoriraFuncionario() {
		return nomeCategoriraFuncionario;
	}

	public void setNomeCategoriraFuncionario(String nomeCategoriraFuncionario) {
		this.nomeCategoriraFuncionario = nomeCategoriraFuncionario;
	}

	public boolean isStatusCategoriraFuncionario() {
		return statusCategoriraFuncionario;
	}

	public void setStatusCategoriraFuncionario(boolean statusCategoriraFuncionario) {
		this.statusCategoriraFuncionario = statusCategoriraFuncionario;
	}	

	public void setDataActualizacaoCategoriraFuncionario(LocalDateTime dataActualizacaoCategoriraFuncionario) {
		this.dataActualizacaoCategoriraFuncionario = dataActualizacaoCategoriraFuncionario;
	}

	@Override
	public String toString() {
		return "CategoriaFuncionario [idCategoriaFuncionario=" + idCategoriaFuncionario + ", nomeCategoriraFuncionario="
				+ nomeCategoriraFuncionario + ", statusCategoriraFuncionario=" + statusCategoriraFuncionario
				+ ", dataRegistoCategoriraFuncionario=" + dataRegistoCategoriraFuncionario
				+ ", dataActualizacaoCategoriraFuncionario=" + dataActualizacaoCategoriraFuncionario + "]";
	}      

}
