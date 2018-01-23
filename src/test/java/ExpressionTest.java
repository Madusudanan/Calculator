import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.calculator.core.ExpressionParser;
import com.calculator.exception.ParseException;
import org.junit.Test;


public class ExpressionTest {

    @Test
    public void positiveTestCases() throws Exception{
        //Addition
        assertEquals("5", ExpressionParser.parseExpression("1+4"));
        //Subtraction
        assertEquals("10", ExpressionParser.parseExpression("15-5"));
        //Multiplication
        assertEquals("100", ExpressionParser.parseExpression("10*10"));
        //Division
        assertEquals("25", ExpressionParser.parseExpression("125/5"));
        //Test for a slightly complicated expression with params
        assertEquals("2500", ExpressionParser.parseExpression("(100*50)/(10*10)/5*(250)"));
        //Test for BODMAS rule
        assertEquals("20",ExpressionParser.parseExpression("20*2-20"));
    }

    @Test
    public void unBalancedParenthesis() throws Exception{
        Throwable t = null;

        try {
            ExpressionParser.parseExpression("((1+2)");
        }
        catch (Throwable p){
            t = p;
        }

        assertTrue(t instanceof ParseException);
    }

    @Test
    public void invalidAlphaNumeric() throws Exception{
        Throwable t = null;

        try {
            ExpressionParser.parseExpression("abc277384834");
        }
        catch (Throwable p){
            t = p;
        }

        assertTrue(t instanceof ParseException);
    }

    @Test
    public void invalidSymbols() throws Exception{
        Throwable t = null;

        try {
            ExpressionParser.parseExpression("{{}8/&}");
        }
        catch (Throwable p){
            t = p;
        }

        assertTrue(t instanceof ParseException);
    }

    @Test
    public void divideByZero() throws Exception{
        Throwable t = null;

        try {
            ExpressionParser.parseExpression("12/0");
        }
        catch (Throwable p){
            t = p;
        }

        assertTrue(t instanceof ParseException);
    }

    @Test
    public void missingOperand() throws Exception{
        Throwable t = null;

        try {
            ExpressionParser.parseExpression("12*");
        }
        catch (Throwable p){
            t = p;
        }

        assertTrue(t instanceof ParseException);
    }
}