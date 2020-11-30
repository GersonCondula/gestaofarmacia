package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class PerfilMethods {
	
	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("perfil.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(Vector perfis) {
		boolean exise = false;
		int id = (1 + Validacao.random.nextInt(perfis.size()));
		int newID = 0;
		for (int i = 0; i < perfis.size(); i++) {
			Perfil perfil = (Perfil)perfis.elementAt(i);
			if (!perfis.isEmpty()) 
				if (perfil.getId() == id || id == 0)
					exise = true;					
		}
		if (exise)
			geradorID(perfis);
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
	public static Perfil getById(int id, Vector perfis) {
		Perfil perfil = null;
		if (id!=0) {        	  
			for (int i = 0; i < perfis.size(); i++) {
				Perfil perfil2 = (Perfil)perfis.elementAt(i);
				if (!perfis.isEmpty()) {	                	  
					if (perfil2.getId() == id) {	                    	  
						perfil = new Perfil(perfil2.getId(), perfil2.getUsuario(),perfil2.getPermissaoSistema());						
					}
				}	                  
			} 	          		
		}       
		if (perfil == null) {
			perfil = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), perfis);
		}
		return perfil;
	}

	public static Usuario selecionaUsuario(Vector usuarios) {
		Usuario usuario = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario2 = (Usuario)usuarios.elementAt(i);
			if (!usuarios.isEmpty()) {
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

	public static PermissaoSistema selecionaPermissaoSistema(Vector permissaoSistemas) {
		PermissaoSistema permissaoSistema = null;		
		int numero = Validacao.validaEntradaInteiro(Language.language_input_id());
		for (int i = 0; i< permissaoSistemas.size(); i++) {
			PermissaoSistema permissaoSistema2 = (PermissaoSistema)permissaoSistemas.elementAt(i);
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

	private static int gravar(Vector perfis) {
		int id = 0;	
		boolean error = false;
		id = geradorID(perfis);		
		PermissaoSistemaMethods.lista(perfis);
		PermissaoSistema permissaoSistema = selecionaPermissaoSistema(perfis);	
		if (permissaoSistema != null) {	
			UsuarioMethods.lista(perfis);
			Usuario usuario = selecionaUsuario(perfis);
			if (usuario != null) {
				Perfil perfil = new Perfil(id, usuario, permissaoSistema);
				Validacao.adicionar(perfis, perfil);
				error = true;
			}			
		} else {
			id = 0;
		}		
		gravarDadosNoFicheiro(perfis);
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
		System.out.println("1. "+Language.language_permissions());
		System.out.println("----------------------------------------------------------------------------------");     
		System.out.println("2. "+Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Vector perfis) {
		int id = 0;
		lista(perfis);
		boolean error = false;
		Perfil perfil = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), perfis);
		if (perfil != null) {
			for (int i = 0; i < perfis.size(); i++) {
				Perfil perfil2 = (Perfil)perfis.elementAt(i);
				if (!perfis.isEmpty()) {
					if (perfil2.getId() == perfil.getId()) {
						id = perfil.getId();
						switch (menuActualizar()) {										
						case 1:
							PermissaoSistemaMethods.lista(perfis);
							PermissaoSistema permissaoSistema = selecionaPermissaoSistema(perfis);							             
							Validacao.adicionar(perfis, permissaoSistema);
							lista(perfis);
							break;
						case 2:
							error = true;
							break;
						default:
							actualizar(perfis);
							break;
						}
					}
				}
			} 
		}
		gravarDadosNoFicheiro(perfis);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deletar(Vector perfis){
		int id = 0;
		lista(perfis);
		boolean error = false;		
		Perfil perfil = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), perfis);
		if (perfil != null) {
			for (int i = 0; i < perfis.size(); i++) {
				Perfil perfil2 = (Perfil)perfis.elementAt(i);
				if (!perfis.isEmpty()) {
					if (perfil2.getId() == perfil.getId()) {
						id = perfil.getId();
						perfis.remove(perfil);
						error = true;
					}
				}
			} 
		}
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(perfis);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean gravarDadosNoFicheiro(Vector perfis) {
		boolean error = false;	
		try {						
			if (new File(filePath).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < perfis.size(); i++) {
					Perfil perfil = (Perfil)perfis.elementAt(i);
					if (!perfis.isEmpty()) {
						bw.write(perfil.getId() 								
								+ "|" + perfil.getUsuario().getId()
								+ "|" + perfil.getPermissaoSistema().getId());
						bw.newLine();
						lista(perfis);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(filePath);
				gravarDadosNoFicheiro(perfis);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static boolean lerDadosNoFicheiro(Vector perfis) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(filePath).exists()) {				
				br = new BufferedReader(new FileReader(new File(filePath)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Perfil perfil = new Perfil(Integer.parseInt(str.nextToken()),							
							UsuarioMethods.getById(Integer.parseInt(str.nextToken()), perfis),
							PermissaoSistemaMethods.getById(Integer.parseInt(str.nextToken()), perfis));					
					Validacao.adicionar(perfis, perfil);
					linha = br.readLine();									
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(filePath);
				lerDadosNoFicheiro(perfis);				
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
	public static void lista(Vector perfis){
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < perfis.size(); i++){
			Perfil perfil = (Perfil)perfis.elementAt(i);
			if (!perfis.isEmpty()){
				dadosImpressao(numeracao, i, perfil, layoutFormat);
				numeracao+=1;
			}else{empty_ += 1; }
		}
		Validacao.formatoImpressaoFooter(perfis.size(),empty_);
	}

	private static void dadosImpressao(int numeracao, int i, Perfil perfil, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,perfil.getId(),Validacao.delimitador,
				perfil.getUsuario().getFuncionario().getNome(),Validacao.delimitador,perfil.getPermissaoSistema().getNome(),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@SuppressWarnings("unused")
	private static void lista(Vector perfis, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < perfis.size(); i++) {
			Perfil perfil = (Perfil)perfis.elementAt(i);
			if (perfil != null && perfil.getId() == id) {					
				dadosImpressao(numeracao, i, perfil, layoutFormat);
				numeracao += 1;					
			} else {
				empty_ += 1;
			}
		}			
		Validacao.formatoImpressaoFooter(perfis.size(), empty_);		
	}         

	public static void load(Vector perfis) {		
		lerDadosNoFicheiro(perfis);
	}

	public static void inicializador(Vector perfis) {			
		load(perfis);
		int caso;
		do {
			caso = Validacao.menu(Language.language_profile());
			switch (caso) {
			case 1:
				gravar(perfis);
				break;
			case 2:
				actualizar(perfis);
				break;
			case 3:
				deletar(perfis);
				break;
			case 4:
				lista(perfis);
				break;			
			case 5:
				;break;
			default:

				break;
			}
		} while (caso != 5);
	}
}
