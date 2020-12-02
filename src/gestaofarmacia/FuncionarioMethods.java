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
public class FuncionarioMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("funcionario.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(Vector funcionarios) {
		int id = 0, iD = 0;	
		if (funcionarios.isEmpty()) {
			id = 1;
		}else{					
			int novoId = (Validacao.random.nextInt(funcionarios.size()+1));
			id = (2 + novoId);		
			for (int i = 0; i < funcionarios.size(); i++) {
				Funcionario funcionario = (Funcionario)funcionarios.elementAt(i);
				if (!funcionarios.isEmpty()) {
					if (funcionario.getId() == id || id == 0)
						geradorID(funcionarios);
					else
						iD = id;
				}				
			}	
		}
		return iD;
	}
	
	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, Vector funcionarios) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < funcionarios.size(); i++) {
				Funcionario funcionario = (Funcionario)funcionarios.elementAt(i);
				if (!funcionarios.isEmpty()) {
					if (!funcionario.getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),funcionarios);
						i = funcionarios.size();
					}
				}else {
					nomed = nome;
				}
			}
		}
		return nomed;
	}
	
	public static String ValidaNumeroIdentificacao(String validaEntradaPalavra, Vector funcionarios) {
		String funcioanrioStr = null;
		if (validaEntradaPalavra != null) {
			for (int i = 0; i < funcionarios.size(); i++) {
				Funcionario funcionario = (Funcionario)funcionarios.elementAt(i);
				if (!funcionarios.isEmpty()) {
					if (!funcionario.getNumeroIdentidade().equalsIgnoreCase(validaEntradaPalavra)) {
						funcioanrioStr = validaEntradaPalavra;
					}else {
						funcioanrioStr = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra(Language.language_input_exist_number()),funcionarios);
						i = funcionarios.size();
					}
				}else {
					funcioanrioStr = validaEntradaPalavra;
				}
			}
		}
		return funcioanrioStr;
	}

	public static String ValidaNumeroIdentificacao(String validaEntradaPalavra, int id, Vector funcionarios) {

		String funcioanrioStr = null;
		if (validaEntradaPalavra != null) {
			for (int i = 0; i < funcionarios.size(); i++) {
				Funcionario funcionario = (Funcionario)funcionarios.elementAt(i);
				if (!funcionarios.isEmpty()) {
					if (!funcionario.getNumeroIdentidade().equalsIgnoreCase(validaEntradaPalavra) && funcionario.getId() == id) {
						funcioanrioStr = validaEntradaPalavra;
					}else {
						funcioanrioStr = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra(Language.language_input_exist_number()),funcionarios);
						i = funcionarios.size();
					}
				}else {
					funcioanrioStr = validaEntradaPalavra;
				}
			}
		}
		return funcioanrioStr;
	}

	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Funcionario getById(int id, Vector funcionarios) {
		Funcionario funcionario = null;		
		if (id!=0) {    	
			for (int i = 0; i < funcionarios.size(); i++) {
				Funcionario funcionario2 = (Funcionario)funcionarios.elementAt(i);				
				if (!funcionarios.isEmpty()) {	                	  
					if (funcionario2.getId() == id) {
						funcionario = new Funcionario(funcionario2.getId(),
								funcionario2.getNome(), funcionario2.getIdentificacao(), 
								funcionario2.getNumeroIdentidade(), funcionario2.getNuit(),
								funcionario2.getMorada(), funcionario2.isStatus(), 
								funcionario2.getDataRegisto(), funcionario2.getDataActualizacao());						
					}
				}
			}			 	          	
		}       
		if (funcionario == null) {
			funcionario = getById(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), funcionarios);	
		}
		return funcionario;
	}

	public static Funcionario selecionaFuncionario(Vector funcionarios) {
		Funcionario funcionario = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i < funcionarios.size(); i++) {
			if (!funcionarios.isEmpty()) 
				if (((Funcionario)funcionarios.elementAt(i)).getId() == numero) 
					funcionario = ((Funcionario)funcionarios.elementAt(i));		
		}
		if (funcionario == null) 
			return selecionaFuncionario(funcionarios);	
		
		return funcionario;
	}
	
	public static Identificacao selecionaIdentificacao(Vector identificacaos) {
		Identificacao identificacao = null;		
		int id = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i < identificacaos.size(); i++) {
			Identificacao identificacao2 = (Identificacao)identificacaos.elementAt(i);
			if (!identificacaos.isEmpty() && identificacao2.getId()==id) {
				identificacao = identificacao2;
			}
		}
		if (identificacao == null) {
			return selecionaIdentificacao(identificacaos);
		}
		return identificacao;
	}

	private static int gravar(Vector funcionarios, Vector identificacoes) {		
		boolean error = false;
		int id = geradorID(funcionarios);		
		String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()), funcionarios);
		if (nome != null) {
			IdentificacaoMethods.listaIdentificacao(identificacoes);
			Identificacao identificacao = selecionaIdentificacao(identificacoes);
			if (identificacao != null) {
				String numeroIdentidade = ValidaNumeroIdentificacao(
						Validacao.validaEntradaPalavra(Language.language_input_number()), funcionarios);
				if (numeroIdentidade != null) {
					int nuit = Validacao.validaEntradaInteiro(Language.language_input_nuit());
					if (nuit != 0) {
						String morada = Validacao.validaEntradaPalavra(Language.language_input_address());
						if (morada != null) {
							Funcionario funcionario = new Funcionario(id, nome, identificacao, numeroIdentidade, nuit,
									morada, true, LocalDateTime.now(), LocalDateTime.now());
							Validacao.adicionar(funcionarios, funcionario);
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
			} else {
				id = 0;
			}
		} else {
			id = 0;
		}
		gravarDadosNoFicheiro(funcionarios, filePath);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());		
		return id;
	}


	private static boolean gravarDadosNoFicheiro(Vector funcionarios, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < funcionarios.size(); i++) {
					Funcionario funcionario = (Funcionario)funcionarios.elementAt(i);
					if (!funcionarios.isEmpty()) {
						bw.write(funcionario.getId() 
								+ "|" + funcionario.getNome()
								+ "|" + funcionario.getIdentificacao().getId()
								+ "|" + funcionario.getNumeroIdentidade()
								+ "|" + funcionario.getNuit()
								+ "|" + funcionario.getMorada()
								+ "|" + funcionario.isStatus() 
								+ "|" + funcionario.getDataRegisto()
								+ "|" + funcionario.getDataActualizacao());
						bw.newLine();		
						lista(funcionarios);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(funcionarios,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizacao() {
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_update_menu());
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. "+ Language.language_name());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. "+ Language.language_identification_type());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. "+ Language.language_number() + Language.language_identification());                                                     
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*4. "+ Language.language_nuit());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. "+ Language.language_address());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*6. "+ Language.language_state());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*0. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}
	
	private static int actualizar(Vector funcionarios, Vector identificacoes) {
		int id = 0;
		lista(funcionarios);
		boolean error = false;		
		Funcionario funcionario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), funcionarios);		
		if (funcionario != null) {		
			switch (menuActualizacao()) {
			case 1:
				deleta(funcionario, funcionarios);					
				Validacao.destroiDirectorioFicheiro(filePath);
				String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),funcionarios);
				funcionario.setNome(nome);
				funcionario.setDataActualizacao(LocalDateTime.now());
				Validacao.adicionar(funcionarios, funcionario);
				id = funcionario.getId();
				gravarDadosNoFicheiro(funcionarios, filePath);
				lista(funcionarios, id);
				break;
			case 2:
				deleta(funcionario, funcionarios);	
				Validacao.destroiDirectorioFicheiro(filePath);
				IdentificacaoMethods.listaIdentificacao(identificacoes);
				Identificacao identificacao = selecionaIdentificacao(identificacoes);
				funcionario.setIdentificacao(identificacao);
				funcionario.setDataActualizacao(LocalDateTime.now());
				Validacao.adicionar(funcionarios, funcionario);
				id = funcionario.getId();
				gravarDadosNoFicheiro(funcionarios, filePath);
				lista(funcionarios, id);
				break;
			case 3:
				deleta(funcionario, funcionarios);	
				Validacao.destroiDirectorioFicheiro(filePath);				
				String numeroIdentidade = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra(Language.language_input_number()),funcionario.getId(), funcionarios);
				funcionario.setNumeroIdentidade(numeroIdentidade);
				funcionario.setDataActualizacao(LocalDateTime.now());
				Validacao.adicionar(funcionarios, funcionario);
				gravarDadosNoFicheiro(funcionarios, filePath);
				id = funcionario.getId();
				lista(funcionarios, id);
				break;
			case 4:								
				deleta(funcionario, funcionarios);					
				Validacao.destroiDirectorioFicheiro(filePath);									
				funcionario.setNuit(Validacao.validaEntradaInteiro(Language.language_input_nuit()));
				funcionario.setDataActualizacao(LocalDateTime.now());				
				Validacao.adicionar(funcionarios, funcionario);
				gravarDadosNoFicheiro(funcionarios, filePath);
				lista(funcionarios);					
				break;
			case 5:
				deleta(funcionario, funcionarios);	
				Validacao.destroiDirectorioFicheiro(filePath);				
				String morada = Validacao.validaEntradaPalavra(Language.language_input_address());						
				funcionario.setMorada(morada);
				funcionario.setDataActualizacao(LocalDateTime.now());
				Validacao.adicionar(funcionarios, funcionario);
				gravarDadosNoFicheiro(funcionarios, filePath);
				lista(funcionarios, id);
				break;
			case 6:
				deleta(funcionario, funcionarios);	
				Validacao.destroiDirectorioFicheiro(filePath);				
				boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
				funcionario.setStatus(estado);
				funcionario.setDataActualizacao(LocalDateTime.now());
				Validacao.adicionar(funcionarios, funcionario);
				gravarDadosNoFicheiro(funcionarios, filePath);
				lista(funcionarios, id);
				break;
			case 0:							
				break;
			default:
				actualizar(funcionarios, identificacoes);
				break;
			}			
		}						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static void deleta(Funcionario funcionario, Vector funcionarios) {
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionarioI = (Funcionario)funcionarios.elementAt(i);
			if (funcionarioI.getId() == funcionario.getId()) {
				funcionario = (Funcionario)funcionarios.remove(i);
			}					
		}	
	}
	
	private static void deletaFuncionario(Vector funcionarios){		
		lista(funcionarios);			
		Funcionario funcionario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), funcionarios);
		if (funcionario != null) {
			deleta(funcionario, funcionarios);
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(funcionarios, filePath);									
	}

	private static boolean lerDadosNoFicheiro(Vector funcionarios, Vector identificacoes, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					int id = Integer.parseInt(str.nextToken());
					String nome = str.nextToken();
					int idIdentificacao = Integer.parseInt(str.nextToken());
					Identificacao identificacao = IdentificacaoMethods.getById(idIdentificacao, identificacoes);
					String numeroIdentificacao = str.nextToken();
					int nuit = Integer.parseInt(str.nextToken());
					String morada = str.nextToken();
					Boolean status = Boolean.parseBoolean(str.nextToken());
					LocalDateTime dRegisto = Validacao.parseStringToLocalDateTime(str.nextToken());
					LocalDateTime dAtualiza = Validacao.parseStringToLocalDateTime(str.nextToken());
					Funcionario funcionario = new Funcionario(id, nome, identificacao, numeroIdentificacao, nuit, morada, status, dRegisto, dAtualiza);
					Validacao.adicionar(funcionarios, funcionario);					
					linha = br.readLine();									
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(funcionarios, identificacoes, file);				
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

		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_name(),"|",Language.language_identification(),
				"|",Language.language_number() + " " + Language.language_identification(),"|",Language.language_nuit(),
				"|",Language.language_address(),"|",Language.language_state(),"|",Language.language_dateRegistration(),
				"|",Language.language_updateDate(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-43.43s";
		String formatNumIden = "%-20.20s",formatIdentifica = "%-15.15s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatIdentifica + " " + formatCaracter + " " + formatNumIden 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_employee()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],
				header[11],header[12],header[13],header[14],header[15],header[16],header[17],header[18],header[19],header[20]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}

	private static void dadosImpressao(int numeracao, Funcionario funcionario, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,funcionario.getId(),
				Validacao.delimitador,funcionario.getNome(),Validacao.delimitador,funcionario.getIdentificacao().getAcronimo(),
				Validacao.delimitador,funcionario.getNumeroIdentidade(),Validacao.delimitador,funcionario.getNuit(),
				Validacao.delimitador,funcionario.getMorada(),Validacao.delimitador,Validacao.mudarStatus(funcionario.isStatus()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(funcionario.getDataRegisto()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(funcionario.getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}	
	
	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Vector funcionarios) {
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();	
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = (Funcionario)funcionarios.elementAt(i);
			if (!funcionarios.isEmpty()){
				dadosImpressao(numeracao, funcionario, layoutFormat);
				numeracao+=1;
			}else empty_ += 1; 		
		}
		Validacao.formatoImpressaoFooter(funcionarios.size(),empty_);		
	}

	private static void lista(Vector funcionarios, int id){
		int numeracao = 1;
		int empty_= 0;        		 
		String layoutFormat = formatoImpressao();		
		Funcionario funcionario = getById(id, funcionarios);		
		if (funcionario != null) {					
			dadosImpressao(numeracao, funcionario, layoutFormat);
			numeracao += 1;					
		}else
			empty_ += 1;							
		Validacao.formatoImpressaoFooter(funcionarios.size(), empty_);		
	}  

	public static void load(Vector funcionarios, Vector identificacoes) {		
		lerDadosNoFicheiro(funcionarios, identificacoes, filePath);
	}
	
	/**
	 * @param funcionarios
	 * @param identificacaos
	 */	
	public static void inicializador(Vector funcionarios, Vector identificacoes) {
		if (!identificacoes.isEmpty()) {
			if (!funcionarios.isEmpty()) {
				int caso;
				do {
					caso = Validacao.menu(Language.language_employee());
					switch (caso) {
					case 1:
						gravar(funcionarios, identificacoes);
						;break;
					case 2:
						actualizar(funcionarios, identificacoes);
						;break;
					case 3:
						deletaFuncionario(funcionarios);
						;break;
					case 4:
						lista(funcionarios);
						;break;
					case 5:
						;break;
					default:
						break;
					}
				} while (caso != 5);
			} else {
				System.out.println(Language.language_empty_array(Language.language_employee()));
				gravar(funcionarios, identificacoes);
			} 
		}else {
			System.out.println(Language.language_empty_array(Language.language_identification()));
			IdentificacaoMethods.gravaIdentificacao(identificacoes);
		}
	}	
}