package chap01;

import java.util.Scanner;

class SumForPos {
    public static void main(String [] args) {
        Scanner stdIn =  new Scanner(System.in);

        System.out.println("1부터 n까지의 합을 구합니다.");
        System.out.println("n의 값 : ");
        int n;
        int sum;

        do {
            System.out.println("n의 값 : ");
            n = stdIn.nextInt();
        } while ( n <= 0 ); {
            sum = 0;

            for (int i =1; i <= n; i++) {
                sum += i;
            }
        }

        System.out.println("1부터" + n + "까지의 합은 " + sum + "입니다.");
    }
}
