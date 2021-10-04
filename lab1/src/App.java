/**
* Java Doc 
* @author  Chi ieong Ng
* @version 1.0
* @since   2021-10-04 
*/

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);

        System.out.print("Enter the length of Side A: ");
        double sideA =  sc.nextDouble();
        System.out.print("Enter the length of Side B: ");
        double sideB =  sc.nextDouble();
        System.out.print("Enter the length of Side C: ");
        double sideC =  sc.nextDouble();
        
        if ((sideA + sideB) > sideC) {
            if (sideA == sideB && sideA == sideC && sideB == sideC) {
                System.out.print("It is a equalateral Triangle");
            }
            else {
                if (sideA == sideB || sideA == sideC || sideB == sideC) {
                    System.out.print("It is a isosceles Triangle");
                }
                else {
                    System.out.print("It is a scalenes Triangle");
                }
            }
        }
        else {
            System.out.print("It is not a Triangle ");
        }
    }
}
