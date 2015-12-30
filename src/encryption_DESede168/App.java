package encryption_DESede168;

public class App {

	public static void main(String args[]) throws Exception{
		String message = "THIS IS SPARTA";
		String key = "1234";
		String encryptedMessage = TripleDES168.cifrar(message);		
		System.out.println("Mensaje en claro: " + message);
		System.out.println("Mensaje cifrado: "+ encryptedMessage);
	}
}
