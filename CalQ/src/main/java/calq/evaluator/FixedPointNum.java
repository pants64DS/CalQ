
package calq.evaluator;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.lang.Math;

public class FixedPointNum {
	static private int fracWidth = 12; // number of fractional bits
	static private BigInteger fullBitMask = null;
	private BigInteger val;

	public static void setFracWidth(int w) {
		fracWidth = w;
	}

	public static void setFullWidth(int fullWidth) {
		if (fullWidth < 0)
			fullBitMask = null;
		else
			fullBitMask = BigInteger.ONE.shiftLeft(fullWidth).subtract(BigInteger.ONE);
	}

	public double toDouble() {
		return this.val.doubleValue() / Math.pow(2.0, (double)fracWidth);
	}

	private FixedPointNum(BigInteger val) {
		if (fullBitMask == null)
			this.val = val;
		else
			this.val = val.and(fullBitMask);
	}

	private FixedPointNum(double d) {
		this(BigDecimal.valueOf(d * (1 << fracWidth)).toBigInteger());
	}

	private static int findSeparator(String str, int start, int end) {
		int separatorPos = start;
		while (separatorPos < end && str.charAt(separatorPos) != '.') {
			++separatorPos;
		}
		return separatorPos;
	}

	private BigInteger getFractionalPart(String str, int start, int end, int radix) {
		String fracPartStr = str.substring(start, end);
		int numFracDigits = fracPartStr.length();
		BigInteger fractionalPart = new BigInteger(fracPartStr, radix).shiftLeft(fracWidth);

		BigInteger bigRadix = BigInteger.valueOf(radix);
		for (int i = 0; i < numFracDigits; ++i) {
			fractionalPart = fractionalPart.divide(bigRadix);
		}
		return fractionalPart;
	}

	private void init(String str, int start, int end, int radix) throws Exception {
		int separatorPos = findSeparator(str, start, end);
                
                if (separatorPos < end && radix == 8) {
                    radix = 10;
                }

		boolean hasIntegerPart = separatorPos > start;
		boolean hasFractionalPart = separatorPos < end - 1;

		if (!hasIntegerPart && !hasFractionalPart)
			throw new Exception("Invalid number: \"" + str.substring(start, end) + "\"");

		BigInteger integerPart = BigInteger.ZERO;
		BigInteger fractionalPart = BigInteger.ZERO;

		if (hasIntegerPart) {
			integerPart = new BigInteger(str.substring(start, separatorPos), radix).shiftLeft(fracWidth);
		}
		if (hasFractionalPart) {
			fractionalPart = getFractionalPart(str, separatorPos + 1, end, radix);
		}

		this.val = fractionalPart.add(integerPart);

		if (fullBitMask != null)
			this.val = this.val.and(fullBitMask);
	}

	public FixedPointNum(String str, int start, int end) throws Exception {	
		if (start + 2 < end && str.substring(start, start + 2).equals("0x")) {
			this.init(str, start + 2, end, 16);
		}
		else if (str.charAt(start) == '0') {
			this.init(str, start + 1, end, 8);
		}
		else {
			this.init(str, start, end, 10);
		}
	}

	public FixedPointNum add(FixedPointNum other) {
		return new FixedPointNum(this.val.add(other.val));
	}

	public FixedPointNum sub(FixedPointNum other) {
		return new FixedPointNum(this.val.subtract(other.val));
	}

	public FixedPointNum mul(FixedPointNum other) {
		return new FixedPointNum(this.val.multiply(other.val).shiftRight(fracWidth));
	}

	public FixedPointNum div(FixedPointNum other) {
		return new FixedPointNum(this.val.shiftLeft(fracWidth).divide(other.val));
	}

	public FixedPointNum mod(FixedPointNum other) {
		return new FixedPointNum(this.val.shiftLeft(fracWidth).mod(other.val));
	}

	public FixedPointNum neg() {
		return new FixedPointNum(this.val.negate());
	}

	public FixedPointNum shift(FixedPointNum other) throws Exception {
		int amount = other.val.shiftRight(fracWidth).intValue();

		if (BigInteger.valueOf(amount).shiftLeft(fracWidth) != other.val)
			throw new Exception("Bad shift");
		else if (amount >= 0)
			return new FixedPointNum(this.val.shiftLeft(amount));
		else
			return new FixedPointNum(this.val.shiftRight(-amount));
	}

	public FixedPointNum and(FixedPointNum other) {
		return new FixedPointNum(this.val.and(other.val));
	}

	public FixedPointNum xor(FixedPointNum other) {
		return new FixedPointNum(this.val.xor(other.val));
	}

	public FixedPointNum orr(FixedPointNum other) {
		return new FixedPointNum(this.val.or(other.val));
	}

	public FixedPointNum not() {
		return new FixedPointNum(this.val.not());
	}

	public FixedPointNum sin() {
	 	return new FixedPointNum(Math.sin(this.toDouble()));
	}

	public FixedPointNum cos() {
	 	return new FixedPointNum(Math.cos(this.toDouble()));
	}

	public FixedPointNum tan() {
	 	return new FixedPointNum(Math.tan(this.toDouble()));
	}

	public FixedPointNum sqrt() {
	 	return new FixedPointNum(Math.sqrt(this.toDouble()));
	}

	public FixedPointNum floor() {
		return new FixedPointNum(this.val.shiftRight(fracWidth).shiftLeft(fracWidth));
	}

	public FixedPointNum ceil() {
		if (this.val.equals(this.floor().val))
			return this;
		else
			return new FixedPointNum(this.val.add(BigInteger.ONE.shiftLeft(fracWidth))).floor();
	}

	public FixedPointNum abs() {
		return new FixedPointNum(this.val.abs());
	}

	public String toString() {
		return new BigDecimal(this.val).divide(new BigDecimal(2).pow(fracWidth)).toString();
	}
}
