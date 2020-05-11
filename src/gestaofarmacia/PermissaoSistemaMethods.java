package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class PermissaoSistemaMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static String filePath = Validacao.geraDirectorioFicheiro("permissoessistema.txt");

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	@SuppressWarnings("unused")
	private static String validaNome(String nome, PermissaoSistema[] permissaoSistemas) {
		String nomed = null;
		if (nome != null) { 
			for (int i = 0; i < permissaoSistemas.length; i++) {
				if (permissaoSistemas[i] != null) {
					if (!permissaoSistemas[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),permissaoSistemas);
						i = permissaoSistemas.length;
					}
				}else {
					nomed = nome;
				}
			}
		}
		return nomed;
	}

	/**
	 *
	 * @param nome
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static PermissaoSistema getByNome(String nome, PermissaoSistema [] permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;
		int count = 0;
		if (nome != null) {        	  
			for (int i = 0; i < permissaoSistemas.length; i++) {	          		
				if (permissaoSistemas[i] != null) {	                	  
					if (permissaoSistemas[i].getNome().equalsIgnoreCase(nome)) {	                    	  
						permissaoSistema = new PermissaoSistema(permissaoSistemas[i].getId(), 
								permissaoSistemas[i].getNome());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (permissaoSistema == null) {
			permissaoSistema = getByNome(Validacao.validaEntradaPalavra(Language.language_input_valid_name()), permissaoSistemas);
			if (count < 3) {

			}
		}
		return permissaoSistema;
	}

	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static PermissaoSistema getById(int id, PermissaoSistema [] permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;
		int count = 0;
		if (id!=0) {        	  
			for (int i = 0; i < permissaoSistemas.length; i++) {	          		
				if (permissaoSistemas[i] != null) {	                	  
					if (permissaoSistemas[i].getId() == id) {	                    	  
						permissaoSistema = new PermissaoSistema(permissaoSistemas[i].getId(), 
								permissaoSistemas[i].getNome());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (permissaoSistema == null) {
			permissaoSistema = getByNome(Validacao.validaEntradaPalavra(Language.language_input_valid_name()), permissaoSistemas);
			if (count < 3) {

			}
		}
		return permissaoSistema;
	}
	
	private static boolean gravarDadosNoFicheiro(PermissaoSistema [] permissaoSistemas, String file) {
		boolean error = false;	
		try {		
			br = new BufferedReader(new FileReader(new File(file)));
			String linha = br.readLine();
			if (new File(file).exists() || linha == null) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < permissaoSistemas.length; i++) {
					if (permissaoSistemas[i] != null) {
						bw.write(permissaoSistemas[i].getId() + "|" + permissaoSistemas[i].getNome());
						bw.newLine();						
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(permissaoSistemas,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}
	
	public static boolean lerDadosNoFicheiro(PermissaoSistema[] permissaoSistemas, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				if (linha!=null) {
					while (linha != null) {
						str = new StringTokenizer(linha, "|");
						PermissaoSistema identificacao = new PermissaoSistema(Integer.parseInt(str.nextToken()),
								str.nextToken());
						permissaoSistemas[i] = identificacao;
						linha = br.readLine();
						i++;
					} 
				}else {
					PermissaoSistema permissaoSistemas1 = new PermissaoSistema(2222,"Administracao");
					PermissaoSistema permissaoSistemas2 = new PermissaoSistema(3333,"Gestor");
					PermissaoSistema permissaoSistemas3 = new PermissaoSistema(4444,"Farmaceutico");
					permissaoSistemas[0]= permissaoSistemas1;
					permissaoSistemas[1]= permissaoSistemas2;
					permissaoSistemas[2]= permissaoSistemas3;
					bw = new BufferedWriter(new FileWriter(new File(filePath)));				
					for (int ii = 0; ii < 3; ii++) {
						if (permissaoSistemas[ii] != null) {
							bw.write(permissaoSistemas[ii].getId() + "|" + permissaoSistemas[ii].getNome());
							bw.newLine();						
						}
					}
					bw.close();
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);				
				lerDadosNoFicheiro(permissaoSistemas,file);				
			}
		} catch (IOException e) {
			System.err.println("error" + e.getLocalizedMessage());		
		}		
		return error;
	}

	/**
	 *
	 * @return
	 * @Descrição imprime o formato de cabeçalho e retorna o formato para impressao dos dados
	 */
	private static String formatoImpressao(){
		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_name()};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-58.58s";			
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_permissions()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return formatColl;
	}

	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(PermissaoSistema [] permissaoSistemas){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < permissaoSistemas.length; i++){
			if (permissaoSistemas[i] != null){
				dadosImpressao(numeracao, i, permissaoSistemas, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(permissaoSistemas.length,empty_);
	}

	private static void dadosImpressao(int numeracao, int i, PermissaoSistema [] identificacaos, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,identificacaos[i].getId(),Validacao.delimitador,
				identificacaos[i].getNome(),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@SuppressWarnings("unused")
	private static void listaIdentificacao(PermissaoSistema [] permissaoSistemas, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < permissaoSistemas.length; i++) {
			if (permissaoSistemas[i] != null && permissaoSistemas[i].getId() == id) {					
				dadosImpressao(numeracao, i, permissaoSistemas, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(permissaoSistemas.length, empty_);		
	}         

	public static void load(PermissaoSistema [] permissaoSistemas) {
		Validacao.init(permissaoSistemas);			
		lerDadosNoFicheiro(permissaoSistemas, filePath);
	}

	public static void inicializador(PermissaoSistema [] permissaoSistemas) {			
		load(permissaoSistemas);
		int caso;
		do {
			caso = Validacao.menuPermissions(Language.language_permissions());
			switch (caso) {
			case 1:							
				lista(permissaoSistemas);
				;break;			
			case 2:
				;break;
			default:

				break;
			}
		} while (caso != 2);
	}


}
