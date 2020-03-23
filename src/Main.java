/*
 * import java.io.*; import java.net.IDN; import java.time.LocalDateTime; import
 * java.time.format.DateTimeFormatter; import java.time.format.FormatStyle;
 * import java.util.Random; import java.util.StringTokenizer;
 * 
 * public class Main {
 *//**
	 * #################################### Area de declaração de variaveis
	 * ####################################
	 */
/*
 * 
 * private static BufferedReader br = new BufferedReader(new
 * InputStreamReader(System.in)); private static DateTimeFormatter
 * dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
 * private static Random random = new Random(); private static final int tamanho
 * = 10000; private static byte options;
 * 
 *//**
	 * declaracao de arrays de dados de tipo de identificacao pessoa
	 */
/*
 * private static int [] idIdentificacao = new int[tamanho]; private static
 * String [] nomeIdentificacao = new String[tamanho]; private static boolean []
 * statusIdentificacao = new boolean[tamanho]; private static LocalDateTime []
 * dataRegistoIdentificacao = new LocalDateTime[tamanho]; private static
 * LocalDateTime [] dataActualizacaoIdentificacaos = new LocalDateTime[tamanho];
 * 
 *//**
	 * declaracao de arrays de dados da categoria funcionario
	 */
/*
 * private static int [] idCategoriaFuncionario = new int[tamanho]; private
 * static String [] nomeCategoriraFuncionario = new String[tamanho]; private
 * static boolean [] statusCategoriraFuncionario = new boolean[tamanho]; private
 * static LocalDateTime [] dataRegistoCategoriraFuncionario = new
 * LocalDateTime[tamanho]; private static LocalDateTime []
 * dataActualizacaoCategoriraFuncionario = new LocalDateTime[tamanho];
 * 
 *//**
	 * declaracao de arrays de dados do funcionario
	 */
/*
 * private static int [] idFuncionario = new int[tamanho]; private static String
 * [] nomeFuncionario = new String[tamanho]; private static int []
 * idIdentificacaoFuncionario = new int[tamanho]; private static int []
 * nuitFunionario = new int[tamanho]; private static String [] moradaFuncionario
 * = new String[tamanho]; private static boolean [] statusFuncionario = new
 * boolean[tamanho]; private static LocalDateTime [] dataRegistoFuncionario =
 * new LocalDateTime[tamanho]; private static LocalDateTime []
 * dataActualizacaoFuncionario = new LocalDateTime[tamanho];
 * 
 * 
 *//**
	 * declaracao de arrays de dados de log do sistema
	 */
/*
 * private static int [] idLogSistema = new int[tamanho]; private static int []
 * idUsuarioLogSistema = new int[tamanho]; private static String []
 * tipoLogSistema = new String[tamanho]; private static String []
 * eventoLogSistema = new String[tamanho]; private static LocalDateTime []
 * dataRegistoLogSistema = new LocalDateTime[tamanho];
 * 
 * 
 *//**
	 * declaracao de arrays de dados de permissoes dos usuarios do sistema
	 */
/*
 * private static int [] idPermissaoSistema = new int[tamanho]; private static
 * String [] nomePermissaoSistema = new String[tamanho]; private static boolean
 * [] statusPermissaoSistema = new boolean[tamanho]; private static
 * LocalDateTime [] dataRegistoPermissaoSistema = new LocalDateTime[tamanho];
 * private static LocalDateTime [] dataActualizacaoPermissaoSistema = new
 * LocalDateTime[tamanho];
 * 
 *//**
	 * declaracao de arrays de dados dos usuarios do sistema
	 */
/*
 * private static int [] idUsuario = new int[tamanho]; private static int []
 * idFunionarioUsuario = new int[tamanho]; private static int []
 * idPermissaoSistemaUsuario = new int[tamanho]; private static boolean []
 * statusUsuario = new boolean[tamanho]; private static LocalDateTime []
 * dataRegistoUsuario = new LocalDateTime[tamanho]; private static LocalDateTime
 * [] dataActualizacaoUsuario = new LocalDateTime[tamanho];
 * 
 *//**
	 * declaracao de arrays de dados do Fornecedor
	 */
/*
 * private static int [] idFornecedor = new int[tamanho]; private static String
 * [] nomeFornecedor = new String[tamanho]; private static String []
 * moradaFornecedor = new String[tamanho]; private static int [] nuitFornecedor
 * = new int[tamanho]; private static String [] tipoPessoaFornecedor = new
 * String[tamanho]; private static boolean [] statusFornecedor = new
 * boolean[tamanho]; private static LocalDateTime [] dataRegistoFornecedor = new
 * LocalDateTime[tamanho]; private static LocalDateTime []
 * dataActualizacaoFornecedor = new LocalDateTime[tamanho];
 * 
 *//**
	 * declaracao de arrays de dados do cliente
	 */
/*
 * private static int [] idCliente = new int[tamanho]; private static String []
 * nomeCliente = new String[tamanho]; private static String [] moradaCliente =
 * new String[tamanho];
 * 
 * 
 * 
 * Categorias de produtos de farmacia
 * 
 * 
 * @PRM é o conjunto de medicamento que dependem de uma receita medica
 * 
 * @OTC é o conjunto de medicamento que não dependem de uma receita medica
 * private static int [] idCategoriaProduto = new int [tamanho]; private static
 * String [] nomeCategoriaProduto = new String[tamanho]; private static boolean
 * [] statusCategoriaProduto = new boolean[tamanho]; private static String []
 * observacoesCategoriaProduto = new String[tamanho]; private static
 * LocalDateTime [] dataRegistoCategoriaProduto = new LocalDateTime[tamanho];
 * private static LocalDateTime [] dataActualizacaoCategoriaProduto = new
 * LocalDateTime[tamanho];
 * 
 *//**
	 * Declaraca de arrays de produtos
	 **/
/*
 * private static int [] idProduto = new int [tamanho]; private static int []
 * idFornecedorProduto = new int [tamanho]; private static int []
 * idUsuarioProduto = new int [tamanho]; private static int []
 * idProdutoCategoria = new int[tamanho]; private static String [] nomeProduto =
 * new String[tamanho]; private static String [] marcaProduto = new
 * String[tamanho]; private static int [] quantidadeProduto = new int[tamanho];
 * private static boolean [] statusProduto = new boolean[tamanho]; private
 * static String [] observacoesProduto = new String[tamanho]; private static
 * double [] precoVendaProduto = new double[tamanho]; private static
 * LocalDateTime [] dataValidadeProduto = new LocalDateTime[tamanho]; private
 * static LocalDateTime [] dataRegistoProduto = new LocalDateTime[tamanho];
 * private static LocalDateTime [] dataActualizacaoProduto = new
 * LocalDateTime[tamanho];
 * 
 *//**
	 * declaracao de arrays de venda
	 **/
/*
 * private static int [] idVenda = new int[tamanho]; private static int []
 * idClienteVenda = new int[tamanho]; private static int [] idUsuarioVenda = new
 * int[tamanho]; private static int [] quantidadeItensVenda = new int[tamanho];
 * private static double [] valorPagoVenda = new double[tamanho]; private static
 * LocalDateTime [] dataRegistoVenda = new LocalDateTime[tamanho]; private
 * static LocalDateTime [] dataActualizacaoVenda = new LocalDateTime[tamanho];
 * 
 * 
 *//**
	 * declaracao de arrays de item Venda
	 **/
/*
 * private static int [] idItemVenda = new int[tamanho]; private static int []
 * idVendaItemVenda = new int[tamanho]; private static int [] idProdutoItemVenda
 * = new int[tamanho]; private static LocalDateTime [] dataActualizacaoItemVenda
 * = new LocalDateTime[tamanho];
 * 
 * 
 *//**
	 * Checkout arrays
	 */
/*
 * private static int [] idConta = new int[tamanho]; private static String []
 * nomeConta = new String[tamanho]; private static double [] numeroConta = new
 * double[tamanho]; private static boolean [] statusConta = new
 * boolean[tamanho]; private static LocalDateTime [] dataRegistoConta = new
 * LocalDateTime[tamanho]; private static LocalDateTime [] dataActualizacaoConta
 * = new LocalDateTime[tamanho];
 * 
 *//**
	 * Localizacao dos ficheiros
	 */
/*
 * private static String filesPathIdentificacao = "Identificacao.txt"; private
 * static String filesPathCategoriaFuncionario = "CategoriaFuncionario.txt";
 * private static String filesPathFuncionario = "Funcionario.txt"; private
 * static String filesPathLogSistema = "LogSistema.txt"; private static String
 * filesPathPermissaoSistema = "PermissaoSistema.txt"; private static String
 * filesPathUsuario = "Usuario.txt"; private static String filesPathFornecedor =
 * "Fornecedor.txt"; private static String filesPathCliente = "Cliente.txt";
 * private static String filesPathCategoriaProduto = "CategoriaProduto.txt";
 * private static String filesPathProduto = "Produto.txt"; private static String
 * filesPathVenda = "Venda.txt"; private static String filesPathItemVenda =
 * "ItemVenda.txt"; private static String filesPathConta = "Conta.txt";
 * 
 *//**
	 * @Metodos ########################################## Area dos metodos
	 *          ################################################
	 */
/*

*//**
	 *
	 * @return
	 * @Descrição retorna quantas registos nao nulos que tem no array
	 */
/*
 * 
 * private static int notNull_Array(int [] idArray){ int not_null = 0; for (int
 * i = 0; i < tamanho; i++) if (idArray[i] != 0) not_null+=1; return not_null; }
 * 
 * 
 *//**
	 * Metodos de Validacao
	 */
/*

*//**
	 * @Descrição valida e nao permite repeticao de ID's usando o metodo recursivo
	 */
/*
 * private static int validaID(int id, int j, int [] idArray){ boolean exise =
 * false; int newID = 0; for (int i = 0; i < idArray.length; i++) { if
 * (idArray[i] == id || id == 0) exise = true; } if (exise) validaID((1 +
 * random.nextInt(tamanho))+2,j,idArray); else newID = idArray[j] = id; return
 * newID; }
 * 
 *//**
	 *
	 * @param count
	 * @return
	 * @Descrição para um processo apos 3 tentativas de erro (fail2ban)
	 */
/*
 * private static boolean processTimeOut(int count){ boolean error = false; if
 * (count>=3){ error = true; } return error; }
 * 
 * 
 *//**
	 *
	 * @param valor
	 * @return
	 * @Descrição: valida se o valor informado e uma String e se contem espacos em
	 *             branco
	 */
/*
 * private static boolean validaDados(String valor){ return
 * valor.matches("[a-zA-Z/\\s/]*") && !valor.equals(""); }
 * 
 *//**
	 *
	 * @param valor
	 * @return
	 * @Descrição: valida se o valor informado e um numero inteiro diferente de 0 e
	 *             se contem espacos em branco
	 */
/*
 * private static boolean validaDados(int valor){ return
 * (Integer.toString(valor)).matches("[0-9]*") && valor != 0; }
 * 
 *//**
	 *
	 * @param valor
	 * @return
	 * @Descrição validar se realmente sao numeros flutuantes
	 */
/*
 * private static boolean validaDados(float valor){ return
 * (Float.toString(valor)).matches("[0.0-9.0]*") && valor != 0.0; }
 * 
 * static boolean validarVerificacaoForeign(int idAcomparar,int [] idArray) {
 * boolean error = false; for (int i = 0; i < idArray.length; i++) { if
 * (idArray[i] == idAcomparar) { error = true; } } return error; }
 * 
 *//**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar recebe uma String para validacao
	 */
/*
 * private static String validaEntradaPalavra(String msg){ String valor = null;
 * boolean error = false; int count = 0; do { try { System.out.print(msg); valor
 * = br.readLine(); if (validaDados(valor)) { error = true; }else {
 * System.err.println("Informe nome valido."); valor = null; }
 * 
 * } catch (Exception e) { System.err.println("Valor Invalido!"); } count++; if
 * (!error) { error = processTimeOut(count); } } while (!error); return valor; }
 * 
 *//**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar estado
	 */
/*
 * private static Boolean validaEntradaStatus(String msg){ boolean error =
 * false, valor = false; int count = 0; do { try { System.out.print(msg); String
 * recebe = br.readLine(); if (recebe.equalsIgnoreCase("Activo")) { valor =
 * true; error = true; }else if (recebe.equalsIgnoreCase("Inactivo")) { valor =
 * false; error = true; }else{
 * System.err.println("Informe um estado valido [Activo ou Inactivo]"); } }
 * catch (Exception e) { System.err.println("Valor Invalido!"); } count++; if
 * (!error) { error = processTimeOut(count); } } while (!error); return valor; }
 * 
 *//**
	 *
	 * @param nome
	 * @return
	 * @Descrição validar Dados por nome
	 */
/*
 * private static String validaPorNome(String nome, String [] nomeArray){
 * boolean error = false; try { for (int i = 0; i < tamanho; i++) { if
 * (nome.equalsIgnoreCase(nomeArray[i])) error = true; } if (nome.equals(null))
 * error = true; } catch (Exception e) { } if (!error){ return nome; }else {
 * return validaPorNome(
 * validaEntradaPalavra("Nome Ja existe, Informe o nome do cliente: "),
 * nomeArray); } }
 * 
 *//**
	 *
	 * @param nome
	 * @return
	 * @Descrição validar Dados por nome da identificacao
	 */
/*
 * private static String validaPorNome(String [] nomeArray, String nome){
 * boolean error = false; try { for (int i = 0; i < tamanho; i++) { if
 * (nome.equalsIgnoreCase(nomeArray[i])) error = true; } if (nome.equals(null))
 * error = true; } catch (Exception e) { } if (!error){ return
 * validaPorNome(nomeArray,
 * validaEntradaPalavra("Nome nao existe, Informe o nome da identificacao valida: "
 * )); }else { return nome; } }
 * 
 *//**
	 *
	 * @param id
	 * @return
	 * @Descrição validar codigo do cliente
	 */
/*
 * private static int validaIDCliente(int id){ boolean error = false; try { for
 * (int i = 0; i < tamanho; i++) { if (id == idCliente[i]) error = true; } if
 * (id == 0) error = true; } catch (Exception e) { } if (!error){ return
 * validaIDCliente(
 * validaEntradaInteiro("Codigo do cliente nao existe, Informe um codigo valido do cliente: "
 * )); }else { return id; } }
 * 
 *//**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar numero inteiro
	 */
/*
 * private static int validaEntradaInteiro(String msg){ int valor = 0,count = 0;
 * boolean error = false; do { try { System.out.print(msg); valor =
 * Integer.parseInt(br.readLine()); if (validaDados(valor)) { error = true;
 * }else { System.err.println("Informe valor valido: "); }
 * 
 * } catch (Exception e) { System.err.println("Informe numero valido: "); } if
 * (!error) { count++; error = processTimeOut(count); if (error) valor = 0; } }
 * while (!error); return valor; }
 * 
 *//**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar numero float
	 */
/*
 * private static float validaEntradaFlutuante(String msg){ float valor = 0.0f;
 * boolean error = false; do { try { System.out.print(msg); valor =
 * Integer.parseInt(br.readLine()); if (validaDados(valor)) { error = true;
 * }else { System.err.println("Informe valor valido."); }
 * 
 * } catch (Exception e) { System.err.println("Valor Invalido!"); } } while
 * (!error); return valor; }
 * 
 *//**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar numero byte
	 */
/*
 * private static byte validaEntradaByte(String msg){ byte valor = 0; boolean
 * error = false; do { try { System.out.print(msg); valor =
 * Byte.parseByte(br.readLine()); if (validaDados(valor)) { error = true; }else
 * { System.err.println("Informe valor valido."); }
 * 
 * } catch (Exception e) { } } while (!error); return valor; }
 * 
 *//**
	 *
	 * @param i
	 * @return
	 * @Descrição altera a viualizacao do estado de true ou false para activo ou
	 *            inactivo
	 */
/*
 * private static String mudarStatus(boolean status){ String state = "Inactivo";
 * if (status){ state = "Activo"; } return state; }
 * 
 * 
 * 
 *//**
	 *
	 * @param nome
	 * @return
	 * @Descrição recebe o nome do cliente e devolve o ID
	 */
/*
 * private static int getIdByName(String nome, int [] idArray, String []
 * nomeArray) { int id = 0; if (nome!=null) { for (int i = 0; i < tamanho; i++)
 * { if (idArray[i] != 0) { if (nomeArray[i].equalsIgnoreCase(nome)) { id =
 * idArray[i]; } } } } return id; }
 * 
 *//**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID do cliente, consulta se existe e devolve o ID
	 */
/*
 * private static int filtrarPorId(int idi, int [] idArray) { int id = 0; if
 * (idi!=0) { for (int i = 0; i < tamanho; i++) { if (idArray[i] != 0) { if
 * (idArray[i] == idi) { id = idArray[i]; } } } } return id; }
 * 
 *//**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID da peca do cliente, consulta se existe e devolve o ID
	 */
/*
 * private static int filtrarPorItemId(int id) { int id0 = 0; if (id!=0) { for
 * (int i = 0; i < tamanho; i++) { if (idItemVenda[i] != 0) { if (idItemVenda[i]
 * == id) { id0 = idItemVenda[i]; } } } } return id0; }
 * 
 * 
 * 
 *//**
	 *
	 * @param
	 * @return
	 * @Descrição informa que o processo foi parado
	 */
/*
 * private static void processTimeOutMsg(){ System.out.println();
 * System.err.println(
 * "******************************************************************\n" +
 * "Esgotaram-se as tentativas, revise bem os dados e tente de novo..."); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu principal
	 */
/*
 * private static byte menu() { System.out.println("\n\n"+
 * "************************* Sistema de Gestao de Lavandaria *************************\n"
 * +
 * "***********************************************************************************\n"+
 * "*1. Clientes e pecas                                                              *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*2. Gerir Lavagens e Contas                                                       *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*3. Sair                                                                          *\n"
 * +
 * "***********************************************************************************"
 * ); return validaEntradaByte("Escolha uma opcao:"); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu de gestao de clientes e suas pecas
	 */
/*
 * public static byte menuGestaoDeClientesEPecas() { System.out.println("\n\n"+
 * "********************************* Clientes e pecas ********************************\n"
 * +
 * "***********************************************************************************\n"+
 * "*1. Registar cliente                                                              *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*2. Actualizar cliente                                                            *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*3. Deletar cliente                                                               *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*4. Adicionar item para cliente                                                   *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*5. deletar item para cliente                                                     *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*6. Actualizar item de cliente                                                    *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*7. Visualizar todos os clientes                                                  *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*8. Visualizar todos itens de um clientes                                         *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*9. Visualizar todos itens                                                        *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*10. Voltar para o menu principal                                                 *\n"
 * +
 * "***********************************************************************************"
 * ); return validaEntradaByte("Escolha uma opcao:"); }
 * 
 * public static byte menuGestaoLavagemsEContas() { System.out.println("\n\n"+
 * "********************************* Lavagens e contas *******************************\n"
 * +
 * "***********************************************************************************\n"+
 * "*1. Verificacao de Saida de Itens de Cliente                                      *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*2. Verificar Itens com data de levantamento expirado                             *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*3. Tipo de Lavagem Mais Frequente                                                *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*4. Conta                                                                         *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*5. Voltar para o menu principal                                                  *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "***********************************************************************************"
 * ); return validaEntradaByte("Escolha uma opcao: "); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu de registo de pecas do clientes
	 */
/*
 * private static byte menuRegistoDePecas(){ System.out.println();
 * System.out.println("\n"+
 * "******************************** Cadastro de Pecas ********************************\n"
 * +
 * "***********************************************************************************\n"+
 * "*Fornecemos o Seguinte Tipos de Lavagem:                                          *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*1. Lavagem a seco                                                                *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*2. Lavagem a Mao                                                                 *\n"
 * +
 * "*---------------------------------------------------------------------------------*\n"+
 * "*3. Lavagem a Maquina                                                             *\n"
 * +
 * "***********************************************************************************"
 * ); return validaEntradaByte("Escolha uma opcao:"); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu de atualizacao de dados de Identificacao
	 */
/*
 * private static byte menuActualizarIdentificacao() { System.out.println();
 * System.out.
 * println("*********************** Actualizar dados de Identificacao *************************"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*1. Nome                                                                          *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*2. Estado                                                                        *"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); return validaEntradaByte("Indique o dado de que deseja editar:"); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu de atualizacao de dados de Funcionario
	 */
/*
 * private static byte menuActualizarFuncionario() { System.out.println();
 * System.out.
 * println("******************** Actualizar dados de Funcionario ********************"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*1. Nome                                                                          *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*2. Estado                                                                        *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); return validaEntradaByte("Indique o dado de que deseja editar:"); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu de atualizacao de dados de Categoria Funcionario
	 */
/*
 * private static byte menuActualizarCategoriaFuncionario() {
 * System.out.println(); System.out.
 * println("******************** Actualizar dados de Categoria Funcionario ********************"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*1. Nome                                                                          *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*2. Estado                                                                        *"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); return validaEntradaByte("Indique o dado de que deseja editar:"); }
 * 
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descrição Menu de atualizacao de dados de pecas do clientes
	 */
/*
 * private static byte menuActualizarItemCliente() { System.out.println();
 * System.out.
 * println("************************ Actualizar dados de Item Cliente *************************"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*1. Nome                                                                          *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*2. Tipo de Lavagem                                                               *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*3. Tecido                                                                        *"
 * ); System.out.println(
 * "*---------------------------------------------------------------------------------*"
 * ); System.out.
 * println("*4. Peso                                                                          *"
 * ); System.out.println(
 * "***********************************************************************************"
 * ); return validaEntradaByte("Indique o dado de que deseja editar:"); }
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição Registar cliente e suas pecas
	 */
/*
 * public static void clientesEPecas() { do{ options =
 * menuGestaoDeClientesEPecas(); switch (options){ case 1:
 *//** Regista cliente */
/*
 * inserirDadosPecasCliente(registaCliente()); break; case 2:
 *//** Actualizar cliente */
/*
 * actualizarCliente(); break; case 3:
 *//** Deletar cliente */
/*
 * deletarCliente(); break; case 4:
 *//** Adicionar peca para cliente */
/*
 * inserirDadosItemcliente(); break; case 5:
 *//** deletar peca de cliente */
/*
 * deletarItem(); break; case 6:
 *//** Actualizar peca de cliente */
/*
 * actualizarItemCliente(); break; case 7:
 *//** Visualizar todos os clientes */
/*
 * listaDeClientes(); break; case 8:
 *//** Visualizar todas pecas de um clientes */
/*
 * listaDeItensCliente(); break; case 9:
 *//** Visualizar todos itens */
/*
 * listaDeItens(); break; default:break; } }while (options != 10); }
 * 
 * 
 * 
 * private static void listaDeClientes() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * 
 *//**
	 *
	 * @param data_
	 * @return
	 * @Descrição converte data em string e retornar uma string de data
	 */
/*
 * private static String parseLocalDateTimeToSring(LocalDateTime data_){ String
 * _data = null; if (data_!=null) _data = dateTimeFormatter.format(data_);
 * 
 * return _data; }
 *//**
	 *
	 * @param data_
	 * @return
	 * @Descrição converte string em data e retornar uma data
	 */
/*
 * private static LocalDateTime parseStringToLocalDateTime(String data_){
 * LocalDateTime _data = null; if (data_ != null && data_!="null") _data =
 * LocalDateTime.parse(data_); else _data = null; return _data; }
 * 
 * 
 *//**
	 * @Descrição Operacoes sobre Identificacao
	 */
/*

*//**
	 * @Descrição preenche os dados de Identificacao
	 */
/*
 * private static void preencheDadosIdentificacao(int posicao, int id, String
 * nome, boolean status, LocalDateTime dataRegisto,LocalDateTime
 * dataActualizacao) { idIdentificacao[posicao] = id; nomeIdentificacao[posicao]
 * = nome; statusIdentificacao[posicao] = status;
 * dataRegistoIdentificacao[posicao] = dataRegisto;
 * dataActualizacaoIdentificacaos[posicao] = dataActualizacao;
 * gravarDadosIdentificacaoNoFicheiro(filesPathIdentificacao); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descriptio Inserir dados de clientes
	 */
/*
 * private static int gravaIdentificacao() { int id = 0; try { String nome =
 * validaIdentificacaoPorNome(
 * validaEntradaPalavra("Informe o nome da Identificacao: "),
 * nomeIdentificacao); if (nome!=null) { id = validaID((1 +
 * random.nextInt(tamanho)), notNull_Array(idIdentificacao),idIdentificacao);
 * preencheDadosIdentificacao(notNull_Array(idIdentificacao),id,nome,true,
 * LocalDateTime.now(), LocalDateTime.now()); }else{ id = 0; } } catch
 * (Exception e) { e.printStackTrace(); } return id; }
 * 
 * private static String validaIdentificacaoPorNome(String validaEntradaPalavra,
 * String[] nomeIdentificacao2) { // TODO Auto-generated method stub return
 * null; }
 * 
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição atualiza dados de Identificacao
	 */
/*
 * private static void actualizarIdentificacao() { boolean errorV = false; int
 * count = 0; do { int id =
 * filtrarPorId(validaEntradaInteiro("Informe o codigo da Identificacao: "),
 * idIdentificacao); if (id != 0) { switch (menuActualizarIdentificacao()){ case
 * 1://Nome String nomeNovaIdentificacao =
 * validaPorNome(validaEntradaPalavra("Informe o Novo Nome Para o Cliente: "),
 * nomeIdentificacao); for (int i = 0; i < idIdentificacao.length; i++) { if
 * (idIdentificacao[i] != 0) { if (idIdentificacao[i] == id) {
 * nomeIdentificacao[i] = nomeNovaIdentificacao;
 * gravarDadosIdentificacaoNoFicheiro(filesPathIdentificacao); } } }
 * listaDeIdentificacao(id); break; case 2://Estado boolean
 * estadoNovaIdentificacao =
 * validaEntradaStatus("Informe o Novo Estado Para o Cliente [Activo ou Inactivo]: "
 * ); for (int i = 0; i < idIdentificacao.length; i++){ if
 * (idIdentificacao[i]!=0) { if (idIdentificacao[i] == id) {
 * statusIdentificacao[i] = estadoNovaIdentificacao;
 * gravarDadosIdentificacaoNoFicheiro(filesPathIdentificacao); } } }
 * listaDeIdentificacao(id); break; default:
 * System.err.println("Informou dado invalido!"); break; } errorV = true; }else{
 * System.err.println("O Cliente nao existe no sistema!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } while (!errorV); }
 * 
 *//**
	 *
	 * @Descrição remove Identificacao
	 */
/*
 * private static void deletarIdentificacao() { boolean errorV = false; int
 * count = 0; do { try { int id =
 * filtrarPorId(validaEntradaInteiro("Informe o codigo do cliente: "),
 * idIdentificacao); if (id != 0) { if (!validarVerificacaoForeign(id,
 * idFuncionario)){ listaDeIdentificacao(id); boolean error = false; do {
 * System.out.
 * println("Tem certeza de que deseja remover a identificacao [S - sim ou N - nao]"
 * ); char selecao = (char)br.read(); switch
 * ((Character.toString(selecao)).toLowerCase()){ case "s": for (int i = 0; i
 * <idIdentificacao.length ; i++) { if (idIdentificacao[i]!=0){ if
 * (idIdentificacao[i]==id) { preencheDadosIdentificacao(i,0, null, false, null,
 * null); } } } error = true; break; case "n": error = true; break; default:
 * break; } } while (!error); errorV = true; }else{ System.err.
 * println("Nao pode remover a identificacao pois esta associados a alguns funcionarios"
 * ); listaDeItens(); errorV = true; } }else {
 * System.err.println("O codigo da identificacao nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } catch (IOException e) { e.printStackTrace(); } } while (!errorV); }
 * 
 * 
 *//**
	 * @Descrição preenche os dados de Cagoria Funcionario
	 */
/*
 * private static void preencheDadosCategoriaFuncionario(int posicao, int id,
 * String nome, boolean status, LocalDateTime dataRegisto,LocalDateTime
 * dataActualizacao) { idCategoriaFuncionario[posicao] = id;
 * nomeCategoriraFuncionario[posicao] = nome;
 * statusCategoriraFuncionario[posicao] = status;
 * dataRegistoCategoriraFuncionario[posicao] = dataRegisto;
 * dataActualizacaoCategoriraFuncionario[posicao] = dataActualizacao;
 * gravarDadosCategoriaFuncionarioNoFicheiro(filesPathCategoriaFuncionario); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descriptio Inserir dados da Categoria Funcionario
	 */
/*
 * private static int gravaCategoriaFuncionario() { int id = 0; try { String
 * nome = validaIdentificacaoPorNome(
 * validaEntradaPalavra("Informe o nome da Identificacao: "),
 * nomeCategoriaProduto); if (nome!=null) { id = validaID((1 +
 * random.nextInt(tamanho)),
 * notNull_Array(idCategoriaFuncionario),idCategoriaFuncionario);
 * preencheDadosIdentificacao(notNull_Array(idCategoriaFuncionario),id,nome,
 * true, LocalDateTime.now(), LocalDateTime.now()); }else{ id = 0; } } catch
 * (Exception e) { e.printStackTrace(); } return id; }
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição atualiza dados de Categoria Funcionario
	 */
/*
 * private static void actualizaCategoriaFuncionario() { boolean errorV = false;
 * int count = 0; do { int id = filtrarPorId(
 * validaEntradaInteiro("Informe o codigo da Categoria Funcionario: "),
 * idCategoriaFuncionario); if (id != 0) { switch
 * (menuActualizarIdentificacao()){ case 1://Nome String
 * nomeNovaCategoriaFuncionario =
 * validaPorNome(validaEntradaPalavra("Informe o Novo Nome Para a categoria: "),
 * nomeCategoriraFuncionario); for (int i = 0; i <
 * idCategoriaFuncionario.length; i++) { if (idCategoriaFuncionario[i] != 0) {
 * if (idCategoriaFuncionario[i] == id) { nomeCategoriraFuncionario[i] =
 * nomeNovaCategoriaFuncionario;
 * gravarDadosCategoriaFuncionarioNoFicheiro(filesPathCategoriaFuncionario); } }
 * } listaDeCategoriaFuncionario(id); break; case 2://Estado boolean
 * estadoNovaCategoriaFuncionario =
 * validaEntradaStatus("Informe o Novo Estado Para a categoria [Activo ou Inactivo]: "
 * ); for (int i = 0; i < idCategoriaFuncionario.length; i++){ if
 * (idCategoriaFuncionario[i]!=0) { if (idCategoriaFuncionario[i] == id) {
 * statusCategoriraFuncionario[i] = estadoNovaCategoriaFuncionario;
 * gravarDadosCategoriaFuncionarioNoFicheiro(filesPathCategoriaFuncionario); } }
 * } listaDeCategoriaFuncionario(id); break; default:
 * System.err.println("Informou dado invalido!"); break; } errorV = true; }else{
 * System.err.println("O Cliente nao existe no sistema!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } while (!errorV); }
 * 
 *//**
	 *
	 * @Descrição remove categoria funcionario
	 */
/*
 * private static void deletarCategriaFuncionario() { boolean errorV = false;
 * int count = 0; do { try { int id =
 * filtrarPorId(validaEntradaInteiro("Informe o codigo da categoria: "),
 * idCategoriaFuncionario); if (id != 0) { if
 * (!validarVerificacaoForeign(id,idFuncionario)){
 * listaDeCategoriaFuncionario(id); boolean error = false; do { System.out.
 * println("Tem certeza de que deseja remover a Categoria [S - sim ou N - nao]"
 * ); char selecao = (char)br.read(); switch
 * ((Character.toString(selecao)).toLowerCase()){ case "s": for (int i = 0; i
 * <idCategoriaFuncionario.length ; i++) { if (idCategoriaFuncionario[i]!=0){ if
 * (idCategoriaFuncionario[i]==id) { preencheDadosCategoriaFuncionario(i, 0,
 * null, false, null, null); } } } error = true; break; case "n": error = true;
 * break; default: break; } } while (!error); errorV = true; }else{ System.err.
 * println("Nao pode remover a identificacao pois esta associados a alguns funcionarios"
 * ); listaDeItens(); errorV = true; } }else {
 * System.err.println("O codigo da identificacao nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } catch (IOException e) { e.printStackTrace(); } } while (!errorV); }
 * 
 *//**
	 * @Descrição preenche os dados de Funcionario
	 */
/*
 * private static void preencheDadoFuncionario(int posicao, int id, String
 * nome,int idIdentificacaoFuncionarioR,int nuitFunionarioR, String
 * moradaFuncionarioR, boolean status, LocalDateTime dataRegisto,LocalDateTime
 * dataActualizacao) { idFuncionario[posicao] = id; nomeFuncionario[posicao] =
 * nome; idIdentificacaoFuncionario[posicao] = idIdentificacaoFuncionarioR;
 * nuitFunionario[posicao] = nuitFunionarioR; moradaFuncionario[posicao] =
 * moradaFuncionarioR; statusFuncionario[posicao] = status;
 * dataRegistoFuncionario[posicao] = dataRegisto;
 * dataActualizacaoFuncionario[posicao] = dataActualizacao;
 * gravarDadosFuncionarioNoFicheiro(filesPathFuncionario); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descriptio Inserir dados da Funcionario
	 */
/*
 * private static int gravaFuncionario() { int id = 0; try { String nome =
 * validaPorNome(validaEntradaPalavra("Informe o nome do funcionario: "),
 * nomeFuncionario); if (nome!=null) { listaDeIdentificacao(); String
 * nomeIdentificacaoVer = validaPorNome(nomeIdentificacao,
 * validaEntradaPalavra("Informe o nome da Identificacao: ")); if
 * (nomeIdentificacao!=null) { int nuit =
 * validaEntradaInteiro("Informe o numero de nuit"); if (nuit <= 0) { String
 * morada = validaEntradaPalavra("Inform a morada"); if(morada!=null) { id =
 * validaID((1 + random.nextInt(tamanho)),
 * notNull_Array(idFuncionario),idFuncionario);
 * preencheDadoFuncionario(notNull_Array(idFuncionario),id,nome,
 * getIdByName(nomeIdentificacaoVer, idIdentificacao, nomeIdentificacao), nuit ,
 * morada, true, LocalDateTime.now(), LocalDateTime.now()); } } } }else{ id = 0;
 * } } catch (Exception e) { e.printStackTrace(); } return id; }
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição atualiza dados de Funcionario
	 */
/*
 * private static void actualizaFuncionario() { boolean errorV = false; int
 * count = 0; do { int id =
 * filtrarPorId(validaEntradaInteiro("Informe o codigo do Funcionario: "),
 * idFuncionario); if (id != 0) { switch (menuActualizarFuncionario()){ case
 * 1://Nome String nomeNovaFuncionario =
 * validaPorNome(validaEntradaPalavra("Informe o Novo Nome Para a categoria: "),
 * nomeFuncionario); for (int i = 0; i < idFuncionario.length; i++) { if
 * (idFuncionario[i] != 0) { if (idFuncionario[i] == id) { nomeFuncionario[i] =
 * nomeNovaFuncionario; gravarDadosFuncionarioNoFicheiro(filesPathFuncionario);
 * } } } listaDeFuncionario(id); break; case 2://Estado boolean
 * estadoNovaCategoriaFuncionario =
 * validaEntradaStatus("Informe o Novo Estado Para a categoria [Activo ou Inactivo]: "
 * ); for (int i = 0; i < idCategoriaFuncionario.length; i++){ if
 * (idCategoriaFuncionario[i]!=0) { if (idCategoriaFuncionario[i] == id) {
 * statusCategoriraFuncionario[i] = estadoNovaCategoriaFuncionario;
 * gravarDadosCategoriaFuncionarioNoFicheiro(filesPathIdentificacao); } } }
 * listaDeFuncionario(id); break; default:
 * System.err.println("Informou dado invalido!"); break; } errorV = true; }else{
 * System.err.println("O Cliente nao existe no sistema!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } while (!errorV); }
 * 
 *//**
	 *
	 * @Descrição remove categoria funcionario
	 */
/*
 * private static void deletarFuncionario() { boolean errorV = false; int count
 * = 0; do { try { listaDeFuncionario(); int id =
 * filtrarPorId(validaEntradaInteiro("Informe o codigo da: "), idFuncionario);
 * if (id != 0) { if (!validarVerificacaoForeign(id,idFuncionario)){
 * listaDeCategoriaFuncionario(id); boolean error = false; do { System.out.
 * println("Tem certeza de que deseja remover a Categoria [S - sim ou N - nao]"
 * ); char selecao = (char)br.read(); switch
 * ((Character.toString(selecao)).toLowerCase()){ case "s": for (int i = 0; i
 * <idCategoriaFuncionario.length ; i++) { if (idCategoriaFuncionario[i]!=0){ if
 * (idCategoriaFuncionario[i]==id) { preencheDadosCategoriaFuncionario(i, 0,
 * null, false, null, null); } } } error = true; break; case "n": error = true;
 * break; default: break; } } while (!error); errorV = true; }else{ System.err.
 * println("Nao pode remover a identificacao pois esta associados a alguns funcionarios"
 * ); listaDeItens(); errorV = true; } }else {
 * System.err.println("O codigo da identificacao nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } catch (IOException e) { e.printStackTrace(); } } while (!errorV); }
 * 
 * 
 *//**
	 *
	 * @param id
	 * @Descrição imprime a lista de uma unica Identificacao
	 */
/*
 * private static void listaDeIdentificacao(){ int numeracao = 1; int empty_= 0;
 * String layoutFormat = formatoImpressaoIdentificacao(); for (int i = 0; i <
 * tamanho; i++){ if (idCliente[i] != 0){
 * System.out.format(layoutFormat,"|",numeracao,"|",idIdentificacao[i],"|",
 * nomeIdentificacao[i],"|",statusIdentificacao[i],"|",moradaCliente[i],"|",
 * parseLocalDateTimeToSring(dataRegistoIdentificacao[i]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoIdentificacaos[i]),"|");
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 *//**
	 *
	 * @param id
	 * @Descrição imprime a lista de Identificacao
	 */
/*
 * private static void listaDeIdentificacao(int id){ int numeracao = 1; int
 * empty_= 0; String layoutFormat = formatoImpressaoIdentificacao(); for (int i
 * = 0; i < tamanho; i++){ if (idCliente[i] == id){
 * System.out.format(layoutFormat,"|",numeracao,"|",idIdentificacao[i],"|",
 * nomeIdentificacao[i],"|",statusIdentificacao[i],"|",moradaCliente[i],"|",
 * parseLocalDateTimeToSring(dataRegistoIdentificacao[i]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoIdentificacaos[i]),"|");
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 *//**
	 *
	 * @param id
	 * @Descrição imprime a lista de Categoria Funcionairo
	 */
/*
 * private static void listaDeCategoriaFuncionario(){ int numeracao = 1; int
 * empty_= 0; String layoutFormat = formatoImpressaoIdentificacao(); for (int i
 * = 0; i < tamanho; i++){ if (idCliente[i] != 0){
 * System.out.format(layoutFormat,"|",numeracao,"|",idIdentificacao[i],"|",
 * nomeIdentificacao[i],"|",statusIdentificacao[i],"|",moradaCliente[i],"|",
 * parseLocalDateTimeToSring(dataRegistoIdentificacao[i]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoIdentificacaos[i]),"|");
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 *//**
	 *
	 * @param id
	 * @Descrição imprime a lista de uma Categoria Funcionairo
	 */
/*
 * private static void listaDeCategoriaFuncionario(int id){ int numeracao = 1;
 * int empty_= 0; String layoutFormat = formatoImpressaoIdentificacao(); for
 * (int i = 0; i < tamanho; i++){ if (idCliente[i] == id){
 * System.out.format(layoutFormat,"|",numeracao,"|",idIdentificacao[i],"|",
 * nomeIdentificacao[i],"|",statusIdentificacao[i],"|",moradaCliente[i],"|",
 * parseLocalDateTimeToSring(dataRegistoIdentificacao[i]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoIdentificacaos[i]),"|");
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 * 
 *//**
	 *
	 * @param id
	 * @Descrição imprime a lista de Funcionairo
	 */
/*
 * private static void listaDeFuncionario(){ int numeracao = 1; int empty_= 0;
 * String layoutFormat = formatoImpressaoIdentificacao(); for (int i = 0; i <
 * tamanho; i++){ if (idCliente[i] != 0){
 * System.out.format(layoutFormat,"|",numeracao,"|",idIdentificacao[i],"|",
 * nomeIdentificacao[i],"|",statusIdentificacao[i],"|",moradaCliente[i],"|",
 * parseLocalDateTimeToSring(dataRegistoIdentificacao[i]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoIdentificacaos[i]),"|");
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 *//**
	 *
	 * @param id
	 * @Descrição imprime a lista de uma Categoria Funcionairo
	 */
/*
 * private static void listaDeFuncionario(int id){ int numeracao = 1; int
 * empty_= 0; String layoutFormat = formatoImpressaoIdentificacao(); for (int i
 * = 0; i < tamanho; i++){ if (idCliente[i] == id){
 * System.out.format(layoutFormat,"|",numeracao,"|",idIdentificacao[i],"|",
 * nomeIdentificacao[i],"|",statusIdentificacao[i],"|",moradaCliente[i],"|",
 * parseLocalDateTimeToSring(dataRegistoIdentificacao[i]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoIdentificacaos[i]),"|");
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 * 
 * 
 * private static boolean[] estadoCliente;
 *//**
	 * @Descrição preenche os dados do cliente
	 */
/*
 * private static void preencheDadosCliente(int posicao, int id, String nome,
 * String morada, boolean estado, LocalDateTime dateA,LocalDateTime dateR) {
 * idCliente[posicao] = id; nomeCliente[posicao] = nome;
 * 
 * estadoCliente[posicao] = estado;
 * gravarDadosClienteNoFicheiro(filesPathCliente); }
 * 
 *//**
	 *
	 * @return
	 * @throws IOException
	 * @Descriptio Inserir dados de clientes
	 */
/*
 * private static int registaCliente() { int id = 0; try { String nome =
 * validaNomeCliente(validaEntradaPalavra("Informe o nome Cliente: ")); if
 * (nome!=null) { String morada =
 * validaEntradaPalavra("Informe a morada do Cliente: "); if (morada!=null) { id
 * = validaID((1 + random.nextInt(tamanho)), null_cliente(),idCliente);
 * preencheDadosCliente(null_cliente(),id,nome,morada,true, LocalDateTime.now(),
 * LocalDateTime.now()); }else{ id = 0; } }else{ id = 0; } } catch (Exception e)
 * { e.printStackTrace(); } return id; }
 * 
 * private static int null_cliente() { // TODO Auto-generated method stub return
 * 0; }
 * 
 * 
 * private static String validaNomeCliente(String validaEntradaPalavra) { //
 * TODO Auto-generated method stub return null; }
 * 
 * 
 *//**
	 *
	 * @param id
	 * @throws IOException
	 * @Descrição menu gravação dos dados das pecas
	 */
/*
 * private static void inserirDadosPecasCliente(int id){ if (id!=0) { byte
 * options = menuRegistoDePecas(); switch (options) { case 1://A seco
 * inserirDadosPecasPorTipoLavagem(); break; case 2://A mão
 * inserirDadosPecasPorTipoLavagem(); break; case 3://A maquina
 * inserirDadosPecasPorTipoLavagem(); break; default:
 * System.out.println("Valor invalido."); break; } } }
 * 
 *//**
	 *
	 * @param posicao
	 * @param ticket
	 * @param id
	 * @param nome
	 * @param tipo
	 * @param lavagem
	 * @param _tipoDeLavagem
	 * @param peso_
	 * @param dateA
	 * @param dateR
	 * @param dateL
	 * @Descrição preenche dados de pecas
	 */
/*
 * private static float[] valorApagar; private static void
 * preencheDadosItens(int posicao, int ticket,int id, String nome, String tipo,
 * String lavagem, float _tipoDeLavagem,float peso_, LocalDateTime
 * dateA,LocalDateTime dateR,LocalDateTime dateL){ nomeProduto[posicao] = nome;
 * 
 * valorApagar[posicao] = (_tipoDeLavagem); dataRegistoItem[posicao] = dateR;
 * dataActualizacaoItemVenda[posicao] = dateA; clienteId[posicao] = ticket;
 * idItemVenda[posicao] = id; gravarDadosPecasClienteNoFicheiro(filesPathItem);
 * }
 *//**
	 *
	 * @param id
	 * @param tipoDeLavagem_
	 * @param _tipoDeLavagem
	 * @throws IOException
	 */
/*
 * @SuppressWarnings("unused") private static void
 * inserirDadosPecasPorTipoLavagem(int id,String tipoDeLavagem_, float
 * _tipoDeLavagem) { int quantPecas =
 * validaEntradaInteiro("Informe o numero de Pecas: "); if (quantPecas!=0) { for
 * (int i = 0; i < quantPecas; i++) { String nome =
 * validaEntradaPalavra("Informe o nome da peca: "); if (nome!=null) { String
 * tipo = validaEntradaPalavra("Informe o tipo de tecido da peca: "); if
 * (tipo!=null) { float peso_ =
 * validaEntradaFlutuante("Informe o peso da peca: "); if (peso_!=0.0f) { int
 * itemID = validaID((1 + random.nextInt(tamanho)),
 * (null_peca()+1),idItemVenda);
 * preencheDadosItens((null_peca()+1),id,itemID,nome,tipo,tipoDeLavagem_,
 * _tipoDeLavagem,peso_, LocalDateTime.now(),
 * LocalDateTime.now(),(LocalDateTime.now().plusDays(3))); }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }
 * 
 * @SuppressWarnings("unused") private static void
 * inserirDadosPecasPorTipoLavagem1() { int id = 0; String tipoDeLavagem_ =
 * null; float _tipoDeLavagem = 0; int quantPecas =
 * validaEntradaInteiro("Informe o numero de Pecas: "); if (quantPecas!=0) { for
 * (int i = 0; i < quantPecas; i++) { String nome =
 * validaEntradaPalavra("Informe o nome da peca: "); if (nome!=null) { String
 * tipo = validaEntradaPalavra("Informe o tipo de tecido da peca: "); if
 * (tipo!=null) { float peso_ =
 * validaEntradaFlutuante("Informe o peso da peca: "); if (peso_!=0.0f) { int
 * itemID = validaID((1 + random.nextInt(tamanho)),
 * (null_peca()+1),idItemVenda);
 * preencheDadosItens((null_peca()+1),id,itemID,nome,tipo,tipoDeLavagem_,
 * _tipoDeLavagem,peso_, LocalDateTime.now(),
 * LocalDateTime.now(),(LocalDateTime.now().plusDays(3))); }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }
 * 
 * 
 * 
 * private static void inserirDadosPecasPorTipoLavagem() { int id = 0; String
 * tipoDeLavagem_ = null; float _tipoDeLavagem = 0; int quantPecas =
 * validaEntradaInteiro("Informe o numero de Pecas: "); if (quantPecas!=0) { for
 * (int i = 0; i < quantPecas; i++) { String nome =
 * validaEntradaPalavra("Informe o nome da peca: "); if (nome!=null) { String
 * tipo = validaEntradaPalavra("Informe o tipo de tecido da peca: "); if
 * (tipo!=null) { float peso_ =
 * validaEntradaFlutuante("Informe o peso da peca: "); if (peso_!=0.0f) { int
 * itemID = validaID((1 + random.nextInt(tamanho)),
 * (null_peca()+1),idItemVenda);
 * preencheDadosItens((null_peca()+1),id,itemID,nome,tipo,tipoDeLavagem_,
 * _tipoDeLavagem,peso_, LocalDateTime.now(),
 * LocalDateTime.now(),(LocalDateTime.now().plusDays(3))); }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } } }else{ System.out.
 * println("Ocorreu um erro inesperado, por favor contacte o administrador do sistema."
 * ); } }
 * 
 *//**
	 *
	 * @Descrição grava dados por peca cliente
	 */
/*
 * private static void inserirDadosItemcliente() {
 * inserirDadosPecasCliente(validaIDCliente(
 * validaEntradaInteiro("Informe o codigo do cliente"))); }
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição atualiza dados de cliente
	 * @PARTE EDITADA: ESTA FOI A PARTE EDITADA
	 */
/*
 * private static void actualizarCliente() {
 *//**
	 * ESTA FOI A LINHA INSERIDA PARA QUE AO CLICAR EM ACUALIZAR PARA EDITAR CLIENTE
	 * SE POSSA LISTAR OS CLIENTES listaDeClienes()
	 */
/*
 * listaDeClientes(); boolean errorV = false; int count = 0; do { int id =
 * filtrarPorClienteId(validaEntradaInteiro("Informe o codigo do cliente: "));
 * if (id != 0) { switch (menuActualizarCliente()){ case 1://Nome String
 * nomeNovoCliente =
 * validaNomeCliente(validaEntradaPalavra("Informe o Novo Nome Para o Cliente: "
 * )); for (int i = 0; i < idCliente.length; i++) { if (idCliente[i] != 0) { if
 * (idCliente[i] == id) { nomeCliente[i] = nomeNovoCliente;
 * gravarDadosClienteNoFicheiro(filesPathCliente); } } } listaDeClientes(id);
 * break; case 2://Estado boolean estadoNovoCliente =
 * validaEntradaEstado("Informe o Novo Estado Para o Cliente [Activo ou Inactivo]: "
 * ); for (int i = 0; i < idCliente.length; i++){ if (idCliente[i]!=0) { if
 * (idCliente[i] == id) { estadoCliente[i] = estadoNovoCliente;
 * gravarDadosClienteNoFicheiro(filesPathCliente); } } } listaDeClientes(id);
 * break; default: System.err.println("Informou dado invalido!"); break; }
 * errorV = true; }else{ System.err.println("O Cliente nao existe no sistema!");
 * } count++; if (!errorV) { errorV = processTimeOut(count); if (errorV)
 * processTimeOutMsg(); } } while (!errorV); }
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição Actualiza peca do cliente
	 * @Esta parte foi editada
	 */
/*
 * private static void actualizarItemCliente() { boolean errorV = false;
 *//**
	 * ESTA FOI A LINHA INSERIDA PARA QUE AO CLICAR EM ACUALIZAR PARA EDITAR CLIENTE
	 * SE POSSA LISTAR OS CLIENTES istaDeClientes()
	 */
/*
 * listaDeClientes(); int count = 0; do { int idc =
 * filtrarPorClienteId(validaEntradaInteiro("Informe o codigo do cliente: "));
 * if (idc != 0) { if (quantidadeItensCliente(idc)>0){ listaDeItens(idc); int
 * idp = filtrarPorItemId(validaEntradaInteiro("Informe o Codigo do item: "));
 * if (idp != 0) {
 * 
 * switch (menuActualizarItemCliente()){ case 1://Nome String nomeNovo =
 * validaEntradaPalavra("Informe o Novo Nome Para a peca: ");; for (int i = 0; i
 * < clienteId.length; i++) { if (idItemVenda[i] != 0) { if (idItemVenda[i] ==
 * idp) { nomeProduto[i] = nomeNovo; dataActualizacaoItemVenda[i] =
 * LocalDateTime.now(); gravarDadosPecasClienteNoFicheiro(filesPathItem); } } }
 * listaDeItensID(idp); break; default: break; } errorV = true; } }else{
 * System.err.println("O cliente nao tem itens associados a ele");
 * listaDeItens(); } }else {
 * System.err.println("O codigo do cliente nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } while (!errorV); }
 * 
 *//**
	 *
	 * @Descrição remove cliente
	 */
/*
 * private static void deletarCliente() { boolean errorV = false; int count = 0;
 * do { try { int id =
 * filtrarPorClienteId(validaEntradaInteiro("Informe o codigo do cliente: "));
 * if (id != 0) { if (quantidadeItensCliente(id)<=0){ listaDeClientes(id);
 * boolean error = false; do { System.out.
 * println("Tem certeza de que deseja remover o cliente [S - sim ou N - nao]");
 * char selecao = (char)br.read(); switch
 * ((Character.toString(selecao)).toLowerCase()){ case "s": for (int i = 0; i
 * <idCliente.length ; i++) { if (idCliente[i]!=0){ if (idCliente[i]==id) {
 * preencheDadosCliente(i,0,null,null,false,0,null,null); } } } error = true;
 * break; case "n": error = true; break; default: break; } } while (!error);
 * errorV = true; }else{
 * System.err.println("Nao pode remover o cliente tem itens associados a ele");
 * listaDeItens(); errorV = true; } }else {
 * System.err.println("O codigo do cliente nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } catch (IOException e) { e.printStackTrace(); } } while (!errorV); }
 * 
 *//**
	 *
	 * @throws IOException
	 * @Descrição remove uma peca
	 */
/*
 * private static void deletarItem() { boolean errorV = false; int count = 0; do
 * { try { int idc =
 * filtrarPorClienteId(validaEntradaInteiro("Informe o codigo do cliente: "));
 * if (idc != 0) { if (quantidadeItensCliente(idc)>0){ listaDeItens(idc); int
 * idp = filtrarPorItemId(validaEntradaInteiro("Informe o Codigo do item: "));
 * if (idp != 0) { boolean error = false; do { System.out.
 * print("Tem certeza de que deseja remover o cliente [S - sim ou N - nao]: ");
 * char selecao = (char)br.read(); switch
 * ((Character.toString(selecao)).toLowerCase()){ case "s": for (int i = 0; i <
 * idItemVenda.length; i++) { if (idItemVenda[i]!=0){ if (idItemVenda[i]==idp) {
 * preencheDadosItens(i,0,0,null,null,null,0,0,null,null,null); } } } error =
 * true; break; case "n": error = true; break; default: break; } } while
 * (!error); errorV = true; } }else{
 * System.err.println("O cliente nao tem itens associados a ele");
 * listaDeItens(); } }else {
 * System.err.println("O codigo do cliente nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * } } catch (IOException e) { e.printStackTrace(); } } while (!errorV); }
 * 
 *//**
	 *
	 * @return
	 * @Descrição imprime o formato de cabeçalho e retorna o formato para impressao
	 *            dos dados
	 */
/*
 * private static String formatoImpressaoIdentificacao(){ String [] header = new
 * String[]{"|","#","|","Numero","|","Nome","|","Telefone","|","Morada","|",
 * "Estado","|","Valor pagar","|","Qtd","|","Data Reg","|","Data Act","|"};
 * String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome =
 * "%-28.29s"; String formatTelefone = "%-12.10s",formatMorada =
 * "%-13.10s",formatEstado = "%-6.6s"; String formatValor = "%-13.20s",formatQtd
 * = "%-4.4s",formatData = "%-19.19s"; String formatColl = formatCaracter + " "
 * + formatCaracter + " " + formatCaracter + " " + formatNumero + " " +
 * formatCaracter + " " + formatNome + " " + formatCaracter + " " +
 * formatTelefone + " " + formatCaracter + " " + formatMorada + " " +
 * formatCaracter + " " + formatEstado + " " + formatCaracter + " " +
 * formatValor + " " + formatCaracter + " " + formatQtd + " " + formatCaracter +
 * " " + formatData + " " + formatCaracter + " " + formatData + " " +
 * formatCaracter; System.out.println(); System.out.
 * println("******************************************************************** Lista de Identificao *********************************************************************"
 * ); System.out.println(
 * "************************************************************************************************************************************************************"
 * ); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * );
 * System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4
 * ],header[5],header[6],header[7],header[8],header[9],header[10],header[11],
 * header[12],header[13],header[14],header[15],header[16],header[17],header[18],
 * header[19],header[20]); System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); return formatColl; }
 * 
 *//**
	 *
	 * @return
	 * @Descrição imprime o formato de cabeçalho e retorna o formato para impressao
	 *            dos dados
	 */
/*
 * private static String formatoImpressaoCategoriaFuncionario(){ String []
 * header = new
 * String[]{"|","#","|","Numero","|","Nome","|","Telefone","|","Morada","|",
 * "Estado","|","Valor pagar","|","Qtd","|","Data Reg","|","Data Act","|"};
 * String formatCaracter = "%s",formatNumero = "%-10.6s", formatNome =
 * "%-28.29s"; String formatTelefone = "%-12.10s",formatMorada =
 * "%-13.10s",formatEstado = "%-6.6s"; String formatValor = "%-13.20s",formatQtd
 * = "%-4.4s",formatData = "%-19.19s"; String formatColl = formatCaracter + " "
 * + formatCaracter + " " + formatCaracter + " " + formatNumero + " " +
 * formatCaracter + " " + formatNome + " " + formatCaracter + " " +
 * formatTelefone + " " + formatCaracter + " " + formatMorada + " " +
 * formatCaracter + " " + formatEstado + " " + formatCaracter + " " +
 * formatValor + " " + formatCaracter + " " + formatQtd + " " + formatCaracter +
 * " " + formatData + " " + formatCaracter + " " + formatData + " " +
 * formatCaracter; System.out.println(); System.out.
 * println("******************************************************************** Lista de Identificao *********************************************************************"
 * ); System.out.println(
 * "************************************************************************************************************************************************************"
 * ); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * );
 * System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4
 * ],header[5],header[6],header[7],header[8],header[9],header[10],header[11],
 * header[12],header[13],header[14],header[15],header[16],header[17],header[18],
 * header[19],header[20]); System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); return formatColl; }
 * 
 * 
 *//**
	 *
	 * @param tamamho
	 * @param empty_
	 * @Descrição imprime uma mensagem para casos de nao houver registos de dados
	 */
/*
 * private static void formatoImpressaoFooter(int tamamho, int empty_){ if
 * (empty_== tamamho){
 * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t Nao ha dados registados");
 * System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); System.out.println(
 * "************************************************************************************************************************************************************"
 * ); }else { System.out.println(
 * "************************************************************************************************************************************************************"
 * ); } System.out.println(); }
 * 
 * 
 * 
 *//**
	 * @Descrição pede o codigo do cliente para informar ao metodo listaDeItens(id)
	 */
/*
 * private static void listaDeItensCliente(){ boolean errorV = false; int count
 * = 0; do { int id =
 * filtrarPorClienteId(validaEntradaInteiro("Informe o codigo do cliente: "));
 * if (id != 0) { listaDeItens(id); errorV = true; }else{ if (id == 0) { errorV
 * = processTimeOut(5); if (errorV) processTimeOutMsg(); } else
 * System.err.println("O Cliente nao existe no sistema!"); } if (!errorV) {
 * count++; errorV = processTimeOut(count); if (errorV) processTimeOutMsg(); } }
 * while (!errorV); }
 * 
 *//**
	 *
	 * @return
	 * @Descrição imprime o formato do cabeçalho da lista de pecas
	 */
/*
 * private static String formatoImpressaoItens(){ String [] header = new
 * String[]{"|","#","|","Ticket","|","Codigo","|","Nome","|","Lavagem","|",
 * "Tecido","|","Peso","|","Valor","|","Data reg","|","Data Act","|","Data Lev"
 * ,"|"}; String formatCaracter = "%s", formatCodigo = "%-6.6s", formatNome =
 * "%-20.20s"; String formatLavagem = "%-8.10s", formatTecido = "%-10.10s",
 * formatPeso = "%-8.10s"; String formatValor = "%-9.10s", formatData =
 * "%-18.18s"; String formatColl = formatCaracter + " " + formatCaracter + " " +
 * formatCaracter + " " + formatCodigo + " " + formatCaracter + " " +
 * formatCodigo + " " + formatCaracter + " " + formatNome + " " + formatCaracter
 * + " " + formatLavagem + " " + formatCaracter + " " + formatTecido + " " +
 * formatCaracter + " " + formatPeso + " " + formatCaracter + " " + formatValor
 * + " " + formatCaracter + " " + formatData + " " + formatCaracter + " " +
 * formatData + " " + formatCaracter + " " + formatData + " " + formatCaracter;
 * System.out.println(); System.out.
 * println("**************************************************************** Lista de Itens ****************************************************************************"
 * ); System.out.println(
 * "************************************************************************************************************************************************************"
 * ); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * );
 * System.out.format(formatColl,header[0],header[1],header[2],header[3],header[4
 * ],header[5],header[6],header[7],header[8],header[9],header[10],header[11],
 * header[12],header[13],header[14],header[15],header[16],header[17],header[18],
 * header[19],header[20],header[21],header[22]); System.out.println();
 * System.out.println(
 * "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); return formatColl; }
 * 
 *//**
	 *
	 * @param posicao
	 * @param layoutFormat
	 * @param numeracao
	 * @Descrição imprime os dados das pecas com base no formato
	 */
/*
 * private static void listaItens(int posicao, String layoutFormat, int
 * numeracao){
 * System.out.format(layoutFormat,"|",numeracao,"|",clienteId[posicao],"|",
 * idItemVenda[posicao],"|",nomeProduto[posicao],"|",cate[posicao],"|",
 * tipoTecido[posicao],"|",peso[posicao],"|",valorApagar[posicao],"|",
 * parseLocalDateTimeToSring(dataRegistoItem[posicao]),"|",
 * parseLocalDateTimeToSring(dataActualizacaoItemVenda[posicao]),"|",
 * parseLocalDateTimeToSring(dataLevantamentoPeca[posicao]),"|"); }
 * 
 *//**
	 * @Descrição imprime uma lista de pecas
	 */
/*
 * private static void listaDeItens(){ int numeracao = 1,empty_ = 0; String
 * layoutFormat = formatoImpressaoItens(); for (int i = 0; i < tamanho; i++) {
 * if (clienteId[i] != 0){ listaItens(i,layoutFormat,numeracao);
 * System.out.println(); System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 *//**
	 * @param id
	 * @Descrição imprime uma lista de pecas com base no ID do cliente
	 */
/*
 * private static void listaDeItens(int id){ int numeracao = 1,empty_ = 0;
 * String layoutFormat = formatoImpressaoItens(); for (int i = 0; i < tamanho;
 * i++) { if (clienteId[i] != 0){ if (clienteId[i] == id) {
 * listaItens(i,layoutFormat,numeracao); System.out.println();
 * System.out.println(
 * "---------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; } }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 * private static void listaDeItens(LocalDateTime date_){ int numeracao =
 * 1,empty_ = 0; String layoutFormat = formatoImpressaoItens(); for (int i = 0;
 * i < tamanho; i++) { if (clienteId[i] != 0){ if
 * (dataLevantamentoPeca[i].isBefore(date_)) {
 * listaItens(i,layoutFormat,numeracao); System.out.println();
 * System.out.println(
 * "------------------------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; } }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 * private static void listaDeItensID(int id){ int numeracao = 1,empty_ = 0;
 * String layoutFormat = formatoImpressaoItens(); for (int i = 0; i < tamanho;
 * i++) { if (clienteId[i] != 0){ if (idItemVenda[i] == id) {
 * listaItens(i,layoutFormat,numeracao); System.out.println();
 * System.out.println(
 * "---------------------------------------------------------------------------------------------------------------------------------------------"
 * ); numeracao+=1; } }else{empty_ += 1; } }
 * formatoImpressaoFooter(idCliente.length,empty_); }
 * 
 * private static boolean gravarDadosIdentificacaoNoFicheiro(String file){
 * boolean error = false; try { if (new File(file).exists()) { BufferedWriter bw
 * = new BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i <
 * tamanho; i++) { if (idIdentificacao[i]!=0){
 * bw.write(idIdentificacao[i]+"|"+nomeIdentificacao[i]+"|"+statusIdentificacao[
 * i]+"|"+ dataRegistoIdentificacao[i]+"|"+dataActualizacaoIdentificacaos[i]);
 * bw.newLine(); } } bw.close(); error = true; } } catch (IOException e) {
 * e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosIdentificacaoNoFicheiro(String file){ boolean
 * error = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idIdentificacao[i] =
 * Integer.parseInt(str.nextToken()); nomeIdentificacao[i] =str.nextToken();
 * statusIdentificacao[i] = Boolean.Boolean(str.nextToken());
 * dataRegistoIdentificacao[i] = StringToLocalDateTime(str.nextToken());
 * dataActualizacaoIdentificacaos[i] = StringToLocalDateTime(str.nextToken());
 * linha = br.readLine(); i++; } br.close(); error = true; } } catch
 * (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean gravarDadosCategoriaFuncionarioNoFicheiro(String
 * file){ boolean error = false; try { if (new File(file).exists()) {
 * BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file))); for
 * (int i = 0; i < tamanho; i++) { if (idCategoriaFuncionario[i]!=0){
 * bw.write(idCategoriaFuncionario[i]+"|"+nomeCategoriraFuncionario[i]+"|"+
 * statusCategoriraFuncionario[i]+"|"+
 * dataRegistoCategoriraFuncionario[i]+"|"+dataActualizacaoCategoriraFuncionario
 * [i]); bw.newLine(); } } bw.close(); error = true; } } catch (IOException e) {
 * e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosCategoriaFuncionarioNoFicheiro(String file){
 * boolean error = false; StringTokenizer str; try { if (new
 * File(file).exists()) { BufferedReader br = new BufferedReader(new
 * FileReader(new File(file))); String linha = br.readLine(); int i = 0; while
 * (linha!=null){ str = new StringTokenizer(linha,"|");
 * idCategoriaFuncionario[i] = Integer.Int(str.nextToken());
 * nomeCategoriraFuncionario [i] =str.nextToken();
 * statusCategoriraFuncionario[i] = Boolean.Boolean(str.nextToken());
 * dataRegistoCategoriraFuncionario[i] =
 * parseStringToLocalDateTime(str.nextToken());
 * dataActualizacaoCategoriraFuncionario[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosFuncionarioNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idFuncionario[i]!=0){
 * bw.write(idFuncionario[i]+"|"+nomeFuncionario[i]+"|"+
 * idIdentificacaoFuncionario[i]+"|"+nuitFunionario[i]+"|"+moradaFuncionario[i]+
 * "|"+ statusFuncionario[i]+"|"+dataRegistoCategoriraFuncionario[i]+"|"+
 * dataActualizacaoCategoriraFuncionario[i]); bw.newLine(); } } bw.close();
 * error = true; } } catch (IOException e) { e.printStackTrace(); } return
 * error; }
 * 
 * private static boolean lerDadosFuncionarioNoFicheiro(String file){ boolean
 * error = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idFuncionario[i] =
 * Integer.parseInt(str.nextToken()); nomeFuncionario [i] =str.nextToken();
 * idIdentificacaoFuncionario [i] = Integer.parseInt(str.nextToken());
 * nuitFunionario [i] = Integer.parseInt(str.nextToken()); moradaFuncionario [i]
 * =str.nextToken(); statusFuncionario[i] =
 * Boolean.parseBoolean(str.nextToken()); dataRegistoFuncionario[i] =
 * parseStringToLocalDateTime(str.nextToken()); dataActualizacaoFuncionario[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosLogSistemaNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idLogSistema[i]!=0){
 * bw.write(idLogSistema[i]+"|"+idUsuarioLogSistema[i]+"|"+tipoLogSistema[i]+"|"
 * +eventoLogSistema[i]+"|"+dataRegistoLogSistema[i]); bw.newLine(); } }
 * bw.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean lerDadosLogSistemaNoFicheiro(String file){ boolean
 * error = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idLogSistema[i] =
 * Integer.parseInt(str.nextToken()); idUsuarioLogSistema [i] =
 * Integer.parseInt(str.nextToken()); tipoLogSistema [i] = str.nextToken();
 * eventoLogSistema [i] =str.nextToken(); dataRegistoLogSistema[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosPermissaoSistemaNoFicheiro(String file){
 * boolean error = false; try { if (new File(file).exists()) { BufferedWriter bw
 * = new BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i <
 * tamanho; i++) { if (idPermissaoSistema[i]!=0){
 * bw.write(idPermissaoSistema[i]+"|"+nomePermissaoSistema[i]+"|"+
 * statusPermissaoSistema[i]+"|"+dataRegistoPermissaoSistema[i]+"|"+
 * dataActualizacaoPermissaoSistema[i]); bw.newLine(); } } bw.close(); error =
 * true; } } catch (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosPermissaoSistemaNoFicheir(String file){
 * boolean error = false; StringTokenizer str; try { if (new
 * File(file).exists()) { BufferedReader br = new BufferedReader(new
 * FileReader(new File(file))); String linha = br.readLine(); int i = 0; while
 * (linha!=null){ str = new StringTokenizer(linha,"|"); idPermissaoSistema[i] =
 * Integer.parseInt(str.nextToken()); nomePermissaoSistema [i] =str.nextToken();
 * statusPermissaoSistema[i] = Boolean.parseBoolean(str.nextToken());
 * dataRegistoPermissaoSistema[i] = parseStringToLocalDateTime(str.nextToken());
 * dataActualizacaoPermissaoSistema[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosUsuarioNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idUsuario[i]!=0){
 * bw.write(idUsuario[i]+"|"+idFunionarioUsuario[i]+"|"+
 * idPermissaoSistemaUsuario[i]+"|"+statusUsuario[i]+"|"+dataRegistoUsuario[i]+
 * "|"+dataActualizacaoUsuario[i]); bw.newLine(); } } bw.close(); error = true;
 * } } catch (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosUsuarioNoFicheiro(String file){ boolean error
 * = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idUsuario[i] = Integer.parseInt(str.nextToken());
 * idFunionarioUsuario [i] = Integer.parseInt(str.nextToken());
 * idPermissaoSistemaUsuario [i] = Integer.parseInt(str.nextToken());
 * nuitFunionario [i] = Integer.parseInt(str.nextToken()); moradaFuncionario [i]
 * =str.nextToken(); statusUsuario[i] = Boolean.parseBoolean(str.nextToken());
 * dataRegistoUsuario[i] = parseStringToLocalDateTime(str.nextToken());
 * dataActualizacaoUsuario[i] = parseStringToLocalDateTime(str.nextToken());
 * linha = br.readLine(); i++; } br.close(); error = true; } } catch
 * (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean gravarDadosFornecedorNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idFornecedor[i]!=0){
 * bw.write(idFornecedor[i]+"|"+nomeFornecedor[i]+"|"+moradaFornecedor[i]+"|"+
 * nuitFornecedor[i]+"|"+tipoPessoaFornecedor[i]+"|"+
 * statusFornecedor[i]+"|"+dataRegistoFornecedor[i]+"|"+
 * dataActualizacaoFornecedor[i]); bw.newLine(); } } bw.close(); error = true; }
 * } catch (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosFornecedorNoFicheiro(String file){ boolean
 * error = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idFornecedor[i] =
 * Integer.parseInt(str.nextToken()); nomeFornecedor [i] = str.nextToken();
 * moradaFornecedor [i] =str.nextToken(); nuitFornecedor [i] =
 * Integer.parseInt(str.nextToken()); tipoPessoaFornecedor [i] =str.nextToken();
 * statusFornecedor[i] = Boolean.parseBoolean(str.nextToken());
 * dataRegistoFornecedor[i] = parseStringToLocalDateTime(str.nextToken());
 * dataActualizacaoFornecedor[i] = parseStringToLocalDateTime(str.nextToken());
 * linha = br.readLine(); i++; } br.close(); error = true; } } catch
 * (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean gravarDadosClienteNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idCliente[i]!=0){
 * bw.write(idCliente[i]+"|"+nomeCliente[i]+"|"+moradaCliente[i]); bw.newLine();
 * } } bw.close(); error = true; } } catch (IOException e) {
 * e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosClienteNoFicheiro(String file){ boolean error
 * = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idCliente[i] = Integer.parseInt(str.nextToken());
 * nomeCliente [i] =str.nextToken(); moradaCliente[i] =str.nextToken(); linha =
 * br.readLine(); i++; } br.close(); error = true; } } catch (IOException e) {
 * e.printStackTrace(); } return error; }
 * 
 * private static boolean gravarDadosCategoriaProdutoNoFicheiro(String file){
 * boolean error = false; try { if (new File(file).exists()) { BufferedWriter bw
 * = new BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i <
 * tamanho; i++) { if (idCategoriaProduto[i]!=0){
 * bw.write(idCategoriaProduto[i]+"|"+nomeCategoriaProduto[i]+"|"+
 * statusCategoriaProduto[i]+"|"+observacoesCategoriaProduto[i]+"|"+
 * dataRegistoCategoriaProduto[i]+"|"+dataActualizacaoCategoriaProduto[i]);
 * bw.newLine(); } } bw.close(); error = true; } } catch (IOException e) {
 * e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosCategoriaProdutoNoFicheiro(String file){
 * boolean error = false; StringTokenizer str; try { if (new
 * File(file).exists()) { BufferedReader br = new BufferedReader(new
 * FileReader(new File(file))); String linha = br.readLine(); int i = 0; while
 * (linha!=null){ str = new StringTokenizer(linha,"|"); idCategoriaProduto[i] =
 * Integer.parseInt(str.nextToken()); nomeCategoriaProduto [i] =
 * str.nextToken(); statusCategoriaProduto[i] =
 * Boolean.parseBoolean(str.nextToken()); observacoesCategoriaProduto [i]
 * =str.nextToken(); dataRegistoCategoriaProduto[i] =
 * parseStringToLocalDateTime(str.nextToken());
 * dataActualizacaoCategoriaProduto[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosProdutoNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idProduto[i]!=0){
 * bw.write(idProduto[i]+"|"+idFornecedorProduto[i]+"|"+idUsuarioProduto[i]+"|"+
 * idProdutoCategoria[i]+"|"+nomeProduto[i]+"|"+
 * marcaProduto[i]+"|"+quantidadeProduto[i]+"|"+observacoesProduto[i]+"|"+
 * statusProduto[i]+"|"+
 * precoVendaProduto[i]+"|"+dataValidadeProduto[i]+"|"+dataRegistoProduto[i]+"|"
 * +dataActualizacaoProduto[i]); bw.newLine(); } } bw.close(); error = true; } }
 * catch (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosProdutoNoFicheiro(String file){ boolean error
 * = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idProduto[i] = Integer.parseInt(str.nextToken());
 * idFornecedorProduto[i] = Integer.parseInt(str.nextToken());
 * idUsuarioProduto[i] = Integer.parseInt(str.nextToken());
 * idProdutoCategoria[i] = Integer.parseInt(str.nextToken()); nomeProduto [i]
 * =str.nextToken(); marcaProduto [i] =str.nextToken(); quantidadeProduto[i] =
 * Integer.parseInt(str.nextToken()); observacoesProduto [i] =str.nextToken();
 * statusProduto[i] = Boolean.parseBoolean(str.nextToken());
 * precoVendaProduto[i] = Double.parseDouble(str.nextToken());
 * dataValidadeProduto[i] = parseStringToLocalDateTime(str.nextToken());
 * dataRegistoProduto[i] = parseStringToLocalDateTime(str.nextToken());
 * dataActualizacaoProduto[i] = parseStringToLocalDateTime(str.nextToken());
 * linha = br.readLine(); i++; } br.close(); error = true; } } catch
 * (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean gravarDadosVendaNoFicheiro(String file){ boolean error
 * = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idVenda[i]!=0){
 * bw.write(idVenda[i]+"|"+idClienteVenda[i]+"|"+idUsuarioVenda[i]+"|"+
 * quantidadeItensVenda[i]+"|"+quantidadeItensVenda[i]+"|"+valorPagoVenda[i]+
 * "|"+ dataRegistoVenda[i]+"|"+dataActualizacaoVenda[i]); bw.newLine(); } }
 * bw.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean lerDadosVendaNoFicheiro(String file){ boolean error =
 * false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idVenda[i] = Integer.parseInt(str.nextToken());
 * idClienteVenda[i] = Integer.parseInt(str.nextToken()); idUsuarioVenda[i] =
 * Integer.parseInt(str.nextToken()); quantidadeItensVenda[i] =
 * Integer.parseInt(str.nextToken()); valorPagoVenda[i] =
 * Double.parseDouble(str.nextToken()); dataRegistoVenda[i] =
 * parseStringToLocalDateTime(str.nextToken()); dataActualizacaoVenda[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosItemVendaNoFicheiro(String file){ boolean
 * error = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idItemVenda[i]!=0){
 * bw.write(idItemVenda[i]+"|"+idVendaItemVenda[i]+"|"+idProdutoItemVenda[i]+"|"
 * +dataActualizacaoItemVenda[i]); bw.newLine(); } } bw.close(); error = true; }
 * } catch (IOException e) { e.printStackTrace(); } return error; }
 * 
 * private static boolean lerDadosItemVendaNoFicheiro(String file){ boolean
 * error = false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idItemVenda[i] =
 * Integer.parseInt(str.nextToken()); idVendaItemVenda[i] =
 * Integer.parseInt(str.nextToken()); idProdutoItemVenda[i] =
 * Integer.parseInt(str.nextToken()); dataActualizacaoItemVenda[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean gravarDadosContaNoFicheiro(String file){ boolean error
 * = false; try { if (new File(file).exists()) { BufferedWriter bw = new
 * BufferedWriter(new FileWriter(new File(file))); for (int i = 0; i < tamanho;
 * i++) { if (idConta[i]!=0){
 * bw.write(idConta[i]+"|"+nomeConta[i]+"|"+numeroConta[i]+"|"+statusConta[i]+
 * "|"+ dataRegistoConta[i]+"|"+dataActualizacaoConta[i]); bw.newLine(); } }
 * bw.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * private static boolean lerDadosContaNoFicheiro(String file){ boolean error =
 * false; StringTokenizer str; try { if (new File(file).exists()) {
 * BufferedReader br = new BufferedReader(new FileReader(new File(file)));
 * String linha = br.readLine(); int i = 0; while (linha!=null){ str = new
 * StringTokenizer(linha,"|"); idConta[i] = Integer.parseInt(str.nextToken());
 * nomeConta[i] = str.nextToken(); numeroConta[i] =
 * Integer.parseInt(str.nextToken()); statusConta[i] =
 * Boolean.parseBoolean(str.nextToken()); dataRegistoConta[i] =
 * parseStringToLocalDateTime(str.nextToken()); dataActualizacaoConta[i] =
 * parseStringToLocalDateTime(str.nextToken()); linha = br.readLine(); i++; }
 * br.close(); error = true; } } catch (IOException e) { e.printStackTrace(); }
 * return error; }
 * 
 * 
 * private static void populaArray(){ //
 * lerDoFicheiroDadosPecasCliente(filesPathItem); //
 * lerDoFicheiroDadosCliente(filesPathCliente); }
 * 
 * private static void checkoutItem() { boolean errorV = false; int count = 0;
 * do { try {
 * 
 * int idc =
 * filtrarPorClienteId(validaEntradaInteiro("Informe o codigo do cliente: "));
 * if (idc != 0) { if (quantidadeItensCliente(idc)>0){ listaDeItens(idc); int
 * idp = filtrarPorItemId(validaEntradaInteiro("Informe o Codigo do item: "));
 * if (idp != 0) { boolean error = false; do { System.out.
 * print("Tem certeza de que deseja efetuar a Verificacao de Saida do item do cliente [S - sim ou N - nao]: "
 * ); char selecao = (char)br.read(); switch
 * ((Character.toString(selecao)).toLowerCase()){ case "s": for (int i = 0; i <
 * idItemVenda.length; i++) { if (idItemVenda[i]!=0){ if (idItemVenda[i]==idp) {
 * conta[0] = (conta[0]!=0.0)?conta[0] += valorApagar[i]:valorApagar[i];
 * gravarSaldoConta(filesPathConta); quantidadeItem[(null_Item()+1)] = cate[i];
 * gravarMaisFequente(filesPathTipoLavagem);
 * preencheDadosItens(i,0,0,null,null,null,0,0,null,null,null); } } } error =
 * true; break; case "n": error = true; break; default: break; } } while
 * (!error); errorV = true; } }else{
 * System.err.println("O cliente nao tem itens associados a ele");
 * listaDeItens(); } }else {
 * System.err.println("O codigo do cliente nao existe!"); } count++; if
 * (!errorV) { errorV = processTimeOut(count); if (errorV) processTimeOutMsg();
 * }
 * 
 * } catch (Exception e) { e.printStackTrace(); } } while (!errorV); }
 * 
 * private static void gerirLavagensEContas(){ do{ options =
 * menuGestaoLavagemsEContas(); switch (options){ case 1:
 *//** Verificacao de Saida de Itens de Cliente */
/*
 * checkoutItem(); break; case 2:
 *//** Verificar Itens com data de levantamento expirado */
/*
 * listaDeItens(LocalDateTime.now()); break; case 3:
 *//** Tipo de Lavagem Mais Frequente */
/*
 * //lavagemFrequente(); break; case 4:
 *//** Conta */
/*
 * //saldoConta(); break; case 5:
 *//** Voltar para o menu principal */
/*
 * 
 * break; default:break; } }while (options != 5); }
 * 
 * private static void menuPrincipal() { populaArray(); int count = 0; do{
 * options = menu(); switch (options) { case 1://Clientes e pecas
 * clientesEPecas(); break; case 2://Gerir Lavagens e Contas
 * gerirLavagensEContas(); break; case 3://Sair
 * System.out.print("Muito obrigado, ate mais"); break; default:
 * System.err.println("Opcao selecionada invalida"); break; } count++; if
 * (count>5){ options = 3; System.err.
 * println("\n\n\n*******************\nSeccao bloqueada!!!\nGood bye ..."); }
 * }while (options != 3); }
 * 
 *//**
	 * Metodo main
	 *//*
		 * public static void main(String[] args) { menuPrincipal(); } }
		 */