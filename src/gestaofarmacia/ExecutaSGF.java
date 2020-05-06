package gestaofarmacia;

public class ExecutaSGF {

	private static void gestaoFuncionarios() {
		Identificacao [] identificacaos = new Identificacao[Validacao.getTamanho()];			
		IdentificacaoMethods.inicializador(identificacaos);
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
		System.out.println("*2. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	private static void inicializador() {	 
		int caso;
		do {
			caso = menu();
			switch (caso) {
			case 1:
				gestaoFuncionarios();
				;
				break;
			case 2:
				;
				break;
			default:
				;
				break;
			}   		  
		} while (caso!=2);
	}

	public static void main(String[]args) {				
		inicializador();
	}
}
