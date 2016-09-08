package com.rovio.math;

import java.util.Scanner;
import java.util.Stack;

public final class Evaluator {
	public static void main(String[] args) {
		if (args.length!=0) {
			p("Found an argument, parsing "+args[0]);
			try {
				System.out.println(evaluate(args[0]));
			}
			catch (MissingParenthasesException e) {
				System.err.println(e.toString());
			}
		}
		else {
			Scanner scan=new Scanner(System.in);
			p("Please enter an expression to evaluate");
			String str=scan.nextLine();
			p("Evaluating "+str);
			try {
				System.out.println(evaluate(str));
			}
			catch (MissingParenthasesException e) {
				System.err.println(e.toString());
			}
			scan.close();
		}
	}

	public static double evaluate(String str) throws MissingParenthasesException {
		double value=0.0;

		//Turn infix into RPN
		p("Tokenizing "+str);
		Stack<Token> inStack=Token.tokenize(str);
		Stack<Token> outStack=new Stack<Token>();
		Stack<Token> operations=new Stack<Token>();
		while(!inStack.empty()) {
			p("There are still values in the input stack to parse");
			Token token=inStack.pop();
			if (!token.isOperator()) {
				outStack.push(token);
				continue;
			}
			if (token.isOperator()) {
				System.out.println("Detected Operator");
				if (token.value()==5) {
					operations.push(token);
					continue;
				}
				else if (token.value()==6) {
					while (operations.peek().value()!=5) {
						outStack.push(operations.pop());
						if (operations.isEmpty()) {
							throw new MissingParenthasesException("You're missing a parenthases somewhere. Check your equation to make sure every parenthases is balanced.");
						}
					}
					operations.pop();
				}
				else {
					if (!operations.isEmpty()) { 
						while (operations.peek().isOperator&&
								(token.isLeftAssociative())?
										(token.getPrecedence()<=operations.peek().getPrecedence()):
											(token.getPrecedence()<operations.peek().getPrecedence())) {
							outStack.push(operations.pop());
						}
					}
					outStack.push(token);
					System.out.println("Pushing "+token);
				}
			}
		}

		//Reverse output stack
		Stack<Token> finalStack=new Stack<Token>();
		System.out.println(outStack.size());
		while (!outStack.isEmpty()) {
			System.out.print(outStack.peek().toString());
			finalStack.push(outStack.pop());
		}

		//Evaluate RPN form
		Stack<Token> valueStack=new Stack<Token>();
		while (!finalStack.isEmpty()) {
			if (!finalStack.peek().isOperator) {
				valueStack.push(finalStack.pop());
			}
			else {
				Token operator=finalStack.pop();
				Token[] operands=new Token[operator.getParameters()];
				for (int i=0;i<operator.getParameters();i++) {
					operands[i]=valueStack.pop();
				}
				Token evaluatedValue=new Token(operator.evaluate(operands));
				valueStack.push(evaluatedValue);
			}
		}
		value=valueStack.pop().evaluate(new Token[0]);
		return value;
	}

	public static <T> void reverseStack(Stack<T> stack) {
		if (stack.isEmpty()) {
			return;
		}
		// Remove bottom element from stack
		T bottom = popBottom(stack);

		// Reverse everything else in stack
		reverseStack(stack);

		// Add original bottom element to top of stack
		stack.push(bottom);
	}
	private static <T> T popBottom(Stack<T> stack) {
		T top = stack.pop();
		if (stack.isEmpty()) {
			// If we removed the last element, return it
			return top;
		} else {
			// We didn't remove the last element, so remove the last element from what remains
			T bottom = popBottom(stack);
			// Since the element we removed in this function call isn't the bottom element, add it back onto the top of the stack where it came from
			stack.push(top);
			return bottom;
		}
	}
	//System.out.println for the lazy
	public static void p(String in) {
		System.out.println(in);
	}
}
