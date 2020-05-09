package gestaofarmacia;

public class ExecutaSGF {

	private static Identificacao [] identificacaos = new Identificacao[Validacao.getTamanho()];
	private static Funcionario [] funcionarios = new Funcionario[Validacao.getTamanho()];
	private static PermissaoSistema [] permissaoSistemas = new PermissaoSistema[Validacao.getTamanho()];
	private static Usuario [] usuarios = new Usuario[Validacao.getTamanho()]; 
	private static Perfil [] perfils = new Perfil[Validacao.getTamanho()]; 

	private static void load() {	
		IdentificacaoMethods.load(identificacaos);
		FuncionarioMethods.load(funcionarios, identificacaos);
		PermissaoSistemaMethods.load(permissaoSistemas);
		UsuarioMethods.load(usuarios, funcionarios);
	}

	private static byte menuOutros() {

		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_pharmacy_management());
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_identification_type());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("2. "+Language.language_systempermissions());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("3. "+Language.language_profile());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}

	private static void outros() {
		int caso;
		do {
			caso = menuOutros();
			switch (caso) {
			case 1:				
				IdentificacaoMethods.inicializador(identificacaos);
				break;
			case 2:		
				PermissaoSistemaMethods.inicializador(permissaoSistemas);
				break;	
			case 3:				
				PerfilMethods.inicializador(perfils, permissaoSistemas, usuarios);
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
		System.out.println("1. "+Language.language_employee());
		System.out.println("-----------------------------------------------------------------------------------");     	       
		System.out.println("2. "+Language.language_user());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("3. "+Language.language_other());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}

	private static void inicializador() {
		Language.load();
		load();
		int caso;	
		do {
			caso = menu();
			switch (caso) {
			case 1:
				FuncionarioMethods.inicializador(funcionarios, identificacaos);				
				break;
			case 2:		
				UsuarioMethods.inicializador(usuarios, funcionarios, identificacaos);		
				break;
			case 3:
				outros();
				break;
			case 4:
				;
				break;
			default:
				;
				break;
			}   		  
		} while (caso!=4);
	}

	public static void main(String[]args) {				
		inicializador();
	}
}
