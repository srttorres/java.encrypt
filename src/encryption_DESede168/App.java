package encryption_DESede168;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;


public class App {
	private static final String TIPO_COMPLETO ="DESede/CBC/PKCS5Padding";
	private static final String TIPO ="DESede";


	//programa principal
	//Genera N distancias de Hamming entre cifrados con un bit cambiado en la entrada
	public static void main(String args[]) throws Exception{
		int N = 1;// N es el tamaño del vector distancias de hamming
		SecretKey[] skArray = clave.generarClave(N, TIPO);
		List listaDistanciasMsg = new ArrayList();	
		List listaDistanciasClave = new ArrayList();
		for (int i = 0; i<skArray.length;i++) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			SecretKey sk = skArray[i];
			//mensaje de longitud 15 CARACTERES
			String message1 = "THIS IS SPARTA!";
			//01010100 01001000 01001001 01010011 00100000 01001001 01010011 00100000 01010011 01010000 01000001 01010010 01010100 01000001 00100001
			String message2 = "UHIS IS SPARTA!";//mensaje con el octavo bit cambiado
			//01010101 01001000 01001001 01010011 00100000 01001001 01010011 00100000 01010011 01010000 01000001 01010010 01010100 01000001 00100001
			//50 CARACTERES
			//String message1 = "this is sparta, it's a good city to visit spartans";
			//String message2 = "uhis is sparta, it's a good city to visit spartans";//mensaje con el octavo bit cambiado
			//128 CARACTERES
			//String message1 = "El enemigo, únicamente nos triplica en número, alentador para cualquier griego. En este día liberamos al mundo del misticismo...";
			//String message2 = "Dl enemigo, únicamente nos triplica en número, alentador para cualquier griego. En este día liberamos al mundo del misticismo...";
			//256 CARACTERES
			//String message1 = "el valeroso leónidas y sus 300 hombres, tan lejos del hogar, entregaron la vida, no sólo por esparta sino por toda grecia, y por la promesa que este país representa y aquí, ahora, en esta escarpada tierra llamada Platea, las hordas de Jerjes se enfrentan..";
			//String message2 = "dl valeroso leónidas y sus 300 hombres, tan lejos del hogar, entregaron la vida, no sólo por esparta sino por toda grecia, y por la promesa que este país representa y aquí, ahora, en esta escarpada tierra llamada Platea, las hordas de Jerjes se enfrentan..";
			//1024 CARACTERES
			//String message1 ="Mucho he reflexionado sobre las enigmáticas palabras de victoria, por parte de mi Rey. El tiempo le ha dado la razón, y de griego libre a griego libre se transmitió el mensaje de que el valeroso leónidas y sus 300 hombres, tan lejos del hogar, entregaron la vida, no sólo por esparta sino por toda grecia, y por la promesa que este país representa y aquí, ahora, en esta escarpada tierra llamada Platea, las hordas de Jerjes se enfrentan a la aniquilación! Ahí están, los bárbaros desalmados con el corazón encogido y tembloroso, el pulso, aterrorizados. Pues son conscientes, del despiadado y brutal horror que sufrieron frente a las espadas y lanzas de los 300. Y ahora, desde el otro lado de la llanura, contemplan a 10.000 espartanos a la cabeza de 30.000 griegos libres. Au!  El enemigo, únicamente nos triplica en número, alentador para cualquier griego.En este día liberamos al mundo del misticismo y la tiranía y damos la bienvenida al futuro más esperanzador que hayamos imaginado.Demos las gracias a Leónidas y ...";
			//String message2 ="Lucho he reflexionado sobre las enigmáticas palabras de victoria, por parte de mi Rey. El tiempo le ha dado la razón, y de griego libre a griego libre se transmitió el mensaje de que el valeroso leónidas y sus 300 hombres, tan lejos del hogar, entregaron la vida, no sólo por esparta sino por toda grecia, y por la promesa que este país representa y aquí, ahora, en esta escarpada tierra llamada Platea, las hordas de Jerjes se enfrentan a la aniquilación! Ahí están, los bárbaros desalmados con el corazón encogido y tembloroso, el pulso, aterrorizados. Pues son conscientes, del despiadado y brutal horror que sufrieron frente a las espadas y lanzas de los 300. Y ahora, desde el otro lado de la llanura, contemplan a 10.000 espartanos a la cabeza de 30.000 griegos libres. Au!  El enemigo, únicamente nos triplica en número, alentador para cualquier griego.En este día liberamos al mundo del misticismo y la tiranía y damos la bienvenida al futuro más esperanzador que hayamos imaginado.Demos las gracias a Leónidas y ...";

			String clave1 = Base64.getEncoder().encodeToString(sk.getEncoded());//clave de 32 caracteres							

			//la clave 1 se pasa a bytes y después se convierte a secret key, finalmente se cifra
			byte[] claveEnBytes1 = Base64.getDecoder().decode(clave1);	
			SecretKey claveGenerada1 = new SecretKeySpec(claveEnBytes1, 0, claveEnBytes1.length, TIPO);				
			String encryptedMessage1 = Cifrado.cifrar(message1, claveGenerada1, TIPO);

			//la clave 2 se pasa a bytes, se cambia un bit y después se convierte a secret key, finalmente se cifra
			byte[] claveEnBytes2 = Base64.getDecoder().decode(clave1);
			claveEnBytes2 = Transformaciones.ligeroCambio(claveEnBytes2);
			//System.out.println(Arrays.toString(claveEnBytes1));
			//System.out.println(Arrays.toString(claveEnBytes2));
			SecretKey claveCambiada = new SecretKeySpec(claveEnBytes2, 0, claveEnBytes2.length, TIPO);	
			String clave2 = Base64.getEncoder().encodeToString(claveCambiada.getEncoded());
			String encryptedMessage2 = Cifrado.cifrar(message1, claveCambiada, TIPO);		



			//la clave 1 y mensaje 2 se pasa a bytes y después se convierte a secret key, finalmente se cifra						
			String encryptedMessage3 = Cifrado.cifrar(message2, claveGenerada1, TIPO);

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
			Hamming h1 = new Hamming(encryptedMessage1,encryptedMessage2);
			Hamming h2 = new Hamming(encryptedMessage1,encryptedMessage3);
			int distanciaM= h2.distanciaByteArray();
			int distanciaC = h1.distanciaByteArray();
			//System.out.println("distancia N-M: " + distanciaM);
			//System.out.println("distancia N-C: " + distanciaC);
			listaDistanciasClave.add(distanciaC);
			listaDistanciasMsg.add(distanciaM);
		}//fin del for
		String distanciasC = "";		
		for(int i = 0;i<listaDistanciasClave.size();i++){
			int dist;
			dist = (int) listaDistanciasClave.get(i);
			String s = String.valueOf(dist);
			distanciasC = distanciasC + s + "\t";
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("vector de distanciasC: "+distanciasC);

		String distanciasM = "";		
		for(int i = 0;i<listaDistanciasMsg.size();i++){
			int dist;
			dist = (int) listaDistanciasMsg.get(i);
			String s = String.valueOf(dist);
			distanciasM = distanciasM + s + "\t";
		}
		System.out.println("vector de distanciasM: "+distanciasM);
	}//main



}
