package gestaofarmacia;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;


public class Validacao {



	/**
	 * Color out print
	 **/	
	public static final String ANSI_GREEN = "\u001B[32m";

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter dateTimeFormatterInput = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);    
	public static Random random = new Random();
	private static final int tamanho = 10000;
	public static final char delimitador = '|'; 

	public static int getTamanho() {
		return tamanho;
	}

	/*#--------------------------------------------------------------------------------#
									Metodos genericos
	  #--------------------------------------------------------------------------------#*/

	/**	 	
	 * @param <E>
	 * @param <obj>
	 * @Descrição Inicializa o array de objecto com valores nulos com recurso a genericos
	 */
	public static <E, obj> E[] init(E[] anArray) {
		for (int i = 0; i < anArray.length; i++)			
			anArray[i] = null;							
		return anArray;
	}

	/**	 	
	 * @param <E>
	 * @Descrição retorna quantidade de registos nao nulos que tem no array
	 */
	public static <E> int notNull(E[] anArray) {		
		int not_null = 0;		
		for (int i = 0; i < anArray.length; i++)
			if(anArray[i] != null)			
				not_null+=1; 				
		return not_null;
	}	

	/*#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------#*/

	public static void validaGravacao(int retorno, boolean error, String msg_success, String msg_unsuccess) {
		if (retorno != 0) {
			String msg = null;
			if (error)
				msg = msg_success;				
			else 
				msg = msg_unsuccess;		
			System.out.println(msg);
		}
	}

	public static String geraDirectorioFicheiro(String fileNames) {
		Path currentAbsolutePath1 = Paths.get("");
		String filePath1 = currentAbsolutePath1.toAbsolutePath().toString().concat("\\files");
		File fileDirectory = new File(filePath1);
		String filePath2 = filePath1.concat("\\"+fileNames);
		File file = new File(filePath2);
		try {
			if (!new File(filePath1).exists()) {				
				fileDirectory.mkdir();
				if (!new File(filePath2).exists())
					file.createNewFile();				
			}else {
				if (!new File(filePath2).exists())
					file.createNewFile();				
			}
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return filePath2;
	}

	public static String destroiDirectorioFicheiro(String fileNames) {
		Path currentAbsolutePath1 = Paths.get("");
		String filePath1 = currentAbsolutePath1.toAbsolutePath().toString().concat("\\files");
		File fileDirectory = new File(filePath1);
		String filePath2 = filePath1.concat("\\"+fileNames);
		File file = new File(filePath2);
		try {
			if (!new File(filePath1).exists()) {				
				fileDirectory.mkdir();											
			}else {
				if (new File(filePath2).exists())				
					file.delete();
			}
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return filePath2;
	}

	/**
	 *
	 * @param data_
	 * @return
	 * @Descrição converte data em string e retornar uma string de data
	 */
	public static String parseLocalDateTimeToSring(LocalDateTime data_){
		String _data = null;
		if (data_!=null)
			_data = dateTimeFormatter.format(data_);

		return _data;
	}
	/**
	 *
	 * @param data_
	 * @return
	 * @Descrição converte string em data e retornar uma data
	 */
	public static LocalDateTime parseStringToLocalDateTime(String data_){
		LocalDateTime _data = null;
		if (data_ != null && data_!="null")
			_data = LocalDateTime.parse(data_);
		else
			_data = null;
		return _data;
	}

	/**
	 *
	 * @return
	 * @Descrição retorna quantas registos nao nulos que tem no array
	 */

	public static int notNull_Array(int [] idArray){
		int not_null = 0;
		for (int i = 0; i < idArray.length; i++)
			if  (idArray[i] != 0)
				not_null+=1;
		return not_null;
	}


	/**    
	 * Metodos de Validacao
	 */      

	/**    
	 * @Descrição valida e nao permite repeticao de ID's usando o metodo recursivo
	 */
	public static int validaID(int j, int [] idArray){
		boolean exise = false;
		int id = (1 + random.nextInt(idArray.length));
		int newID = 0;
		for (int i = 0; i < idArray.length; i++) {
			if (idArray[i] == id ||  id == 0)
				exise = true;
		}
		if (exise)
			validaID(j,idArray);
		else
			newID = idArray[j] = id;
		return newID;
	}

	/**
	 *
	 * @param count
	 * @return
	 * @Descrição para um processo apos 3 tentativas de erro (fail2ban)
	 */
	public static boolean processTimeOut(int count){
		boolean error = false;
		if (count>=3){
			error = true;
		}
		return error;
	}


	/**
	 *
	 * @param valor
	 * @return
	 * @Descrição: valida se o valor informado e uma String e se contem espacos em branco
	 */
	public static boolean validaDados(String valor){
		return valor.matches("[a-zA-Z/\\s/]*") && !valor.equals("") || Character.isAlphabetic(valor.charAt(valor.length() -1));
	}
	
	public static boolean validaDados(char valor){
		return Character.isDigit(valor);
	}

	/**
	 *
	 * @param valor
	 * @return
	 * @Descrição: valida se o valor informado e um numero inteiro diferente de 0 e se contem espacos em branco
	 */
	public static boolean validaDados(int valor){
		return (Integer.toString(valor)).matches("[0-9]*") && valor != 0 && !Integer.toString(valor).isBlank();
	}

	/**
	 *
	 * @param valor
	 * @return
	 * @Descrição validar se realmente sao numeros flutuantes
	 */
	public static boolean validaDados(float valor){
		return (Float.toString(valor)).matches("[0.0-9.0]*") && valor != 0.0;
	}

	static boolean validarVerificacaoForeign(int idAcomparar,int [] idArray) {
		boolean error = false;
		for (int i = 0; i < idArray.length; i++) {
			if (idArray[i] == idAcomparar) {
				error = true;
			}
		}
		return error;
	}

	/**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar recebe uma String para validacao
	 */
	public static String validaEntradaPalavra(String msg){
		String valor = null;
		boolean error = false;
		int count = 0;
		do {
			try {
				System.out.print(msg);
				valor = br.readLine();
				if (validaDados(valor)) {
					error = true;
				}else {
					System.err.println(Language.language_input_valid_name());
					valor = null;
				}

			} catch (Exception e) {
				System.err.println(Language.language_invalid_value());
			}
			count++;
			if (!error) {
				error = processTimeOut(count);
			}
		} while (!error);
		return valor;
	}
	

	/**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar estado
	 */
	public static Boolean validaEntradaStatus(String msg){
		boolean error = false, valor = false;
		int count = 0;
		do {
			try {
				System.out.print(msg);
				String recebe = br.readLine();
				if (recebe.equalsIgnoreCase(Language.language_active())) {
					valor = true;
					error = true;
				}else if (recebe.equalsIgnoreCase(Language.language_inactive())) {
					valor = false;
					error = true;
				}else{
					System.err.println(Language.language_input_valid_state());
				}
			} catch (Exception e) {
				System.err.println(Language.language_invalid_value());
			}
			count++;
			if (!error) {
				error = processTimeOut(count);
			}
		} while (!error);
		return valor;
	}

	public static LocalDateTime validaDadosData(String language_expirationDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *
	 * @param nome
	 * @return
	 * @Descrição validar Dados por nome
	 */
	public static String validaPorNome(String nome, String [] nomeArray){
		boolean error = false;
		try {
			for (int i = 0; i < nomeArray.length; i++) {
				if (nome.equalsIgnoreCase(nomeArray[i]))
					error = true;
			}
			if (nome.equals(null))
				error = true;
		} catch (Exception e) {
		}
		if (!error){
			return nome;
		}else {
			return validaPorNome(validaEntradaPalavra(Language.language_input_exist_name()), nomeArray);
		}
	}

	/**
	 *
	 * @param nome
	 * @return
	 * @Descrição validar Dados por nome da identificacao
	 */
	public static String validaPorNome(String [] nomeArray, String nome){
		boolean error = false;
		try {
			for (int i = 0; i < nomeArray.length; i++) {
				if (nome.equalsIgnoreCase(nomeArray[i]))
					error = true;
			}
			if (nome.equals(null))
				error = true;
		} catch (Exception e) {
		}
		if (!error){         
			return validaPorNome(nomeArray,validaEntradaPalavra(Language.language_input_exist_name()));
		}else {
			return nome;
		}
	}

	/**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar numero inteiro
	 */
	public static int validaEntradaInteiro(String msg){
		int valor = 0,count = 0;
		boolean error = false;
		do {
			try {
				System.out.print(msg);
				valor = Integer.parseInt(br.readLine());
				if (validaDados(valor)) {
					error = true;
				}else {
					validaEntradaByte(msg);
				}

			} catch (Exception e) {      
			}
			if (!error) {
				count++;
				error = processTimeOut(count);
				if (error)
					valor = 0;
			}
		} while (!error);
		return valor;
	}

	/**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar numero float
	 */
	public static float validaEntradaFlutuante(String msg){
		float valor = 0.0f;
		boolean error = false;
		do {
			try {
				System.out.print(msg);
				valor = Integer.parseInt(br.readLine());
				if (validaDados(valor)) {
					error = true;
				}else {
					System.err.println(Language.language_valid_value());
				}

			} catch (Exception e) {
				System.err.println(Language.language_invalid_value());
			}
		} while (!error);
		return valor;
	}

	/**
	 *
	 * @param msg
	 * @return
	 * @Descrição validar numero byte
	 */
	public static byte validaEntradaByte(String msg){
		byte valor = 0;
		boolean error = false;
		do {
			try {
				System.out.print(msg);
				valor = Byte.parseByte(br.readLine());
				if (validaDados(valor)) {
					error = true;
				}else {
					System.err.println(Language.language_valid_value());
				}

			} catch (Exception e) {
			}
		} while (!error);
		return valor;
	}

	/**
	 *
	 * @param i
	 * @return
	 * @Descrição altera a viualizacao do estado de true ou false para activo ou inactivo
	 */
	public static String mudarStatus(boolean status){
		String state = "Inactivo";
		if (status){
			state = "Activo";
		}
		return state;
	}



	/**
	 *
	 * @param nome
	 * @return
	 * @Descrição recebe o nome do cliente e devolve o ID
	 */    
	public static int getIdByName(String nome, int [] idArray, String [] nomeArray) {
		int id = 0;
		if (nome!=null) {
			for (int i = 0; i < nomeArray.length; i++) {
				if (idArray[i] != 0) {
					if (nomeArray[i].equalsIgnoreCase(nome)) {
						id = idArray[i];
					}
				}
			}
		}
		return id;
	}

	/**
	 *
	 * @param id
	 * @return
	 * @Descrição recebe o ID do cliente, consulta se existe e devolve o ID
	 */
	public static int getById(int idi, int [] idArray) {
		int id = 0;        
		if (idi!=0) {
			for (int i = 0; i < idArray.length; i++) {
				if (idArray[i] != 0) {
					if (idArray[i] == idi) {
						id = idArray[i];
					}
				}
			} 
		}       
		return id;
	}


	/**
	 *
	 * @param
	 * @return
	 * @Descrição informa que o processo foi parado
	 */
	public static void processTimeOutMsg(){
		System.out.println();
		System.err.println("******************************************************************\n" +
				Language.language_time_out());
	}

	/**	 
	 * @param tamamho
	 * @param empty_
	 * @Descrição imprime uma mensagem para casos de nao houver registos de dados
	 */
	public static void formatoImpressaoFooter(int tamamho, int empty_){
		if (empty_== tamamho){
			System.out.println("\t\t\t\t\t\t\t\t\t  " + Language.language_empty_regist());
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("************************************************************************************************************************************************************");
		}else {
			System.out.println("************************************************************************************************************************************************************");
		}
		System.out.println();
	}
	
	/**       
	 * @Descrição Menu de atualizacao de dados any class
	 */
	public static byte menu(String obj) {
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+ Language.language_menu(obj));
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+ Language.language_register());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("2. "+ Language.language_update());
		System.out.println("-----------------------------------------------------------------------------------");     
		System.out.println("3. "+ Language.language_delete());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("4. "+ Language.language_listing());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("5. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
	
	/**       
	 * @Descrição Menu de atualizacao de dados any class
	 */
	public static byte menuPermissions(String obj) {
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t"+ Language.language_menu(obj));
		System.out.println("***********************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("1. "+ Language.language_listing());
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("2. "+ Language.language_cancel());
		System.out.println("***********************************************************************************");
		return Validacao.validaEntradaByte(Language.language_select_option());
	}
}
