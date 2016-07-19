package questions.q1051;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            int n = in.nextInt();
            int m = in.nextInt();

            int[] array = new int[102];
            array[0] = 0;
            array[n] = 101;
            for (int j = 1; j <= n; j++) {
                array[j] = in.nextInt();
            }

            if (m >= n) {
                System.out.println(100);
                continue;
            }

            int max = 0;
            for (int j = 0; j <= n - m; j++) {
                int len = array[j + m + 1] - array[j] - 1;
                if (len > max) {
                    max = len;
                }
            }
            System.out.println(max);
        }
    }

}
