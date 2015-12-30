package encryption_DESede168;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class TripleDES168 {
	
	//private static final String TIPO ="DESede/CBC/NoPadding";
	private static final String TIPO ="DESede";
	//private static final String TIPO ="DESede/CBC/NoPadding(168)";//mal
	private static final byte[] CLAVE = new byte[]{'1','2','3','4','5','6','7','8','9','0','1','2','3'};
	
	
	public static String cifrar (String msg) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(TIPO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(msg.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}
	private static SecretKey generateKey() throws Exception {
		//SecretKey key = new SecretKeySpec(CLAVE,TIPO);
		KeyGenerator key = KeyGenerator.getInstance(TIPO);
		return key.generateKey();
	}
	
}//class
