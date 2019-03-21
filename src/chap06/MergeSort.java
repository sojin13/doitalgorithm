package chap06;
import java.util.Scanner;
//병합정렬

class MergeSort {
    static int[] buff;      //작업용 배열

    //a[left] ~ a[right]를 재귀적으로 병합정렬
    static void __mergeSort(int[] a, int left, int right) {
        if(left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            __mergeSort(a, left, center);           //배열 앞부분 병합정렬
            __mergeSort(a, center+1, right);   //배열 뒷부분 병합정렬

            for (i=left; i<=center; i++) {
                buff[p++] = a[i];
            }
            while( i<= right && j<p) {
                a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
            }
            while(j<p) {
                a[k++] = buff[j++];
            }
        }
    }

    //병합정렬
    static void mergeSort(int[] a, int n) {
        buff = new int[n];                  //작업용 배열 생성

        __mergeSort(a, 0, n-1); //배열 전체 병합정렬

        buff = null;
    }

    public static void main (String[] args) {
        Scanner stdIn  = new Scanner(System.in);

        System.out.println("병합정렬");
        System.out.print("요소수: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i=0; i<nx; i++) {
            System.out.print("x[" + i +"] : ");
            x[i] = stdIn.nextInt();
        }

        mergeSort(x, nx);               //배열 x을 병합정렬

        System.out.println("오름차순으로 정렬하였습니다.");
        for (int i=0; i<nx; i++) {
            System.out.println("x[" + i + "]=" + x[i]);
        }
    }
}
