package encryption_DESede168;

public class Hamming {
    private String cifrado1;
    private String cifrado2;
    //constructor
    public Hamming(String c1, String c2)
    {
        this.cifrado1 = c1;
        this.cifrado2 = c2;
        if (cifrado1.length()!=cifrado2.length()){
        	new Exception("longitud desigual"); //Para comparar cifrados con Hamming deben de tener la misma longitud
        }
    }
    //método distancia
    //la distancia de hamming entre dos conjuntos de strings 
    public int distancia(String serie1, String serie2){
    	int distancia=-1;
    	int contador=0;
    	if (serie1.length() == serie2.length()){
    		for(int i =0;i<serie1.length();i++){
    			char caracter1 = serie1.charAt(i);
    			char caracter2 = serie2.charAt(i); 
    			if(caracter1!=caracter2){
    				contador++;
    			}
    		}
    		distancia=contador;
    	}
    	return distancia;
    }
    public int distanciaByteArray(){
    	byte[] cifrado1EnBytes = cifrado1.getBytes();
    	byte[] cifrado2EnBytes = cifrado2.getBytes();
    	int contador = 0;
    	boolean[] bool1 = new boolean[8];
    	boolean[] bool2 = new boolean[8];
    	String serie1=null;
    	String serie2=null;
    	for(int i = 0; i<cifrado1.length();i++){
    		bool1 = Transformaciones.byteToBooleanArray(cifrado1EnBytes[i]);
    		bool2 = Transformaciones.byteToBooleanArray(cifrado2EnBytes[i]);
    		
    		serie1 = Transformaciones.booleanArray8ToString(bool1);
    		serie2 = Transformaciones.booleanArray8ToString(bool2);
    		
    		contador = contador + distancia(serie1,serie2);
    	}
    	return contador;
    }
    
    
    
}
