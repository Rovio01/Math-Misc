package com.rovio.math;

import java.util.Stack;

public class Token {

	double value;
	boolean isOperator;
	int parameters;
	int precedence;
	boolean leftAssociative;

	public Token(double val) {
		isOperator=false;
		value=val;
	}

	public Token(String str) {
		if (str.equals("+")) {
			isOperator=true;
			value=0;
			parameters=2;
			leftAssociative=true;
			precedence=2;
		}
		else if (str.equals("-")) {
			isOperator=true;
			value=1;
			parameters=2;
			leftAssociative=true;
			precedence=2;
		}
		else if (str.equals("*")) {
			isOperator=true;
			value=2;
			parameters=2;
			leftAssociative=true;
			precedence=3;
		}
		else if (str.equals("/")) {
			isOperator=true;
			value=3;
			parameters=2;
			leftAssociative=true;
			precedence=3;
		}
		else if (str.equals("^")) {
			isOperator=true;
			value=4;
			parameters=2;
			leftAssociative=false;
			precedence=4;
		}
		else if (str.equals("(")) {
			isOperator=true;
			value=5;
		}
		else if (str.equals(")")) {
			isOperator=true;
			value=6;
		}
		else if (str.equals("sin")) {
			isOperator=true;
			value=7;
			parameters=1;
			leftAssociative=false;
			precedence=5;
		}
		else if (str.equals("cos")) {
			isOperator=true;
			value=8;
			parameters=1;
			leftAssociative=false;
			precedence=5;
		}
		else if (str.equals("tan")) {
			isOperator=true;
			value=9;
			parameters=1;
			leftAssociative=false;
			precedence=5;
		}
		else {
			isOperator=false;
			value=Double.valueOf(str);
		}
	}

	public boolean isOperator() {
		return isOperator;
	}

	public double value() {
		return value;
	}

	public int parameters() {
		return parameters;
	}

	public boolean isLeftAssociative() {
		return leftAssociative;
	}

	public int getPrecedence() {
		return precedence;
	}

	public int getParameters() {
		return parameters;
	}

	public double evaluate(Token[] parameters) {
		if (!isOperator) {
			return value;
		}
		double val=0;
		switch ((int)value) {
		case 0: val=parameters[0].evaluate(new Token[0])+parameters[1].evaluate(new Token[0]);
		case 1: val=parameters[0].evaluate(new Token[0])-parameters[1].evaluate(new Token[0]);
		case 2: val=parameters[0].evaluate(new Token[0])*parameters[1].evaluate(new Token[0]);
		case 3: val=parameters[0].evaluate(new Token[0])/parameters[1].evaluate(new Token[0]);
		//TODO implement the rest of the operators
		}
		return val;
	}

	public static Stack<Token> tokenize(String str) {
		String string=str;
		Stack<Token> tokens = new Stack<Token>();
		//TODO parse
		for (int i=str.length()-1;i>=0&&string.length()>0;i++) {
			p("Adding the operator, value, or function at the end of "+string+" to the stack");
			if (string.endsWith("+")||string.endsWith("-")||string.endsWith("*")
					||string.endsWith("/")||string.endsWith("%")) {
				p("Operator detected, adding "+string.substring(string.length()-1,string.length())+" to the stack");
				tokens.push(new Token(string.substring(string.length()-1, string.length())));
				string=string.substring(0, string.length()-1);
			}
			else {
				p("Not an operator, must be a number");
				String num="";
				int a;
				//TODO add all operators
				for (a=string.length()-1;a!=-1&&
						string.charAt(a)!='+'&&
						string.charAt(a)!='-'&&
						string.charAt(a)!='*'&&
						string.charAt(a)!='/';
						a--) {
					p("Adding character "+string.charAt(a));
					num=string.charAt(a)+num;
				}
				p("Adding "+num+" to the stack");
				tokens.push(new Token(num));
				string=string.substring(0,a+1);
			}
		}
		return tokens;
	}

	@Override
	public String toString() {
		if (!isOperator) {
			return ""+value;
		}
		String out="";
		switch ((int)value) {
		case 0: out="+";
		case 1: out="-";
		case 2: out="*";
		case 3: out="/";
		//TODO implement the rest of the operators
		}
		return out;
	}

	public static void p(String in) {
		System.out.println(in);
	}
}
