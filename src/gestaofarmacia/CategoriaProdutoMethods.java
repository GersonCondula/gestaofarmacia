package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class CategoriaProdutoMethods {
	
	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("categoriaProduto.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, CategoriaProduto[] categoriaProdutos) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(categoriaProdutos.length));
		int newID = 0;
		for (int i = 0; i < categoriaProdutos.length; i++) {
			if (categoriaProdutos[i] != null) {
				if (categoriaProdutos[i].getId() == id || id == 0)
					exise = true;
			}
		}
		if (exise)
			geradorID(j, categoriaProdutos);
		else
			newID = id;
		return newID;
	}

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, CategoriaProduto[] categoriaProdutos) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < categoriaProdutos.length; i++) {
				if (categoriaProdutos[i] != null) {
					if (!categoriaProdutos[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()), categoriaProdutos);
						i = categoriaProdutos.length;
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
	public static CategoriaProduto getById(int id, CategoriaProduto [] categoriaProdutos) {
		CategoriaProduto categoriaProduto = null;
		int count = 0;
		if (id!=0) {        	  
			for (int i = 0; i < categoriaProdutos.length; i++) {	          		
				if (categoriaProdutos[i] != null) {	                	  
					if (categoriaProdutos[i].getId() == id) {	                    	  
						categoriaProduto = new CategoriaProduto(categoriaProdutos[i].getId(), 
								categoriaProdutos[i].getNome(),categoriaProdutos[i].getDescricao(), 
								categoriaProdutos[i].isStatus(),categoriaProdutos[i].getDataRegisto(), 
								categoriaProdutos[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (categoriaProduto == null) {
			categoriaProduto = getById(Validacao.validaEntradaInteiro(Language.language_input_valid_id()), categoriaProdutos);
			if (count < 3) {

			}
		}
		return categoriaProduto;
	}

	public static CategoriaProduto selecionaCategoriaProduo(CategoriaProduto [] categoriaProdutos) {
		CategoriaProduto categoriaProduto = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (CategoriaProduto categoriaProduto2 : categoriaProdutos)			
			if (categoriaProduto2 != null) 
				if (categoriaProduto2.getId() == numero) 
					categoriaProduto = categoriaProduto2;		
		if (categoriaProduto == null) 
			return selecionaCategoriaProduo(categoriaProdutos);
		return categoriaProduto;
	}
	
	public static int gravar(CategoriaProduto[] categoriaProdutos) {
		int id = 0, i = Validacao.notNull(categoriaProdutos);
		boolean error = false;
		id = geradorID(i, categoriaProdutos);		
		String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),categoriaProdutos);		
		if (nome != null) {
			String descricao = Validacao.validaEntradaPalavra(Language.language_input_acronym());
			if (descricao != null) {
				categoriaProdutos[i] = new CategoriaProduto(id, nome, descricao, true, LocalDateTime.now(), LocalDateTime.now());
				error = true;
			}else {
				id = 0;
			}
		} else {
			id = 0;
		}		
		gravarDadosNoFicheiro(categoriaProdutos, filePath);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());			
		return id;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados
	 */
	private static byte menuActualizar() {
		System.out.println();
		System.out.println("********************* "+ Language.language_update_menu() +" **********************");
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+Language.language_name());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("2. "+Language.language_description());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("3. "+Language.language_state());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("5. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(CategoriaProduto[] categoriaProdutos) {
		int id = 0;
		lista(categoriaProdutos);
		boolean error = true;		
		CategoriaProduto categoriaProduto = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), categoriaProdutos);
		if (categoriaProduto != null) {
			for (int i = 0; i < categoriaProdutos.length; i++) {
				if (categoriaProdutos[i] != null) {
					if (categoriaProdutos[i].getId() == categoriaProduto.getId()) {
						id = categoriaProduto.getId();
						switch (menuActualizar()) {
						case 1:
							String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),categoriaProdutos);
							categoriaProdutos[i].setNome(nome);
							categoriaProdutos[i].setDataActualizacao(LocalDateTime.now());
							lista(categoriaProdutos, id);
							break;
						case 2:
							String descricao = Validacao.validaEntradaPalavra(Language.language_input_acronym());
							categoriaProdutos[i].setDescricao(descricao);
							categoriaProdutos[i].setDataActualizacao(LocalDateTime.now());
							lista(categoriaProdutos, id);
							break;
						case 3:
							boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
							categoriaProdutos[i].setStatus(estado);
							categoriaProdutos[i].setDataActualizacao(LocalDateTime.now());
							lista(categoriaProdutos, id);
							break;
						case 5:
							error = false;
							break;
						default:
							actualizar(categoriaProdutos);
							break;
						}
					}
				}
			} 
		}
		gravarDadosNoFicheiro(categoriaProdutos, filePath);		
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deleta(CategoriaProduto[] categoriaProdutos){
		int id = 0;
		lista(categoriaProdutos);
		boolean error = false;		
		CategoriaProduto categoriaProduto = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), categoriaProdutos);
		if (categoriaProduto != null) {
			for (int i = 0; i < categoriaProdutos.length; i++) {
				if (categoriaProdutos[i] != null) {
					if (categoriaProdutos[i].getId() == categoriaProduto.getId()) {
						id = categoriaProduto.getId();
						categoriaProdutos[i] = null;
						error = true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(categoriaProdutos, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean gravarDadosNoFicheiro(CategoriaProduto[] categoriaProdutos, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < categoriaProdutos.length; i++) {
					if (categoriaProdutos[i] != null) {
						bw.write(categoriaProdutos[i].getId() + "|" + categoriaProdutos[i].getNome()
								+ "|" + categoriaProdutos[i].getDescricao()
								+ "|" + categoriaProdutos[i].isStatus() 
								+ "|" + categoriaProdutos[i].getDataRegisto()
								+ "|" + categoriaProdutos[i].getDataActualizacao());
						bw.newLine();
						lista(categoriaProdutos);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(categoriaProdutos,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static boolean lerDadosNoFicheiro(CategoriaProduto[] categoriaProdutos, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");		
					CategoriaProduto categoriaProduto = new CategoriaProduto(Integer.parseInt(str.nextToken()),
							str.nextToken(),str.nextToken(), Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));
					categoriaProdutos[i] = categoriaProduto;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(categoriaProdutos,file);				
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
													Language.language_description(),"|",Language.language_state(),"|",
													Language.language_dateRegistration(),"|",Language.language_updateDate(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-60.60s";
		String formatEstado = "%-8.10s",formatAcronimo = "%-10.10s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatAcronimo + " " + formatCaracter + " " + formatEstado 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** "+Language.language_list(Language.language_product_category())+" ***************************************************************");
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
	public static void lista(CategoriaProduto [] categoriaProdutos){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < categoriaProdutos.length; i++){
			if (categoriaProdutos[i] != null){
				dadosImpressao(numeracao, i, categoriaProdutos, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(categoriaProdutos.length,empty_);
	}

	private static void dadosImpressao(int numeracao, int i, CategoriaProduto [] categoriaProdutos, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,categoriaProdutos[i].getId(),Validacao.delimitador,
				categoriaProdutos[i].getNome(),Validacao.delimitador,categoriaProdutos[i].getDescricao(),Validacao.delimitador,Validacao.mudarStatus(categoriaProdutos[i].isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(categoriaProdutos[i].getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(categoriaProdutos[i].getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static void lista(CategoriaProduto [] categoriaProdutos, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < categoriaProdutos.length; i++) {
			if (categoriaProdutos[i] != null && categoriaProdutos[i].getId() == id) {					
				dadosImpressao(numeracao, i, categoriaProdutos, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(categoriaProdutos.length, empty_);		
	}         	

	public static void load(CategoriaProduto [] categoriaProdutos) {
		Validacao.init(categoriaProdutos);	
		lerDadosNoFicheiro(categoriaProdutos, filePath);
	}
	
	public static void inicializador(CategoriaProduto [] categoriaProdutos) {			
		load(categoriaProdutos);
		int caso;
		if (Validacao.notNull(categoriaProdutos) != 0) {
			do {
				caso = Validacao.menu(Language.language_identification());
				switch (caso) {
				case 1:
					gravar(categoriaProdutos);
					;
					break;
				case 2:
					actualizar(categoriaProdutos);
					;
					break;
				case 3:
					deleta(categoriaProdutos);
					;
					break;
				case 4:
					lista(categoriaProdutos);
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
			System.out.println(Language.language_empty_array(Language.language_product_category()));
			gravar(categoriaProdutos);
		}
	}
}
