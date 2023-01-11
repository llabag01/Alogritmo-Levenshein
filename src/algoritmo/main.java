package algoritmo;
import java.util.*;

public class main {
	static String palabra1;
	static String palabra2;
	public static void main(String[] args) {
		palabra1 = "Algoritmo";
		palabra2 = "Grafos";
		int a=0;
		int devolver1=recursivo(palabra1, palabra2,a);
		int devolver2=iterativo(palabra1, palabra2);
		System.out.println(devolver1);
	}
	public static int recursivo(String a, String b, int devolver) {
		// TODO Auto-generated method stub
		if(a.isEmpty()==true) {
			devolver = a.length();
		}
		if(b.isEmpty()==true) {
			devolver = b.length();
		}
		int caso1 = recursivo(a.substring(1),b.substring(1),devolver) + remplazarnumero(a.charAt(0),b.charAt(0));
		int caso2 = recursivo(a,b.substring(1),devolver) + 1;
		int caso3 = recursivo(a.substring(1),b,devolver) + 1;
		devolver = minimo(caso1,caso2,caso3);
		return devolver;
	}
	public static int iterativo(String a, String b) {
		// TODO Auto-generated method stub
		int devolver = 0;
		int [][] matriz = new int[a.length()+1][b.length()+1];
		for(int i=0; i<a.length(); i++) {
			for(int j=0; j<b.length(); j++) {
				if(i==0) {
					matriz[i][j]=j;
				}else if(j==0) {
					matriz[i][j]=i;
				}else {
					matriz[i][j] =minimo(matriz[i-1][j-1]+remplazarnumero(a.charAt(i-1),b.charAt(j-1)),matriz[i-1][j]+1,matriz[i][j-1]+1);
				}
			}
		}
		devolver = matriz[a.length()][b.length()];
		return devolver;
	}
	public static int remplazarnumero(char charAt, char charAt2) {
		// TODO Auto-generated method stub
		return charAt == charAt2 ? 0 : 1;
	}
	public static int minimo(int... num) {
		return Arrays.stream(num).min().orElse(Integer.MAX_VALUE);
	}
}

