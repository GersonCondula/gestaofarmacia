package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class IdentificacaoMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("identificacao.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, Identificacao[] identificacaos) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(identificacaos.length));
		int newID = 0;
		for (int i = 0; i < identificacaos.length; i++) {
			if (identificacaos[i] != null) {
				if (identificacaos[i].getId() == id || id == 0)
					exise = true;
			}
		}
		if (exise)
			geradorID(j, identificacaos);
		else
			newID = id;
		return newID;
	}

   /**	 
	* @Descrição Garante que os nomes das identificacoes sejam unicos
	*/
	private static String validaAcronimo(String acronimo, Identificacao[] identificacaos) {
		String acronimod = null;
		if (acronimo != null) {
			for (int i = 0; i < identificacaos.length; i++) {
				if (identificacaos[i] != null) {
					if (!identificacaos[i].getAcronimo().equalsIgnoreCase(acronimo)) {
						acronimod = acronimo;
					}else {
						acronimod = validaAcronimo(Validacao.validaEntradaPalavra(Language.language_input_exist_acronym()),identificacaos);
						i = identificacaos.length;
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
	private static String validaNome(String nome, Identificacao[] identificacaos) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < identificacaos.length; i++) {
				if (identificacaos[i] != null) {
					if (!identificacaos[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()), identificacaos);
						i = identificacaos.length;
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
	public static Identificacao getByAcronimo(String acronimo, Identificacao [] identificacoes) {
		Identificacao identificacaos = null;
		int count = 0;
		if (acronimo != null) {        	  
			for (int i = 0; i < identificacoes.length; i++) {	          		
				if (identificacoes[i] != null) {	                	  
					if (identificacoes[i].getAcronimo().equalsIgnoreCase(acronimo)) {	                    	  
						identificacaos = new Identificacao(identificacoes[i].getId(), 
								identificacoes[i].getNome(),identificacoes[i].getAcronimo(), 
								identificacoes[i].isStatus(),identificacoes[i].getDataRegisto(), 
								identificacoes[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (identificacaos == null) {
			identificacaos = getByAcronimo(Validacao.validaEntradaPalavra(Language.language_invalid_acronym()), identificacoes);
			if (count < 3) {

			}
		}
		return identificacaos;
	}
	
	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Identificacao getById(int id, Identificacao [] identificacoes) {
		Identificacao identificacaos = null;
		int count = 0;
		if (id!=0) {        	  
			for (int i = 0; i < identificacoes.length; i++) {	          		
				if (identificacoes[i] != null) {	                	  
					if (identificacoes[i].getId() == id) {	                    	  
						identificacaos = new Identificacao(identificacoes[i].getId(), 
								identificacoes[i].getNome(),identificacoes[i].getAcronimo(), 
								identificacoes[i].isStatus(),identificacoes[i].getDataRegisto(), 
								identificacoes[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (identificacaos == null) {
			identificacaos = getById(Validacao.validaEntradaInteiro(Language.language_input_valid_id()), identificacoes);
			if (count < 3) {

			}
		}
		return identificacaos;
	}

	public static int gravaIdentificacao(Identificacao[] identificacaos) {
		int id = 0, i = Validacao.notNull(identificacaos);
		boolean error = false;
		id = geradorID(i, identificacaos);		
		String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),identificacaos);		
		if (nome != null) {
			String acronimo = validaAcronimo(Validacao.validaEntradaPalavra(Language.language_input_acronym()),identificacaos);
			if (acronimo != null) {
				identificacaos[i] = new Identificacao(id, nome, acronimo, true, LocalDateTime.now(), LocalDateTime.now());
				error = true;
			}else {
				id = 0;
			}
		} else {
			id = 0;
		}		
		gravarDadosIdentificacaoNoFicheiro(identificacaos, filePath);
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

	private static int actualizarIdentificacao(Identificacao[] identificacaos) {
		int id = 0;
		listaIdentificacao(identificacaos);
		boolean error = true;		
		Identificacao identificacao = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), identificacaos);
		if (identificacao != null) {
			for (int i = 0; i < identificacaos.length; i++) {
				if (identificacaos[i] != null) {
					if (identificacaos[i].getId() == identificacao.getId()) {
						id = identificacao.getId();
						switch (menuActualizarIdentificacao()) {
						case 1:
							String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),identificacaos);
							identificacaos[i].setNome(nome);
							identificacaos[i].setDataActualizacao(LocalDateTime.now());
							listaIdentificacao(identificacaos, id);
							break;
						case 2:
							String acronimo = validaAcronimo(Validacao.validaEntradaPalavra(Language.language_input_acronym()),identificacaos);
							identificacaos[i].setAcronimo(acronimo);
							identificacaos[i].setDataActualizacao(LocalDateTime.now());
							listaIdentificacao(identificacaos, id);
							break;
						case 3:
							boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
							identificacaos[i].setStatus(estado);
							identificacaos[i].setDataActualizacao(LocalDateTime.now());
							listaIdentificacao(identificacaos, id);
							break;
						case 4:
							error = false;
							break;
						default:
							actualizarIdentificacao(identificacaos);
							break;
						}
					}
				}
			} 
		}
		gravarDadosIdentificacaoNoFicheiro(identificacaos, filePath);		
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deletaIdentificacao(Identificacao[] identificacaos){
		int id = 0;
		listaIdentificacao(identificacaos);
		boolean error = false;		
		Identificacao identificacao = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), identificacaos);
		if (identificacao != null) {
			for (int i = 0; i < identificacaos.length; i++) {
				if (identificacaos[i] != null) {
					if (identificacaos[i].getId() == identificacao.getId()) {
						id = identificacao.getId();
						identificacaos[i] = null;
						error = true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosIdentificacaoNoFicheiro(identificacaos, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean gravarDadosIdentificacaoNoFicheiro(Identificacao[] identificacaos, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < identificacaos.length; i++) {
					if (identificacaos[i] != null) {
						bw.write(identificacaos[i].getId() + "|" + identificacaos[i].getNome()
								+ "|" + identificacaos[i].getAcronimo()
								+ "|" + identificacaos[i].isStatus() 
								+ "|" + identificacaos[i].getDataRegisto()
								+ "|" + identificacaos[i].getDataActualizacao());
						bw.newLine();
						listaIdentificacao(identificacaos);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosIdentificacaoNoFicheiro(identificacaos,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static boolean lerDadosNoFicheiro(Identificacao[] identificacaos, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Identificacao identificacao = new Identificacao(Integer.parseInt(str.nextToken()),
							str.nextToken(),str.nextToken(), Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));
					identificacaos[i] = identificacao;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(identificacaos,file);				
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
	public static void listaIdentificacao(Identificacao [] identificacaos){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < identificacaos.length; i++){
			if (identificacaos[i] != null){
				dadosImpressao(numeracao, i, identificacaos, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(identificacaos.length,empty_);
	}

	private static void dadosImpressao(int numeracao, int i, Identificacao [] identificacaos, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,identificacaos[i].getId(),Validacao.delimitador,
				identificacaos[i].getNome(),Validacao.delimitador,identificacaos[i].getAcronimo(),Validacao.delimitador,Validacao.mudarStatus(identificacaos[i].isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(identificacaos[i].getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(identificacaos[i].getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static void listaIdentificacao(Identificacao [] identificacaos, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < identificacaos.length; i++) {
			if (identificacaos[i] != null && identificacaos[i].getId() == id) {					
				dadosImpressao(numeracao, i, identificacaos, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(identificacaos.length, empty_);		
	}         	

	public static void load(Identificacao [] identificacaos) {
		Validacao.init(identificacaos);	
		lerDadosNoFicheiro(identificacaos, filePath);
	}
	
	public static void inicializador(Identificacao [] identificacaos) {			
		load(identificacaos);
		int caso;
		if (Validacao.notNull(identificacaos) != 0) {
			do {
				caso = Validacao.menu(Language.language_identification());
				switch (caso) {
				case 1:
					gravaIdentificacao(identificacaos);
					;
					break;
				case 2:
					actualizarIdentificacao(identificacaos);
					;
					break;
				case 3:
					deletaIdentificacao(identificacaos);
					;
					break;
				case 4:
					listaIdentificacao(identificacaos);
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
			gravaIdentificacao(identificacaos);
		}
	}
}