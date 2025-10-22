import java.util.Stack;
import java.util.Queue;
/**
 * Provides a method to evaluate postfix expressions.
 */
public class CalculatePostfix {

    /**
     * Evaluates a postfix expression represented as a queue of tokens.
     * Operands are Doubles and operators are Characters (+, -, *, /, ^).
     *
     * @param tokens a queue of postfix tokens containing Doubles and operator Characters
     * @return the evaluated result as a Double
     * @throws IllegalArgumentException if the expression is invalid,
     *                                  contains invalid tokens,
     *                                  or involves division by zero
     */
    public static Double postfixToResult(QueueADT<Object> tokens) {
        // FILL IN
        //Push numbers onto the stack
        //Pop two numbers when you see an operator
        //Make sure the expression is valid (like when there are/aren't enough operands)
        Stack<Double> stack = new Stack<>(); //creates empty stack for doubles
        //loop through the queue of tokens
        while (!tokens.isEmpty()) {
            Object token = tokens.remove(); //remove the front token from the queue

            if (token instanceof Double){
                stack.push((Double) token); //push the double onto the stack
            } 
            else if (token instanceof Character){
                if (stack.isEmpty()) throw new IllegalArgumentException("Not enough: " + token);
                Double b = stack.pop(); //pop the top two doubles from the stack, b then a
                Double a = stack.pop();
                char operator = (Character) token; //cast the token to a character

                //if (stack.isEmpty()) throw new IllegalArgumentException("Not enough: " + token);
                

                Double result;

                switch (operator) { //perform the operation based on the operator
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case '*': result = a * b; break;
                    case '/' : {
                        if (b == 0) throw new IllegalArgumentException("Division by zero");
                        result = a / b;
                        break;
                    }
                    //phase 4
                    case '^':
                    result = Math.pow(a, b);
                    break;

                    default: throw new IllegalArgumentException("Invalid op: " + operator);
                }
                stack.push(result); //push the result back onto the stack
            } 
            else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }
        // After processing all tokens there must be exactly one result on the stack
        if (stack.isEmpty()) throw new IllegalArgumentException("No result");
        Double finalResult = stack.pop(); //pop the final result from the stack
        if (!stack.isEmpty()) throw new IllegalArgumentException("Too many ops");
        //return the final result
        return finalResult; 
    }

    /**
     * Runs the Postfix calculator program.
     * Reads a postfix expression from the command line, tokenizes it,
     * and prints the evaluated result.
     *
     * @param args command-line arguments; expects one postfix expression as a string
     */
    public static void main(String[]args){

        // read expression from command line
        String expressionString = args[0];

        // convert expression to queue of tokens
        QueueADT<Object> tokens = Tokenizer.readTokens(expressionString);

        //print to see what the result is 
        System.out.println(tokens);

        //compute the result
        Double result = postfixToResult(tokens);

        //print the result
        System.out.println(result);
    }
}