package gestaofarmacia;

import java.time.LocalDateTime;

public class Language {
		
	public static boolean pt = true;	
	public static boolean en = false;
	
	public static boolean default_language = pt;
	
	private static byte menu() {
		System.out.println();
		System.out.println("******************************* Gestao de Farmacia ********************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Portugues                                                                     *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*2. Ingles                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. Sair                                                                          *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Selecione uma Idioma: ");
	}
	
	public static void load() {	
		int caso;
		do {
			caso = menu();
			switch (caso) {
			case 1:				
				default_language = pt; break;
			case 2:				
				default_language = en; break;
			case 3:	
				
				break;			
			default:
				break;
			}
		} while (caso == 3);		
	}

	 
	/**
	 * Propreties language menssage
	 * 
	 * pt 
	 * ----------------- Geral ----------------
	 **/
	public static final String pt_inicializacao_empty_array = "Nao ha nenhum tipo de identificacao no sistema, registe o primeiro";
	public static final String pt_inicializacao_editar_dado = "Indique o dado de que deseja editar: ";
	public static final String pt_inicializacao_sucesso = "Identificacao Atualizada com Sucesso!";
	public static final String pt_inicializacao_unsucesso = "Identificacao Não Atualizada com Sucesso!";
	
	/**
	 * pt
	 * -------------- Identificacao -----------
	 **/
	public static final String pt_identificacao_valida_id = "Codigo";
	public static final String pt_identificacao_valida_nome = "Nome";
	public static final String pt_identificacao_valida_acronimo = "Acronimo";    
	public static final String pt_identificacao_valida_estado = "Estado";
	public static final String pt_identificacao_valida_dataRegisto = "Data de Registo";
	public static final String pt_identificacao_valida_dataActualizacao = "Data de Actualizacao";
	public static final String pt_identificacao_valida_numero = "Informe o numero da identificacao: ";
	public static final String pt_identificacao_valida_numero_invalido = "Volte a informar o numero da identificacao valido: ";
	public static final String pt_identificacao_nome = "Informe o nome da identificacao: ";
	public static final String pt_identificacao_nome_existe = "O nome da identificacao ja existe, queira por favor informar um novo nome: ";
	public static final String pt_identificacao_acronimo = "Informe o acronimo da identificacao: ";
	public static final String pt_identificacao_acronimo_existe = "O acronimo da identificacao ja existe, queira por favor informar um novo acronimo: ";
	public static final String pt_identificacao_acronimo_invalido = "Volte a informar o acronimo da identificacao valido: ";
	public static final String pt_identificacao_novo_estado = "Informe o Novo Estado [Activo ou Inactivo]: ";
	public static final String pt_identificacao_salva = "Identificacao Gravada com sucesso!";
	public static final String pt_identificacao_actualizacao_menu = "Actualizar dados de Item Identificacao";
	public static final String pt_identificacao_actualizacao_cancelar = "Cancelar";
	
	
	/**
	 * en
	 * ------------------ General --------------
	 **/
	public static final String en_identification_empty_array = "There is no type of identification in the system, register the first";
	public static final String en_identification_edit_data = "Indicate the data you want to edit: ";
	public static final String en_identification_successs = "Identification successfully updated!";
	public static final String en_identification_unsuccesss = "Identification not updated successfully!";
	
	/**
	 * en
	 * -------------- Identification ------------
	 **/
	public static final String en_identification_valid_id = "ID";
	public static final String en_identification_valid_name = "Name";
	public static final String en_identification_valid_acronym = "acronym";    
	public static final String en_identification_valid_status = "Status";
	public static final String en_identification_valid_dateRegistration = "Registration Date";
	public static final String en_identification_valid_updateDate = "Update Date";
	public static final String en_identification_number = "Enter the identification number: ";
	public static final String en_identification_valid_number = "Re-enter the valid identification number: ";
	public static final String en_identification_name = "Enter the name of the identification: ";
	public static final String en_identification_name_existe = "The identification name already exists, please enter a new name: ";
	public static final String en_identification_acronym = "Enter the acronym of identification: ";
	public static final String en_identification_acronym_exist = "The acronym of the identification already exists, please inform a new acronym: ";
	public static final String en_identification_acronym_invalid = "Re-enter the acronym of the valid identification: ";
	public static final String en_identification_new_status = "Inform the New State [Active or Inactive]: ";
	public static final String en_identification_save = "Identification Recorded successfully!";
	public static final String en_identification_update_menu = "Update Item Identification data";
	public static final String en_identification_update_cancel = "Cancel";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
