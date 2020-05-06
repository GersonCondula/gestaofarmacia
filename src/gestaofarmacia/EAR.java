package gestaofarmacia;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class EAR {
	private static BufferedReader br;
	private static BufferedWriter bw;		

	/**	 	
	 * @param <E>
	 * @return 
	 * @Descri��o Inicializa o array de objecto com valores nulos com recurso a genericos
	 */
	public static <E> E[] init(E[] anArray) {
		for (int i = 0; i < anArray.length; i++)			
			anArray[i] = null;							
		return anArray;
	}
	
	/**	 	
	 * @param <E>
	 * @Descri��o retorna quantidade de registos nao nulos que tem no array
	 */
	public static <E> int notNull(E[] anArray) {		
		int not_null = 0;		
		for (int i = 0; i < anArray.length; i++)
			if(anArray.equals(null))			
				not_null+=1; 					
		return not_null;
	}
}
