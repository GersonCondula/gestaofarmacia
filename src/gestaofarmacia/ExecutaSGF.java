package gestaofarmacia;

import java.io.IOException;
import java.util.Vector;

public class ExecutaSGF {
	
	@SuppressWarnings({"unused", "rawtypes"})
	private static Vector vector = new Vector();
	
	private static void load() {
		Language.load();
		IdentificacaoMethods.load(vector);
		FuncionarioMethods.load(vector);
		PermissaoSistemaMethods.load(vector);
		UsuarioMethods.load(vector);
		CategoriaProdutoMethods.load(vector);
		FornecedorMethods.load(vector);
		ProdutoMethods.load(vector);
	}
	
	private static byte menuRH() {

		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_pharmacy_management());
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_employee());
		System.out.println("-----------------------------------------------------------------------------------");     	       
		System.out.println("2. "+Language.language_user());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("3. "+Language.language_identification_type());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("4. "+Language.language_systempermissions());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("5. "+Language.language_profile());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("6. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}

	private static void humanResources() {
		int caso;
		do {
			caso = menuRH();
			switch (caso) {
			case 1:
				FuncionarioMethods.inicializador(vector);
				break;
			case 2:
				UsuarioMethods.inicializador(vector);
				break;
			case 3:				
				IdentificacaoMethods.inicializador(vector);
				break;
			case 4:		
				PermissaoSistemaMethods.inicializador(vector);
				break;	
			case 5:				
				PerfilMethods.inicializador(vector);
				break;	
			case 6:								
				break;
			default:
				;
				break;
			}   		  
		} while (caso!=6);
	}

	private static byte menuStockManagement() {

		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_pharmacy_management());
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_product());
		System.out.println("-----------------------------------------------------------------------------------");     	       
		System.out.println("2. "+Language.language_product_category());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("3. "+Language.language_provider());
		System.out.println("-----------------------------------------------------------------------------------");		
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
	
	private static void stockManagement() {
		int caso;
		do {
			caso = menuStockManagement();
			switch (caso) {
			case 1:			
				ProdutoMethods.inicializador(vector);
				break;
			case 2:
				CategoriaProdutoMethods.inicializador(vector);
				break;
			case 3:		
				FornecedorMethods.inicializador(vector);
				break;
			case 4:						
				break;				
			default:
				;
				break;
			}   		  
		} while (caso!=4);
	}
	
	/**       
	 * @Descrição Menu da Farmacia
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_pharmacy_management());
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_human_resources());
		System.out.println("-----------------------------------------------------------------------------------");     	       
		System.out.println("2. "+Language.language_stock_management());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("3. "+Language.language_other());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}

	private static void inicializador() throws IOException {		
		load();
		Usuario usuario = Login.login(vector);
		if (usuario != null) {
			Login.setUsuario_id(usuario);
			int caso;
			do {
				caso = menu();
				switch (caso) {
				case 1:
					humanResources();
					break;
				case 2:				
					stockManagement();
					break;
				case 3:				
					break;
				case 4:					
					break;
				default:
					break;
				}
			} while (caso != 4);
		}
	}

	public static void main(String[]args) throws IOException {				
		inicializador();
	}
}
