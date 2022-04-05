package calq;
import calq.calq.Evaluator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {
    @Test
    public void KahdenPositiivisenSummaOikein() {
        assertEquals(Evaluator.evaluate("3 + 7"), "10");
    }
}
