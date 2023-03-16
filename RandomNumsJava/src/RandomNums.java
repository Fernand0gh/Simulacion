import java.util.Random;
import java.util.Scanner;
public class RandomNums {
    public static void main(String[] args){
        //instanciar elementos
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        //Elección de método de entrada
        System.out.println("Indica cómo quieres elegir la cantidad de numeros a generar \n 1)Teclado 2)Random");
        int op=sc.nextInt();
        int n=0;

        //lectura de N en caso 1
        if(op==1){
            System.out.println("Ingresa la cantidad de números a generar");
            n= sc.nextInt();
        } //generación de N en caso 2
        else if (op==2){
            n= random.nextInt(10000);
        }

        int [] numbers= new int[n];

        //encabezado de impresión
        System.out.printf("%7s%15s\n", "i", "x     ");

        //generar números
        for(int i=0; i<n; i++){
            numbers[i]= random.nextInt();
            System.out.printf("%7s%15s\n", (i+1), numbers[i]);
        }

        System.out.println("Se generaron " + n + " números");

        //cálculo de la media
        int sum=0;
        for(int i=0; i<n; i++){
            sum+=numbers[i];
        }
        int prom=sum/n;
        System.out.println("La media es: " + prom);

        //cálculo de la varianza
        int sum2=0;
        for(int i=0; i<n; i++){
            sum2+=Math.pow((numbers[i]-prom),2);
        }
        int varianza=sum2/n;
        System.out.println("La varianza es: " + varianza);
    }
}