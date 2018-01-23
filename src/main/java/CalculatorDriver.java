import com.calculator.core.ExpressionParser;
import com.calculator.exception.ParseException;
import com.calculator.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CalculatorDriver {

    private static Logger logger = LogManager.getLogger(CalculatorDriver.class);

    public static void main(String[] args) throws IOException
    {

        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Read input string till the user hits "quit"
        while (true) {

            System.out.println();
            System.out.print(Constants.WELCOME_MESSAGE);
            try {

                input = br.readLine();

                if(input.trim().equals(Constants.EXIT_CODE)){
                    br.close();
                    System.exit(0);
                }

                if(input.trim().length()<1){
                    System.out.println(Constants.ENTER_EXPRESSION);
                    continue;
                }

                String result = ExpressionParser.parseExpression(input);

                if(result.length()>0) {
                    System.out.println();
                    System.out.println(Constants.RESULT_MESSAGE + result);
                }

            } catch (IOException e) {
                logger.error("Exception occurred when reading input.",e);
                System.out.println("Error when reading Input.");
            }
              catch (ParseException p){
                  System.out.println();
                  System.out.println(p.getMessage());
              }

        }

    }
}