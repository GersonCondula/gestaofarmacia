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
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);    
    public static Random random = new Random();
    private static final int tamanho = 10000;
    public static final char delimitador = '|'; 
		
	public static int getTamanho() {
		return tamanho;
	}
	
	public static void validaGravacao(int retorno, String msg) {
		if (retorno != 0) {
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
			// TODO Auto-generated catch block
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
    return valor.matches("[a-zA-Z/\\s/]*") && !valor.equals("");
}

/**
 *
 * @param valor
 * @return
 * @Descrição: valida se o valor informado e um numero inteiro diferente de 0 e se contem espacos em branco
 */
public static boolean validaDados(int valor){
    return (Integer.toString(valor)).matches("[0-9]*") && valor != 0;
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
                System.err.println("Informe nome valido.");
                valor = null;
            }

        } catch (Exception e) {
            System.err.println("Valor Invalido!");
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
            if (recebe.equalsIgnoreCase("Activo")) {
                valor = true;
                error = true;
            }else if (recebe.equalsIgnoreCase("Inactivo")) {
                valor = false;
                error = true;
            }else{
                System.err.println("Informe um estado valido [Activo ou Inactivo]");
            }
        } catch (Exception e) {
            System.err.println("Valor Invalido!");
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
        return validaPorNome(validaEntradaPalavra("Nome Ja existe, Informe o nome do cliente: "), nomeArray);
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
       return validaPorNome(nomeArray,validaEntradaPalavra("Nome nao existe, Informe o nome da identificacao valida: "));
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
               System.err.println("Informe valor valido: ");
           }

       } catch (Exception e) {
           System.err.println("Informe numero valido: ");
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
               System.err.println("Informe valor valido.");
           }

       } catch (Exception e) {
           System.err.println("Valor Invalido!");
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
               System.err.println("Informe valor valido.");
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
              "Esgotaram-se as tentativas, revise bem os dados e tente de novo...");
  }
  
  /**
  *
  * @param tamamho
  * @param empty_
  * @Descrição imprime uma mensagem para casos de nao houver registos de dados
  */
 public static void formatoImpressaoFooter(int tamamho, int empty_){
     if (empty_== tamamho){
         System.out.println("\t\t\t\t\t\t\t\t  Nao ha dados registados");
         System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.println("************************************************************************************************************************************************************");
     }else {
         System.out.println("************************************************************************************************************************************************************");
     }
     System.out.println();
 }

}
