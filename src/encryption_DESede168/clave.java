package encryption_DESede168;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class clave {	
public String claveAString(SecretKey c){
		String stringClave = Base64.getEncoder().encodeToString(c.getEncoded());
		return stringClave;
	}	
	//n es el número de claves que se desean generar
	//cifrado es el tipo de cifrado que se desea aplicar.
	//Devuelve el array de claves generadas
public static SecretKey[] generarClave(int n, String cifrado) throws Exception {
	SecretKey[] arrayClaves=new SecretKey[n];
	for(int i = 0; i<n; i++){
		KeyGenerator generador = KeyGenerator.getInstance(cifrado);
		SecretKey claveGenerada = generador.generateKey();
		arrayClaves[i]=claveGenerada;
	}
	return arrayClaves;
}
}//clase clave
