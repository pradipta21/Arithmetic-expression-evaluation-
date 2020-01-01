import java.util.Stack;

public class InfixEvaluation {

	public Double evaluate(String expression) {
		System.out.println(expression);
		//stack for operands
		Stack<Double> numberStack = new Stack<>();
		
		//stack for characters
		Stack<Character> operatorStack = new Stack<>();
		
		char []expToken = expression.toCharArray();
		for(int i=0; i<expToken.length; i++) {
			
			char ch = expToken[i];
			
			//Handling space in the expression
			if(ch == ' ') {
				continue;
			}
			else if(Character.isDigit(ch) || ch == '.') {
				StringBuffer buff = new StringBuffer();
				while((i < expToken.length) && (Character.isDigit(expToken[i]) || expToken[i] == '.')) {
					buff.append(expToken[i++]);
				}
				i--;
				numberStack.push(Double.parseDouble(buff.toString()));		
			}
			else if(ch == '(') {
				//push to the operator stack
				operatorStack.push(ch);
			}
			else if(ch == ')') {
				while(operatorStack.peek() != '(') {
					double output = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop());
					numberStack.push(output);
				}
				operatorStack.pop();
			}
			else if(isOperator(ch)) {
				while(!operatorStack.isEmpty() && (precedence(ch) < precedence(operatorStack.peek()))) {
					double output = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop());
					numberStack.push(output);
				}
				operatorStack.push(ch);		
			}	
		}
		
		while(!operatorStack.isEmpty()) {
			double output = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop());
			numberStack.push(output);
		}
		return numberStack.peek();
	}
	
	public int precedence(char op) {
		if(op == '*' || op == '/') {
			return 2;
		}else if(op == '+' || op == '-') {
			return 1;
		}else if(op == '^') {
			return 3;
		}
		return -1;
	}
	
	public boolean isOperator(char op) {
		return (op == '+' || op == '-' || op == '*' || op == '/' || op == '^');
	}
	
	public double performOperation(Double a, Double b, Character op) {
		switch(op) {
			case '+' : return a+b;
			case '-' : return b-a;
			case '*' : return a*b;
			case '^' : return Math.pow(b, a);
			case '/' : 
				if(a == 0) {
					throw new UnsupportedOperationException("Cannot divide by 0");
				}else {
					return b/a;
				}
		}
		return 0;
	}
}
