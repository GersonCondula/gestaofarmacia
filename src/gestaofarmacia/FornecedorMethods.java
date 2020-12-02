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

@SuppressWarnings("rawtypes")
public class FornecedorMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("fornecedor.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(Vector fornecedors) {		
		int id = 0, iD = 0;		
		if (fornecedors.isEmpty()) {
			id = 1;
		}else {
			int novoId = (1 + Validacao.random.nextInt(fornecedors.size()));
			id = (2 + novoId);
			for (int i = 0; i < fornecedors.size(); i++) {
				Fornecedor fornecedor = (Fornecedor)fornecedors.elementAt(i);
				if(!fornecedors.isEmpty()) 
					if (fornecedor.getId() == id || id == 0) 
						geradorID(fornecedors);
					else
						iD = id;										
			}							
		}
		return iD;
	}

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, Vector fornecedors) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < fornecedors.size(); i++) {
				Fornecedor fornecedor = (Fornecedor)fornecedors.elementAt(i);
				if (!fornecedors.isEmpty()) {
					if (!fornecedor.getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),fornecedors);
						i = fornecedors.size();
					}
				}else {
					nomed = nome;
				}
			}
		}
		return nomed;
	}

	private static int validaNuit(int nuit_, Vector fornecedors) {
		int nuit = 0;
		if (nuit_ != 0) {
			for (int i = 0; i < fornecedors.size(); i++) {
				Fornecedor fornecedor = (Fornecedor)fornecedors.elementAt(i);
				if (!fornecedors.isEmpty()) {
					if (fornecedor.getNuit() != nuit_) {
						nuit = nuit_;
					}else {
						nuit = validaNuit(Validacao.validaEntradaInteiro(Language.language_input_exist_number()),fornecedors);
						i = fornecedors.size();
					}
				}else {
					nuit = nuit_;
				}
			}
		}
		return nuit;
	}

	public static Fornecedor selecionaFornecedor(Vector fornecedors) {
		Fornecedor fornecedor = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i < fornecedors.size(); i++) {	
			Fornecedor fornecedor2 = (Fornecedor)fornecedors.elementAt(i);
			if (!fornecedors.isEmpty()) 
				if (fornecedor2.getId() == numero) 
					fornecedor = fornecedor2;		
		}
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
	public static Fornecedor getById(int id, Vector fornecedors) {
		Fornecedor fornecedor = null;
		if (id!=0) {    
			for (int i = 0; i < fornecedors.size(); i++) {	
				Fornecedor fornecedor2 = (Fornecedor)fornecedors.elementAt(i);
				if (fornecedor2 != null) {	                	  
					if (fornecedor2.getId() == id) {
						fornecedor = new Fornecedor(fornecedor2.getId(),
								fornecedor2.getNome(), fornecedor2.getMorada(), 
								fornecedor2.getNuit(),fornecedor2.isStatus(),
								fornecedor2.getDataRegisto(),
								fornecedor2.getDataActualizacao());
					}
				}
			}			 	          	
		}       
		if (fornecedor == null) {
			fornecedor = getById(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), fornecedors);			
		}
		return fornecedor;
	}



	public static int gravar(Vector fornecedors) {
		int id = 0;		
		boolean error = false;
		id = geradorID(fornecedors);		
		String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()), fornecedors);
		if (nome != null) {
			String morada = Validacao.validaEntradaPalavra(Language.language_input_address());
			if (morada != null) {				
				int nuit = validaNuit(Validacao.validaEntradaInteiro(Language.language_input_nuit()), fornecedors);
				if (nuit != 0) {											
					Fornecedor fornecedor = new Fornecedor(id, nome, morada, nuit, true, LocalDateTime.now(), LocalDateTime.now());
					Validacao.adicionar(fornecedors, fornecedor);
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


	private static boolean gravarDadosNoFicheiro(Vector fornecedors, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < fornecedors.size(); i++) {
					Fornecedor fornecedor2 = (Fornecedor)fornecedors.elementAt(i);
					if (!fornecedors.isEmpty()) {
						bw.write(fornecedor2.getId()
								+ "|" + fornecedor2.getNome()
								+ "|" + fornecedor2.getMorada()
								+ "|" + fornecedor2.getNuit()								
								+ "|" + fornecedor2.isStatus() 
								+ "|" + fornecedor2.getDataRegisto()
								+ "|" + fornecedor2.getDataActualizacao());
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
		System.out.println("*3. "+ Language.language_nuit());		
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*4. "+ Language.language_state());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*5. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Vector fornecedors) {
		int id = 0;
		lista(fornecedors);
		boolean error = false;		
		Fornecedor fornecedor = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), fornecedors);
		if (fornecedor != null) {			
				switch (menuActualizacao()) {
				case 1:
					String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()), fornecedors);
					fornecedor.setNome(nome);
					fornecedor.setDataActualizacao(LocalDateTime.now());
					Validacao.adicionar(fornecedors, fornecedor);
					lista(fornecedors, id);
					break;
				case 2:
					String morada = Validacao.validaEntradaPalavra(Language.language_input_address());						
					fornecedor.setMorada(morada);
					fornecedor.setDataActualizacao(LocalDateTime.now());
					Validacao.adicionar(fornecedors, fornecedor);
					lista(fornecedors, id);
					break;
				case 3:							
					int nuit = Validacao.validaEntradaInteiro(Language.language_input_nuit());						
					fornecedor.setNuit(nuit);
					fornecedor.setDataActualizacao(LocalDateTime.now());
					Validacao.adicionar(fornecedors, fornecedor);
					lista(fornecedors, id);
					break;
				case 4:							
					boolean estado = Validacao
					.validaEntradaStatus(Language.language_input_state());
					fornecedor.setStatus(estado);
					fornecedor.setDataActualizacao(LocalDateTime.now());
					Validacao.adicionar(fornecedors, fornecedor);
					lista(fornecedors, id);
					break;
				case 5:
					error = false;
					break;
				default:
					lista(fornecedors);
					break;
				}			
			error = true;				 
		}
		gravarDadosNoFicheiro(fornecedors, filePath);		
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static void deleta(Fornecedor fornecedor, Vector fornecedors) {
		for (int i = 0; i < fornecedors.size(); i++) {
			Fornecedor fornecedor2 = (Fornecedor)fornecedors.elementAt(i);
			if (fornecedor2.getId() == fornecedor.getId()) {
				fornecedor = (Fornecedor)fornecedors.remove(i);
			}					
		}	
	}
	
	private static void deleta(Vector fornecedors){		
		lista(fornecedors);		
		Fornecedor fornecedor = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), fornecedors);
		if (fornecedor != null) {
			deleta(fornecedor, fornecedors);
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(fornecedors, filePath);											
	}

	private static boolean lerDadosNoFicheiro(Vector fornecedors, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Fornecedor fornecedor = new Fornecedor(
							Integer.parseInt(str.nextToken()),str.nextToken(),
							str.nextToken(),Integer.parseInt(str.nextToken()),
							Boolean.parseBoolean(str.nextToken()),Validacao.parseStringToLocalDateTime(str.nextToken()),Validacao.parseStringToLocalDateTime(str.nextToken()));
					Validacao.adicionar(fornecedors, fornecedor);
					linha = br.readLine();									
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

		String [] header = new String[]{
				"|","#",
				"|",Language.language_id(),
				"|",Language.language_name(),
				"|",Language.language_address(),
				"|",Language.language_nuit(),
				"|",Language.language_state(),
				"|",Language.language_dateRegistration(),
				"|",Language.language_updateDate(),
				"|"};
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
				header[11],header[12],header[13],header[14],header[15],header[16]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}

	private static void dadosImpressao(int numeracao, Fornecedor fornecedor, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,fornecedor.getId(),
				Validacao.delimitador,fornecedor.getNome(),Validacao.delimitador,fornecedor.getMorada(),
				Validacao.delimitador,fornecedor.getNuit(),Validacao.delimitador,Validacao.mudarStatus(fornecedor.isStatus()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(fornecedor.getDataRegisto()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(fornecedor.getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Vector fornecedors) {
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();	
		for (int i = 0; i < fornecedors.size(); i++) {
			Fornecedor fornecedor = (Fornecedor)fornecedors.elementAt(i);
			if (!fornecedors.isEmpty()){
				dadosImpressao(numeracao, fornecedor, layoutFormat);
				numeracao+=1;
			}else empty_ += 1; 		
		}
		Validacao.formatoImpressaoFooter(fornecedors.size(),empty_);		
	}

	private static void lista(Vector fornecedors, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();		
		Fornecedor fornecedor = getById(id, fornecedors);
		if (!fornecedors.isEmpty() && fornecedor.getId() == id) {					
			dadosImpressao(numeracao, fornecedor, layoutFormat);
			numeracao += 1;					
		}else 
			empty_ += 1;								
		Validacao.formatoImpressaoFooter(fornecedors.size(), empty_);		
	}  

	public static void load(Vector fornecedors) {
		lerDadosNoFicheiro(fornecedors, filePath);
	}

	/**
	 * @param fornecedors 
	 */	
	public static void inicializador(Vector fornecedors) {							
		if (fornecedors.isEmpty()) {
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
