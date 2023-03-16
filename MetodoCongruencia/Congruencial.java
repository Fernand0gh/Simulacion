import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Congruencial{
    public static void congruencial(int a, int c, int m, int x0){
        ArrayList <Integer> x = new ArrayList<Integer>();
        //agregar la semilla a la lista
        x.add(x0);
        //imprimir encabezado
        System.out.printf("%5s%5s%11s%15s%10s\n", "n", "x", "ax_n+c", "(ax_n+c)/m", "x_n+1");
        boolean stop = false;
        //calcular siguiente número
        int next=0; //aquí se guarda el siguiente número
        int i=0;
        int num=0; //aquí se guarda el número que se va a agregar a la lista
        while(!stop){
            num=(a * x.get(i) + c) % m; //formula para calcular el número pseudoaleatorio
            if(x.contains(num)){
                x.add(num); //agregar el número a la lista
                next = (a * x.get(i) + c) % m; //calcular el siguiente número
                //imprimir resultados de las últimas 2 iteraciones en caso de que se repita un número
                System.out.printf("%5s%5s%11s%15s%10s\n", i, x.get(i), a*x.get(i)+c, (a*x.get(i)+c)+"/"+m , next);
                next = (a * x.get(i+1) + c) % m;
                System.out.printf("%5s%5s%11s%15s%10s\n", i+1, x.get(i+1), a*x.get(i+1)+c, (a*x.get(i+1)+c)+"/"+m , next);
                //finalizar la ejecución del método
                return;
            }
            //agregar e imprimir la iteración en un caso normal
            x.add(num);
            next = (a * x.get(i) + c) % m;
            System.out.printf("%5s%5s%11s%15s%10s\n", i, x.get(i), a*x.get(i)+c, (a*x.get(i)+c)+"/"+m , next);
            //incrementar el contador
            i++;
        }
    }
    //saber si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= numero / 2; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        int a, c, m=0, x0=0;

        System.out.println("Método congruencial \n García Hernadez Jesús Fernando \n Simulación 12-1pm");
        System.out.println("--------------------------------------------------------");

        System.out.println("1)Generar números pseudoaleatorios con el método congruencial \n 2)Salir");
        int opMain = sc.nextInt();
        do{
            if(opMain==1){
            //leer a
            System.out.println("Ingrese el valor de a (multiplicador): ");
            a = sc.nextInt();
            while(a%5==0 || a%3==0){ //validar que a no sea divisible entre 3 o 5
                System.out.println("El valor de a no es válido, ingrese otro: ");
                a = sc.nextInt();
            }
            //leer x0
            System.out.println("¿Cómo desea generar la semilla? \n 1. Manual \n 2. Aleatorio");
            int opcion = sc.nextInt();
            if(opcion == 1){ //si se ingresa manualmente
                System.out.println("Ingrese el valor de x (semilla): ");
                x0 = sc.nextInt();
            }else if(opcion == 2){ //si se ingresa aleatoriamente
                x0 = rnd.nextInt(100);
                System.out.println("El valor de x es: "+x0);
            } else{ //si se ingresa una opción no válida
                System.out.println("Opción no válida");
                System.exit(0);
            }
            //leer c
            System.out.println("Ingrese el valor de c (incremento): ");
            c = sc.nextInt();
            while(c%200!=21){ //validar que c sea congruente con 21 módulo 200
                System.out.println("El valor de c no es válido, ingrese otro: ");
                c = sc.nextInt();
            }
            //leer m
            System.out.println("¿Cómo desea ingresar el valor de m(modulo)? \n 1. Aleatorio \n 2. Número primo menor a 10^63");
            int opMod = sc.nextInt();
            if(opMod == 2){ //si se ingresa manualmente
                m = sc.nextInt();
                while(!esPrimo(m)){
                    System.out.println("El valor de m no es válido, ingrese otro: ");
                    m = sc.nextInt();
                }
            } else if (opMod == 1){ //si se toma aleatoriamente
                m = rnd.nextInt(1, 10^63);
                while(!esPrimo(m)){
                    m = rnd.nextInt(1, 10^63);
                }
                System.out.println("El valor de m es: "+m);
            } else { //si se ingresa una opción no válida
                System.out.println("Opción no válida");
                System.exit(0);
            }
            //llamar al método congruencial
            congruencial(a, c, m, x0);
            //preguntar si se desea volver a ejecutar el programa
            System.out.println("--------------------------------------------------------");
            System.out.println("1)Generar números pseudoaleatorios con el método congruencial \n 2)Salir");
            opMain = sc.nextInt();
            //si se ingresa una opción no válida
            } else{
                System.out.println("Opción no válida");
                System.out.println("1)Generar números pseudoaleatorios con el método congruencial \n 2)Salir");
                opMain = sc.nextInt();
            }
        } while (opMain!=2);
        sc.close();
    }
}