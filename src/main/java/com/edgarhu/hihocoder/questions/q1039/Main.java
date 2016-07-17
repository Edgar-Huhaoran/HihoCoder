package com.edgarhu.hihocoder.questions.q1039;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            String oStr = in.next();
            int min = oStr.length();
            for (int j = 0; j <= oStr.length(); j++) {
                for (char ch = 'A'; ch <= 'C'; ch++) {
                    StringBuffer tStr = new StringBuffer(oStr);
                    tStr.insert(j, ch);
                    int len = count(tStr);
                    if (len < min) {
                        min = len;
                    }
                }
            }
            System.out.println(oStr.length() - min + 1);
        }
    }

    public static int count(StringBuffer tStr) {
        StringBuffer bStr = new StringBuffer();
        while (true) {
            for (int i = 0; i < tStr.length(); ) {
                int j = i + 1;
                while (j < tStr.length() && tStr.charAt(i) == tStr.charAt(j)) {
                    j++;
                }

                if (j == i + 1) {
                    bStr.append(tStr.charAt(i));
                }

                i = j;
            }

            if (tStr.toString().equals(bStr.toString())) {
                break;
            }
            tStr = bStr;
            bStr = new StringBuffer();
        }

        return bStr.length();
    }

}
