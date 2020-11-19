package schoperation.RandomSchop.text.calculator;

public class Calculator
{
    /*
        Takes an infix expression and solves it.
        That's about it...
     */
    public void main()
    {
        PostfixExp exp = new PostfixExp("(5 + 75) * 2130", true);
        System.out.println(addSpaces(exp.getExpression()));
    }

    private boolean validInfixExp(String exp)
    {
        // 6 ^ 4 - 2 * 3 + (5 / 2) % 3
        return true;
    }

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
