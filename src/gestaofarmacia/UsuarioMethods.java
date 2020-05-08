package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class UsuarioMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("usuario.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, Usuario[] usuarios) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(usuarios.length));
		int newID = 0;
		for (int i = 0; i < usuarios.length; i++) 
			if (usuarios[i] != null) 
				if (usuarios[i].getId() == id || id == 0)
					exise = true;					
		if (exise)
			geradorID(j, usuarios);
		else
			newID = id;
		return newID;
	}


	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Usuario getById(int id, Usuario [] usuarios) {
		Usuario usuario = null;
		int count = 0;
		if (id!=0) {        	  
			for (int i = 0; i < usuarios.length; i++) {	          		
				if (usuarios[i] != null) {	                	  
					if (usuarios[i].getId() == id) {	                    	  
						usuario = new Usuario(usuarios[i].getId(), 
								usuarios[i].getFuncionario(),
								usuarios[i].isStatus(),usuarios[i].getDataRegisto(), 
								usuarios[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (usuario == null) {
			usuario = getById(Validacao.validaEntradaInteiro("Volte a informar o numero de funcionario valido: "), usuarios);
			if (count < 3) {

			}
		}
		return usuario;
	}

	public static Funcionario selecionaFuncionario(Funcionario [] funcionarios) {
		Funcionario funcionario = null;		
		int numero = Validacao.validaEntradaInteiro("Selecione o numero do funcionario: ");
		for (Funcionario funcionario2 : funcionarios) {			
			if (funcionario2 != null) {
				if (funcionario2.getId() == numero) {
					funcionario = funcionario2;
				} 
			}
		}
		if (funcionario == null) {
			return selecionaFuncionario(funcionarios);
		}
		return funcionario;
	}

	public static PermissaoSistema selecionaPermissaoSistema(PermissaoSistema [] permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;		
		int numero = Validacao.validaEntradaInteiro("Selecione o numero da permissao: ");
		for (PermissaoSistema permissaoSistema2 : permissaoSistemas) {			
			if (permissaoSistema2 != null) {
				if (permissaoSistema2.getId() == numero) {
					permissaoSistema = permissaoSistema2;
				} 
			}
		}
		if (permissaoSistema == null) {
			return selecionaPermissaoSistema(permissaoSistemas);
		}
		return permissaoSistema;
	}

	private static int gravar(Usuario[] usuarios, Funcionario [] funcionarios) {
		int id = 0, i = Validacao.notNull(usuarios);		
		id = geradorID(i, usuarios);		
		FuncionarioMethods.lista(funcionarios);
		Funcionario funcionario = selecionaFuncionario(funcionarios);	
		if (funcionario != null) {			
			usuarios[i] = new Usuario(id, funcionario, true, LocalDateTime.now(), LocalDateTime.now());			
		} else {
			id = 0;
		}		
		gravarDadosNoFicheiro(usuarios, filePath);
		Validacao.validaGravacao(id, "Identificacao Gravada com sucesso!");			
		return id;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizar() {
		System.out.println();
		System.out.println("*************************** Actualizar dados de Usuario ***************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");     		   
		System.out.println("*1. Estado                                                                        *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	private static int actualizar(Usuario[] usuarios) {
		int id = 0;
		lista(usuarios);
		boolean error = true;
		String msg;
		Usuario identificacao = getById(Validacao.validaEntradaInteiro("Informe o numero do usuario: "), usuarios);
		if (identificacao != null) {
			for (int i = 0; i < usuarios.length; i++) {
				if (usuarios[i] != null) {
					if (usuarios[i].getId() == identificacao.getId()) {
						id = identificacao.getId();
						switch (menuActualizar()) {										
						case 1:
							boolean estado = Validacao.validaEntradaStatus("Informe o Novo Estado Para o Cliente [Activo ou Inactivo]: ");
							usuarios[i].setStatus(estado);
							usuarios[i].setDataActualizacao(LocalDateTime.now());
							lista(usuarios, id);
							break;
						case 2:
							error = false;
							break;
						default:
							actualizar(usuarios);
							break;
						}
					}
				}
			} 
		}
		gravarDadosNoFicheiro(usuarios, filePath);
		if (error) {
			msg = "Identificacao Atualizada com Sucesso!";
		}else {
			msg = "Identificacao Não Atualizada com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;
	}

	private static int deletar(Usuario[] usuarios){
		int id = 0;
		lista(usuarios);
		boolean error = true;
		String msg;
		Usuario usuario = getById(Validacao.validaEntradaInteiro("Informe o numero do usuario: "), usuarios);
		if (usuario != null) {
			for (int i = 0; i < usuarios.length; i++) {
				if (usuarios[i] != null) {
					if (usuarios[i].getId() == usuario.getId()) {
						id = usuario.getId();
						usuarios[i] = null;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(usuarios, filePath);				
		if (error) {
			msg = "Identificacao removida com Sucesso!";
		}else {
			msg = "Identificacao Não removida com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;		
	}

	private static boolean gravarDadosNoFicheiro(Usuario[] usuarios, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < usuarios.length; i++) {
					if (usuarios[i] != null) {
						bw.write(usuarios[i].getId() 
								+ "|" + usuarios[i].getFuncionario()
								+ "|" + usuarios[i].isStatus() 
								+ "|" + usuarios[i].getDataRegisto()
								+ "|" + usuarios[i].getDataActualizacao());
						bw.newLine();
						lista(usuarios);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(usuarios,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static boolean lerDadosNoFicheiro(Usuario[] usuarios, Funcionario [] funcionarios, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Usuario identificacao = new Usuario(Integer.parseInt(str.nextToken()),
							FuncionarioMethods.getById(Integer.parseInt(str.nextToken()), funcionarios), Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));
					usuarios[i] = identificacao;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(usuarios, funcionarios, file);				
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
		String [] header = new String[]{"|","#","|","Numero","|","Funcionario","|","Estado","|","Data Reg","|","Data Act","|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-60.60s";
		String formatEstado = "%-8.10s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatEstado 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** Lista de Identificacoes ***************************************************************");
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
	public static void lista(Usuario [] usuarios){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < usuarios.length; i++){
			if (usuarios[i] != null){
				dadosImpressao(numeracao, i, usuarios, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(usuarios.length,empty_);
	}

	private static void dadosImpressao(int numeracao, int i, Usuario [] identificacaos, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,identificacaos[i].getId(),Validacao.delimitador,
				identificacaos[i].getFuncionario().getNome(),Validacao.delimitador,Validacao.mudarStatus(identificacaos[i].isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToSring(identificacaos[i].getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToSring(identificacaos[i].getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static void lista(Usuario [] usuarios, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null && usuarios[i].getId() == id) {					
				dadosImpressao(numeracao, i, usuarios, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(usuarios.length, empty_);		
	}         

	/**       
	 * @Descrição Menu de atualizacao de dados
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("******************************* Gestao de Usuarios *******************************");
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
		return Validacao.validaEntradaByte("Selecione uma opcao:");
	}

	public static void load(Usuario [] usuarios, Funcionario [] funcionarios) {
		Validacao.init(usuarios);	
		lerDadosNoFicheiro(usuarios, funcionarios, filePath);
	}

	public static void inicializador(Usuario [] usuarios, Funcionario [] funcionarios, Identificacao [] identificacaos) {			
		load(usuarios, funcionarios);
		int caso;
		if (Validacao.notNull(funcionarios) != 0) {
			do {
				caso = menu();
				switch (caso) {
				case 1:
					gravar(usuarios, funcionarios);
					;
					break;
				case 2:
					actualizar(usuarios);
					;
					break;
				case 3:
					deletar(usuarios);
					;
					break;
				case 4:
					lista(usuarios);
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
			FuncionarioMethods.inicializador(funcionarios, identificacaos);
			gravar(usuarios, funcionarios);
		}
	}


}
