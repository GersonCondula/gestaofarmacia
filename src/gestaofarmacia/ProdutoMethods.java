package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class ProdutoMethods {

	private static BufferedReader br;
	private static BufferedWriter bw;		
	private static String filePath = Validacao.geraDirectorioFicheiro("produto.txt");

	/**
	  @Descrição Gerador de ID e nao permite repeticao de ID's usando o metodo recursivo
	 **/
	private static int geradorID(int j, Produto[] produtos) {
		boolean existe = false;
		int id = (1 + Validacao.random.nextInt(produtos.length));
		int newID = 0;		
		for (Produto produto : produtos) 
			if(produto != null) 
				if (produto.getId() == id || id == 0) 
					existe = true;									
		if (existe)
			geradorID(j, produtos);
		else
			newID = id;
		return newID;
	}

	/**	 
	 * @Descrição Garante que os nomes sejam unicos
	 */
	private static String validaNome(String nome, Produto [] produtos) {
		String nomed = null;
		if (nome != null) {
			for (int i = 0; i < produtos.length; i++) {
				if (produtos[i] != null) {
					if (!produtos[i].getNome().equalsIgnoreCase(nome)) {
						nomed = nome;
					}else {
						nomed = validaNome(Validacao.validaEntradaPalavra(Language.language_input_exist_name()),produtos);
						i = produtos.length;
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
	 * @Descrição recebe o ID, consulta se existe e devolve um objecto
	 */
	public static Produto getById(int id, Produto [] produtos) {
		Produto produto = null;
		int count = 0;
		if (id!=0) {    
			for (Produto produto2 : produtos) {
				if (produto2 != null) {	                	  
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
						count ++;
					}
				}
			}			 	          	
		}       
		if (produto == null) {
			produto = getById(Validacao.validaEntradaInteiro(Language.language_input_invalid_number()), produtos);
			if (count < 3) {
			}
		}
		return produto;
	}

	@SuppressWarnings("unused")
	private static int gravar(Produto [] produtos, Fornecedor [] fornecedors, CategoriaProduto [] categoriaProdutos) {
		int id = 0, i = Validacao.notNull(produtos);		
		boolean error = false;
		id = geradorID(i, produtos);				
			FornecedorMethods.lista(fornecedors);
			Fornecedor fornecedor = FornecedorMethods.selecionaFornecedor(fornecedors);
			if (fornecedor != null) {
				CategoriaProdutoMethods.lista(categoriaProdutos);
				CategoriaProduto categoriaProduto = CategoriaProdutoMethods.selecionaCategoriaProduo(categoriaProdutos);
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
												produtos[i] = new Produto(id, fornecedor, categoriaProduto, nome, marca,
														descricao, quantidade, preco, true, dataValidade,
														LocalDateTime.now(), LocalDateTime.now());
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


	private static boolean gravarDadosNoFicheiro(Produto[] produtos, String file) {
		boolean error = false;	
		try {						
			if (new File(file).exists()) {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));				
				for (int i = 0; i < produtos.length; i++) {
					if (produtos[i] != null) {
						bw.write(produtos[i].getId()
								+ "|" + produtos[i].getFornecedor().getId()
								+ "|" + produtos[i].getCategoriaProduto().getId()
								+ "|" + produtos[i].getNome()					
								+ "|" + produtos[i].getMarca()
								+ "|" + produtos[i].getDescricao()
								+ "|" + produtos[i].getQuantidade()
								+ "|" + produtos[i].getPreco()
								+ "|" + produtos[i].isStatus()
								+ "|" + produtos[i].getDataValidade()
								+ "|" + produtos[i].getDataRegisto()
								+ "|" + produtos[i].getDataActualizacao());
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
	 * @Descrição Menu de atualizacao de dados de Identificacao
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
		System.out.println("*0. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Produto[] produtos, Fornecedor [] fornecedors, CategoriaProduto [] categoriaProdutos) {
		int id = 0;
		lista(produtos);
		boolean error = false;		
		Produto produto = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), produtos);
		if (produto != null) {
			for (int i = 0; i < produtos.length; i++) {
				if (produtos[i] != null) {
					if (produtos[i].getId() == produto.getId()) {
						id = produto.getId();
						switch (menuActualizacao()) {
						case 1:
							FornecedorMethods.lista(fornecedors);
							Fornecedor fornecedor = FornecedorMethods.selecionaFornecedor(fornecedors);
							produtos[i].setFornecedor(fornecedor);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 2:
							CategoriaProdutoMethods.lista(categoriaProdutos);
							CategoriaProduto categoriaProduto = CategoriaProdutoMethods.selecionaCategoriaProduo(categoriaProdutos);
							produtos[i].setCategoriaProduto(categoriaProduto);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 3:
							String nome = validaNome(Validacao.validaEntradaPalavra(Language.language_input_name()),produtos);
							produtos[i].setNome(nome);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 4:
							String marca = Validacao.validaEntradaPalavra(Language.language_input_brand());
							produtos[i].setMarca(marca);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 5:
							String descricao = Validacao.validaEntradaPalavra(Language.language_input_description());
							produtos[i].setDescricao(descricao);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 6:
							int quantidade = Validacao.validaEntradaInteiro(Language.language_input_nuit());						
							produtos[i].setQuantidade(quantidade);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 7:
							double preco = Validacao.validaEntradaFlutuante(Language.language_input_price());					
							produtos[i].setPreco(preco);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 8:
							LocalDateTime dataValidade = Validacao.validaDadosData(Language.language_input_expirationDate());
							produtos[i].setDataValidade(dataValidade);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;							
						case 9:
							boolean estado = Validacao.validaEntradaStatus(Language.language_input_state());
							produtos[i].setStatus(estado);
							produtos[i].setDataActualizacao(LocalDateTime.now());
							lista(produtos, id);
							break;
						case 0:
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

	private static int deleta(Produto[] produtos){
		int id = 0;
		lista(produtos);
		boolean error = false;		
		Produto produto = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), produtos);
		if (produto != null) {
			for (int i = 0; i < produtos.length; i++) {
				if (produtos[i] != null) {
					if (produtos[i].getId() == produto.getId()) {
						id = produto.getId();
						produtos[i] = null;
						error =  true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(produtos, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean lerDadosNoFicheiro(Produto [] produtos, Fornecedor [] fornecedors, CategoriaProduto [] categoriaProdutos, String file) {
		boolean error = false;		
		StringTokenizer str;
		try {		
			if (new File(file).exists()) {				
				br = new BufferedReader(new FileReader(new File(file)));
				String linha = br.readLine();
				int i = 0;
				while (linha != null) {					
					str = new StringTokenizer(linha, "|");
					Produto produto = new Produto(
							Integer.parseInt(str.nextToken()),
							FornecedorMethods.getById(Integer.parseInt(str.nextToken()), fornecedors),
							CategoriaProdutoMethods.getById(Integer.parseInt(str.nextToken()), categoriaProdutos),
							str.nextToken(),str.nextToken(),str.nextToken(),
							Integer.parseInt(str.nextToken()),
							Double.parseDouble(str.nextToken()),
							Boolean.parseBoolean(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()),
							Validacao.parseStringToLocalDateTime(str.nextToken()));							
					produtos[i] = produto;					
					linha = br.readLine();
					i++;					
				}
				br.close();
				error = true;				
			}else {								
				Validacao.geraDirectorioFicheiro(file);
				lerDadosNoFicheiro(produtos, fornecedors, categoriaProdutos, file);				
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

	private static void dadosImpressao(int numeracao, int i, Produto [] produtos, String layoutFormat) {
		System.out.format(layoutFormat,
				Validacao.delimitador,numeracao,
				Validacao.delimitador,produtos[i].getId(),
				Validacao.delimitador,produtos[i].getNome(),
				Validacao.delimitador,produtos[i].getMarca(),
				Validacao.delimitador,produtos[i].getCategoriaProduto().getNome(),
				Validacao.delimitador,produtos[i].getFornecedor().getNome(),
				Validacao.delimitador,produtos[i].getDescricao(),
				Validacao.delimitador,produtos[i].getQuantidade(),
				Validacao.delimitador,produtos[i].getPreco(),
				Validacao.delimitador,Validacao.mudarStatus(produtos[i].isStatus()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(produtos[i].getDataValidade()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(produtos[i].getDataRegisto()),
				Validacao.delimitador,Validacao.parseLocalDateTimeToString(produtos[i].getDataActualizacao()),
				Validacao.delimitador);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * @Descrição imprime a lista
	 */
	public static void lista(Produto[] produtos) {
		int numeracao = 1;
		int empty_= 0;
		String layoutFormat = formatoImpressao();	
		for (int i = 0; i < produtos.length; i++)
			if (produtos[i] != null){
				dadosImpressao(numeracao, i, produtos, layoutFormat);
				numeracao+=1;
			}else empty_ += 1; 		
		Validacao.formatoImpressaoFooter(produtos.length,empty_);		
	}

	private static void lista(Produto[] produtos, int id){
		int numeracao = 1;
		int empty_= 0;        
		String layoutFormat = formatoImpressao();
		for (int i = 0; i < produtos.length; i++) 
			if (produtos[i] != null && produtos[i].getId() == id) {					
				dadosImpressao(numeracao, i, produtos, layoutFormat);
				numeracao += 1;					
			}else empty_ += 1;						
		Validacao.formatoImpressaoFooter(produtos.length, empty_);		
	}  

	public static void load(Produto [] produtos, Fornecedor [] fornecedors,CategoriaProduto [] categoriaProdutos ) {
		Validacao.init(produtos);	
		lerDadosNoFicheiro(produtos, fornecedors ,categoriaProdutos , filePath);
	}

	/**
	 * @param produtos
	 * @param fornecedors
	 */	
	public static void inicializador(Produto [] produtos, Fornecedor [] fornecedors, CategoriaProduto [] categoriaProdutos) {					
		if (Validacao.notNull(fornecedors) != 0) {
			if (Validacao.notNull(categoriaProdutos) != 0) {
				if (Validacao.notNull(produtos) != 0) {
					int caso;
					do {
						caso = Validacao.menu(Language.language_employee());
						switch (caso) {
						case 1:
							gravar(produtos, fornecedors, categoriaProdutos);
							;
							break;
						case 2:
							actualizar(produtos, fornecedors, categoriaProdutos);
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
					gravar(produtos, fornecedors, categoriaProdutos);
				} 
			} else {
				System.out.println(Language.language_empty_array(Language.language_product_category()));
				CategoriaProdutoMethods.gravar(categoriaProdutos);
			}
		}else {
			System.out.println(Language.language_empty_array(Language.language_employee()));
			FornecedorMethods.gravar(fornecedors);
		}
	}

}
