import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    static Random rnd = new Random();
    public static void mediosCuadrados(int n){
        ArrayList<Integer> nums =new ArrayList<>();
        nums.add(n);
        boolean contains=false;
        while(!contains){
            //elevar n al cuadrado
            n=(int)Math.pow(n,2);
            //guardarlo en string
            String str = String.valueOf(n);
            //convertirlo a un numero de 8 digitos
            while(str.length()<8){
                str="0"+str;
            }
            //sacar los 4 digitos centrales y hacerlos int
            str = str.substring(2,6);
            n=Integer.parseInt(str);
            if(nums.contains(n)){
                contains=true;
            }
            else{
                nums.add(n);
            }
        }
        for (int x : nums){
            System.out.println(x);
        }
        System.out.println("El número " + n + " se ha repetido, el ciclo terminará");
        //4100, 6100, 9600 y 0
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Elija el método de obtención de semilla: 1)Teclado 2)Random");
        int op=sc.nextInt();
        if(op==1){
            System.out.println("Ingrese la semilla");
            mediosCuadrados(sc.nextInt());
        } else if(op==2){
            int seed= rnd.nextInt(1000, 9999);
            mediosCuadrados(seed);}
        }

}