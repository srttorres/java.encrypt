package encryption_DESede168;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;


public class App {
	private static final String TIPO_COMPLETO ="DESede/CBC/PKCS5Padding";
	private static final String TIPO ="DESede";

	
    // programa principal
	public static void main(String args[]) throws Exception{
		SecretKey[] skArray = clave.generarClave(1, TIPO);
		SecretKey sk = skArray[0];
		String message1 = "THIS IS SPARTA";//mensaje de longitud 14 bytes
		//0101010001001000010010010101001100100000010010010101001100100000010100110101000001000001010100100101010001000001
		String message2 = "UHIS IS SPARTA";//mensaje con el octavo bit cambiado
		//0101010101001000010010010101001100100000010010010101001100100000010100110101000001000001010100100101010001000001
		String clave1 = Base64.getEncoder().encodeToString(sk.getEncoded());//clave de 32 bits
					
		
		//la clave 1 se pasa a bytes y después se convierte a secret key, finalmente se cifra
		byte[] claveEnBytes1 = Base64.getDecoder().decode(clave1);	
		SecretKey claveGenerada1 = new SecretKeySpec(claveEnBytes1, 0, claveEnBytes1.length, TIPO);				
		String encryptedMessage1 = TripleDES168.cifrar(message1, claveGenerada1, TIPO);
		
		//la clave 2 se pasa a bytes, se cambia un bit y después se convierte a secret key, finalmente se cifra
		byte[] claveEnBytes2 = Base64.getDecoder().decode(clave1);
		claveEnBytes2 = ligeroCambio(claveEnBytes2);
		System.out.println(Arrays.toString(claveEnBytes1));
		System.out.println(Arrays.toString(claveEnBytes2));
		SecretKey claveCambiada = new SecretKeySpec(claveEnBytes2, 0, claveEnBytes2.length, TIPO);	
		String clave2 = Base64.getEncoder().encodeToString(claveCambiada.getEncoded());
		String encryptedMessage2 = TripleDES168.cifrar(message1, claveCambiada, TIPO);		
		

		
		//la clave 1 y mensaje 2 se pasa a bytes y después se convierte a secret key, finalmente se cifra						
		String encryptedMessage3 = TripleDES168.cifrar(message2, claveGenerada1, TIPO);
		
		System.out.println("#Mensaje en claro:\t" + message1);				
		System.out.println("Clave:\t\t\t" + clave1);
		//System.out.println("##Mensaje cifrado:\t" + encryptedMessage1);
		System.out.println();		
		System.out.println("#Mensaje en claro:\t" + message1);
		System.out.println("Clave:\t\t\t" + clave2);
		//System.out.println("##Mensaje cifrado2:\t" + encryptedMessage2);
		System.out.println();		
		System.out.println("#Mensaje en claro:\t" + message2);
		System.out.println("Clave:\t\t\t" + clave1);
		System.out.println("##Mensaje cifrado3:\t" + encryptedMessage3);
		//calculo de la distancia entre los dos mensajes cifrados
		/////Hamming h = new Hamming(encryptedMessage1,encryptedMessage2);
		System.out.println();

		//ahora cambio el 
	}
	//cambia de todo el byte[] un bit, el bit 8 del primer byte
	public static byte[] ligeroCambio(byte[] byteArray){
		
		byte[] byteArrayTransformado = byteArray;
		byte primerByte = byteArray[0];
		boolean[] bits = byteToBooleanArray(primerByte);		
		String s = booleanArrayToString(bits);
		bits[7]=!bits[7];
		String s2 = booleanArrayToString(bits);		
		byte primerByteCambiado = stringToByte(s2);
		byteArrayTransformado[0]=primerByteCambiado;
		//System.out.println(bits[0] +","+bits[1]+","+bits[2]+","+bits[3]+","+bits[4]+","+bits[5] +","+bits[6]+","+bits[7]);
		System.out.println(s);
		System.out.println(s2);
		return byteArrayTransformado;
	}
	
	private static byte stringToByte(String s2) {
		 String number = s2; //sin espacios
         byte numberByte = (byte) Integer.parseInt(number, 2); //so mode 2
         //System.out.println(numberByte);
		return numberByte;
	}

	public static boolean[] byteToBooleanArray(byte x) {
	    boolean bs[] = new boolean[8];
	    bs[0] = ((x & 0x01) != 0);
	    bs[1] = ((x & 0x02) != 0);
	    bs[2] = ((x & 0x03) != 0);
	    bs[3] = ((x & 0x04) != 0);
	    bs[4] = ((x & 0x05) != 0);
	    bs[5] = ((x & 0x06) != 0);
	    bs[6] = ((x & 0x07) != 0);
	    bs[7] = ((x & 0x08) != 0);
	    return bs;
	}
	public static String booleanArrayToString(boolean[] b){
		String s = "00000000";
		for (int i = 0;i<8;i++){
			if (b[i]){
				String s2 = s.substring(0,i)+'1'+s.substring(i+1);
				s = s2;
			}
			
		}
		return s;
	}


}
