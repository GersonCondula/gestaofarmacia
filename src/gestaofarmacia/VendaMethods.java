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
public class VendaMethods {
	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePathVenda = Validacao.geraDirectorioFicheiro("venda.txt");
	private static String filePathItemVenda = Validacao.geraDirectorioFicheiro("itemVenda.txt");	

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(Vector vendas) {
		int id = 0, iD = 0;	
		if (vendas.isEmpty()) {
			id = 1;
		}else{					
			int novoId = (Validacao.random.nextInt(vendas.size()+1));
			id = (2 + novoId);		
			for (int i = 0; i < vendas.size(); i++) {
				Venda venda = (Venda)vendas.elementAt(i);
				if (!vendas.isEmpty()) {
					if (venda.getId() == id || id == 0)
						geradorID(vendas);
					else
						iD = id;
				}				
			}	
		}
		return iD;
	}
	
	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID_itemVenda(Vector itemVenda) {
		int id = 0, iD = 0;	
		if (itemVenda.isEmpty()) {
			id = 1;
		}else{					
			int novoId = (Validacao.random.nextInt(itemVenda.size()+1));
			id = (2 + novoId);		
			for (int i = 0; i < itemVenda.size(); i++) {
				Venda venda = (Venda)itemVenda.elementAt(i);
				if (!itemVenda.isEmpty()) {
					if (venda.getId() == id || id == 0)
						geradorID(itemVenda);
					else
						iD = id;
				}				
			}	
		}
		return iD;
	}

	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Venda getById(int id, Vector vendas) {
		Venda venda = null;		
		if (id!=0) {    	
			for (int i = 0; i < vendas.size(); i++) {
				Venda venda2 = (Venda)vendas.elementAt(i);				
				if (!vendas.isEmpty()) {	                	  
					if (venda2.getId() == id) {
						venda = new Venda(venda2.getId(),
								venda2.getUsuario(), 								
								venda2.getDataRegistoVenda(), venda2.getDataActualizacaoVenda());						
					}
				}
			}			 	          	
		}       
		if (venda == null) {
			venda = getById(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), vendas);	
		}
		return venda;
	}
	
	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static ItemVenda getById_itemVenda(int id, Vector itemVendas) {
		ItemVenda itemVenda = null;		
		if (id!=0) {    	
			for (int i = 0; i < itemVendas.size(); i++) {
				ItemVenda itemVenda2 = (ItemVenda)itemVendas.elementAt(i);				
				if (!itemVendas.isEmpty()) {	                	  
					if (itemVenda2.getId() == id) {
						itemVenda = new ItemVenda(itemVenda2.getId(),
								itemVenda2.getVenda(), itemVenda2.getProduto(), itemVenda.getQuantidade(),								
								itemVenda2.getDataActualizacao());					
					}
				}
			}			 	          	
		}       
		if (itemVenda == null) {
			itemVenda = getById_itemVenda(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), itemVendas);	
		}
		return itemVenda;
	}

	private static int gravar(Vector vendas, Vector itemVendas, Vector produtos, Vector usuarios) {		
		boolean error = false;
		int id = geradorID(vendas);		
		int id_item = geradorID_itemVenda(itemVendas);				
		Venda venda = new Venda(id, ExecutaSGF.userLogado, LocalDateTime.now(), LocalDateTime.now());
		Validacao.adicionar(vendas, venda);
		int quantidade = Validacao.validaEntradaInteiro("Quantos produtos deseja?: ");
		for (int i = 0; i < quantidade; i++) {
			ProdutoMethods.lista(produtos);
			Produto produto = ProdutoMethods.selecionaProduto(produtos);										
			ItemVenda itemVenda = new ItemVenda(id_item, venda, produto, quantidade, LocalDateTime.now());
			Validacao.adicionar(itemVendas, itemVenda);
		}																		
		error = true;						
		gravarDadosNoFicheiro(vendas, itemVendas, filePathVenda);
		gravarDadosItemVendaNoFicheiro(vendas, itemVendas, filePathItemVenda);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());		
		return id;
	}


	private static boolean gravarDadosNoFicheiro(Vector vendas, Vector itemVendas, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePathVenda)));				
				for (int i = 0; i < vendas.size(); i++) {
					Venda venda = (Venda)vendas.elementAt(i);
					if (!vendas.isEmpty()) {
						bw.write(venda.getId() 								
								+ "|" + venda.getUsuario().getId()								
								+ "|" + venda.getDataRegistoVenda()
								+ "|" + venda.getDataActualizacaoVenda());
						bw.newLine();		
						lista(vendas, itemVendas);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(vendas, itemVendas, file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}
	
	private static boolean gravarDadosItemVendaNoFicheiro(Vector vendas, Vector itemVendas, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePathItemVenda)));				
				for (int i = 0; i < vendas.size(); i++) {
					ItemVenda itemVenda = (ItemVenda)itemVendas.elementAt(i);
					if (!itemVendas.isEmpty()) {
						bw.write(itemVenda.getId() 								
								+ "|" + itemVenda.getVenda().getId()								
								+ "|" + itemVenda.getProduto().getId()
								+ "|" + itemVenda.getQuantidade()
								+ "|" + itemVenda.getDataActualizacao());
						bw.newLine();		
						lista(vendas, itemVendas);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(vendas, itemVendas, file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}


	private static boolean lerDadosNoFicheiro(Vector vendas, Vector usuarios, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					int id = Integer.parseInt(str.nextToken());					
					int idusuario = Integer.parseInt(str.nextToken());
					Usuario usuario = UsuarioMethods.getById(idusuario, usuarios);					
					LocalDateTime dRegisto = Validacao.parseStringToLocalDateTime(str.nextToken());
					LocalDateTime dAtualiza = Validacao.parseStringToLocalDateTime(str.nextToken());
					Venda venda = new Venda(id, usuario, dRegisto, dAtualiza);
					Validacao.adicionar(vendas, venda);					
					linha = br.readLine();									
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(vendas, usuarios, file);				
			}
		} catch (IOException e) {
			System.err.println("error" + e.getLocalizedMessage());		
		}		
		return error;
	}
	
	private static boolean lerDadosItemVendaNoFicheiro(Vector vendas, Vector itemVendas, Vector produtos, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					int id = Integer.parseInt(str.nextToken());					
					int idVenda = Integer.parseInt(str.nextToken());
					Venda venda = getById(idVenda, vendas);	
					int idProduto = Integer.parseInt(str.nextToken());
					Produto produto = ProdutoMethods.getById(idProduto, produtos);
					int quantidade =  Integer.parseInt(str.nextToken());
					LocalDateTime dAtualiza = Validacao.parseStringToLocalDateTime(str.nextToken());
					ItemVenda itemVenda = new ItemVenda(id, venda, produto, quantidade, dAtualiza);
					Validacao.adicionar(itemVendas, itemVenda);					
					linha = br.readLine();									
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosItemVendaNoFicheiro(vendas, itemVendas, produtos, file);				
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
	@SuppressWarnings("unused")
	private static String formatoImpressao(){

		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_name(),"|",Language.language_dateRegistration(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-43.43s";
		String formatNumIden = "%-20.20s",formatIdentifica = "%-15.15s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter + " " + formatCaracter + " " + formatNumero + " " + formatCaracter
				+ " " + formatNome + " " + formatCaracter + " " + formatIdentifica + " " + formatCaracter;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_employee()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}
	
	@SuppressWarnings("unused")
	private static String formatoImpressaoUp(){

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
	
	@SuppressWarnings("unused")
	private static String formatoImpressaoDown(){

		String [] header = new String[]{"|","#","|",Language.language_id(),"|",Language.language_name(),"|"};
		String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome = "%-43.43s";
		String formatNumIden = "%-20.20s",formatIdentifica = "%-15.15s";
		String formatData = "%-19.19s", formatDataLast = "%-26.20s";
		String formatColl = formatCaracter + " " + formatCaracter;
		System.out.println();
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_employee()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}

	private static void dadosImpressaoVenda(int numeracao, Venda venda, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,venda.getId(),
				Validacao.delimitador,venda.getUsuario().getUsername(),Validacao.delimitador,venda.getDataRegistoVenda(),
				Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}	
	
	private static void dadosImpressaoItemVenda(int numeracao, ItemVenda itemvenda, String layoutFormat) {
		System.out.format(layoutFormat,Validacao.delimitador,numeracao,Validacao.delimitador,itemvenda.getId(),
				Validacao.delimitador,itemvenda.getProduto().getNome(),Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}	
	
	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Vector vendas, Vector itemVendas) {
		int numeracao = 1;
		int empty_= 0;
		Venda venda = null;
		String layoutFormatUp = formatoImpressaoUp();
		String layoutFormatDown = formatoImpressaoDown();
		if (!vendas.isEmpty()) {
			if (!itemVendas.isEmpty()) {
				for (int i = 0; i < vendas.size(); i++) {
					venda = (Venda)vendas.elementAt(i);			
					dadosImpressaoVenda(numeracao, venda, layoutFormatUp);					
					for (int j = 0; j < itemVendas.size(); j++) {
						ItemVenda itemVenda = (ItemVenda)itemVendas.elementAt(j);							
						if (venda.getId() == itemVenda.getVenda().getId()) {
							dadosImpressaoItemVenda(numeracao, itemVenda, layoutFormatDown);
							numeracao+=1;
						}											
					}														
				}
			}else empty_ += 1; 	
		}else empty_ += 1; 			
		Validacao.formatoImpressaoFooter(vendas.size(),empty_);		
	}

	@SuppressWarnings("unused")
	private static void lista(Vector vendas, Vector itemVendas, int id){		
		int numeracao = 1;
		int empty_= 0;
		Venda venda = null;
		String layoutFormatUp = formatoImpressaoUp();
		String layoutFormatDown = formatoImpressaoDown();
		if (!vendas.isEmpty()) {
			if (!itemVendas.isEmpty()) {				
				venda = getById(id, vendas);		
				dadosImpressaoVenda(numeracao, venda, layoutFormatUp);					
				for (int j = 0; j < itemVendas.size(); j++) {
					ItemVenda itemVenda = (ItemVenda)itemVendas.elementAt(j);							
					if (venda.getId() == itemVenda.getVenda().getId()) {
						dadosImpressaoItemVenda(numeracao, itemVenda, layoutFormatDown);
						numeracao+=1;
					}											
				}																		
			}else empty_ += 1; 	
		}else empty_ += 1; 			
		Validacao.formatoImpressaoFooter(vendas.size(),empty_);	
	}  

	public static void load(Vector vendas, Vector usuarios, Vector itemVendas, Vector produtos) {		
		lerDadosNoFicheiro(vendas, usuarios, filePathVenda);
		lerDadosItemVendaNoFicheiro(vendas, itemVendas, produtos, filePathItemVenda);
	}
	
	/**
	 * @param vendas
	 * @param identificacaos
	 */	
	public static void inicializador(Vector vendas, Vector itemVendas, Vector produtos, Vector usuarios) {		
		int caso;
		do {
			caso = Validacao.menu(Language.language_employee());
			switch (caso) {
			case 1:
				gravar(vendas, itemVendas, produtos, usuarios);
				;break;
			case 2:
				
				;break;
			case 3:
				
				;break;
			case 4:
				lista(vendas, itemVendas);
				;break;
			case 5:
				;break;
			default:
				break;
			}
		} while (caso != 5);			
	}
}
