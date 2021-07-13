import StringProcessor.StringProcessorProxy;

import static java.lang.Double.parseDouble;

/**
 * Created by jakeg on 9/15/2018.
 */

public class Main {

    public static void main(String[] args){
        if(args.length == 3){
            StringProcessorProxy processor = new StringProcessorProxy();

            //Run test for toLowerCase
            String initialLowerCaseString = args[0];
            processor.setInitialLowerCase(initialLowerCaseString);

            String newLowerCaseString = processor.toLowerCase(initialLowerCaseString);

            String lowerCaseTestComparison = initialLowerCaseString.toLowerCase();

            if(newLowerCaseString.equals(lowerCaseTestComparison)){
                System.out.println("To Lower Case test is a success!");
            }
            else{
                System.out.println("ERROR! To Lower Case test was unsuccessful");
            }

            //Run test for trimString
            String initialTrimString = args[1];
            processor.setIntialTrim(initialTrimString);

            String newTrimString = processor.trim(initialTrimString);

            String trimTestComparison = initialTrimString.trim();

            if(newTrimString.equals(trimTestComparison)){
                System.out.println("Trim String test is a success!");
            }
            else{
                System.out.println("ERROR! Trim String test was unsuccessful");
            }

            //Run test for parseDouble
            String initialParseDouble = args[3];
            processor.setInitialDouble(initialParseDouble);

            Double newParseDouble = processor.parseDouble(initialParseDouble);

            Double parseDoubleTestComparison = parseDouble(initialParseDouble);

            if(newParseDouble.equals(parseDoubleTestComparison)){
                System.out.println("Parse Double test is a success!");
            }
            else{
                System.out.println("ERROR! Parse Double test was unsuccessful");
            }

        }
    }
}
