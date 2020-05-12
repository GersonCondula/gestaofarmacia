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
								usuarios[i].getPassword(),
								usuarios[i].isStatus(),usuarios[i].getDataRegisto(), 
								usuarios[i].getDataActualizacao());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (usuario == null) {
			usuario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), usuarios);
			if (count < 3) {

			}
		}
		return usuario;
	}

	public static Usuario selecionaUsuario(Usuario [] usuarios) {
		Usuario usuario = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (Usuario usuario2 : usuarios)			
			if (usuario2 != null) 
				if (usuario2.getId() == numero) 
					usuario = usuario2;									
		if (usuario == null) 
			return selecionaUsuario(usuarios);		
		return usuario;
	}

	public static PermissaoSistema selecionaPermissaoSistema(PermissaoSistema [] permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
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
		boolean error =  false;
		id = geradorID(i, usuarios);		
		FuncionarioMethods.lista(funcionarios);
		Funcionario funcionario = FuncionarioMethods.selecionaFuncionario(funcionarios);	
		if (funcionario != null) {
			String password = Validacao.validaEntradaPalavra(Language.language_input_password());
			if (password != null) {
				usuarios[i] = new Usuario(id, funcionario,password ,true, LocalDateTime.now(), LocalDateTime.now());
				error = true;
			} else {
				id = 0;
			}
			
		} else {
			id = 0;
		}		
		gravarDadosNoFicheiro(usuarios, filePath);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());				
		return id;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizar() {
		System.out.println();
		System.out.println("*************************** "+ Language.language_update_menu() +" ***************************");
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");     		   
		System.out.println("3. "+Language.language_state());
		System.out.println("----------------------------------------------------------------------------------");     
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Usuario[] usuarios) {
		int id = 0;
		lista(usuarios);
		boolean error = false;		
		Usuario identificacao = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), usuarios);
		if (identificacao != null) {
			for (int i = 0; i < usuarios.length; i++) {
				if (usuarios[i] != null) {
					if (usuarios[i].getId() == identificacao.getId()) {
						id = identificacao.getId();
						switch (menuActualizar()) {										
						case 1:
							boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
							usuarios[i].setStatus(estado);
							usuarios[i].setDataActualizacao(LocalDateTime.now());
							lista(usuarios, id);
							break;
						case 2:
							error = true;
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
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deletar(Usuario[] usuarios){
		int id = 0;
		lista(usuarios);
		boolean error = false;		
		Usuario usuario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), usuarios);
		if (usuario != null) {
			for (int i = 0; i < usuarios.length; i++) {
				if (usuarios[i] != null) {
					if (usuarios[i].getId() == usuario.getId()) {
						id = usuario.getId();
						usuarios[i] = null;
						error = true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(usuarios, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
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
								+ "|" + usuarios[i].getFuncionario().getId()
								+ "|" + usuarios[i].getPassword() 
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
							FuncionarioMethods.getById(Integer.parseInt(str.nextToken()), funcionarios),
							str.nextToken(), Boolean.parseBoolean(str.nextToken()),
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
		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_employee(),"|",Language.language_password(),"|",Language.language_state(),
									    "|",Language.language_dateRegistration(),"|",Language.language_updateDate(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-35.35s";
		String formatEstado = "%-8.10s", formatPassword = "%-25.25s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatPassword + " " + formatCaracter + " " + formatEstado 
				+ " " + formatCaracter + " " + formatData + " " + formatCaracter + " " + formatDataLast;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_user()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],header[11],header[12],header[13]);
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

	private static void dadosImpressao(int numeracao, int i, Usuario [] usuarios, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,usuarios[i].getId(),Validacao.delimitador,
				usuarios[i].getFuncionario().getNome(),Validacao.delimitador,usuarios[i].getPassword(),
				Validacao.delimitador,Validacao.mudarStatus(usuarios[i].isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(usuarios[i].getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(usuarios[i].getDataActualizacao()),Validacao.delimitador);
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

	public static void load(Usuario [] usuarios, Funcionario [] funcionarios) {
		Validacao.init(usuarios);	
		lerDadosNoFicheiro(usuarios, funcionarios, filePath);
	}

	public static void inicializador(Usuario [] usuarios, Funcionario [] funcionarios, Identificacao [] identificacaos) {			
		load(usuarios, funcionarios);
		int caso;
		if (Validacao.notNull(funcionarios) != 0) {
			do {
				caso = Validacao.menu(Language.language_user());
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
