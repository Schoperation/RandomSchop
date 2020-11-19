package schoperation.RandomSchop.text.calculator;

import java.util.Stack;

public class PostfixExp
{
    /*
        A post-fix expression. The constructor can take in a postfix or an infix, which is then converted.
     */

    // Actual expression
    String exp;

    public PostfixExp(String exp, boolean convert)
    {
        this.exp = convert ? convertToPostfix(exp) : exp;
    }

    public String getExpression()
    {
        return this.exp;
    }

    /**
     * Converts an infix expression to postfix.
     * @param infix
     * @return String postfix
     */
    private String convertToPostfix(String infix)
    {
        // Create a stack for the operators, and digits
        Stack<Character> operators = new Stack<>();
        Stack<Character> digits = new Stack<>();

        /*
           The while loop below is structured to loop until the stack is empty.
           This'll happen when the last expression in parentheses is taken care of.
           Thus, the entire thing will be in parentheses! WOAAHHH
        */
        operators.push('(');
        infix = infix.concat(")");
        StringBuilder postfix = new StringBuilder();

        int i = 0;
        char c;
        while (!operators.isEmpty())
        {
            c = infix.charAt(i);

            // If digit, add to stack
            if (Character.isDigit(c))
            {
                digits.push(c);
                //postfix.append(infix.charAt(i));
            }
            // If left parenthesis, push onto stack
            else if (c == '(')
                operators.push(c);
            else if (isOperator(c))
            {
                // Pop any digits in the stack, and convert them to a postfix expression of single digits
                // 369 = 3 * 100 + 6 * 10 + 9
                //     = 3 * 10 ^ 2 + 6 * 10 ^ 1 + 9
                //     = 3 * (5 * 2) ^ 2 + 6 * 5 * 2 ^ 1 + 9
                // first part 3 * (5 * 2) ^ 10 = 352*2^*
                // We can start at 10 ^ 0 since the digits are backwards.
                int power = 0;
                while (!digits.isEmpty())
                {
                    postfix.append(digits.pop().toString() + "52*" + power + "^*");
                    power++;
                }

                power--;
                int j;
                for (j = 0; j < power; j++)
                    postfix.append("+");

                // Pop any operators of equal and higher precedence from the stack, and into the postfix expression
                while (determinePrecedence(operators.peek(), c) >= 0)
                    postfix.append(operators.pop());

                operators.push(c);
            }
            else if (c == ')')
            {
                // Pop all digits, just like in other else if statement
                int power = 0;
                while (!digits.isEmpty())
                {
                    postfix.append(digits.pop().toString() + "52*" + power + "^*");
                    power++;
                }

                power--;
                int j;
                for (j = 0; j < power; j++)
                    postfix.append("+");

                // Pop all operators until we reach a left parenthesis, which will then be popped and deleted
                while (operators.peek() != '(')
                    postfix.append(operators.pop());

                operators.pop();
            }

            i++;
        }

        return postfix.toString();
    }

    /**
     * Evaluates the postfix expression.
     * @return
     */
    public int evaluate()
    {
        return 42;
    }

    /**
     * Detects if the specified char is an operator.
     * @param op
     * @return
     */
    private boolean isOperator(char op)
    {
        switch (op)
        {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
            case '%':
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines which operator is more precedent (overpowers) than the other.
     * Returns:
     *      -1 if op1 is less than op2
     *       0 if op1 is equal to op2
     *       1 if op1 is greater than op2
     * @param op1
     * @param op2
     * @return int precedence
     */
    private int determinePrecedence(char op1, char op2)
    {
        // Figure out precedence of each operator
        int prec1 = getPrecedence(op1);
        int prec2 = getPrecedence(op2);

        // Compare
        if (prec1 < prec2)
            return -1;
        else if (prec1 == prec2)
            return 0;
        else
            return 1;
    }

    /**
     * Used by the method determinePrecedence(char op1, char op2). Returns an integer which reflects its precedence
     * according to PEMDAS. Highest goes to ^, second highest to %, /, and *, and lowest to - and +.
     * @param op
     * @return
     */
    private int getPrecedence(char op)
    {
        switch (op)
        {
            case '^':
                return 3;
            case '%':
            case '/':
            case '*':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
}
