package gestaofarmacia;

public class Language {
	private static boolean pt = true;	
	private static boolean en = false;	
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
		return Validacao.validaEntradaByte("Selecione uma Idioma / Select Language: ");
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
	 * ----------------- Propreties language menssage -----------------
	 **/
	
	/** 
	 * ------------------------  Geral--------------------------------- 
	 **/
	public static final String language_pharmacy_management() {
		return default_language ? "Gestao de Farmacia" : "Pharmacy Management";
	}
	public static final String language_human_resources() {
		return default_language ? "Recursos Humanos" : "Human Resources";
	}
	public static final String language_stock_management() {
		return default_language ? "Gestao de estoque" : "Stock Management";
	}
	public static final String language_sales_management() {
		return default_language ? "Gestao de Vednas" : "Sales Management";
	}
	public static final String language_account_statics_management() {
		return default_language ? "Gestao de Contas e Estatisticas" : "Account Management and Statistics";
	}
	public static final String language_active() {
		return default_language ? "Activo" : "Active";
	}
	public static final String language_inactive() {
		return default_language ? "Inactivo" : "inactive";
	}
	public static final String language_id() {
		return default_language ? "Codigo" : "ID";
	}	
	public static final String language_password() {
		return default_language ? "Senha" : "Password";
	}
	public static final String language_input_password() {
		return default_language ? "Informe a senha: " :
								  "Enter the password: ";
	}
	public static final String language_name() {
		return default_language ? "Nome" : "Name";
	}
	public static final String language_acronym() {
		return default_language ? "Acronimo" : "Acronym";
	}
	public static final String language_description() {
		return default_language ? "Descricao" : "Description";
	}
	public static final String language_brand() {
		return default_language ? "Marca" : "Brand";
	}
	public static final String language_price() {
		return default_language ? "Preco" : "Price";
	}
	public static final String language_amount() {
		return default_language ? "Quantidade" : "Amount";
	}
	public static final String language_person_type() {
		return default_language ? "Tipo Pessoa" : "Person type";
	}
	public static final String language_state() {
		return default_language ? "Status" : "State";
	}
	public static final String language_register() {
		return default_language ? "Registar" : "Register";
	}
	public static final String language_update() {
		return default_language ? "Actualizar" : "Update";
	}
	public static final String language_delete() {
		return default_language ? "apagar" : "Delete";
	}
	public static final String language_listing() {
		return default_language ? "listar" : "listing";
	}
	public static final String language_cancel() {
		return default_language ? "Cancelar" :"Cancel";
	}
	public static final String language_logout() {
		return default_language ? "Sair" :"Logout";
	}
	public static final String language_expirationDate() {
		return default_language ? "Data de validade" : "Expiration Date";
	}
	public static final String language_input_expirationDate() {
		return default_language ? "Informe a data de validade [aaaa-MM-dd]: " :
								  "Enter the expiration date [yyyy-MM-dd]: ";
	}
	public static final String language_input_valid_expirationDate() {
		return default_language ? "Informe a data no formato [aaaa-MM-dd]: " :
								  "Enter the date on format [yyyy-MM-dd]: ";
	}
	public static final String language_dateRegistration() {
		return default_language ? "Data Reg" : "Reg Data";
	}
	public static final String language_updateDate() {
		return default_language ? "Data Actua" : "Upda Date";
	}
	public static final String language_input_id() {
		return default_language ? "Informe o Codigo: " :
								  "Enter the ID: ";
	}
	public static final String language_input_valid_id() {
		return default_language ? "Volte a informar o Codigo valido: " :
								  "Re-enter the valid ID: ";
	}
	public static final String language_input_name() {
		return default_language ? "Informe o nome: " :
								  "Enter the name: ";
	}
	public static final String language_input_valid_name() {
		return default_language ? "Informe um nome valido: " :
								  "Enter the valid name: ";
	}
	public static final String language_input_exist_name() {
		return default_language ? "O nome ja existe, queira por favor informar um novo nome: " :
								  "The name already exists, please enter a new name: ";
	}
	public static final String language_number() {
		return default_language ? "Numero" : "Number";
	}
	public static final String language_input_number() {
		return default_language ? "Informe o numero: " :
								  "Enter the number: ";
	}
	public static final String language_input_invalid_number() {
		return default_language ? "Volte a Informe um numero valido: " :
								  "Re-enter the valid number: ";
	}
	public static final String language_input_exist_number() {
		return default_language ? "O numero ja existe, queira por favor informar um novo numero: " :
								  "The number already exists, please enter a new number: ";
	}
	public static final String language_nuit() {
		return default_language ? "Nuit" : "Nuit";
	}
	public static final String language_input_nuit() {
		return default_language ? "Informe o nuit: " :
								  "Enter the nuit: ";
	}
	public static final String language_input_valid_nuit() {
		return default_language ? "Informe um nuit valido: " :
								  "Enter the valid nuit: ";
	}
	public static final String language_input_exist_nuit() {
		return default_language ? "O nuit ja existe, queira por favor informar um novo nuit: " :
								  "The nuit already exists, please enter a new nuit: ";
	}
	public static final String language_address() {
		return default_language ? "Morada" : "address";
	}
	public static final String language_input_address() {
		return default_language ? "Informe a morada: " :
								  "Enter the address: ";
	}	
	public static final String language_input_acronym() {
		return default_language ? "Informe o acronimo: " :
								  "Enter the acronym: ";
	}
	public static final String language_input_exist_acronym() {
		return default_language ? "O acronimo ja existe, queira por favor informar um novo acronimo: " :
								  "The acronym already exists, please inform a new acronym: ";
	}
	public static final String language_invalid_acronym() {
		return default_language ? "Volte a informar o acronimo valido: " :
								  "Re-enter the acronym: ";
	}
	public static final String language_input_state() {
		return default_language ? "Informe o Novo Status [Activo ou Inactivo]: " :
								  "Inform the New State [Active or Inactive]: ";
	}
	public static final String language_input_valid_state() {
		return default_language ? "Informe um status valido [Activo ou Inactivo]: " :
								  "Inform the valid State [Active or Inactive]: ";
	}
	public static final String language_input_description() {
		return default_language ? "Informe a descricao: " :
								  "Enter the description: ";
	}
	public static final String language_input_price() {
		return default_language ? "Informe o preco: " :
								  "Enter the price: ";
	}
	public static final String language_input_brand() {
		return default_language ? "Informe o nome marca: " :
								  "Enter the brand name: ";
	}
	public static final String language_input_amount() {
		return default_language ? "Informe a quantidade: " :
								  "Enter the amount: ";
	}
	public static final String language_update_menu() {
		return default_language ? "Actualizar dados " :
								  "Update data";
	}
	public static final String language_menu(String obj) {
		return default_language ? "Gestao de " +obj :
								  obj+ " Management";
	}
	public static final String language_delete_successs() {
		return default_language ? "Removido com Sucesso!" :
								  "Removed Successfully!";
	}
	public static final String language_delete_unsuccesss() {
		return default_language ? "Não Removido com Sucesso!" :
								  "Not Successfully Removed!";
	}
	public static final String language_edit_data() {
		return default_language ? "Indique o dado de que deseja editar: " :
								  "Indicate the data you want to edit: ";
	}
	public static final String language_save_successs() {
		return default_language ? "Atualizado com Sucesso!" :
								  "successfully updated!";
	}
	public static final String language_save_unsuccesss() {
		return default_language ? "Não Atualizada com Sucesso!" :
								  "Successfully updated!";
	}
	public static final String language_invalid_value() {
		return default_language ? "Valor Invalido!" : "Invalid Value";
	}
	public static final String language_valid_value() {
		return default_language ? "Informe valor valido: " : "Inform valid value: ";
	}
	public static final String language_time_out() {
		return default_language ? "Esgotaram-se as tentativas, revise bem os dados e tente de novo..." 
				: "Attempts have run out, review the data well and try again ...";
	}
	public static final String language_empty_regist() {
		return default_language ? "Nao ha dados registados" 
				: "No data recorded";
	}
	public static final String language_select_option() {
		return default_language ? "Selecione uma opcao: " 
				: "Select one option: ";
	}
	public static final String language_empty_array(String obj) {
		return default_language ? "Nao ha nenhum tipo de "+obj+" no sistema, registe o primeiro" :
								  "There is no type of "+obj+" in the system, register the first";
	}
	public static final String language_list(String obj) {
		return default_language ? "Lista de " + obj : "List " + obj;
	}
	public static final String language_login() {
		return default_language ? "Autenticacao" : "Sign in";
	}	
	public static final String language_invalid_value_login() {
		return default_language ? "Valor Invalido, deseja tentar mais uma vez? \nsim (s) nao (n)" : "Invalid Value, need to retry? \nyes (y) no (n)";
	}
	public static final char language_option_yes_login() {
		return default_language ? (char)'s' : (char)'y';
	}	
	/** 
	 * ------------------------ Identificacao -------------------------	 	 
	 **/
	public static final String language_identification() {
		return default_language ? "Identificacao" : "identification";
	}
	public static final String language_identification_type() {
		return default_language ? "Tipos de Identidade" : "Types of Identity";
	}		
	/** 
	 * -------------------- Permissoes de sistema ---------------------	 	 
	 **/
	public static final String language_systempermissions() {
		return default_language ? "Permissoes de sistema" : "System permissions";
	}
	public static final String language_permissions() {
		return default_language ? "Permissoes" : "permissions";
	}
	/** 
	 * ---------------------------- Funcionario -----------------------	 	 
	 **/
	public static final String language_employee() {
		return default_language ? "Funcionario" : "Employee";
	}
	/** 
	 * ----------------------------- Usuario --------------------------	 	 
	 **/
	public static final String language_user() {
		return default_language ? "Usuario" : "User";
	}
	/** 
	 * ----------------------------- Perfil --------------------------	 	 
	 **/
	public static final String language_profile() {
		return default_language ? "Perfil" : "Profile";
	}
	/** 
	 * ----------------------------- Categoria Produto --------------------------	 	 
	 **/
	public static final String language_product_category() {
		return default_language ? "Categoria Produto" : "Product Category";
	}
	/** 
	 * ----------------------------- Fornecedor ---------------------------------	 	 
	 **/
	public static final String language_provider() {
		return default_language ? "Fornecedor" : "Provider";
	}
	/** 
	 * ----------------------------- Produto ------------------------------------ 	 
	 **/
	public static final String language_product() {
		return default_language ? "Produto" : "Product";
	}
	/** 
	 * ----------------------------- Vendas ------------------------------------ 	 
	 **/
	public static final String language_sales() {
		return default_language ? "Vendas" : "Sales";
	}
	public static final String language_sales_devolution() {
		return default_language ? "Devolucoes" : "Devolutions";
	}
	/** 
	 * ----------------------------- Contas e estatisticas ------------------------------------ 	 
	 **/
	public static final String language_account() {
		return default_language ? "Contas" : "Account";
	}
	public static final String language_account_statics() {
		return default_language ? "Contas" : "Account";
	}
	
}