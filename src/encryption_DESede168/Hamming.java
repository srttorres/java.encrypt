package encryption_DESede168;

public class Hamming {
    private String cifrado1;
    private String cifrado2;
    //constructor
    public Hamming(String c1, String c2)
    {
        this.cifrado1 = c1;
        this.cifrado2 = c2;
    }
    //método distancia
    //la distancia de hamming entre dos conjuntos de strings 
    public int distancia(){
    	int distancia=-1;
    	int contador=0;
    	if (cifrado1.length() == cifrado2.length()){
    		for(int i =0;i<cifrado1.length();i++){
    			char caracter1 = cifrado1.charAt(i);
    			char caracter2 = cifrado2.charAt(i); 
    			if(caracter1!=caracter2){
    				contador++;
    			}
    		}
    		distancia=contador;
    	}
    	return distancia;
    		
    			
    }
    
    
    
    
}
