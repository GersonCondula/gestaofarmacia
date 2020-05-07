package gestaofarmacia;

public class ExecutaSGF {

	private static Identificacao [] identificacaos = new Identificacao[Validacao.getTamanho()];
	private static Funcionario [] funcionarios = new Funcionario[Validacao.getTamanho()];
	private static PermissaoSistema [] permissaoSistemas = new PermissaoSistema[Validacao.getTamanho()];

	private static void load() {
		IdentificacaoMethods.load(identificacaos);
		FuncionarioMethods.load(funcionarios, identificacaos);
		PermissaoSistemaMethods.load(permissaoSistemas);
	}

	private static byte menuOutros() {

		System.out.println();
		System.out.println("******************************* Gestao de Farmacia ********************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Tipos de Identidade                                                           *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*2. Permissoes de sistema                                                         *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Selecione uma opcao:");
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
				;
				break;	
			default:
				;
				break;
			}   		  
		} while (caso!=3);
	}

	/**       
	 * @Descrição Menu da Farmacia
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("******************************* Gestao de Farmacia ********************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Funcionarios                                                                  *");
		System.out.println("*---------------------------------------------------------------------------------*");     	       
		System.out.println("*2. Outros                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Selecione uma opcao:");
	}

	private static void inicializador() {
		load();
		int caso;	
		do {
			caso = menu();
			switch (caso) {
			case 1:
				FuncionarioMethods.inicializador(funcionarios, identificacaos);				
				break;
			case 2:
				outros();				
				break;
			case 3:
				;
				break;
			default:
				;
				break;
			}   		  
		} while (caso!=3);
	}

	public static void main(String[]args) {				
		inicializador();
	}
}
