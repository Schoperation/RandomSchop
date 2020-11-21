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
        int errorCode = -1;
        while (!validInput)
        {
            infix = scanner.nextLine();
            errorCode = validInfixExp(infix);
            if (errorCode == 0)
                validInput = true;
            else
                System.out.println("Error. " + errorCode + ". Try again."); // TODO make exception
        }

        PostfixExp exp = new PostfixExp(infix, true);
        System.out.println(exp.evaluate());
    }

    /**
     * Determines if this infix expression is valid.
     * @param exp Infix expression.
     * @return 1 if valid, 0 otherwise.
     */
    public int validInfixExp(String exp)
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
                return 1000 + i;
            // E.g. 88 99 + 33 is invalid
            else if (Character.isDigit(current) && numberBeforeSpace)
                return 2000 + i;
            // Parentheses
            // E.g. (3 *) is invalid
            else if (current == ')' && PostfixExp.isOperator(previous))
                return 3000 + i;
            // E.g. (* 3 + 2) is invalid
            else if (PostfixExp.isOperator(current) && previous == '(')
                return 4000 + i;
            // E.g. 4(3 * 2) is invalid, same with 4 (3 * 2)
            else if (current == '(' && (Character.isDigit(previous) || numberBeforeSpace))
                return 5000 + i;
            // E.g. (3 + 2) 4 is invalid, same with (3 + 2)4
            else if (Character.isDigit(current) && previous == ')')
                return 6000 + i;
            // Digits before operators... make sure there aren't two operators in a row
            else if (PostfixExp.isOperator(current) && !Character.isDigit(previous) && previous != ')')
                return 7000 + i;
            // Technical stuff
            // Keep track of parentheses
            else if (current == '(')
                numParentheses++;
            else if (current == ')')
                numParentheses--;
            // numberBeforeSpace stuff
            else if (current == ' ' && Character.isDigit(previous))
                numberBeforeSpace = true;
            else
                numberBeforeSpace = false;

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
            return 8000;
        else if (numParentheses != 0)
            return 9000;
        else
            return 0;
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
