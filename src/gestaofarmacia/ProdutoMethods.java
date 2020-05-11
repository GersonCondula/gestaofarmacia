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
	private static int gravar(Produto [] produtos, Fornecedor [] fornecedors, Usuario []  usuarios, CategoriaProduto [] categoriaProdutos) {
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
									int quantidade = Validacao.validaEntradaInteiro(Language.language_input_nuit());
									if (quantidade!=0) {
										double preco = Validacao.validaEntradaFlutuante(Language.language_input_price());
										if (preco != 0) {
											date 
											if (error) {
												produtos[i] = new Produto(id, fornecedor, categoriaProduto, nome, marca,
														descricao, quantidade, preco, true, LocalDateTime.now(),
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
		System.out.println("*1. "+ Language.language_name());
		System.out.println("*---------------------------------------------------------------------------------*");     
		System.out.println("*2. "+ Language.language_brand());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*3. "+ Language.language_description());                                                     
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*4. "+ Language.language_amount());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. "+ Language.language_price());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*6. "+ Language.language_state());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*5. "+ Language.language_expirationDate());
		System.out.println("*---------------------------------------------------------------------------------*");
		System.out.println("*7. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_edit_data());
	}

	private static int actualizar(Funcionario[] funcionarios, forn [] identificacaos) {
		int id = 0;
		lista(funcionarios);
		boolean error = false;		
		Funcionario funcionario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), funcionarios);
		if (funcionario != null) {
			for (int i = 0; i < funcionarios.length; i++) {
				if (funcionarios[i] != null) {
					if (funcionarios[i].getId() == funcionario.getId()) {
						id = funcionario.getId();
						switch (menuActualizacao()) {
						case 1:
							String nome = validaNome(
									Validacao.validaEntradaPalavra(Language.language_input_name()),
									funcionarios);
							funcionarios[i].setNome(nome);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 2:
							IdentificacaoMethods.listaIdentificacao(identificacaos);
							forn identificacao = selecionaIdentificacao(identificacaos);
							funcionarios[i].setIdentificacao(identificacao);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 3:
							String numeroIdentidade = ValidaNumeroIdentificacao(Validacao.validaEntradaPalavra(Language.language_input_number()),funcionarios[i].getId(), funcionarios);
							funcionarios[i].setNumeroIdentidade(numeroIdentidade);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 4:
							int nuit = Validacao.validaEntradaInteiro(Language.language_input_nuit());						
							funcionarios[i].setNuit(nuit);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 5:
							String morada = Validacao.validaEntradaPalavra(Language.language_input_address());						
							funcionarios[i].setMorada(morada);
							funcionarios[i].setDataActualizacao(LocalDateTime.now());
							lista(funcionarios, id);
							break;
						case 6:
							boolean estado = Validacao
							.validaEntradaStatus(Language.language_input_state());
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
					error = true;
				}
			} 
		}
		gravarDadosNoFicheiro(funcionarios, filePath);		
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;
	}

	private static int deleta(Funcionario[] funcionarios){
		int id = 0;
		lista(funcionarios);
		boolean error = false;		
		Funcionario funcionario = getById(Validacao.validaEntradaInteiro(Language.language_input_id()), funcionarios);
		if (funcionario != null) {
			for (int i = 0; i < funcionarios.length; i++) {
				if (funcionarios[i] != null) {
					if (funcionarios[i].getId() == funcionario.getId()) {
						id = funcionario.getId();
						funcionarios[i] = null;
						error =  true;
					}
				}
			} 
		}		
		Validacao.destroiDirectorioFicheiro(filePath);
		gravarDadosNoFicheiro(funcionarios, filePath);						
		Validacao.validaGravacao(id, error, Language.language_save_successs(),Language.language_save_unsuccesss());
		return id;		
	}

	private static boolean lerDadosNoFicheiro(Funcionario[] funcionario, forn [] identificacoes, String file) {
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

	public static void load(Funcionario [] funcionarios, forn [] identificacaos) {
		Validacao.init(funcionarios);	
		lerDadosNoFicheiro(funcionarios, identificacaos, filePath);
	}

	/**
	 * @param funcionarios
	 * @param identificacaos
	 */	
	public static void inicializador(Funcionario [] funcionarios, forn [] identificacaos) {					
		if (Validacao.notNull(identificacaos) != 0) {
			if (Validacao.notNull(funcionarios) != 0) {
				int caso;
				do {
					caso = Validacao.menu(Language.language_employee());
					switch (caso) {
					case 1:
						gravar(funcionarios, identificacaos);
						;
						break;
					case 2:
						actualizar(funcionarios, identificacaos);
						;
						break;
					case 3:
						deleta(funcionarios);
						;
						break;
					case 4:
						lista(funcionarios);
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
				System.out.println(Language.language_empty_array(Language.language_employee()));
				gravar(funcionarios, identificacaos);
			} 
		}else {
			System.out.println(Language.language_empty_array(Language.language_identification()));
			IdentificacaoMethods.gravaIdentificacao(identificacaos);
		}
	}

}
