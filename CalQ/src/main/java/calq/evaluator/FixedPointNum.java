package calq.evaluator;

public class FixedPointNum {
    int val;
    
    // WIP
    public FixedPointNum(String str) {
        str = str.toLowerCase();
        int length = str.length();
        String whole = str.replace(".", "");
        int numPoints = length - whole.length();
        int numFracDigits = 0;
        
        if (numPoints == 1)
            numFracDigits = length + str.indexOf('.') - 1;
        else if (numPoints > 1)
            throw new NumberFormatException("A number can't have more than one point");
        
        int radix = 10;
        if (length > 2 && str.charAt(0) == '0') {
            int secondChar = str.charAt(1);
            if (secondChar == 'x')
                radix = 16;
            else if (secondChar == 'b')
                radix = 2;
            else
                radix = 8;
        }
        else if (numFracDigits > 0)
            throw new NumberFormatException("Fractional decimal numbers aren't supported yet");
        
        this.val = Integer.parseInt(whole, radix);
    }
}
