package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import java.util.Vector;

@SuppressWarnings("rawtypes") //esta linha é devido a forma como usamos os vetores nesta classe
public class IdentificacaoMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("identificacao.txt");
	

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/	
	private static int geradorID(Vector identificacoes) {		
		int id = 0, iD = 0;
		if (identificacoes.isEmpty()) {
			
		}else {
			int novoId = (1 + Validacao.random.nextInt(identificacoes.size())+1);
			id = (2 + novoId);
			for (int i = 0; i < identificacoes.size(); i++) {
				Identificacao identificacao = (Identificacao)identificacoes.elementAt(i);
				if (!identificacoes.isEmpty()) {
					if (identificacao.getId() == id || id == 0)
						geradorID(identificacoes);
					else
						iD = id;
				}
			}	
		}
		return iD;
	}

   /**	 
	* @Descrição Garante que os nomes das identificacoes sejam unicos
	*/	
	private static String validaAcronimo(String acronimo, Vector identificacoes) {
		String acronimod = null;
		if (acronimo != null) {
			for (int i = 0; i < identificacoes.size(); i++) {				
				Identificacao identificacao = (Identificacao)identificacoes.elementAt(i);
				if (!identificacoes.isEmpty()) {
					if (!identificacao.getAcronimo().equalsIgnoreCase(acronimo)) {
						acronimod = acronimo;
					}else {
						acronimod = validaAcronimo(Validacao.validaEntradaPalavra(Language.language_input_exist_acronym()),identificacoes);						
					}
				}else {
					acronimod = acronimo;
				}
			}
		}
		return acronimod;
	}

	/**	 
	 * @Descrição Garante que os nomes das identificacoes sejam unicos
	 */
	private static String validaNome(String nome, Vector identificacoes) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < identificacoes.size(); i++) {
				Identificacao identificacao = (Identificacao)identificacoes.elementAt(i);
				if (!identificacoes.isEmpty()) {
					if (!identificacao.getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()), identificacoes);
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
	 * @param acronimo
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Identificacao getByAcronimo(String acronimo, Vector identificacoes) {
		Identificacao identificacao = null;	
		if (acronimo != null) {        	  
			for (int i = 0; i < identificacoes.size(); i++) {	
				Identificacao identificacaoVetor = (Identificacao)identificacoes.elementAt(i);
				if (!identificacoes.isEmpty()) {	                	  
					if (identificacaoVetor.getAcronimo().equalsIgnoreCase(acronimo)) {	                    	  
						identificacao = new Identificacao(identificacaoVetor.getId(), 
								identificacaoVetor.getNome(),identificacaoVetor.getAcronimo(), 
								identificacaoVetor.isStatus(),identificacaoVetor.getDataRegisto(), 
								identificacaoVetor.getDataActualizacao());						
					}
				}	                  
			} 	          		
		}       		
		return identificacao;
	}
	
	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Identificacao getById(int id, Vector identificacoes) {
		Identificacao identificacao = null;	
		if (id!=0) {        	  
			for (int i = 0; i < identificacoes.size(); i++) {	          		
				if (!identificacoes.isEmpty()) {
					Identificacao identificacaoVetor = (Identificacao)identificacoes.elementAt(i);
					if (identificacaoVetor.getId() == id) {	                    	  
						identificacao = new Identificacao(identificacaoVetor.getId(), 
								identificacaoVetor.getNome(),identificacaoVetor.getAcronimo(), 
								identificacaoVetor.isStatus(),identificacaoVetor.getDataRegisto(), 
								identificacaoVetor.getDataActualizacao());						
					}
				}	                  
			} 	          		
		}       		
		return identificacao;
	}

	public static int gravaIdentificacao(Vector identificacoes) {
		int id = 0;
		boolean error = false;
		id = geradorID(identificacoes);		
		String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),identificacoes);		
		if (nome != null) {
			String acronimo = validaAcronimo(Validacao.validaEntradaPalavra(Language.language_input_acronym()),identificacoes);
			if (acronimo != null) {
				Identificacao identificacao = new Identificacao(id, nome, acronimo, true, LocalDateTime.now(), LocalDateTime.now());				
				Validacao.adicionar(identificacoes, identificacao);
				error = true;
			}else {
				id = 0;
			}
		} else {
			id = 0;
		}		
		gravarDadosIdentificacaoNoFicheiro(identificacoes, filePath);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());			
		return id;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizarIdentificacao() {
		System.out.println();
		System.out.println("********************* "+ Language.language_update_menu() +" **********************");
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_name());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("2. "+Language.language_acronym());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("3. "+Language.language_state());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizarIdentificacao(Vector identificacoes) {
		int id = 0;
		listaIdentificacao(identificacoes);
		boolean error = true;		
		Identificacao identificacao = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), identificacoes);
		if (identificacao != null) {			
			switch (menuActualizarIdentificacao()) {
			case 1:
				deleta(identificacao, identificacoes);
				Validacao.destroiDirectorioFicheiro(filePath);
				String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),identificacoes);
				identificacao.setNome(nome);
				identificacao.setDataActualizacao(LocalDateTime.now());		
				id = identificacao.getId();
				gravarDadosIdentificacaoNoFicheiro(identificacoes, filePath);
				listaIdentificacao(identificacoes, id);
				break;
			case 2:
				deleta(identificacao, identificacoes);
				Validacao.destroiDirectorioFicheiro(filePath);
				String acronimo = validaAcronimo(Validacao.validaEntradaPalavra(Language.language_input_acronym()),identificacoes);
				identificacao.setAcronimo(acronimo);
				identificacao.setDataActualizacao(LocalDateTime.now());
				id = identificacao.getId();
				gravarDadosIdentificacaoNoFicheiro(identificacoes, filePath);
				listaIdentificacao(identificacoes, id);
				break;
			case 3:
				deleta(identificacao, identificacoes);
				Validacao.destroiDirectorioFicheiro(filePath);
				boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
				identificacao.setStatus(estado);
				identificacao.setDataActualizacao(LocalDateTime.now());
				id = identificacao.getId();
				gravarDadosIdentificacaoNoFicheiro(identificacoes, filePath);
				listaIdentificacao(identificacoes, id);
				break;
			case 4:
				error = false;
				break;
			default:
				actualizarIdentificacao(identificacoes);
				break;
			}
		}						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static void deleta(Identificacao identificacao, Vector identificacoes) {
		for (int i = 0; i < identificacoes.size(); i++) {
			Identificacao identificacao2 = (Identificacao)identificacoes.elementAt(i);
			if (identificacao2.getId() == identificacao.getId()) {
				identificacao = (Identificacao)identificacoes.remove(i);
			}					
		}	
	}
	
	private static int deletaIdentificacao(Vector identificacoes){
		int id = 0;
		listaIdentificacao(identificacoes);
		boolean error = false;		
		Identificacao identificacao = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), identificacoes);
		if (identificacao!=null) {
			deleta(identificacao, identificacoes);
			error = true;
		}			
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosIdentificacaoNoFicheiro(identificacoes, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}
		
	private static boolean gravarDadosIdentificacaoNoFicheiro(Vector identificacoes, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < identificacoes.size(); i++) {
					if (!identificacoes.isEmpty()) {
						Identificacao identificacao = (Identificacao)identificacoes.elementAt(i);
						bw.write(identificacao.getId() + "|" + identificacao.getNome()
								+ "|" + identificacao.getAcronimo()
								+ "|" + identificacao.isStatus() 
								+ "|" + identificacao.getDataRegisto()
								+ "|" + identificacao.getDataActualizacao());
						bw.newLine();
						listaIdentificacao(identificacoes);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosIdentificacaoNoFicheiro(identificacoes,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}
	
	public static boolean lerDadosNoFicheiro(Vector identificacoes, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Identificacao identificacao = new Identificacao(Integer.parseInt(str.nextToken()),
							str.nextToken(),str.nextToken(), Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));
										
						Validacao.adicionar(identificacoes, identificacao);				
					linha = br.readLine();								
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(identificacoes,file);				
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
		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_name(),"|",
													Language.language_acronym(),"|",Language.language_state(),"|",
													Language.language_dateRegistration(),"|",Language.language_updateDate(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-60.60s";
		String formatEstado = "%-8.10s",formatAcronimo = "%-10.10s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatAcronimo + " " + formatCaracter + " " + formatEstado 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** "+Language.language_list(Language.language_identification())+" ***************************************************************");
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],header[11],header[12],header[13],header[14]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return formatColl;
	}  

	/**
	 * @Descrição imprime a lista de identificacoes
	 */	
	public static void listaIdentificacao(Vector identificacoes){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < identificacoes.size(); i++){
			if (!identificacoes.isEmpty()){
				dadosImpressao(numeracao, (Identificacao)identificacoes.elementAt(i), layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(identificacoes.size(),empty_);
	}
	
	
	private static void dadosImpressao(int numeracao, Identificacao identificacoes, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,identificacoes.getId(),Validacao.delimitador,
				identificacoes.getNome(),Validacao.delimitador,identificacoes.getAcronimo(),Validacao.delimitador,Validacao.mudarStatus(identificacoes.isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(identificacoes.getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(identificacoes.getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	
	private static void listaIdentificacao(Vector identificacoes, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();		
		Identificacao identificacao = getById(id, identificacoes);
		if ((!identificacoes.isEmpty()) && identificacao.getId() == id) {					
			dadosImpressao(numeracao, identificacao, layoutFormat);
			numeracao += 1;					
		} else {
			empty_ += 1;
		}					
		Validacao.formatoImpressaoFooter(identificacoes.size(), empty_);		
	}         	

	public static void load(Vector vector) {
		lerDadosNoFicheiro(vector, filePath);
	}
	
	public static void inicializador(Vector identificacoes) {			
		load(identificacoes);
		int caso;
		if (!identificacoes.isEmpty()) {
			do {
				caso = Validacao.menu(Language.language_identification());
				switch (caso) {
				case 1:
					gravaIdentificacao(identificacoes);
					;
					break;
				case 2:
					actualizarIdentificacao(identificacoes);
					;
					break;
				case 3:
					deletaIdentificacao(identificacoes);
					;
					break;
				case 4:
					listaIdentificacao(identificacoes);
					;
					break;
				case 5:
					;
					break;
				default:

					break;
				}
			} while (caso != 5);
		}else {
			System.out.println(Language.language_empty_array(Language.language_identification()));
			gravaIdentificacao(identificacoes);
		}
	}
}