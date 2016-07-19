package questions.q1341;

import java.util.*;

public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();

        String[] exps = new String[n];
        for (int i = 0; i < n; i++) {
            exps[i] = in.nextLine();
        }

        int t = in.nextInt();
        in.nextLine();
        Map map = new HashMap<String, Integer>();
        int k = keyNum(exps);

        for (int r = 0; r < t; r++) {
            for (int j = 0; j < k; j++) {
                String[] s = in.nextLine().split(" ");
                map.put(s[0], Integer.valueOf(s[1]));
            }
            validate(exps, map);
        }
    }

    private static int keyNum(String[] exps) {
        Set<String> set = new HashSet<String>();
        for (String exp : exps) {
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z') {
                    set.add(exp.substring(i, i + 1));
                }
            }

        }
        return set.size();
    }

    private static void validate(String[] exps, Map<String, Integer> map) {
        boolean flag = true;
        for (String exp : exps) {
            String s[] = exp.split("<=|<");

            int index = 0;
            for (int b = 0; b < s.length - 1; b++) {
                int first, second;
                first = getValue(s[b], map);
                second = getValue(s[b + 1], map);

                index += s[b].length();
                String sub = exp.substring(index, index + 2);
                if (sub.equals("<=")) {
                    if (!(first <= second)) {
                        flag = false;
                        break;
                    }
                    index += 2;
                } else {
                    if (!(first < second)) {
                        flag = false;
                        break;
                    }
                    index += 1;
                }


            }
        }
        if (flag)
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    public static int getValue(String s, Map<String, Integer> map) {
        boolean isNum = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                isNum = false;
                break;
            }
        }

        if (isNum) {
            return Integer.valueOf(s);
        } else {
            return map.get(s);
        }
    }

}
