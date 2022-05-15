package calq.evaluator;

import java.lang.Math;

public class Evaluator {

	private static char[][] binaryOperators = {{'*', '/', '%'}, {'+', '-'}, {'L', 'R'}, {'&'}, {'^'}, {'|'}};
	private static int initialOpID = binaryOperators.length - 1;

	private static FixedPointNum evalUnary(String expr, int start, int end) throws Exception {
		if (Character.isDigit(expr.charAt(start)))
			return new FixedPointNum(expr, start, end);
		else if (expr.charAt(start) == '(' && expr.charAt(end - 1) == ')')
			return evalImpl(expr, start + 1, end - 1, initialOpID);

		FixedPointNum res = evalUnary(expr, start + 1, end);

		switch (expr.charAt(start)) {
			case '+': return res;
			case '-': return res.neg();
			case '~': return res.not();
			case 'S': return res.sin();
			case 'K': return res.cos();
			case 'T': return res.tan();
			case 'Q': return res.sqrt();
			case 'F': return res.floor();
			case 'C': return res.ceil();
			case 'A': return res.abs();
		}

		throw new Exception("Bad unary expression");
	}

	private static FixedPointNum evalBinary(FixedPointNum lhs, Character operator, String expr, int start, int end, int opID) throws Exception {
		FixedPointNum rhs = evalImpl(expr, start, end, opID - 1);
		if (lhs == null) return rhs;

		switch (operator) {
			case '*': return lhs.mul(rhs);
			case '/': return lhs.div(rhs);
			// case '%': return lhs % rhs;
			case '+': return lhs.add(rhs);
			case '-': return lhs.sub(rhs);
			case 'L': return lhs.shift(rhs);
			case 'R': return lhs.shift(rhs.neg());
			case '&': return lhs.and(rhs);
			case '^': return lhs.xor(rhs);
			case '|': return lhs.orr(rhs);
		}

		throw new Exception("Bad binary expression");
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

	private static FixedPointNum evalImpl(String expr, int start, int end, int opID) throws Exception {
		if (opID == -1) return evalUnary(expr, start, end);

		
		int depth = 0;
		FixedPointNum operand = null;
		Character operator = null;
		boolean isBinaryOp = false;

		for (int i = start; i < end; ++i) {
			char c = expr.charAt(i);

			depth += asInt(c == '(') - asInt(c == ')');

			if (depth == 0 && isBinaryOp &&  in(c, binaryOperators[opID])) {
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
			.replace("ceil", "C")
			.replace("abs", "A");
		
		try {
			return " = " + evalImpl(expr, 0, expr.length(), initialOpID).toString();
		}
		catch(Exception e) {
			return "Invalid expression";
		}
	}
}
