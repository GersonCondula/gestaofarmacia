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
public class UsuarioMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("usuario.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(Vector usuarios) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(usuarios.size()));
		int newID = 0;
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = (Usuario)usuarios.elementAt(i);
			if (!usuarios.isEmpty()) 
				if (usuario.getId() == id || id == 0)
					exise = true;					
		}
		if (exise)
			geradorID(usuarios);
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
	public static Usuario getById(int id, Vector usuarios) {
		Usuario usuario = null;		
		if (id!=0) {        	  
			for (int i = 0; i < usuarios.size(); i++) {	 
				Usuario usuario2 = (Usuario)usuarios.elementAt(i);
				if (!usuarios.isEmpty()) {	                	  
					if (usuario2.getId() == id) {	                    	  
						usuario = new Usuario(usuario2.getId(), 
								usuario2.getFuncionario(),
								usuario2.getPassword(),
								usuario2.isStatus(),
								usuario2.getDataRegisto(), 
								usuario2.getDataActualizacao());						
					}
				}	                  
			} 	          		
		}       
		if (usuario == null) {
			usuario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), usuarios);
		}
		return usuario;
	}

	public static Usuario selecionaUsuario(Vector usuarios) {
		Usuario usuario = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario2 = (Usuario)usuarios.elementAt(i);
			if (!usuarios.isEmpty()) 
				if (usuario2.getId() == numero) 
					usuario = usuario2;				
		}
		if (usuario == null) 
			return selecionaUsuario(usuarios);		
		return usuario;
	}

	public static PermissaoSistema selecionaPermissaoSistema(Vector permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i < permissaoSistemas.size(); i++) {
			PermissaoSistema permissaoSistema2 = (PermissaoSistema)permissaoSistema;
			if (!permissaoSistemas.isEmpty()) {
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
	
	private static int gravar(Vector usuarios) {			
		boolean error =  false;
		int id = geradorID(usuarios);		
		FuncionarioMethods.lista(usuarios);
		Funcionario funcionario = FuncionarioMethods.selecionaFuncionario(usuarios);	
		if (funcionario != null) {
			String password = Validacao.validaEntradaPalavra(Language.language_input_password());
			if (password != null) {
				Usuario usuario = new Usuario(id, funcionario,password ,true, LocalDateTime.now(), LocalDateTime.now());
				Validacao.adicionar(usuarios, usuario);
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

	private static int actualizar(Vector usuarios) {
		int id = 0;
		lista(usuarios);
		boolean error = false;		
		Usuario identificacao = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), usuarios);
		if (identificacao != null) {
			for (int i = 0; i < usuarios.size(); i++) {
				Usuario usuario = (Usuario)usuarios.elementAt(i);
				if (!usuarios.isEmpty()) {
					if (usuario.getId() == identificacao.getId()) {
						id = identificacao.getId();
						switch (menuActualizar()) {										
						case 1:
							boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
							usuario.setStatus(estado);
							usuario.setDataActualizacao(LocalDateTime.now());
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

	private static int deletar(Vector usuarios){
		int id = 0;
		lista(usuarios);
		boolean error = false;		
		Usuario usuario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), usuarios);
		if (usuario != null) {
			for (int i = 0; i < usuarios.size(); i++) {
				Usuario usuario2 = (Usuario)usuarios.elementAt(i);
				if (!usuarios.isEmpty()) {
					if (usuario2.getId() == usuario.getId()) {
						id = usuario.getId();
						usuarios.remove(usuario);
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

	private static boolean gravarDadosNoFicheiro(Vector usuarios, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < usuarios.size(); i++) {
					Usuario usuario = (Usuario)usuarios.elementAt(i);
					if (!usuarios.isEmpty()) {
						bw.write(usuario.getId() 
								+ "|" + usuario.getFuncionario().getId()
								+ "|" + usuario.getPassword() 
								+ "|" + usuario.isStatus() 
								+ "|" + usuario.getDataRegisto()
								+ "|" + usuario.getDataActualizacao());
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

	public static boolean lerDadosNoFicheiro(Vector usuarios, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();			
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Usuario usuario = new Usuario(Integer.parseInt(str.nextToken()),
							FuncionarioMethods.getById(Integer.parseInt(str.nextToken()), usuarios),
							str.nextToken(), Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));								
					Validacao.adicionar(usuarios, usuario);
					linha = br.readLine();						
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(usuarios, file);				
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
	public static void lista(Vector usuarios){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < usuarios.size(); i++){
			Usuario usuario = (Usuario)usuarios.elementAt(i);
			if (!usuarios.isEmpty()){
				dadosImpressao(numeracao, i, usuario, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(usuarios.size(), empty_);
	}

	private static void dadosImpressao(int numeracao, int i, Usuario usuario, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,usuario.getId(),Validacao.delimitador,
				usuario.getFuncionario().getNome(),Validacao.delimitador,usuario.getPassword(),
				Validacao.delimitador,Validacao.mudarStatus(usuario.isStatus()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(usuario.getDataRegisto()),Validacao.delimitador,
				Validacao.parseLocalDateTimeToString(usuario.getDataActualizacao()),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static void lista(Vector usuarios, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = (Usuario)usuarios.elementAt(i);
			if (!usuarios.isEmpty() && usuario.getId() == id) {					
				dadosImpressao(numeracao, i, usuario, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(usuarios.size(), empty_);		
	}         

	public static void load(Vector usuarios) {
		lerDadosNoFicheiro(usuarios, filePath);
	}

	public static void inicializador(Vector usuarios) {			
		load(usuarios);
		int caso;
		if (!usuarios.isEmpty()) {
			do {
				caso = Validacao.menu(Language.language_user());
				switch (caso) {
				case 1:
					gravar(usuarios);
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
			FuncionarioMethods.inicializador(usuarios);
			gravar(usuarios);
		}
	}
}
