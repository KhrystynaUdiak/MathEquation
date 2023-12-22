
public class Equation {

    public static boolean isValidEquation(String equation) {
        if (!isValidParentheses(equation)) {
            return false;
        }

        String expressionPattern = "[\\d.x()\\-+*/=]+";
        String doubleOperatorsPattern = ".*[+\\-*/]{2,}.*";

        return equation.matches(expressionPattern) && !equation.matches(doubleOperatorsPattern);
    }

    public static boolean isValidParentheses(String equation) {
        int count = 0;

        for (char c : equation.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    public static boolean checkRoot(String equation, String root) {

        equation = equation.replaceAll("x", root);
        equation = equation.replaceAll(" ", "");
        String[] equationParts = equation.split("=");

        double leftSide = getResultOfParentheses1(equationParts[0]);
        double rightSide = getResultOfParentheses1(equationParts[1]);
        if (leftSide == rightSide) {
            return true;
        } else {
            return false;
        }
    }

        public static double getResultOfParentheses1(String expression){
            if(expression.contains("(")){
                String expressionInsideParentheses = expression.substring(expression.indexOf("(")+1, expression.lastIndexOf(")"));
                expression = expression.replace("(" + expressionInsideParentheses + ")", String.valueOf(getResultOfParentheses1(expressionInsideParentheses)));
            }
            return getResult(expression);
        }
        public static double getResult(String expression){//7+4-4
            String[] splitstrings = (expression.split("((?<=[+-/*])|(?=[+-/*]))"));
            double result = Double.parseDouble(splitstrings[0]);
            for (int i = 1; i < splitstrings.length; i++) {
                if (splitstrings[i].equals("+") && Character.isDigit(splitstrings[i + 1].charAt(0))) {
                    result = result + Double.parseDouble(splitstrings[i+1]);
                } else if (splitstrings[i].equals("-") && Character.isDigit(splitstrings[i + 1].charAt(0))) {
                    result = result - Double.parseDouble(splitstrings[i+1]);
                } else if (splitstrings[i].equals("*") && Character.isDigit(splitstrings[i + 1].charAt(0))) {
                    result = result * Double.parseDouble(splitstrings[i+1]);
                } else if (splitstrings[i].equals("/") && Character.isDigit(splitstrings[i + 1].charAt(0))) {
                    result = result / Double.parseDouble(splitstrings[i+1]);
                }
            }
            return result;
        }
}

