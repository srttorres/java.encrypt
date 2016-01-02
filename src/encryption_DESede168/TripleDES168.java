package encryption_DESede168;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

//import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class TripleDES168 {
	

	

	//cifrado es el tipo de cifrado con el que se desa cifrar
	public static String cifrar (String msg, SecretKey k, String cifrado) throws Exception {
		
		Cipher c = Cipher.getInstance(cifrado);
		c.init(Cipher.ENCRYPT_MODE, k);
		byte[] encVal = c.doFinal(msg.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
		
	}

}//class
