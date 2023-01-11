
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

class DistanciaLevenshtein
{
   
    
    public static void main(String[] args) throws InterruptedException
    {
        String palabra1 = "Algoritmos";
        String palabra2 = "Grafos";
         
        System.out.println("La distancia de Levenshtein entre " + palabra1+ " y "+ palabra2+ " calculada con el algoritmo recursivo es: " + algoritmoRecursivo(palabra1, palabra2));
        System.out.println("La distancia de Levenshtein entre " + palabra1+ " y "+ palabra2+ " calculada con el algoritmo de programación dinámica es: " + programacionDinamica(palabra1, palabra2));
        calculaTiempos(palabra1,palabra2);
    }
    
    
 
    
    // Algoritmo implementado de forma recursiva:
    
    public static int algoritmoRecursivo(String palabra1 , String palabra2) {
        if (palabra1.isEmpty()) {
            return palabra2.length();
        }

        if (palabra2.isEmpty()) {
            return palabra1.length();
        } 

        int sustituir = algoritmoRecursivo(palabra1.substring(1), palabra2.substring(1)) + cantidadLetrasCambiar(palabra1.charAt(0), palabra2.charAt(0));
        int insertar = algoritmoRecursivo(palabra1, palabra2.substring(1)) + 1;
        int borrar = algoritmoRecursivo(palabra1.substring(1), palabra2) + 1;

        return min(sustituir, insertar, borrar);
    }
    
    // Algoritmo implementado con programación dinámica:
    
    public static int programacionDinamica(String palabra1, String palabra2)
    {
        int numeroCaracteres1 = palabra1.length();
        int numeroCaracteres2 = palabra2.length();
 
        // Creamos una matriz almacenando el numero de caracteres de cada palabra

        int[][] T = new int[numeroCaracteres1 + 1][numeroCaracteres2 + 1];
 
     
        // Insertamos en la matriz en cada posicion el numero de caracteres correspondiente
 
        for (int i = 1; i <= numeroCaracteres1; i++) {
            T[i][0] = i;
        }

 
        for (int j = 1; j <= numeroCaracteres2; j++) {
            T[0][j] = j;
        }
 
        int contadorCaracter;
 
        // Llenamos la tabla de búsqueda de forma ascendente
        
        for (int i = 1; i <= numeroCaracteres1; i++)
        {
            for (int j = 1; j <= numeroCaracteres2; j++)
            {
                if (palabra1.charAt(i-1) == palabra2.charAt(j-1)) {   
                    contadorCaracter = 0;  
                }
                else {
                    contadorCaracter = 1;                       
                }
 
                T[i][j] = min(T[i - 1][j] + 1, T[i][j - 1] + 1 , T[i - 1][j - 1] + contadorCaracter);     
            }
        }

        return T[numeroCaracteres1][numeroCaracteres2];
        
        

    }
    
    // Funcion que calcula los tiempos de ejecucion entre cada algoritmo
	public static int calculaTiempos(String palabra1, String palabra2) {
	    	
	    	long startTime = System.nanoTime();
	    	algoritmoRecursivo(palabra1,palabra1); // llamamos al método
	        long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
	        long convert = TimeUnit.MICROSECONDS.convert(endTime, TimeUnit.NANOSECONDS);
	        
	    	long startTime2 = System.nanoTime();
	        programacionDinamica(palabra1,palabra1); // llamamos al método
	        long endTime2 = System.nanoTime() - startTime2; // tiempo en que se ejecuta su método
	        long convert2 = TimeUnit.MICROSECONDS.convert(endTime2, TimeUnit.NANOSECONDS);
	        System.out.println("El algoritmo recursivo tarda " +convert+" μs");
	        System.out.println("El algoritmo de programacion dinamica tarda " +convert2+" μs");
			return 0;
	    	
	    }
    
    // Función que calcula la cantidad de letras a cambiar
    public static int cantidadLetrasCambiar(char a, char b) {
        return a == b ? 0 : 1;
    }

    
    // Función que calcula el mínimo
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }


 
    
}