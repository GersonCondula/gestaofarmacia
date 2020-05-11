package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class FornecedorMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("fornecedor.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, Fornecedor[] fornecedors) {
		boolean existe = false;
		int id = (1 + Validacao.random.nextInt(fornecedors.length));
		int newID = 0;		
		for (Fornecedor fornecedor : fornecedors) 
			if(fornecedor != null) 
				if (fornecedor.getId() == id || id == 0) 
					existe = true;									
		if (existe)
			geradorID(j, fornecedors);
		else
			newID = id;
		return newID;
	}

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, Fornecedor [] fornecedors) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < fornecedors.length; i++) {
				if (fornecedors[i] != null) {
					if (!fornecedors[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),fornecedors);
						i = fornecedors.length;
					}
				}else {
					nomed = nome;
				}
			}
		}
		return nomed;
	}

	private static int validaNuit(int nuit_, Fornecedor [] fornecedors) {
		int nuit = 0;
		if (nuit_ != 0) {
			for (int i = 0; i < fornecedors.length; i++) {
				if (fornecedors[i] != null) {
					if (fornecedors[i].getNuit() != nuit_) {
						nuit = nuit_;
					}else {
						nuit = validaNuit(Validacao.validaEntradaInteiro(Language.language_input_exist_number()),fornecedors);
						i = fornecedors.length;
					}
				}else {
					nuit = nuit_;
				}
			}
		}
		return nuit;
	}

	public static Fornecedor selecionaFornecedor(Fornecedor [] fornecedors) {
		Fornecedor fornecedor = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (Fornecedor fornecedor2 : fornecedors)			
			if (fornecedor2 != null) 
				if (fornecedor2.getId() == numero) 
					fornecedor = fornecedor2;		
		if (fornecedor == null) 
			return selecionaFornecedor(fornecedors);		
		return fornecedor;
	}
	
	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Fornecedor getById(int id, Fornecedor [] fornecedors) {
		Fornecedor fornecedor = null;
		int count = 0;
		if (id!=0) {    
			for (Fornecedor fornecedor2 : fornecedors) {
				if (fornecedor2 != null) {	                	  
					if (fornecedor2.getId() == id) {
						fornecedor = new Fornecedor(fornecedor2.getId(),
								fornecedor2.getNome(), fornecedor2.getMorada(), 
								fornecedor2.getNuit(),fornecedor2.isStatus(),
								fornecedor2.getDataRegisto(),
								fornecedor2.getDataActualizacao());
						count ++;
					}
				}
			}			 	          	
		}       
		if (fornecedor == null) {
			fornecedor = getById(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), fornecedors);
			if (count < 3) {
			}
		}
		return fornecedor;
	}



	private static int gravar(Fornecedor [] fornecedors) {
		int id = 0, i = Validacao.notNull(fornecedors);		
		boolean error = false;
		id = geradorID(i, fornecedors);		
		String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()), fornecedors);
		if (nome != null) {
			String morada = Validacao.validaEntradaPalavra(Language.language_input_address());
			if (morada != null) {				
				int nuit = validaNuit(Validacao.validaEntradaInteiro(Language.language_input_nuit()), fornecedors);
				if (nuit != 0) {											
					fornecedors[i] = new Fornecedor(id, nome, morada, nuit, true, LocalDateTime.now(), LocalDateTime.now());
					error = true;						
				} else {
					id = 0;
				}			
			} else {
				id = 0;
			}
		} else {
			id = 0;
		}
		gravarDadosNoFicheiro(fornecedors, filePath);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());		
		return id;
	}


	private static boolean gravarDadosNoFicheiro(Fornecedor[] fornecedors, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < fornecedors.length; i++) {
					if (fornecedors[i] != null) {
						bw.write(fornecedors[i].getId()
								+ "|" + fornecedors[i].getNome()
								+ "|" + fornecedors[i].getMorada()
								+ "|" + fornecedors[i].getNuit()								
								+ "|" + fornecedors[i].isStatus() 
								+ "|" + fornecedors[i].getDataRegisto()
								+ "|" + fornecedors[i].getDataActualizacao());
						bw.newLine();
						lista(fornecedors);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(fornecedors,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados
	 */
	private static byte menuActualizacao() {
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_update_menu());
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. "+ Language.language_name());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. "+ Language.language_address());
		System.out.println("*---------------------------------------------------------------------------------*");	
		System.out.println("*4. "+ Language.language_nuit());		
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*6. "+ Language.language_state());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*7. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Fornecedor[] fornecedors) {
		int id = 0;
		lista(fornecedors);
		boolean error = false;		
		Fornecedor fornecedor = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), fornecedors);
		if (fornecedor != null) {
			for (int i = 0; i < fornecedors.length; i++) {
				if (fornecedors[i] != null) {
					if (fornecedors[i].getId() == fornecedor.getId()) {
						id = fornecedor.getId();
						switch (menuActualizacao()) {
						case 1:
							String nome = validaNome(
									Validacao.validaEntradaPalavra(Language.language_input_name()),
									fornecedors);
							fornecedors[i].setNome(nome);
							fornecedors[i].setDataActualizacao(LocalDateTime.now());
							lista(fornecedors, id);
							break;
						case 2:
							String morada = Validacao.validaEntradaPalavra(Language.language_input_address());						
							fornecedors[i].setMorada(morada);
							fornecedors[i].setDataActualizacao(LocalDateTime.now());
							lista(fornecedors, id);
							break;
						case 3:							
							int nuit = Validacao.validaEntradaInteiro(Language.language_input_nuit());						
							fornecedors[i].setNuit(nuit);
							fornecedors[i].setDataActualizacao(LocalDateTime.now());
							lista(fornecedors, id);
							break;
						case 5:							
							boolean estado = Validacao
							.validaEntradaStatus(Language.language_input_state());
							fornecedors[i].setStatus(estado);
							fornecedors[i].setDataActualizacao(LocalDateTime.now());
							lista(fornecedors, id);
							break;
						case 7:
							error = false;
							break;
						default:
							lista(fornecedors);
							break;
						}
					}
					error = true;
				}
			} 
		}
		gravarDadosNoFicheiro(fornecedors, filePath);		
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deleta(Fornecedor[] fornecedors){
		int id = 0;
		lista(fornecedors);
		boolean error = false;		
		Fornecedor fornecedor = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), fornecedors);
		if (fornecedor != null) {
			for (int i = 0; i < fornecedors.length; i++) {
				if (fornecedors[i] != null) {
					if (fornecedors[i].getId() == fornecedor.getId()) {
						id = fornecedor.getId();
						fornecedors[i] = null;
						error =  true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(fornecedors, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean lerDadosNoFicheiro(Fornecedor[] fornecedors, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Fornecedor fornecedor = new Fornecedor(
							Integer.parseInt(str.nextToken()),str.nextToken(),
							str.nextToken(),Integer.parseInt(str.nextToken()),
							Boolean.parseBoolean(str.nextToken()),Validacao.parseStringToLocalDateTime(str.nextToken()),Validacao.parseStringToLocalDateTime(str.nextToken()));
					fornecedors[i] = fornecedor;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(fornecedors, file);				
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

		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_name(),"|",Language.language_address(),
				"|",Language.language_nuit(),"|",Language.language_state(),
				"|",Language.language_dateRegistration(),"|",Language.language_updateDate(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-43.43s";
		String formatNumIden = "%-20.20s",formatIdentifica = "%-15.15s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatIdentifica + " " + formatCaracter + " " + formatNumIden 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_provider()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],
				header[11],header[12],header[13],header[14],header[15],header[16],header[17],header[18],header[19],header[20]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}

	private static void dadosImpressao(int numeracao, int i, Fornecedor [] fornecedors, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,fornecedors[i].getId(),
				Validacao.delimitador,fornecedors[i].getNome(),Validacao.delimitador,fornecedors[i].getMorada(),
				Validacao.delimitador,fornecedors[i].getNuit(),Validacao.delimitador,Validacao.mudarStatus(fornecedors[i].isStatus()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToSring(fornecedors[i].getDataRegisto()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToSring(fornecedors[i].getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Fornecedor[] fornecedors) {
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();	
		for (int i = 0; i < fornecedors.length; i++)
			if (fornecedors[i] != null){
				dadosImpressao(numeracao, i, fornecedors, layoutFormat);
				numeracao+=1;
			}else empty_ += 1; 		
		Validacao.formatoImpressaoFooter(fornecedors.length,empty_);		
	}

	private static void lista(Fornecedor[] fornecedors, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < fornecedors.length; i++) 
			if (fornecedors[i] != null && fornecedors[i].getId() == id) {					
				dadosImpressao(numeracao, i, fornecedors, layoutFormat);
				numeracao += 1;					
			}else empty_ += 1;						
		Validacao.formatoImpressaoFooter(fornecedors.length, empty_);		
	}  

	public static void load(Fornecedor [] fornecedors) {
		Validacao.init(fornecedors);	
		lerDadosNoFicheiro(fornecedors, filePath);
	}

	/**
	 * @param fornecedors 
	 */	
	public static void inicializador(Fornecedor [] fornecedors) {							
			if (Validacao.notNull(fornecedors) != 0) {
				int caso;
				do {
					caso = Validacao.menu(Language.language_provider());
					switch (caso) {
					case 1:
						gravar(fornecedors);
						;
						break;
					case 2:
						actualizar(fornecedors);
						;
						break;
					case 3:
						deleta(fornecedors);
						;
						break;
					case 4:
						lista(fornecedors);
						;
						break;
					case 5:
						;
						break;
					default:
						break;
					}
				} while (caso != 5);
			} else {
				System.out.println(Language.language_empty_array(Language.language_provider()));
				gravar(fornecedors);
			} 		
	}	
}
