package gestaofarmacia;

import java.io.IOException;
import java.util.Vector;

public class ExecutaSGF {

	private static Identificacao [] identificacaos = new Identificacao[Validacao.getTamanho()];
	private static Funcionario [] funcionarios = new Funcionario[Validacao.getTamanho()];
	private static PermissaoSistema [] permissaoSistemas = new PermissaoSistema[Validacao.getTamanho()];
	private static Usuario [] usuarios = new Usuario[Validacao.getTamanho()]; 
	private static Perfil [] perfils = new Perfil[Validacao.getTamanho()]; 
	private static CategoriaProduto [] categoriaProdutos = new CategoriaProduto[Validacao.getTamanho()];
	private static Fornecedor[] fornecedors =  new Fornecedor[Validacao.getTamanho()];
	private static Produto [] produtos = new Produto[Validacao.getTamanho()];
	
	@SuppressWarnings({"unused", "rawtypes"})
	private static Vector vetor = new Vector();
	
	private static void load() {
		Language.load();
		IdentificacaoMethods.load(identificacaos);
		FuncionarioMethods.load(funcionarios, identificacaos);
		PermissaoSistemaMethods.load(permissaoSistemas);
		UsuarioMethods.load(usuarios, funcionarios);
		CategoriaProdutoMethods.load(categoriaProdutos);
		FornecedorMethods.load(fornecedors);
		ProdutoMethods.load(produtos, fornecedors, categoriaProdutos);
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
				FuncionarioMethods.inicializador(funcionarios, identificacaos);
				break;
			case 2:
				UsuarioMethods.inicializador(usuarios, funcionarios, identificacaos);
				break;
			case 3:				
				IdentificacaoMethods.inicializador(identificacaos);
				break;
			case 4:		
				PermissaoSistemaMethods.inicializador(permissaoSistemas);
				break;	
			case 5:				
				PerfilMethods.inicializador(perfils, permissaoSistemas, usuarios);
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
				ProdutoMethods.inicializador(produtos, fornecedors, categoriaProdutos);
				break;
			case 2:
				CategoriaProdutoMethods.inicializador(categoriaProdutos);
				break;
			case 3:		
				FornecedorMethods.inicializador(fornecedors);
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
		Usuario usuario = Login.login(usuarios);
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
