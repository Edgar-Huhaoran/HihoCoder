package edgarhu.hihocoder.competition.race9.C;

import java.util.Scanner;

public class Main {

    public static final long MAX_N = 1000000000000000000L;

    public static void main(String[] args) {
        long[] boundArr = getBound(MAX_N);
        long n;
        Scanner in = new Scanner(System.in);

        n = in.nextLong();
        System.out.println(getNumber(boundArr, n));
    }

    /**
     * 先找到原始数字和原始数字对应的位置,然后读取这一位
     * @param boundArr
     * @param n
     * @return
     */
    public static int getNumber(long[] boundArr, long n) {
        if (n == 0) {
            return 0;
        }

        int i = 1;
        while (boundArr[i] < n) {
            i++;
        }

        long temp = n - boundArr[i - 1] - 1;
        long originNum = temp / i + (long)Math.pow(10, i - 1);
        long bitPos = temp % i;

        return (int)(originNum / (long)(Math.pow(10, i - 1 - bitPos)) % 10);
    }

    /**
     * 获取不同位数数字之间的阀值
     * @param max
     * @return
     */
    public static long[] getBound(long max) {
        long[] boundArr = new long[20];
        int i = 1;
        long factor = 9;
        boundArr[0] = 0;
        while (boundArr[i - 1] < max) {
            boundArr[i] = boundArr[i - 1] + factor * i;
            factor *= 10;
            i++;
        }
        return boundArr;
    }

}
