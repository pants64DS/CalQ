package calq.evaluator;
import calq.evaluator.Evaluator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {
    @Test
    public void KahdenPositiivisenSummaOikein() {
        assertEquals(Evaluator.evaluate("3 + 7"), "10");
    }
    
    @Test
    public void MonimutkainenLausekeOikein() {
        assertEquals(Evaluator.evaluate("3*(+(-2*-1-(-2)-(-0x3+-4))-+-+-+10)/2"), "1.5");
    }
}
