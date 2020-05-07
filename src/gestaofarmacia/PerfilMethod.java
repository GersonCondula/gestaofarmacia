package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class PerfilMethod {
	
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
						perfil = new Perfil(perfils[i].getId(), 
								perfils[i].getPermissaoSistema(),						
								perfils[i].getUsuario());
						count ++;
					}
				}	                  
			} 	          		
		}       
		if (perfil == null) {
			perfil = getById(Validacao.validaEntradaInteiro("Volte a informar o numero de perfil valido: "), perfils);
			if (count < 3) {

			}
		}
		return perfil;
	}

	public static Usuario selecionaUsuario(Usuario [] usuarios) {
		Usuario usuario = null;		
		int numero = Validacao.validaEntradaInteiro("Selecione o numero do usuario: ");
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

	private static int gravar(Perfil[] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		int id = 0, i = Validacao.notNull(perfils);		
		id = geradorID(i, perfils);		
		PermissaoSistemaMethods.lista(permissaoSistemas);
		PermissaoSistema permissaoSistema = selecionaPermissaoSistema(permissaoSistemas);	
		if (permissaoSistema != null) {	
			UsuarioMethods.lista(usuarios);
			Usuario usuario = selecionaUsuario(usuarios);
			if (usuario != null) {
				perfils[i] = new Perfil(id, permissaoSistema, usuario);
			}			
		} else {
			id = 0;
		}		
		gravarDadosNoFicheiro(perfils,permissaoSistemas, usuarios);
		Validacao.validaGravacao(id, "Identificacao Gravada com sucesso!");			
		return id;
	}

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizar() {
		System.out.println();
		System.out.println("*************************** Actualizar dados de Perfil ***************************");
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");     		   
		System.out.println("*1. Permissao                                                                     *");
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. Cancelar                                                                      *");
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte("Indique o dado de que deseja editar:");
	}

	private static int actualizar(Perfil[] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		int id = 0;
		lista(perfils);
		boolean error = true;
		String msg;
		Perfil perfil = getById(Validacao.validaEntradaInteiro("Informe o numero do usuario: "), perfils);
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
							error = false;
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
		if (error) {
			msg = "Identificacao Atualizada com Sucesso!";
		}else {
			msg = "Identificacao Não Atualizada com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
		return id;
	}

	private static int deletar(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario[] usuarios){
		int id = 0;
		lista(perfils);
		boolean error = true;
		String msg;
		Perfil perfil = getById(Validacao.validaEntradaInteiro("Informe o numero do perfil: "), perfils);
		if (perfil != null) {
			for (int i = 0; i < perfils.length; i++) {
				if (perfils[i] != null) {
					if (perfils[i].getId() == perfil.getId()) {
						id = perfil.getId();
						perfils[i] = null;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(perfils, permissaoSistemas, usuarios);				
		if (error) {
			msg = "Identificacao removida com Sucesso!";
		}else {
			msg = "Identificacao Não removida com Sucesso!";
		}
		Validacao.validaGravacao(id, msg);
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
								+ "|" + perfils[i].getPermissaoSistema()
								+ "|" + perfils[i].getUsuario());
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
							PermissaoSistemaMethods.getById(Integer.parseInt(str.nextToken()), permissaoSistemas),
							UsuarioMethods.getById(Integer.parseInt(str.nextToken()), usuarios));
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
		String [] header = new String[]{"|","#","|","Numero","|","Permissao","|","Usuario","|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-60.60s";
		String formatAcronimo = "%-10.10s";		
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatAcronimo + " " + formatCaracter;
		System.out.println();
		System.out.println("******************************************************************** Lista de Identificacoes ***************************************************************");
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8]);
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
				perfils[i].getPermissaoSistema().getNome(),Validacao.delimitador,perfils[i].getUsuario().getFuncionario().getNome(),Validacao.delimitador);
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

	/**       
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
	private static byte menu() {

		System.out.println();
		System.out.println("************************** Gestao de Item Identificacao ***************************");
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

	public static void load(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {
		Validacao.init(perfils);	
		lerDadosNoFicheiro(perfils, permissaoSistemas, usuarios);
	}

	public static void inicializador(Perfil [] perfils, PermissaoSistema [] permissaoSistemas, Usuario [] usuarios) {			
		load(perfils, permissaoSistemas, usuarios);
		int caso;
		do {
			caso = menu();
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
