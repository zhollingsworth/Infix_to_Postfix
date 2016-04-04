/*************************************************************************************
 CS3410 Data Structures
 Assignment 3
 
 Zac Hollingsworth
 February 7, 2013
 
 Write a Java Program that converts a given infix expression to the corresponding 
 postfix expression and then evaluates the postfix expression to return the answer. You
 may assume that the input infix expression is correct. That is, the user will always 
 type a correct infix expression with proper parenthesis.
 
 You must use stack to convert to postfix expression and then evaluate it.
 The main() method is responsible to capture the input, scan it, use stack, and
 produce output.
 **************************************************************************************/

import java.util.*;

public class Infix_to_Postfix{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String infix = "";
		//Collecting data from user
		System.out.println("Enter an expression: ");
		infix += scan.nextLine();
		String postfix = toPostfix(infix);
		//Printing the output
		System.out.println(postfix);
		System.out.println(compute(postfix));
	}
//******************************************************
//Method to assist in toPostfix method 
//******************************************************
	public static int precedence(char in){
		int go;
		if(in == '*' || in == '/'){
			return 2;
		}
		else if(in == '+' || in == '-'){
			return 1;
		}
		else
			return 3;
	}
//******************************************************
//Method to determine the Postfix expression
//******************************************************
	public static String toPostfix(String infix){
		char c1;
		String postfix = "";
		String par = "";
		char c2;
		Stack<Character> ops = new Stack<Character>();
		//Main loop for string evaluation
		for(int i = 0; i<infix.length(); i++){
			c1 = infix.charAt(i);
			if(Character.isDigit(c1)){
				postfix += c1;
			}
			else if(ops.empty()){
				ops.push(c1);
			}
			else if(precedence(c1) <= precedence(ops.peek()) && c1 != ')'){
				c2 = ops.pop();
				if(c2 != '(')
					postfix += c2;
				ops.push(c1);
			}
			else if(precedence(c1) >= precedence(ops.peek()) && c1 != ')'){
				ops.push(c1);
			}
			else if(precedence(c1) == precedence(ops.peek()) && c1 != ')'){
				c2 = ops.pop();
				if(c2 != '(')
					postfix += c2;	
				ops.push(c1);
			}
			else if(c1 == ')'){
				if(!ops.empty())
					postfix += ops.pop();
			}
		}
		while(!ops.empty()){
			postfix+=ops.pop();
		}
		return postfix;
	}
//******************************************************
//Method to compute the value of the Postfix expression
//******************************************************
	public static double compute(String postfix){
		Stack<Double> statKeep = new Stack<Double>();
		double value = 0;
		char c3;
		double arg1;
		double arg2;
		double c4;
		for(int i = 0; i < postfix.length(); i++){
			c3 = postfix.charAt(i);
			//Main loop for string evaluation
			if(Character.isDigit(c3)){
				c4 = (double)Character.getNumericValue(c3);
				statKeep.push(c4);
			}
			else if(!Character.isDigit(c3)){
				arg2 = statKeep.pop();
				arg1 = statKeep.pop();
					if(c3 == '+'){
						value = arg1 + arg2;
						statKeep.push(value);
					}else if(c3 == '-'){
						value = arg1 - arg2;
						statKeep.push(value);
					}else if(c3 == '*'){
						value = arg1 * arg2;
						statKeep.push(value);
					}else{
						value = arg1 / arg2;
						statKeep.push(value);
					}
				}
			}
		return value;
	}
}
