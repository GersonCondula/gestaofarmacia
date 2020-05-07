package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class FuncionarioMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("funcionario.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, Funcionario[] funcionario) {
		boolean existe = false;
		int id = (1 + Validacao.random.nextInt(funcionario.length));
		int newID = 0;		
		for (Funcionario funcionarios : funcionario) 
			if(funcionarios != null) 
				if (funcionarios.getId() == id || id == 0) 
					existe = true;									
		if (existe)
			geradorID(j, funcionario);
		else
			newID = id;
		return newID;
	}

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, Funcionario [] funcionarios) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < funcionarios.length; i++) {
				if (funcionarios[i] != null) {
					if (!funcionarios[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra("O nome da identificacao ja existe, queira por favor informar um novo nome: "),funcionarios);
						i = funcionarios.length;
					}
				}else {
					nomed = nome;
				}
			}
		}
		return nomed;
	}

	private static String ValidaNumeroIdentificacao(String validaEntradaPalavra, Funcionario [] funcionario) {
		
		String funcioanrioStr = null;
		if (validaEntradaPalavra != null) {
			for (int i = 0; i < funcionario.length; i++) {
				if (funcionario[i] != null) {
					if (!funcionario[i].getNumeroIdentidade().equalsIgnoreCase(validaEntradaPalavra)) {
						funcioanrioStr = validaEntradaPalavra;
					}else {
						funcioanrioStr = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra("O numero da identificacao ja existe, queira por favor informar um novo numero: "),funcionario);
						i = funcionario.length;
					}
				}else {
					funcioanrioStr = validaEntradaPalavra;
				}
			}
		}
		return funcioanrioStr;
	}
	
private static String ValidaNumeroIdentificacao(String validaEntradaPalavra, int id,Funcionario [] funcionario) {
		
		String funcioanrioStr = null;
		if (validaEntradaPalavra != null) {
			for (int i = 0; i < funcionario.length; i++) {
				if (funcionario[i] != null) {
					if (!funcionario[i].getNumeroIdentidade().equalsIgnoreCase(validaEntradaPalavra) && funcionario[i].getId() == id) {
						funcioanrioStr = validaEntradaPalavra;
					}else {
						funcioanrioStr = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra("O numero da identificacao ja existe, queira por favor informar um novo numero: "),funcionario);
						i = funcionario.length;
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
	public static Funcionario getById(int id, Funcionario [] funcionarios) {
		Funcionario funcionario = null;
		int count = 0;
		if (id!=0) {    
			for (Funcionario funcionario2 : funcionarios) {
				if (funcionario2 != null) {	                	  
					if (funcionario2.getId() == id) {
						funcionario = new Funcionario(funcionario2.getId(),
								funcionario2.getNome(), funcionario2.getIdentificacao(), 
								funcionario2.getNumeroIdentidade(), funcionario2.getNuit(),
								funcionario2.getMorada(), funcionario2.isStatus(), 
								funcionario2.getDataRegisto(), funcionario2.getDataActualizacao());
						count ++;
					}
				}
			}			 	          	
		}       
		if (funcionario == null) {
			funcionario = getById(Validacao.validaEntradaInteiro("Volte a informar o numero da identificacao valido: "), funcionarios);
			if (count < 3) {
			}
		}
		return funcionario;
	}

	public static Identificacao selecionaIdentificacao(Identificacao [] identificacaos) {
		Identificacao identificacao = null;		
		int numero = Validacao.validaEntradaInteiro("Selecione o numero do tipo de identidade: ");
		for (Identificacao identificacao2 : identificacaos) {			
			if (identificacao2 != null) {
				if (identificacao2.getId() == numero) {
					identificacao = identificacao2;
				} 
			}
		}
		if (identificacao == null) {
			return selecionaIdentificacao(identificacaos);
		}
		return identificacao;
	}

	private static int gravar(Funcionario [] funcionario, Identificacao [] identificacaos) {
		int id = 0, i = Validacao.notNull(funcionario);		
		id = geradorID(i, funcionario);		
		String nome = validaNome(Validacao.validaEntradaPalavra("Informe o nome do funcionario: "),funcionario);		
		if (nome != null) {
			IdentificacaoMethods.listaIdentificacao(identificacaos);
			Identificacao identificacao = selecionaIdentificacao(identificacaos);
			if (identificacao != null) {
				String numeroIdentidade = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra("Introduza o numero de identidade: "), funcionario);
				if (numeroIdentidade != null) {
					int nuit = Validacao.validaEntradaInteiro("Introduza o numero de nuit: ");
					if (nuit != 0) {
						String morada = Validacao.validaEntradaPalavra("Introduza o endereço da morada: ");
						if (morada != null) {
							funcionario[i] = new Funcionario(id,nome,identificacao,numeroIdentidade,nuit,morada,true,LocalDateTime.now(),LocalDateTime.now());
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
		gravarDadosNoFicheiro(funcionario, filePath);
		Validacao.validaGravacao(id, "Identificacao Gravada com sucesso!");			
		return id;
	}
	

	private static boolean gravarDadosNoFicheiro(Funcionario[] funcionario, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < funcionario.length; i++) {
					if (funcionario[i] != null) {
						bw.write(funcionario[i].getId() + "|" + funcionario[i].getNome()
								+ "|" + funcionario[i].getIdentificacao().getId()
								+ "|" + funcionario[i].getNumeroIdentidade()
								+ "|" + funcionario[i].getNuit()
								+ "|" + funcionario[i].getMorada()
								+ "|" + funcionario[i].isStatus() 
								+ "|" + funcionario[i].getDataRegisto()
								+ "|" + funcionario[i].getDataActualizacao());
						bw.newLine();
						lista(funcionario);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(funcionario,file);
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
		System.out.println("************************ Actualizar dados de Funcionario **************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. Nome                                                                          *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. Tipo de Identificacao                                                         *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. Numero de Identificacao                                                       *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*4. Nuit                                                                          *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. Morada                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*6. Estado                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*7. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	private static int actualizar(Funcionario[] funcionarios, Identificacao [] identificacaos) {
		int id = 0;
		lista(funcionarios);
		boolean error = true;
		String msg;
		Funcionario funcionario = getById(Validacao.validaEntradaInteiro("Informe o numero do funcioanario: "), funcionarios);
		if (funcionario != null) {
			for (int i = 0; i < funcionarios.length; i++) {
				if (funcionarios[i] != null) {
					if (funcionarios[i].getId() == funcionario.getId()) {
						id = funcionario.getId();
						switch (menuActualizacao()) {
						case 1:
							String nome = validaNome(
									Validacao.validaEntradaPalavra("Informe o nome do funcionario: "),
									funcionarios);
							funcionarios[i].setNome(nome);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 2:
							IdentificacaoMethods.listaIdentificacao(identificacaos);
							Identificacao identificacao = selecionaIdentificacao(identificacaos);
							funcionarios[i].setIdentificacao(identificacao);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 3:
							String numeroIdentidade = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra("Introduza o numero de identidade: "),funcionarios[i].getId(), funcionarios);
							funcionarios[i].setNumeroIdentidade(numeroIdentidade);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 4:
							int nuit = Validacao.validaEntradaInteiro("Introduza o numero de nuit: ");						
							funcionarios[i].setNuit(nuit);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 5:
							String morada = Validacao.validaEntradaPalavra("Introduza o endereço da morada: ");						
							funcionarios[i].setMorada(morada);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 6:
							boolean estado = Validacao
							.validaEntradaStatus("Informe o Novo Estado Para o Cliente [Activo ou Inactivo]: ");
							funcionarios[i].setStatus(estado);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 7:
							error = false;
							break;
						default:
							lista(funcionarios);
							break;
						}
					}
				}
			} 
		}
		gravarDadosNoFicheiro(funcionarios, filePath);
		if (error) {
			msg = "Identificacao Atualizada com Sucesso!";
		}else {
			msg = "Identificacao Não Atualizada com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;
	}
	
	private static int deleta(Funcionario[] funcionarios){
		int id = 0;
		lista(funcionarios);
		boolean error = true;
		String msg;
		Funcionario funcionario = getById(Validacao.validaEntradaInteiro("Informe o numero do funcioanario: "), funcionarios);
		if (funcionario != null) {
			for (int i = 0; i < funcionarios.length; i++) {
				if (funcionarios[i] != null) {
					if (funcionarios[i].getId() == funcionario.getId()) {
						id = funcionario.getId();
						funcionarios[i] = null;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(funcionarios, filePath);				
		if (error) {
			msg = "Identificacao removida com Sucesso!";
		}else {
			msg = "Identificacao Não removida com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;		
	}
	
	private static boolean lerDadosNoFicheiro(Funcionario[] funcionario, Identificacao [] identificacoes, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Funcionario funcionarios = new Funcionario(
							Integer.parseInt(str.nextToken()),str.nextToken(),
							IdentificacaoMethods.getById(Integer.parseInt(str.nextToken()), identificacoes),str.nextToken(),Integer.parseInt(str.nextToken()),str.nextToken(),
							Boolean.parseBoolean(str.nextToken()),Validacao.parseStringToLocalDateTime(str.nextToken()),Validacao.parseStringToLocalDateTime(str.nextToken()));
					funcionario[i] = funcionarios;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(funcionario, identificacoes, file);				
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

		String [] header = new String[]{"|","#","|","Numero","|","Nome","|","Identificacao","|","Numero Identificacao","|","Nuit","|","Morada","|","Estado","|","Data Reg","|","Data Act","|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-43.43s";
		String formatNumIden = "%-20.20s",formatIdentifica = "%-15.15s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatIdentifica + " " + formatCaracter + " " + formatNumIden 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** Lista de Funcionarios** ***************************************************************");
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],
				header[11],header[12],header[13],header[14],header[15],header[16],header[17],header[18],header[19],header[20]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}

	private static void dadosImpressao(int numeracao, int i, Funcionario [] funcionarios, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,funcionarios[i].getId(),
				Validacao.delimitador,funcionarios[i].getNome(),Validacao.delimitador,funcionarios[i].getIdentificacao().getAcronimo(),
				Validacao.delimitador,funcionarios[i].getNumeroIdentidade(),Validacao.delimitador,funcionarios[i].getNuit(),
				Validacao.delimitador,funcionarios[i].getMorada(),Validacao.delimitador,Validacao.mudarStatus(funcionarios[i].isStatus()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToSring(funcionarios[i].getDataRegisto()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToSring(funcionarios[i].getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Funcionario[] funcionario) {
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();	
		for (int i = 0; i < funcionario.length; i++)
			if (funcionario[i] != null){
				dadosImpressao(numeracao, i, funcionario, layoutFormat);
				numeracao+=1;
			}else empty_ += 1; 		
		Validacao.formatoImpressaoFooter(funcionario.length,empty_);		
	}

	private static void lista(Funcionario[] funcionario, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < funcionario.length; i++) 
			if (funcionario[i] != null && funcionario[i].getId() == id) {					
				dadosImpressao(numeracao, i, funcionario, layoutFormat);
				numeracao += 1;					
			}else empty_ += 1;						
		Validacao.formatoImpressaoFooter(funcionario.length, empty_);		
	}  
	
	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("************************** Gestao de Item Funcionario *****************************");
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
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	public static void load(Funcionario [] funcionarios, Identificacao [] identificacaos) {
		Validacao.init(funcionarios);	
		lerDadosNoFicheiro(funcionarios, identificacaos, filePath);
	}
	
	/**
	 * @param funcionarios
	 * @param identificacaos
	 */	
	public static void inicializador(Funcionario [] funcionarios, Identificacao [] identificacaos) {			
		
		int caso;
		do {
			caso = menu();
			switch (caso) {
			case 1:
				gravar(funcionarios, identificacaos);
				;break;
			case 2:
				actualizar(funcionarios, identificacaos);
				;break;
			case 3:
				deleta(funcionarios);
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
	}	
}