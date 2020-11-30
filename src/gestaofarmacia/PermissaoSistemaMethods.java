package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class PermissaoSistemaMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static String filePath = Validacao.geraDirectorioFicheiro("permissoessistema.txt");

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	@SuppressWarnings("unused")
	private static String validaNome(String nome, Vector permissaoSistemas) {
		String nomed = null;
		if (nome != null) { 
			for (int i = 0; i < permissaoSistemas.size(); i++) {
				PermissaoSistema permissaoSistema = (PermissaoSistema)permissaoSistemas.elementAt(i);
				if (!permissaoSistemas.isEmpty()) {
					if (!permissaoSistema.getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),permissaoSistemas);
						i = permissaoSistemas.size();
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
	public static PermissaoSistema getByNome(String nome, Vector permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;		
		if (nome != null) {        	  
			for (int i = 0; i < permissaoSistemas.size(); i++) {	 
				PermissaoSistema permissaoSistema2 = (PermissaoSistema)permissaoSistemas.elementAt(i);
				if (!permissaoSistemas.isEmpty()) {	                	  
					if (permissaoSistema2.getNome().equalsIgnoreCase(nome)) {	                    	  
						permissaoSistema = permissaoSistema2;
					}
				}	                  
			} 	          		
		}       
		if (permissaoSistema == null) {
			permissaoSistema = getByNome(Validacao.validaEntradaPalavra(Language.language_input_valid_name()), permissaoSistemas);			
		}
		return permissaoSistema;
	}

	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static PermissaoSistema getById(int id, Vector permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;
		if (id!=0) {        	  
			for (int i = 0; i < permissaoSistemas.size(); i++) {	
				PermissaoSistema permissaoSistema2 = (PermissaoSistema)permissaoSistemas.elementAt(i);
				if (!permissaoSistemas.isEmpty()) {	                	  
					if (permissaoSistema.getId() == id) {	                    	  
						permissaoSistema = permissaoSistema2;						
					}
				}	                  
			} 	          		
		}       
		if (permissaoSistema == null) {
			permissaoSistema = getByNome(Validacao.validaEntradaPalavra(Language.language_input_valid_name()), permissaoSistemas);
		}
		return permissaoSistema;
	}
	@SuppressWarnings("unused")
	private static boolean gravarDadosNoFicheiro(Vector permissaoSistemas, String file) {
		boolean error = false;	
		try {		
			br = new BufferedReader(new FileReader(new File(file)));
			String linha = br.readLine();
			if (new File(file).exists() || linha == null) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < permissaoSistemas.size(); i++) {
					PermissaoSistema permissaoSistema = (PermissaoSistema)permissaoSistemas.elementAt(i);
					if (!permissaoSistemas.isEmpty()) {
						bw.write(permissaoSistema.getId() + "|" + permissaoSistema.getNome());
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
	
	public static boolean lerDadosNoFicheiro(Vector permissaoSistemas, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				if (linha!=null) {
					while (linha != null) {
						str = new StringTokenizer(linha, "|");
						PermissaoSistema permissaoSistema = new PermissaoSistema(Integer.parseInt(str.nextToken()), str.nextToken());
						Validacao.adicionar(permissaoSistemas, permissaoSistema);					
						linha = br.readLine();						
					} 
				}else {
					PermissaoSistema permissaoSistema1 = new PermissaoSistema(2222,"Administracao");
					PermissaoSistema permissaoSistema2 = new PermissaoSistema(3333,"Gestor");
					PermissaoSistema permissaoSistema3 = new PermissaoSistema(4444,"Farmaceutico");
					Validacao.adicionar(permissaoSistemas, permissaoSistema1);
					Validacao.adicionar(permissaoSistemas, permissaoSistema2);
					Validacao.adicionar(permissaoSistemas, permissaoSistema3);

					bw = new BufferedWriter(new FileWriter(new File(filePath)));				
					for (int i = 0; i < 3; i++) {
						PermissaoSistema permissaoSistema = (PermissaoSistema)permissaoSistemas.elementAt(i);
						if (!permissaoSistemas.isEmpty()) {
							bw.write(permissaoSistema.getId() + "|" + permissaoSistema.getNome());
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
	public static void lista(Vector permissaoSistemas){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < permissaoSistemas.size(); i++){			
			if (!permissaoSistemas.isEmpty()){
				dadosImpressao(numeracao, i, (PermissaoSistema)permissaoSistemas.elementAt(i), layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(permissaoSistemas.size(),empty_);
	}

	private static void dadosImpressao(int numeracao, int i, PermissaoSistema identificacao, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,identificacao.getId(),Validacao.delimitador,
				identificacao.getNome(),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@SuppressWarnings("unused")
	private static void listaIdentificacao(Vector permissaoSistemas, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < permissaoSistemas.size(); i++) {
			PermissaoSistema permissaoSistema = (PermissaoSistema)permissaoSistemas.elementAt(i);
			if (!permissaoSistemas.isEmpty() && permissaoSistema.getId() == id) {					
				dadosImpressao(numeracao, i, permissaoSistema, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(permissaoSistemas.size(), empty_);		
	}         

	public static void load(Vector permissaoSistemas) {				
		lerDadosNoFicheiro(permissaoSistemas, filePath);
	}

	public static void inicializador(Vector permissaoSistemas) {				
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
