package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class PerfilMethods {
	
	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("perfil.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, Perfil[] perfils) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(perfils.length));
		int newID = 0;
		for (int i = 0; i < perfils.length; i++) 
			if (perfils[i] != null) 
				if (perfils[i].getId() == id || id == 0)
					exise = true;					
		if (exise)
			geradorID(j, perfils);
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
	public static Perfil getById(int id, Perfil [] perfils) {
		Perfil perfil = null;
		int count = 0;
		if (id!=0) {        	  
			for (int i = 0; i < perfils.length; i++) {	          		
				if (perfils[i] != null) {	                	  
					if (perfils[i].getId() == id) {	                    	  
						perfil = new Perfil(perfils[i].getId(), perfils[i].getUsuario(),perfils[i].getPermissaoSistema());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (perfil == null) {
			perfil = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), perfils);
			if (count < 3) {

			}
		}
		return perfil;
	}

	public static Usuario selecionaUsuario(Usuario [] usuarios) {
		Usuario usuario = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (Usuario usuario2 : usuarios) {			
			if (usuario2 != null) {
				if (usuario2.getId() == numero) {
					usuario = usuario2;
				} 
			}
		}
		if (usuario == null) {
			return selecionaUsuario(usuarios);
		}
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

	private static int gravar(Perfil[] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		int id = 0, i = Validacao.notNull(perfils);	
		boolean error = false;
		id = geradorID(i, perfils);		
		PermissaoSistemaMethods.lista(permissaoSistemas);
		PermissaoSistema permissaoSistema = selecionaPermissaoSistema(permissaoSistemas);	
		if (permissaoSistema != null) {	
			UsuarioMethods.lista(usuarios);
			Usuario usuario = selecionaUsuario(usuarios);
			if (usuario != null) {
				perfils[i] = new Perfil(id, usuario, permissaoSistema);
				error = true;
			}			
		} else {
			id = 0;
		}		
		gravarDadosNoFicheiro(perfils,permissaoSistemas, usuarios);
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
		System.out.println("*---------------------------------------------------------------------------------*");     		   
		System.out.println("3. "+Language.language_permissions());
		System.out.println("----------------------------------------------------------------------------------");     
		System.out.println("4. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Perfil[] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		int id = 0;
		lista(perfils);
		boolean error = false;
		Perfil perfil = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), perfils);
		if (perfil != null) {
			for (int i = 0; i < perfils.length; i++) {
				if (perfils[i] != null) {
					if (perfils[i].getId() == perfil.getId()) {
						id = perfil.getId();
						switch (menuActualizar()) {										
						case 1:
							PermissaoSistemaMethods.lista(permissaoSistemas);
							PermissaoSistema permissaoSistema = selecionaPermissaoSistema(permissaoSistemas);
							perfils[i].setPermissaoSistema(permissaoSistema);                                                                                                                                                                 							
							lista(perfils);
							break;
						case 2:
							error = true;
							break;
						default:
							actualizar(perfils, permissaoSistemas, usuarios);
							break;
						}
					}
				}
			} 
		}
		gravarDadosNoFicheiro(perfils, permissaoSistemas, usuarios);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deletar(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario[] usuarios){
		int id = 0;
		lista(perfils);
		boolean error = false;		
		Perfil perfil = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), perfils);
		if (perfil != null) {
			for (int i = 0; i < perfils.length; i++) {
				if (perfils[i] != null) {
					if (perfils[i].getId() == perfil.getId()) {
						id = perfil.getId();
						perfils[i] = null;
						error = true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(perfils, permissaoSistemas, usuarios);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean gravarDadosNoFicheiro(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario[] usuarios) {
		boolean error = false;	
		try {						
			if (new File(filePath).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < perfils.length; i++) {
					if (perfils[i] != null) {
						bw.write(perfils[i].getId() 								
								+ "|" + perfils[i].getUsuario().getId()
								+ "|" + perfils[i].getPermissaoSistema().getId());
						bw.newLine();
						lista(perfils);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(filePath);
				gravarDadosNoFicheiro(perfils, permissaoSistemas, usuarios);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static boolean lerDadosNoFicheiro(Perfil[] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(filePath).exists()) {				
				br = new BufferedReader(new FileReader(new File(filePath)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Perfil perfil = new Perfil(Integer.parseInt(str.nextToken()),							
							UsuarioMethods.getById(Integer.parseInt(str.nextToken()), usuarios),
							PermissaoSistemaMethods.getById(Integer.parseInt(str.nextToken()), permissaoSistemas));
					perfils[i] = perfil;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(filePath);
				lerDadosNoFicheiro(perfils,permissaoSistemas,usuarios);				
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
		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_user(),"|",Language.language_permissions()};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-60.60s";		
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatNome;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_profile()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return formatColl;
	}

	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Perfil [] perfils){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < perfils.length; i++){
			if (perfils[i] != null){
				dadosImpressao(numeracao, i, perfils, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(perfils.length,empty_);
	}

	private static void dadosImpressao(int numeracao, int i, Perfil [] perfils, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,perfils[i].getId(),Validacao.delimitador,
				perfils[i].getUsuario().getFuncionario().getNome(),Validacao.delimitador,perfils[i].getPermissaoSistema().getNome(),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@SuppressWarnings("unused")
	private static void lista(Perfil [] perfils, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < perfils.length; i++) {
			if (perfils[i] != null && perfils[i].getId() == id) {					
				dadosImpressao(numeracao, i, perfils, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(perfils.length, empty_);		
	}         

	public static void load(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		Validacao.init(perfils);	
		lerDadosNoFicheiro(perfils, permissaoSistemas, usuarios);
	}

	public static void inicializador(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {			
		load(perfils, permissaoSistemas, usuarios);
		int caso;
		do {
			caso = Validacao.menu(Language.language_profile());
			switch (caso) {
			case 1:
				gravar(perfils, permissaoSistemas, usuarios);
				break;
			case 2:
				actualizar(perfils, permissaoSistemas, usuarios);
				break;
			case 3:
				deletar(perfils, permissaoSistemas, usuarios);
				break;
			case 4:
				lista(perfils);
				break;			
			case 5:
				;break;
			default:

				break;
			}
		} while (caso != 5);
	}
}
