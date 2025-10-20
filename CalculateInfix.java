//import java.util.ArrayDeque;

public class CalculateInfix {

    // Helper: operator precedence
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

  

