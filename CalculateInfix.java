//import java.util.ArrayDeque;
/**
 * Provides methods to convert and evaluate infix expressions.
 */
public class CalculateInfix {

    // help with operator precedence

    /**
     * Returns the precedence level of a given operator.
     *
     * @param op the operator character (+, -, *, /, ^)
     * @return an integer representing the operator's precedence level
     */
    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }


/**
* Converts an infix expression (as a queue of tokens) to postfix form
* and evaluates it using CalculatePostfix.
*
* @param tokens a queue of tokens containing Doubles and operator Characters
* @return the evaluated result of the infix expression as a Double
* @throws IllegalArgumentException if the input contains invalid tokens
*                                  or mismatched parentheses
*/
public static Double infixToPostfix(Queue<Object> tokens) {
    // FILL IN
    Queue<Object> output = new Queue<>();
    Stack<Character> operators = new Stack<>();

    while(!tokens.isEmpty()){
        Object token= tokens.remove(); //get next 

        if (token instanceof Double){
            output.add(token); //numbers straight to output
        }
        else if(token instanceof Character){
            char op = (Character) token; //cast the token to a character

            if (op == '('){
                operators.push(op);
            }else if(op == ')'){
                //pop other paren if its not empty and its not (
                while(!operators.isEmpty() && operators.peek() != '('){
                    output.add(operators.pop());
                }
                if(operators.isEmpty()){
                    throw new IllegalArgumentException("Parens mixmatched");
                }
                operators.pop(); //removes the left paren
            }else if (op == '+' || op == '-' || op == '*' || op == '/' || op == '^') { //check which op
                //handles what comes first 
                while(!operators.isEmpty() && precedence(operators.peek()) >= precedence(op)){
                    output.add(operators.pop());
                }
                operators.push(op);
            } else{
                throw new IllegalArgumentException("Invalid Operator"+ op);
            }
        
        }else {
            throw new IllegalArgumentException("Token Invalid"+ token);
    }
}

    //pop the rest of the ops
    while(!operators.isEmpty()){
        char op = operators.pop();

        if(op == '(' || op == ')'){
            throw new IllegalArgumentException("Parens mixmatched");
        }
        output.add(op);
    }

    Double result = CalculatePostfix.postfixToResult(output);
    return result;
}   
// Main method
/**
 * Runs the CalculateInfix program.
 * Takes an infix expression as a command-line argument, tokenizes it,
 * and prints the result of evaluating the expression.
 *
 * @param args command-line arguments; expects one infix expression as a string
 */
public static void main(String[] args) {
    if (args.length == 0) {
        System.out.println("Usage: java CalculateInfix \"(3+2)*5\"");
        return;
    }

    String expr = args[0];
    Queue<Object> tokens = Tokenizer.readTokens(expr);
    System.out.println("Tokens: " + tokens);

    Double result = infixToPostfix(tokens);
    System.out.println("Answer: " + result);
}
}
//javac CalculateInfix.java
//java -cp . CalculateInfix "(3+2)*5"

  

