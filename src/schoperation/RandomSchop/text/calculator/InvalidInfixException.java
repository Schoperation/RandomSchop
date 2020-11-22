package schoperation.RandomSchop.text.calculator;

/**
 * <p>This exception is used in Calculator.validInfixExp(String), which determines if a given infix expression (inputted by the user)
 * is valid in order to be converted to postfix and then evaluated properly.</p>
 * <p></p>
 * <p>It technically isn't too necessary, it's just to play around with custom exceptions. Pretty nice though! <b>Oh and</b> <i>custom effects in Javadocs.</i></p>
 *
 * @author Schoperation
 * @since 420.21.69
 */
public class InvalidInfixException extends Exception
{
    public InvalidInfixException(String errorMessage)
    {
        super(errorMessage);
    }
}

