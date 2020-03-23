package gestaofarmacia;

public class ExecutaGSF {
	public static void main(String[]args) {				
		  Identificacao [] identificacaos = new Identificacao[Validacao.getTamanho()];			
		  IdentificacaoMethods.init(identificacaos);
		  IdentificacaoMethods.actualizarIdentificacao(identificacaos);			 
	}
}
