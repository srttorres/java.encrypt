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
		//clave generada1
		SecretKey claveGenerada1 = generateKey();
		
		//pasar la claveGenerada a string, luego a array de bytes y luego a SecretKey, creando una copia_claveGenerada1
		String stringClaveGenerada1 = Base64.getEncoder().encodeToString(claveGenerada1.getEncoded());		
		byte[] claveDecodificada = Base64.getDecoder().decode(stringClaveGenerada1);
		SecretKey copia_claveGenerada1 = new SecretKeySpec(claveDecodificada, 0, claveDecodificada.length, TIPO);		
		
		//cifrado de ambos mensajes con la claveGenerada por generateKey() y transformada de String a SecretKey 
		String encryptedMessage1 = TripleDES168.cifrar(message1, claveGenerada1);
		String encryptedMessage2 = TripleDES168.cifrar(message1, copia_claveGenerada1);
		
		//se modifica el string en un carácter
		
		
		//substring (inicioIndiceIncluido, finalIndiceIncluido)
		String stringClaveGenerada3 = stringClaveGenerada1.substring(0,0)+'X'+stringClaveGenerada1.substring(1);
	
		//este mensaje se cifra con una clave que cambia un caracter
		claveDecodificada = Base64.getDecoder().decode(stringClaveGenerada3);
		SecretKey claveGenerada3 = new SecretKeySpec(claveDecodificada, 0, claveDecodificada.length, TIPO);
		String encryptedMessage3 = TripleDES168.cifrar(message1, claveGenerada3); 
		
		//aqui cambiamos al mensaje2
		String encryptedMessage4 = TripleDES168.cifrar(message2, claveGenerada1);
		String stringClaveGenerada4 = Base64.getEncoder().encodeToString(claveGenerada1.getEncoded());
		
		System.out.println("#Mensaje en claro:\t" + message1);				
		System.out.println("##Mensaje cifrado:\t" + encryptedMessage1);
		System.out.println();		
		System.out.println("#Mensaje en claro:\t" + message1);
		System.out.println("Clave Generada2:\t" + stringClaveGenerada1);
		System.out.println("Clave decodificada: \t" + Arrays.toString(claveDecodificada));
		System.out.println("##Mensaje cifrado2:\t" + encryptedMessage1);
		
		//calculo de la distancia entre los dos mensajes cifrados
		/////Hamming h = new Hamming(encryptedMessage1,encryptedMessage2);
		System.out.println();
		System.out.println("#Mensaje en claro:\t" + message1);
		System.out.println("Clave Generada3:\t" + stringClaveGenerada3);		
		System.out.println("##Mensaje cifrado3:\t" + encryptedMessage3);
		//ahora cambio el 
		System.out.println();
		System.out.println("#Mensaje en claro:\t" + message2);
		System.out.println("Clave Generada4:\t" + stringClaveGenerada4);		
		System.out.println("##Mensaje cifrado4:\t" + encryptedMessage4);
	}

	private static SecretKey generateKey() throws Exception {
		KeyGenerator generador = KeyGenerator.getInstance(TIPO);
		SecretKey claveGenerada = generador.generateKey();
		return claveGenerada;
	}

}
