package edgarhu.hihocoder.questions.q1341;


import java.util.*;

public class Main1 {

    public static void main(String[] args) {
        List<Experssion> expList = new ArrayList<Experssion>();
        HashSet<String> numSet = new HashSet<String>();
        Scanner in = new Scanner(System.in);

        int line = in.nextInt();
        for (int i = 0; i < line; i++) {
            expList.add(analyse(in.next(), numSet));
        }
        int numAmount = numAmount(numSet);

        int amout = in.nextInt();
        for (int i = 0; i < amout; i++) {
            HashMap<String, Integer> keyMap = new HashMap<String, Integer>();
            for (int j = 0; j < numAmount; j++) {
                keyMap.put(in.next(), in.nextInt());
            }

            boolean isValid = true;
            for (Experssion experssion : expList) {
                if (verify(experssion, keyMap) == false) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    public static int numAmount(HashSet<String> numSet) {
        int amout = 0;
        for (String s : numSet) {
            if (s.length() == 1 && s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
                amout++;
            }
        }
        return amout;
    }

    public static boolean verify(Experssion experssion, HashMap<String, Integer> keyMap) {
        int expNum = experssion.opList.size();
        int first, second;
        for (int i = 0; i < expNum; i++) {
            if (keyMap.containsKey(experssion.numList.get(i))) {
                first = keyMap.get(experssion.numList.get(i));
            } else {
                first = Integer.valueOf(experssion.numList.get(i));
            }

            if (keyMap.containsKey(experssion.numList.get(i + 1))) {
                second = keyMap.get(experssion.numList.get(i + 1));
            } else {
                second = Integer.valueOf(experssion.numList.get(i + 1));
            }

            if (experssion.opList.get(i) == 0) {
                if (!(first < second)) {
                    return false;
                }
            } else {
                if (!(first <= second)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Experssion analyse(String str, HashSet<String> numSet) {
        Experssion experssion = new Experssion();
        int len = str.length();
        StringBuilder builder = new StringBuilder(str);

        int index;
        while ((index = builder.indexOf("<")) != -1) {
            experssion.numList.add(builder.substring(0, index));
            numSet.add(builder.substring(0, index));  //方便统计变量数
            if (builder.charAt(index + 1) == '=') {
                experssion.opList.add(1);
                builder = new StringBuilder(builder.substring(index + 2));
            } else {
                experssion.opList.add(0);
                builder = new StringBuilder(builder.substring(index + 1));
            }
        }
        experssion.numList.add(builder.toString());
        numSet.add(builder.toString());
        return experssion;
    }

    public static class Experssion {
        public List<String> numList = new ArrayList<String>();
        public List<Integer> opList = new ArrayList<Integer>();
    }
}
