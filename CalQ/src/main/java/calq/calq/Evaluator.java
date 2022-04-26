package calq.calq;

import java.lang.Math;

class Evaluator {
	private static double parseNumber(String expr) {
		if (expr.startsWith("0x"))
			return Integer.parseInt(expr.substring(2), 16);
		else if (expr.startsWith("0"))
			return Integer.parseInt(expr, 8);
		else
			return Double.parseDouble(expr);
	}

	private static double evalUnary(String expr, int start, int end) throws Exception {
		if (Character.isDigit(expr.charAt(start)))
			return parseNumber(expr.substring(start, end));
		else if (expr.charAt(start) == '(' && expr.charAt(end - 1) == ')')
			return evalImpl(expr, start + 1, end - 1, 5);

		double res = evalUnary(expr, start + 1, end);

		switch (expr.charAt(start)) {
			case '+': return +res;
			case '-': return -res;
			// case '~': return ~res;
			case 'S': return Math.sin(res);
			case 'K': return Math.cos(res);
			case 'T': return Math.tan(res);
			case 'Q': return Math.sqrt(res);
			case 'F': return Math.floor(res);
			case 'C': return Math.ceil(res);
		}

		if (true) {
			throw new Exception("Bad unary expression");
		}
		return 0;
	}

	private static double evalBinary(Double lhs, Character operator, String expr, int start, int end, int opID) throws Exception {
		double rhs = evalImpl(expr, start, end, opID - 1);
		if (lhs == null) return rhs;

		switch (operator) {
			case '*': return lhs * rhs;
			case '/': return lhs / rhs;
			case '%': return lhs % rhs;
			case '+': return lhs + rhs;
			case '-': return lhs - rhs;
			// case 'L': return (int)lhs << (int)rhs;
			// case 'R': return (int)lhs >> (int)rhs;
			// case '&': return (int)lhs & (int)rhs;
			// case '^': return (int)lhs ^ (int)rhs;
			// case '|': return (int)lhs | (int)rhs;
		}

		if (true) {
			throw new Exception("Bad binary expression");
		}
		return 0;
	}

	private static boolean in(char c, char[] a) {
		for (char x : a) {
			if (x == c) return true;
		}
		return false;
	}

	private static int asInt(boolean b) {
		return b ? 1 : 0;
	}

	private static double evalImpl(String expr, int start, int end, int opID) throws Exception {
		if (opID == -1) return evalUnary(expr, start, end);

		char[][] ops = {{'*', '/', '%'}, {'+', '-'}, {'L', 'R'}, {'&'}, {'^'}, {'|'}};
		int depth = 0;
		Double operand = null;
		Character operator = null;
		boolean isBinaryOp = false;

		for (int i = start; i < end; ++i) {
			char c = expr.charAt(i);

			depth += asInt(c == '(') - asInt(c == ')');

			if (depth == 0 && isBinaryOp &&  in(c, ops[opID])) {
				operand = evalBinary(operand, operator, expr, start, i, opID);
				operator = c;
				start = i + 1;
			}
			else if (depth < 0)
				throw new Exception("Unmatched parentheses");

			isBinaryOp = (c == ')' || Character.isDigit(c));
		}

		return evalBinary(operand, operator, expr, start, end, opID);
	}

	public static String evaluate(String expr) {
        expr = expr.toLowerCase()
            .replace(" ", "")
            .replace("\t", "")
            .replace("<<", "L")
            .replace(">>", "R")
            .replace("sin", "S")
            .replace("cos", "K")
            .replace("tan", "T")
            .replace("sqrt", "Q")
            .replace("floor", "F")
            .replace("ceil", "C");
         
        try {
            return Double.toString(evalImpl(expr, 0, expr.length(), 5));
        }
        catch(Exception e) {
            return "Error";
        }
    }
}
	