package questions.q1332;

public class Main {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String expression = in.next();
//            System.out.println(calculate(expression));
//        }
//    }
//
//    private static String calculate(String expression) {
//        int length = expression.length();
//        for (int i = 0; i < length; i++) {
//            if (expression.charAt(i) == '(') {
//                for (int j = i; j < length; j++) {
//                    if (expression.charAt(j) == ')') {
//                        return calculate(expression.substring(0, i)
//                                + calculate(expression.substring(i + 1, j))
//                                + expression.substring(j + 1, length));
//                    }
//                }
//            } else if (expression.charAt(i) == '+') {
//                return Integer.toString(Integer.parseInt(calculate(expression.substring(0, i)))
//                        + Integer.parseInt(calculate(expression.substring(i + 1, length))));
//            } else if (expression.charAt(i) == '-') {
//                return Integer.toString(Integer.parseInt(calculate(expression.substring(0, i)))
//                        - Integer.parseInt(calculate(expression.substring(i + 1, length))));
//            }
//        }
//
//        for (int i = 0; i < length; i++) {
//            if (expression.charAt(i) == '*') {
//                return Integer.toString(Integer.parseInt(calculate(expression.substring(0, i)))
//                        * Integer.parseInt(calculate(expression.substring(i + 1, length))));
//            } else if (expression.charAt(i) == '/') {
//                return Integer.toString(Integer.parseInt(calculate(expression.substring(0, i)))
//                        / Integer.parseInt(calculate(expression.substring(i + 1, length))));
//            }
//        }
//
//        return expression;
//    }
}
