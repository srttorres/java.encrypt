package encryption_DESede168;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class clave {
	private static final String TIPO ="DESede";
	private static final byte[] CLAVE = new byte[]{'1','2','3','4','5','6','7','8','9','0',
												   '1','2','3','4','5','6','7','8','9','0',
												   '1'};
	public static void main(String args[]) throws Exception{
	SecretKey claveGenerada1 = generateKey();
	String stringClaveGenerada = Base64.getEncoder().encodeToString(claveGenerada1.getEncoded());
	System.out.println("Clave generada: " + stringClaveGenerada);
	}
	
	private static SecretKey generateKey() throws Exception {
		KeyGenerator generador = KeyGenerator.getInstance(TIPO);
		SecretKey claveGenerada = generador.generateKey();
		return claveGenerada;
	}

}
