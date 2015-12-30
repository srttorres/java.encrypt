package encryption_DESede168;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class App {
    private static final String TIPO = "DESede";
    private static final byte[] CLAVE = new byte[]{'1','2','3','4','5','6','7','8','9','0','1','2','3'};
	public static void main(String args[]) throws Exception{
		String message = "THIS IS SPARTA";
		Key key = generateKey();

		String encryptedMessage = TripleDES168.cifrar(message, key);		
		System.out.println("Mensaje en claro: " + message);
		System.out.println("Mensaje cifrado: "+ encryptedMessage);
	}
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(CLAVE, TIPO);
        return key;
}
}
