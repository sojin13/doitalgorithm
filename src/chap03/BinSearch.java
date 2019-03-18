package chap03;
import java.util.Scanner;

//이진 검색 오름차순 or 내림차순 정렬이 되어있을 때 사용 가능.
class BinSearch {
    //요소수가 n인 배열 a에서 key와 같은 요소를 이진 검색 합니다.

    static int binSearch(int[] a, int n, int key) {
        int pl = 0;
        int pr = n - 1;

        do {
            int pc = (pl + pr) / 2; //중앙 요소 인덱스
            if (a[pc] == key) { return pc; }
            else if (a[pc] < key) { pl = pc + 1;} //검색 범위 뒤의 절반으로 좁힘
            else { pr = pc -1; } // 검색 범위 앞쪽 절반으로 좁힘
        } while (pl <= pr);

        return -1; //검색 실패
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("요소수: ");
        int num = stdIn.nextInt();
        int[] x = new int[num];    //요소수가 num인 배열

        System.out.println("오름차순으로 입력하세요");
        System.out.printf("x[0] : "); //첫 요소 입력
        x[0] = stdIn.nextInt();

        for (int i = 1; i < num; i++) {
            do {
                System.out.print("x[" + i + "] : ");
                x[i] = stdIn.nextInt();
            } while (x[i] < x[i - 1]);
        }
        System.out.print("검색할 값 : ");
        int ky = stdIn.nextInt();

        int idx = binSearch(x, num, ky);

        if (idx == -1) {
            System.out.println("그 값의 요소가 없습니다.");
        } else {
            System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
        }
    }
}
