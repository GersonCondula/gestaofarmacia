package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class PermissaoSistemaMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("permissoessistema.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, PermissaoSistema[] permissaoSistemas) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(permissaoSistemas.length));
		int newID = 0;
		for (int i = 0; i < permissaoSistemas.length; i++) {
			if (permissaoSistemas[i] != null) {
				if (permissaoSistemas[i].getId() == id || id == 0)
					exise = true;
			}
		}
		if (exise)
			geradorID(j, permissaoSistemas);
		else
			newID = id;
		return newID;
	}

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, PermissaoSistema[] permissaoSistemas) {
		String nomed = null;
		if (nome != null) { 
			for (int i = 0; i < permissaoSistemas.length; i++) {
				if (permissaoSistemas[i] != null) {
					if (!permissaoSistemas[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra("O nome da Permissao ja existe, queira por favor informar um novo nome: "),permissaoSistemas);
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
								permissaoSistemas[i].getNome(), permissaoSistemas[i].isStatus(),
								permissaoSistemas[i].getDataRegisto(), permissaoSistemas[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (permissaoSistema == null) {
			permissaoSistema = getByNome(Validacao.validaEntradaPalavra("Volte a informar o nome da Permissao valida: "), permissaoSistemas);
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
								permissaoSistemas[i].getNome(), permissaoSistemas[i].isStatus(),
								permissaoSistemas[i].getDataRegisto(), permissaoSistemas[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (permissaoSistema == null) {
			permissaoSistema = getByNome(Validacao.validaEntradaPalavra("Volte a informar o nome da Permissao valida: "), permissaoSistemas);
			if (count < 3) {

			}
		}
		return permissaoSistema;
	}

	private static int gravar(PermissaoSistema[] permissaoSistemas) {
		int id = 0, i = Validacao.notNull(permissaoSistemas);		
		id = geradorID(i, permissaoSistemas);		
		String nome = validaNome(Validacao.validaEntradaPalavra("Informe o nome da permissao: "),permissaoSistemas);		
		if (nome != null)
			permissaoSistemas[i] = new PermissaoSistema(id, nome, true, LocalDateTime.now(), LocalDateTime.now());
		else 
			id = 0;
		gravarDadosNoFicheiro(permissaoSistemas, filePath);
		Validacao.validaGravacao(id, "Identificacao Gravada com sucesso!");			
		return id;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados
	 */
	private static byte menuActualizar() {
		System.out.println();
		System.out.println("************************* Actualizar dados de Permissoes **************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Nome                                                                          *");
		System.out.println("*---------------------------------------------------------------------------------*");     		   
		System.out.println("*2. Estado                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*3. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	private static int actualizar(PermissaoSistema[] permissaoSistemas) {
		int id = 0;
		lista(permissaoSistemas);
		boolean error = true;
		String msg;
		PermissaoSistema identificacao = getById(Validacao.validaEntradaInteiro("Informe o numero da permissao: "), permissaoSistemas);
		if (identificacao != null) {
			for (int i = 0; i < permissaoSistemas.length; i++) {
				if (permissaoSistemas[i] != null) {
					if (permissaoSistemas[i].getId() == identificacao.getId()) {
						id = identificacao.getId();
						switch (menuActualizar()) {
						case 1:
							String nome = validaNome(
									Validacao.validaEntradaPalavra("Informe o nome da permissao: "),
									permissaoSistemas);
							permissaoSistemas[i].setNome(nome);
							permissaoSistemas[i].setDataActualizacao(LocalDateTime.now());
							listaIdentificacao(permissaoSistemas, id);
							break;
						case 2:							
							boolean estado = Validacao
							.validaEntradaStatus("Informe o Novo Estado [Activo ou Inactivo]: ");
							permissaoSistemas[i].setStatus(estado);
							permissaoSistemas[i].setDataActualizacao(LocalDateTime.now());
							listaIdentificacao(permissaoSistemas, id);
							break;
						case 4:
							error = false;
							break;
						default:
							actualizar(permissaoSistemas);
							break;
						}
					}
				}
			} 
		}
		gravarDadosNoFicheiro(permissaoSistemas, filePath);
		if (error) {
			msg = "Identificacao Atualizada com Sucesso!";
		}else {
			msg = "Identificacao Não Atualizada com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;
	}

	private static int deletaIdentificacao(PermissaoSistema[] permissaoSistemas){
		int id = 0;
		lista(permissaoSistemas);
		boolean error = true;
		String msg;
		PermissaoSistema permissaoSistema = getById(Validacao.validaEntradaInteiro("Informe o numero da permissao: "), permissaoSistemas);
		if (permissaoSistema != null) {
			for (int i = 0; i < permissaoSistemas.length; i++) {
				if (permissaoSistemas[i] != null) {
					if (permissaoSistemas[i].getId() == permissaoSistema.getId()) {
						id = permissaoSistema.getId();
						permissaoSistemas[i] = null;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(permissaoSistemas, filePath);				
		if (error) {
			msg = "Identificacao removida com Sucesso!";
		}else {
			msg = "Identificacao Não removida com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;		
	}

	private static boolean gravarDadosNoFicheiro(PermissaoSistema[] permissaoSistemas, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < permissaoSistemas.length; i++) {
					if (permissaoSistemas[i] != null) {
						bw.write(permissaoSistemas[i].getId() + "|" + permissaoSistemas[i].getNome()								
								+ "|" + permissaoSistemas[i].isStatus() 
								+ "|" + permissaoSistemas[i].getDataRegisto()
								+ "|" + permissaoSistemas[i].getDataActualizacao());
						bw.newLine();
						lista(permissaoSistemas);
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
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					PermissaoSistema identificacao = new PermissaoSistema(Integer.parseInt(str.nextToken()),
							str.nextToken(), Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));
					permissaoSistemas[i] = identificacao;					
					linha = br.readLine();
					i++;					
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
		String [] header = new String[]{"|","#","|","Numero","|","Nome","|","Estado","|","Data Reg","|","Data Act","|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-58.58s";
		String formatEstado = "%-16.16s";
		String formatData = "%-26.20s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatEstado 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** Lista de Permissoess ******************************************************************");
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],header[11],header[12]);
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
				identificacaos[i].getNome(),Validacao.delimitador,Validacao.mudarStatus(identificacaos[i].isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToSring(identificacaos[i].getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToSring(identificacaos[i].getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

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

	/**       
	 * @Descrição Menu de atualizacao de dados
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("****************************** Gestao de Permissoes *******************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Registar                                                                      *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. Actualizar                                                                    *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*3. Apagar                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*4. Lista                                                                         *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Selecionar opcao:");
	}

	public static void load(PermissaoSistema [] permissaoSistemas) {
		Validacao.init(permissaoSistemas);	
		lerDadosNoFicheiro(permissaoSistemas, filePath);
	}

	public static void inicializador(PermissaoSistema [] permissaoSistemas) {			
		load(permissaoSistemas);
		int caso;
		do {
			caso = menu();
			switch (caso) {
			case 1:
				gravar(permissaoSistemas);
				;break;
			case 2:
				actualizar(permissaoSistemas);
				;break;
			case 3:
				deletaIdentificacao(permissaoSistemas);
				;break;
			case 4:
				lista(permissaoSistemas);
				;break;			
			case 5:
				;break;
			default:

				break;
			}
		} while (caso != 5);
	}


}
