package gestaofarmacia;

import java.io.IOException;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class ExecutaSGF {
	
	public static Usuario userLogado;
	
	private static Vector identificacaoVector = new Vector();
	private static Vector funcionarioVector = new Vector();
	private static Vector fornecedorVector = new Vector();
	private static Vector permissaoSistemaVector = new Vector();
	private static Vector usuarioVector = new Vector();
	private static Vector categoriaProdutoVector = new Vector();
	private static Vector produtoVector = new Vector();
	private static Vector perfilVector = new Vector();
	private static Vector vendaVector = new Vector();
	private static Vector itemVendaVector = new Vector();	
	
	
	private static void load() {
		IdentificacaoMethods.load(identificacaoVector);
		FuncionarioMethods.load(funcionarioVector, identificacaoVector);						
		FornecedorMethods.load(fornecedorVector);
		CategoriaProdutoMethods.load(categoriaProdutoVector);		
		ProdutoMethods.load(produtoVector, fornecedorVector, categoriaProdutoVector);
		PermissaoSistemaMethods.load(permissaoSistemaVector);
		PerfilMethods.load(perfilVector);
		UsuarioMethods.load(usuarioVector, funcionarioVector);		
		VendaMethods.load(vendaVector, itemVendaVector, produtoVector, usuarioVector);
		
		Language.load();		
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
		System.out.println("6. "+Language.language_logout());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}

	private static void humanResources() {
		int caso;
		do {
			caso = menuRH();
			switch (caso) {
			case 1:
				FuncionarioMethods.inicializador(funcionarioVector, identificacaoVector);
				break;
			case 2:
				UsuarioMethods.inicializador(usuarioVector, funcionarioVector,identificacaoVector);
				break;
			case 3:				
				IdentificacaoMethods.inicializador(identificacaoVector);
				break;
			case 4:		
				PermissaoSistemaMethods.inicializador(permissaoSistemaVector);
				break;	
			case 5:				
				PerfilMethods.inicializador(perfilVector);
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
		System.out.println("4. "+Language.language_logout());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
	
	private static void stockManagement() {
		int caso;
		do {
			caso = menuStockManagement();
			switch (caso) {
			case 1:			
				ProdutoMethods.inicializador(produtoVector,fornecedorVector, categoriaProdutoVector);
				break;
			case 2:
				CategoriaProdutoMethods.inicializador(categoriaProdutoVector);
				break;
			case 3:		
				FornecedorMethods.inicializador(fornecedorVector);
				break;
			case 4:						
				break;				
			default:
				;
				break;
			}   		  
		} while (caso!=4);
	}
	
	private static byte menu_sales(){
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_sales_management());
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_sales());		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("2. "+Language.language_logout());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
	
	private static void sales() {
		int caso;
		do {
			caso = menu_sales();
			switch(caso) {
			case 1:
				VendaMethods.inicializador(vendaVector, itemVendaVector, produtoVector, usuarioVector);
				;break;
			case 2:				
				;break;			
			default:				
				;break;
			}
		}while(caso!=3);
	}
	
	private static byte menu_account_statics(){
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_account_statics_management());
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_account());
		System.out.println("-----------------------------------------------------------------------------------");     	       
		System.out.println("2. "+Language.language_account_statics());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("3. "+Language.language_logout());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
	
	private static void account_statics() {
		int caso;
		do {
			caso = menu_account_statics();			
			switch(caso) {
			case 1:;break;
			case 2:;break;
			case 3:;break;
			default:;break;
			}			
		}while(caso!=3);
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
		System.out.println("3. "+Language.language_sales());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("4. "+Language.language_logout());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
	
	private static void inicializador() throws IOException {		
		load();
		Usuario usuario = Login.login(usuarioVector);
		userLogado = usuario;
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
					sales();
					break;
				case 4:		
					account_statics();
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
