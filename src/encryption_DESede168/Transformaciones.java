package encryption_DESede168;

public class Transformaciones {
	//cambia de todo el byte[] un bit, el bit 8 del primer byte
		public static byte[] ligeroCambio(byte[] byteArray){
			
			byte[] byteArrayTransformado = byteArray;
			byte primerByte = byteArray[0];
			boolean[] bits = byteToBooleanArray(primerByte);		
			//System.out.println(bits[0] +","+bits[1]+","+bits[2]+","+bits[3]+","+bits[4]+","+bits[5] +","+bits[6]+","+bits[7]);
			String s = booleanArray8ToString(bits);		
			bits[7]=!bits[7];
			String s2 = booleanArray8ToString(bits);		
			byte primerByteCambiado = stringToByte(s2);
			byteArrayTransformado[0]=primerByteCambiado;
			
			//System.out.println(s);
			//System.out.println(s2);
			return byteArrayTransformado;
		}
		
		private static byte stringToByte(String s2) {
			 String number = s2; //sin espacios
	         byte numberByte = (byte) Integer.parseInt(number, 2); //so mode 2
	         //System.out.println(numberByte);
			return numberByte;
		}
		public static boolean[] byteArrayToBooleanArray(byte[] xs) {
			boolean bss[] = new boolean[8*xs.length];
			boolean bs[] =new boolean[8];
			int k = 0;
			for (int i=0;i<xs.length;i++){
				bs=byteToBooleanArray(xs[i]);
				for(int j = 0;j<8;j++){				
					bss[k]=bs[j];
					k++;
				}
			}
			return bss;
		}
		public static boolean[] byteToBooleanArray(byte x) {
		    boolean bs[] = new boolean[8];
		    bs[0] = ((x & 0x01) != 0);
		    bs[1] = ((x & 0x02) != 0);
		    bs[2] = ((x & 0x04) != 0);
		    bs[3] = ((x & 0x08) != 0);
		    bs[4] = ((x & 0x10) != 0);
		    bs[5] = ((x & 0x20) != 0);
		    bs[6] = ((x & 0x40) != 0);
		    bs[7] = ((x & 0x80) != 0);
		    return bs;
		}
		//solo booleanos de longitud 8
		public static String booleanArray8ToString(boolean[] b){
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
