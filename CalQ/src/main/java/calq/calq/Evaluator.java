package calq.calq;

import java.lang.System;
import java.util.ArrayList;

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

    private static int findClosingParenthesis(String str, int openingPos) {
        int length = str.length();
        int depth = 1;
		int pos = openingPos + 1;

        for (; pos < length; ++pos) {
            char c = str.charAt(pos);
            if (c == '(')
                ++depth;
            else if (c == ')')
                --depth;

            if (depth == 0) break;
        }
        return pos;
    }
    
    // WIP
    private static int evaluateImpl(String expr, int startPos, int endPos, String depth) {
		System.out.println("\"" + expr.substring(startPos, endPos) + "\"");
        // legal characters: digits, operators and parentheses
        // everything else is an error

        int res = 0;
		int numStart = startPos;
		var values = new ArrayList<Integer>();
		var ops = new ArrayList<Character>();

        for (int pos = startPos; pos <= endPos; ++pos) {
            char c = expr.charAt(pos);

			if (c == '+' || c == '-' || pos == endPos) {
				String s = expr.substring(numStart, pos);

				System.out.println(depth + "[" + numStart + ":" + pos + "] = " + s);
				
				ops.add(c);

				if (expr.charAt(numStart) != '(') {
					values.add(Integer.parseInt(s));
				}
				
				numStart = pos + 1;
				if (expr.charAt(pos + 1) == '(') {
					int end = findClosingParenthesis(expr, pos + 1);
					values.add(evaluateImpl(expr, pos + 2, end, depth + "    "));
					pos = end;
				}
			}
        }


		System.out.println(values);
		String s = expr.substring(numStart, endPos);
		System.out.println(depth + "[" + numStart + ":" + endPos + "] = " + s);
		// System.out.println(values);

		return res;
    }
    
    // WIP
    public static String evaluate2(String expr) {
        String e = expr.replaceAll("\\s+","");
        return Integer.toString(evaluateImpl(e, 0, e.length(), ""));
    }
}
