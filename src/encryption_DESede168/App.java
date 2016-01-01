package encryption_DESede168;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class App {
	private static final String TIPO ="DESede";
	private static final byte[] CLAVE = new byte[]{'1','2','3','4','5','6','7','8','9','0',
												   '1','2','3','4','5','6','7','8','9','0',
												   '1'};


    // programa principal
	public static void main(String args[]) throws Exception{
		String message1 = "THIS IS SPARTA";//mensaje de longitud 14 bytes
		//0101010001001000010010010101001100100000010010010101001100100000010100110101000001000001010100100101010001000001
		String message2 = "UHIS IS SPARTA";//mensaje con el octavo bit cambiado
		//0101010101001000010010010101001100100000010010010101001100100000010100110101000001000001010100100101010001000001
		String clave1 = "MiwQlIMgLEP3Byw7LOoqlyM342IISRlz";//clave de 32 bits
		String clave2 = "LiwQlIMgLEP3Byw7LOoqlyM342IISRlz";//clave cambiada el octavo bit de 1 a 0				
		
		//la clave 1 se pasa a bytes y después se convierte a secret key, finalmente se cifra
		byte[] claveEnBytes1 = Base64.getDecoder().decode(clave1);
		SecretKey claveGenerada1 = new SecretKeySpec(claveEnBytes1, 0, claveEnBytes1.length, TIPO);				
		String encryptedMessage1 = TripleDES168.cifrar(message1, claveGenerada1);
		
		//la clave 2 se pasa a bytes y después se convierte a secret key, finalmente se cifra
		byte[] claveEnBytes2 = Base64.getDecoder().decode(clave2);
		SecretKey claveGenerada2 = new SecretKeySpec(claveEnBytes2, 0, claveEnBytes2.length, TIPO);				
		String encryptedMessage2 = TripleDES168.cifrar(message1, claveGenerada2);		
		
		//la clave 1 y mensaje 2 se pasa a bytes y después se convierte a secret key, finalmente se cifra
						
		String encryptedMessage3 = TripleDES168.cifrar(message2, claveGenerada1);
		
		System.out.println("#Mensaje en claro:\t" + message1);				
		System.out.println("Clave:\t\t\t" + clave1);
		System.out.println("##Mensaje cifrado:\t" + encryptedMessage1);
		System.out.println();		
		System.out.println("#Mensaje en claro:\t" + message1);
		System.out.println("Clave:\t\t\t" + clave2);
		System.out.println("##Mensaje cifrado2:\t" + encryptedMessage2);
		System.out.println();		
		System.out.println("#Mensaje en claro:\t" + message2);
		System.out.println("Clave:\t\t\t" + clave1);
		System.out.println("##Mensaje cifrado3:\t" + encryptedMessage3);
		//calculo de la distancia entre los dos mensajes cifrados
		/////Hamming h = new Hamming(encryptedMessage1,encryptedMessage2);
		System.out.println();

		//ahora cambio el 
	}


}
