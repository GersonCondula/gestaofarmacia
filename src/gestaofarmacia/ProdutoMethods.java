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
public class ProdutoMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("produto.txt");

	/**
	  @Descri��o Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(Vector produtos) {
		boolean existe = false;
		int id = (1 + Validacao.random.nextInt(produtos.size()));
		int newID = 0;		
		for (int i = 0; i < produtos.size(); i++) {
			Produto produto = (Produto)produtos.elementAt(i);
			if(!produtos.isEmpty()) 
				if (produto.getId() == id || id == 0) 
					existe = true;				
		}
		if (existe)
			geradorID(produtos);
		else
			newID = id;
		return newID;
	}

	/**	 
	 * @Descri��o Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, Vector produtos) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < produtos.size(); i++) {
				Produto produto = (Produto)produtos.elementAt(i);
				if (!produtos.isEmpty()) {
					if (!produto.getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),produtos);
						i = produtos.size();
					}
				}else {
					nomed = nome;
				}
			}
		}
		return nomed;
	}
	
	/**
	 *
	 * @param id
	 * @return
	 * @Descri��o recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Produto getById(int id, Vector produtos) {
		Produto produto = null;		
		if (id!=0) {    
			for (int i =0; i < produtos.size(); i++) {
				Produto produto2 = (Produto)produtos.elementAt(i);
				if (!produtos.isEmpty()) {	                	  
					if (produto2.getId() == id) {					
						produto = new Produto(produto2.getId(),
								produto2.getFornecedor(),								
								produto2.getCategoriaProduto(),
								produto2.getNome(), 
								produto2.getMarca(), 
								produto2.getDescricao(),
								produto2.getQuantidade(),
								produto2.getPreco(),
								produto2.isStatus(), 
								produto2.getDataValidade(),
								produto2.getDataRegisto(),
								produto2.getDataActualizacao());						
					}
				}
			}			 	          	
		}       
		if (produto == null) {
			produto = getById(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), produtos);
		}
		return produto;
	}

	@SuppressWarnings("unused")
	private static int gravar(Vector produtos) {
		int id = 0;		
		boolean error = false;
		id = geradorID(produtos);				
			FornecedorMethods.lista(produtos);
			Fornecedor fornecedor = FornecedorMethods.selecionaFornecedor(produtos);
			if (fornecedor != null) {
				CategoriaProdutoMethods.lista(produtos);
				CategoriaProduto categoriaProduto = CategoriaProdutoMethods.selecionaCategoriaProduo(produtos);
				if (categoriaProduto != null) {		
					String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()), produtos);
					if (nome!=null) {
						String marca = Validacao.validaEntradaPalavra(Language.language_input_brand());
						if (marca != null) {
							String descricao = Validacao.validaEntradaPalavra(Language.language_input_description());
							if (descricao != null) {
								if (descricao != null) {
									int quantidade = Validacao.validaEntradaInteiro(Language.language_input_amount());
									if (quantidade!=0) {
										double preco = Validacao.validaEntradaFlutuante(Language.language_input_price());
										if (preco != 0) {
											LocalDateTime dataValidade = Validacao.validaDadosData(Language.language_input_expirationDate());													
											if (dataValidade != null) {
												Produto produto= new Produto(id, fornecedor, categoriaProduto, nome, marca,
														descricao, quantidade, preco, true, dataValidade,
														LocalDateTime.now(), LocalDateTime.now());
												Validacao.adicionar(produtos, produto);
												error = true;
											}
											else {
												id = 0;
											}
										}else {
											id = 0;
										}
									}else {
										id = 0;
									}
								}else {
									id = 0;
								}
							} else {
								id = 0;
							}
						} else {
							id = 0;
						} 
					}else {
						id = 0;
					}
				} else {
					id = 0;
				}
			} else {
				id = 0;
			}		
		gravarDadosNoFicheiro(produtos, filePath);
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());		
		return id;
	}


	private static boolean gravarDadosNoFicheiro(Vector produtos, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < produtos.size(); i++) {
					Produto produto = (Produto)produtos.elementAt(i);
					if (!produtos.isEmpty()) {
						bw.write(produto.getId()
								+ "|" + produto.getFornecedor().getId()
								+ "|" + produto.getCategoriaProduto().getId()
								+ "|" + produto.getNome()					
								+ "|" + produto.getMarca()
								+ "|" + produto.getDescricao()
								+ "|" + produto.getQuantidade()
								+ "|" + produto.getPreco()
								+ "|" + produto.isStatus()
								+ "|" + produto.getDataValidade()
								+ "|" + produto.getDataRegisto()
								+ "|" + produto.getDataActualizacao());
						bw.newLine();
						lista(produtos);
					}
				}
				bw.close();
				error = true;
			}else {				
				Validacao.geraDirectorioFicheiro(file);
				gravarDadosNoFicheiro(produtos,file);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	/**       
	 * @Descri��o Menu de atualizacao de dados de Identificacao
	 */
	private static byte menuActualizacao() {
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+Language.language_update_menu());
		System.out.println("***********************************************************************************");
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*1. "+ Language.language_employee());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*2. "+ Language.language_product_category());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. "+ Language.language_name());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*4. "+ Language.language_brand());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. "+ Language.language_description());                                                     
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*6. "+ Language.language_amount());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*7. "+ Language.language_price());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*8. "+ Language.language_state());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*9. "+ Language.language_expirationDate());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*10. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Vector produtos) {
		int id = 0;
		lista(produtos);
		boolean error = false;		
		Produto produto = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), produtos);
		if (produto != null) {
			for (int i = 0; i < produtos.size(); i++) {
				Produto produto2 = (Produto)produtos.elementAt(i);
				if (!produtos.isEmpty()) {
					if (produto2.getId() == produto.getId()) {
						id = produto.getId();
						switch (menuActualizacao()) {
						case 1:
							FornecedorMethods.lista(produtos);
							Fornecedor fornecedor = FornecedorMethods.selecionaFornecedor(produtos);
							produto2.setFornecedor(fornecedor);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 2:
							CategoriaProdutoMethods.lista(produtos);
							CategoriaProduto categoriaProduto = CategoriaProdutoMethods.selecionaCategoriaProduo(produtos);
							produto2.setCategoriaProduto(categoriaProduto);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 3:
							String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),produtos);
							produto2.setNome(nome);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 4:
							String marca = Validacao.validaEntradaPalavra(Language.language_input_brand());
							produto2.setMarca(marca);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 5:
							String descricao = Validacao.validaEntradaPalavra(Language.language_input_description());
							produto2.setDescricao(descricao);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 6:
							int quantidade = Validacao.validaEntradaInteiro(Language.language_input_nuit());						
							produto2.setQuantidade(quantidade);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 7:
							double preco = Validacao.validaEntradaFlutuante(Language.language_input_price());					
							produto2.setPreco(preco);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 8:
							LocalDateTime dataValidade = Validacao.validaDadosData(Language.language_input_expirationDate());
							produto2.setDataValidade(dataValidade);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;							
						case 9:
							boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
							produto2.setStatus(estado);
							produto2.setDataActualizacao(LocalDateTime.now());
							Validacao.adicionar(produtos, produto2);
							lista(produtos, id);
							break;
						case 10:
							error = false;
							break;
						default:
							lista(produtos);
							break;
						}
					}
					error = true;
				}
			} 
		}
		gravarDadosNoFicheiro(produtos, filePath);		
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deleta(Vector produtos){
		int id = 0;
		lista(produtos);
		boolean error = false;		
		Produto produto = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), produtos);
		if (!produtos.isEmpty()) {
			for (int i = 0; i < produtos.size(); i++) {
				Produto produto2 = (Produto)produtos.elementAt(i);			
				if (produto2.getId() == produto.getId()) {
					id = produto.getId();
					produtos.remove(produto2);
					error =  true;
				}				
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(produtos, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean lerDadosNoFicheiro(Vector produtos, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();				
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Produto produto = new Produto(
							Integer.parseInt(str.nextToken()),
							FornecedorMethods.getById(Integer.parseInt(str.nextToken()), produtos),
							CategoriaProdutoMethods.getById(Integer.parseInt(str.nextToken()), produtos),
							str.nextToken(),str.nextToken(),str.nextToken(),
							Integer.parseInt(str.nextToken()),
							Double.parseDouble(str.nextToken()),
							Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));												
					Validacao.adicionar(produtos, produto);
					linha = br.readLine();					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(produtos, file);				
			}
		} catch (IOException e) {
			System.err.println("error" + e.getLocalizedMessage());		
		}		
		return error;
	}

	/**
	 *
	 * @return
	 * @Descri��o imprime o formato de cabe�alho e retorna o formato para impressao dos dados
	 */
	private static String formatoImpressao(){

		String [] header = new String[]{
				"|","#",
				"|",Language.language_id(),					
				"|",Language.language_name(),
				"|",Language.language_brand(),
				"|",Language.language_product_category(),
				"|",Language.language_employee(),
				"|",Language.language_description(),
				"|",Language.language_amount(),
				"|",Language.language_price(),
				"|",Language.language_state(),
				"|",Language.language_expirationDate(),
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
		System.out.println("\t\t\t\t\t\t\t\t\t"+Language.language_list(Language.language_employee()));
		System.out.println("************************************************************************************************************************************************************");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4],header[5],header[6],header[7],header[8],header[9],header[10],
				                     header[11],header[12],header[13],header[14],header[15],header[16],header[17],header[18],header[19],header[20],
				                     header[21],header[22],header[23],header[24],header[25],header[26]);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return formatColl;
	}

	private static void dadosImpressao(int numeracao, Produto produto, String layoutFormat) {
		System.out.format(layoutFormat,
				Validacao.delimitador,numeracao,
				Validacao.delimitador,produto.getId(),
				Validacao.delimitador,produto.getNome(),
				Validacao.delimitador,produto.getMarca(),
				Validacao.delimitador,produto.getCategoriaProduto().getNome(),
				Validacao.delimitador,produto.getFornecedor().getNome(),
				Validacao.delimitador,produto.getDescricao(),
				Validacao.delimitador,produto.getQuantidade(),
				Validacao.delimitador,produto.getPreco(),
				Validacao.delimitador,Validacao.mudarStatus(produto.isStatus()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(produto.getDataValidade()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(produto.getDataRegisto()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(produto.getDataActualizacao()),
				Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * @Descri��o imprime a lista completa
	 */
	public static void lista(Vector produtos) {
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();	
		for (int i = 0; i < produtos.size(); i++) {
			Produto produto = (Produto)produtos.elementAt(i);
			if (!produtos.isEmpty()){
				dadosImpressao(numeracao, produto, layoutFormat);
				numeracao+=1;
			}else empty_ += 1; 		
		}
		Validacao.formatoImpressaoFooter(produtos.size(),empty_);		
	}

	/**
	 * @Descri��o imprime a lista de um determinado produto
	 */
	private static void lista(Vector produtos, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < produtos.size(); i++) {
			Produto produto = (Produto)produtos.elementAt(i);
			if (!produtos.isEmpty() && produto.getId() == id) {					
				dadosImpressao(numeracao, produto, layoutFormat);
				numeracao += 1;					
			}else empty_ += 1;					
		}
		Validacao.formatoImpressaoFooter(produtos.size(), empty_);		
	}  

	/**
	 * @Descri��o Carregar os dadods do ficheiro para o array de objectos ao iniciar a applica��o
	 */
	public static void load(Vector produtos ) {		
		lerDadosNoFicheiro(produtos, filePath);
	}

	/**
	 * @param produtos
	 * @param fornecedors
	 */	
	public static void inicializador(Vector produtos) {					
		if ((Fornecedor)produtos.firstElement() != null) {
			if ((CategoriaProduto)produtos.firstElement() != null) {
				if (!produtos.isEmpty()) {
					int caso;
					do {
						caso = Validacao.menu(Language.language_employee());
						switch (caso) {
						case 1:
							gravar(produtos);
							;
							break;
						case 2:
							actualizar(produtos);
							;
							break;
						case 3:
							deleta(produtos);
							;
							break;
						case 4:
							lista(produtos);
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
					System.out.println(Language.language_empty_array(Language.language_product()));
					gravar(produtos);
				} 
			} else {
				System.out.println(Language.language_empty_array(Language.language_product_category()));
				CategoriaProdutoMethods.gravar(produtos);
			}
		}else {
			System.out.println(Language.language_empty_array(Language.language_employee()));
			FornecedorMethods.gravar(produtos);
		}
	}

}
