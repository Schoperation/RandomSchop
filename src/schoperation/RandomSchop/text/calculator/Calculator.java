package schoperation.RandomSchop.text.calculator;

import java.util.Scanner;

public class Calculator
{
    /*
        Takes an infix expression and solves it.
        That's about it...
     */
    public void main()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter in an infix expression (e.g. 3 + 2 * 1).");
        System.out.println("Operators: +, -, *, /, %, ^. Can use parentheses.");
        System.out.println("Spaces are allowed but not required.");

        String infix = "";
        boolean validInput = false;
        while (!validInput)
        {
            infix = scanner.nextLine();
            try
            {
                validInput = validInfixExp(infix);
            }
            catch (InvalidInfixException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Try again:");
            }
        }

        PostfixExp exp = new PostfixExp(infix, true);
        System.out.println(exp.evaluate());
    }

    /**
     * Determines if this infix expression is valid.
     * @param exp Infix expression.
     * @return 1 if valid, 0 otherwise.
     */
    public boolean validInfixExp(String exp) throws InvalidInfixException
    {
        /*
            Go through the expression one by one, and keep track
            of what term we're at now and the one immediately before it.
            It should alternate between numbers and operators.
            6 * 3 ^ 2 / 7 * (3 + 4) + 1

            Parentheses should be closed.
            Left parentheses should not start right before an operator, and right ones shouldn't start right after an operator.
         */
        // previous doesn't capture spaces.
        char previous = ' ';
        char current;

        // Add 1 for each (, subtract one for each )... if it's zero at the end, then yay!
        int numParentheses = 0;

        // Basic counters
        int numOperators = 0;
        int numNumbers = 0;

        // Used in case there are multi-digit numbers, where spaces separate them.
        boolean numberBeforeSpace = false;

        int i;
        for (i = 0; i < exp.length(); i++)
        {
            current = exp.charAt(i);

            // Rule out any possibilities that make it invalid. If none ruled out, it is valid.
            // No letters or weird characters
            if (!Character.isDigit(current) && !PostfixExp.isOperator(current) && current != ')' && current != '(' && current != ' ')
                throw new InvalidInfixException("Invalid character detected at position " + i + ": " + current);
            // E.g. 88 99 + 33 is invalid
            else if (Character.isDigit(current) && numberBeforeSpace)
                throw new InvalidInfixException("Found two numbers right by each other at position " + i + ".");
            // Parentheses
            // E.g. (3 *) is invalid
            else if (current == ')' && PostfixExp.isOperator(previous))
                throw new InvalidInfixException("Invalid closing parenthesis at position " + i + ".");
            // E.g. (* 3 + 2) is invalid
            else if (PostfixExp.isOperator(current) && previous == '(')
                throw new InvalidInfixException("Invalid opening parenthesis at position " + i + ".");
            // E.g. 4(3 * 2) is invalid, same with 4 (3 * 2)
            else if (current == '(' && (Character.isDigit(previous) || numberBeforeSpace))
                throw new InvalidInfixException("Invalid number in front of parenthesis at position " + i + ".");
            // E.g. (3 + 2) 4 is invalid, same with (3 + 2)4
            else if (Character.isDigit(current) && previous == ')')
                throw new InvalidInfixException("Invalid number after parenthesis at position " + i + ".");
            // Digits before operators... make sure there aren't two operators in a row
            else if (PostfixExp.isOperator(current) && !Character.isDigit(previous) && previous != ')')
                throw new InvalidInfixException("Found two operators in a row at position " + i + ": " + current + " and " + previous + ".");
            // Technical stuff
            // Keep track of parentheses
            else if (current == '(')
                numParentheses++;
            else if (current == ')')
                numParentheses--;
            // numberBeforeSpace stuff
            else
                numberBeforeSpace = current == ' ' && Character.isDigit(previous);

            // For counting
            if (PostfixExp.isOperator(current))
                numOperators++;
            else if (Character.isDigit(current) && !Character.isDigit(previous))
                numNumbers++;

            // Set previous char
            if (current != ' ')
                previous = current;
        }

        // Last checks
        if (numOperators != numNumbers - 1)
            throw new InvalidInfixException("There appears to be " + (numNumbers - numOperators - 1) + " extra operator(s). Probably at the end.");
        else if (numParentheses != 0)
            throw new InvalidInfixException("Parentheses improperly closed somewhere.");
        else
            return true;
    }

    /**
     * Adds spaces in between each character of a string.
     * @param exp The string you want to add spaces in between each character.
     * @return A string with more spaces....!!!!!
     */
    private String addSpaces(String exp)
    {
        StringBuilder newString = new StringBuilder();
        int i;
        for (i = 0; i < exp.length(); i++)
        {
            newString.append(exp.charAt(i));
            newString.append(" ");
        }

        return newString.toString();
    }
}
