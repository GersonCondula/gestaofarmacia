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
	private static String filePath = Validacao.geraDirectorioFicheiro("identificacao.txt");;

	/**	 	
	 * @Descrição Inicializa o array de objecto de identificacao e preenche o array com dados provenientes do ficheiro de texto
	 */
	/*public static void init(Identificacao[] identificacaos) {
		for (int i = 0; i < identificacaos.length; i++)
			identificacaos[i] = null;
		//identificacaos[i] = new Identificacao(0,/ null, null, false, null, null);							
		lerDadosIdentificacaoNoFicheiro(identificacaos, filePath);		
	}*/

	/**	 	
	 * @Descrição retorna quantidade de registos nao nulos que tem no array
	 
	public static int notNull_Identificacoes(Identificacao[] identificacaos) {		
		int not_null = 0;		
		for (int i = 0; i < identificacaos.length; i++)
			if(identificacaos[i].getIdIdentificacao() != 0)			
				not_null+=1; 					
		return not_null;
	}
	*/

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID_Identificacao(int j, Identificacao[] identificacaos) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(identificacaos.length));
		int newID = 0;
		for (int i = 0; i < identificacaos.length; i++) {
			if (identificacaos[i] != null) {
				if (identificacaos[i].getIdIdentificacao() == id || id == 0)
					exise = true;
			}
		}
		if (exise)
			geradorID_Identificacao(j, identificacaos);
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
				if (identificacaos[i].getIdIdentificacao() != 0) {
					if (!identificacaos[i].getAcronimoIdentificacao().equalsIgnoreCase(acronimo)) {
						acronimod = acronimo;
					}else {
						acronimod = validaNome(Validacao.validaEntradaPalavra("O acronimo da identificacao ja existe, queira por favor informar um novo nome: "),identificacaos);
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
				if (identificacaos[i].getIdIdentificacao() != 0) {
					if (!identificacaos[i].getNomeIdentificacao().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra("O nome da identificacao ja existe, queira por favor informar um novo nome: "),identificacaos);
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
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	private static Identificacao getById(int id, Identificacao [] identificacoes) {
		Identificacao identificacaos = null;
		int count = 0;
		if (id!=0) {        	  
			for (int i = 0; i < identificacoes.length; i++) {	          		
				if (identificacoes[i] != null) {	                	  
					if (identificacoes[i].getIdIdentificacao() == id) {	                    	  
						identificacaos = new Identificacao(identificacoes[i].getIdIdentificacao(), 
								identificacoes[i].getNomeIdentificacao(),identificacoes[i].getAcronimoIdentificacao(), 
								identificacoes[i].isStatusIdentificacao(),identificacoes[i].getDataRegistoIdentificacao(), 
								identificacoes[i].getDataActualizacaoIdentificacaos());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (identificacaos == null) {
			identificacaos = getById(Validacao.validaEntradaInteiro("Volte a informar o numero da identificacao valido: "), identificacoes);
			if (count < 3) {

			}
		}
		return identificacaos;
	}

	private static int gravaIdentificacao(Identificacao[] identificacaos) {
		int id = 0, i = EAR.notNull(identificacaos);		
		id = geradorID_Identificacao(i, identificacaos);		
		String nome = validaNome(Validacao.validaEntradaPalavra("Informe o nome da identificacao: "),identificacaos);		
		if (nome != null) {
			String acronimo = validaAcronimo(Validacao.validaEntradaPalavra("Informe o acronimo da identificacao: "),identificacaos);
			if (acronimo != null) {
				identificacaos[i] = new Identificacao(id, nome, acronimo, true, LocalDateTime.now(), LocalDateTime.now());
			}else {
				id = 0;
			}
		} else {
			id = 0;
		}		
		gravarDadosIdentificacaoNoFicheiro(identificacaos, filePath);
		Validacao.validaGravacao(id, "Identificacao Gravada com sucesso!");			
		return id;
	}


	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizarIdentificacao() {
		System.out.println();
		System.out.println("********************* Actualizar dados de Item Identificacao **********************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Nome                                                                          *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. Acronimo                                                                      *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*3. Estado                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*4. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	private static int actualizarIdentificacao(Identificacao[] identificacaos) {
		int id = 0;
		listaIdentificacao(identificacaos);
		boolean error = true;
		String msg;
		Identificacao identificacao = getById(Validacao.validaEntradaInteiro("Informe o numero da identificacao: "), identificacaos);
		if (identificacao != null) {
			for (int i = 0; i < identificacaos.length; i++) {
				if (identificacaos[i].getIdIdentificacao() != 0) {
					if (identificacaos[i].getIdIdentificacao() == identificacao.getIdIdentificacao()) {
						id = identificacao.getIdIdentificacao();
						switch (menuActualizarIdentificacao()) {
						case 1:
							String nome = validaNome(
									Validacao.validaEntradaPalavra("Informe o nome da identificacao: "),
									identificacaos);
							identificacaos[i].setNomeIdentificacao(nome);
							identificacaos[i].setDataActualizacaoIdentificacaos(LocalDateTime.now());
							listaIdentificacao(identificacaos, id);
							break;
						case 2:
							String acronimo = validaAcronimo(
									Validacao.validaEntradaPalavra("Informe o acronimo da identificacao: "),
									identificacaos);
							identificacaos[i].setAcronimoIdentificacao(acronimo);
							identificacaos[i].setDataActualizacaoIdentificacaos(LocalDateTime.now());
							listaIdentificacao(identificacaos, id);
							break;
						case 3:
							boolean estado = Validacao
							.validaEntradaStatus("Informe o Novo Estado Para o Cliente [Activo ou Inactivo]: ");
							identificacaos[i].setStatusIdentificacao(estado);
							identificacaos[i].setDataActualizacaoIdentificacaos(LocalDateTime.now());
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
		if (error) {
			msg = "Identificacao Atualizada com Sucesso!";
		}else {
			msg = "Identificacao Não Atualizada com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;
	}

	private static int deletaIdentificacao(Identificacao[] identificacaos){
		int id = 0;
		listaIdentificacao(identificacaos);
		boolean error = true;
		String msg;
		Identificacao identificacao = getById(Validacao.validaEntradaInteiro("Informe o numero da identificacao: "), identificacaos);
		if (identificacao != null) {
			for (int i = 0; i < identificacaos.length; i++) {
				if (identificacaos[i].getIdIdentificacao() != 0) {
					if (identificacaos[i].getIdIdentificacao() == identificacao.getIdIdentificacao()) {
						id = identificacao.getIdIdentificacao();
						identificacaos[i] = null;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosIdentificacaoNoFicheiro(identificacaos, filePath);				
		if (error) {
			msg = "Identificacao removida com Sucesso!";
		}else {
			msg = "Identificacao Não removida com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;		
	}

	private static boolean gravarDadosIdentificacaoNoFicheiro(Identificacao[] identificacaos, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < identificacaos.length; i++) {
					if (identificacaos[i] != null) {
						bw.write(identificacaos[i].getIdIdentificacao() + "|" + identificacaos[i].getNomeIdentificacao()
								+ "|" + identificacaos[i].getAcronimoIdentificacao()
								+ "|" + identificacaos[i].isStatusIdentificacao() 
								+ "|" + identificacaos[i].getDataRegistoIdentificacao()
								+ "|" + identificacaos[i].getDataActualizacaoIdentificacaos());
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

	private static boolean lerDadosIdentificacaoNoFicheiro(Identificacao[] identificacaos, String file) {
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
				lerDadosIdentificacaoNoFicheiro(identificacaos,file);				
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
		String [] header = new String[]{"|","#","|","Numero","|","Nome","|","Acronimo","|","Estado","|","Data Reg","|","Data Act","|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-60.60s";
		String formatEstado = "%-8.10s",formatAcronimo = "%-10.10s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatAcronimo + " " + formatCaracter + " " + formatEstado 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** Lista de Identificacoes ***************************************************************");
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
	private static void listaIdentificacao(Identificacao [] identificacaos){
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
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,identificacaos[i].getIdIdentificacao(),Validacao.delimitador,
				identificacaos[i].getNomeIdentificacao(),Validacao.delimitador,identificacaos[i].getAcronimoIdentificacao(),Validacao.delimitador,Validacao.mudarStatus(identificacaos[i].isStatusIdentificacao()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToSring(identificacaos[i].getDataRegistoIdentificacao()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToSring(identificacaos[i].getDataActualizacaoIdentificacaos()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static void listaIdentificacao(Identificacao [] identificacaos, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < identificacaos.length; i++) {
			if (identificacaos[i].getIdIdentificacao() != 0 && identificacaos[i].getIdIdentificacao() == id) {					
				dadosImpressao(numeracao, i, identificacaos, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(identificacaos.length, empty_);		
	}         

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("************************** Gestao de Item Identificacao ***************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Registar Identificacao                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. Actualizar Identificacao                                                      *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*3. Apagar Identificacao                                                          *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*4. Lista Identificacao                                                           *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	public static void inicializador(Identificacao [] identificacaos) {			
		EAR.init(identificacaos);	
		lerDadosIdentificacaoNoFicheiro(identificacaos, filePath);
		int caso;
		do {
			caso = menu();
			switch (caso) {
			case 1:
				gravaIdentificacao(identificacaos);
				;break;
			case 2:
				actualizarIdentificacao(identificacaos);
				;break;
			case 3:
				deletaIdentificacao(identificacaos);
				;break;
			case 4:
				listaIdentificacao(identificacaos);
				;break;
			case 5:

				;break;
			default:

				break;
			}
		} while (caso != 5);
	}
}