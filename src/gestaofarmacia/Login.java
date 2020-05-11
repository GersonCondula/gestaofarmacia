package gestaofarmacia;

import java.io.IOException;

public class Login {
	private static int count = 0;
	private static Usuario usuario; 
	public static Usuario getUsuario_id() {
		return usuario;
	}
	public static void setUsuario_id(Usuario usuario) {
		Login.usuario = usuario;
	}
	public static Usuario login(Usuario [] usuarios) throws IOException {	
		System.out.println("\n\n");
		System.out.println("***********************************************************************************");
		System.out.println("\t\t\t\t\t"+Language.language_login());
		System.out.println("***********************************************************************************");
		String usuario = Validacao.validaEntradaPalavra(Language.language_input_name());		
		String password = Validacao.validaEntradaPalavra(Language.language_input_password());
		Usuario usuario1 = null;			
		for (Usuario usuario2 : usuarios) {
			if (usuario2 != null) {
				if (usuario2.getUsername().toLowerCase().equals(usuario.toLowerCase())
						&& usuario2.getPassword().toLowerCase().equals(password.toLowerCase())) {
					usuario1 = usuario2;
				} else {
					count++;
					if (count < 3) {
						System.out.println(count);
						usuario1 = login(usuarios);
					}else {
						System.err.println(Language.language_time_out());
					}
				} 
			}
		}
		return usuario1;
	}
}
