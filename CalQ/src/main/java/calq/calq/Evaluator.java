package calq.calq;

public class Evaluator {
    public static String evaluate(String expression) {
        expression = expression.replace("-", "+-").replaceAll("\\s+","");
        var parts = expression.split("\\+");
        if (parts.length == 0) return "";
        
        int res = 0;
        try {
            for (String part : parts) {
                res += Integer.parseInt(part);
            }
        }
        catch(NumberFormatException e) {
            return "Error";
        }
        
        return Integer.toString(res);
    }
}
