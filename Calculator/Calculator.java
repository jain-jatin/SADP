/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Stack;

/**
 *
 * @author Jatin
 */
public class Calculator {
    private Stack<Double> values = null;
    private Stack<Character> ops = null;
    private char[] tokens = null;
    BasicCalculator bc;
    
    public Calculator(){
        bc = new BasicCalculator();
    }
    
    public String evaluate(String b)
    {
        tokens = b.toCharArray();
        values = new Stack<>();
        ops = new Stack<>();
 
        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ')
                continue;
            
            if ((tokens[i] >= '0' && tokens[i] <= '9') || (tokens[i]=='.'))
            {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length &&((tokens[i] >= '0' && tokens[i] <= '9') || (tokens[i]=='.')))
                    sbuf.append(tokens[i++]);
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
                
            }
            
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(bc.applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
            {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(bc.applyOp(ops.pop(), values.pop(), values.pop()));
          
                ops.push(tokens[i]);
            }
        }
  
        while (!ops.empty())
            values.push(bc.applyOp(ops.pop(), values.pop(), values.pop()));
   
        return String.format("%.2f", values.pop());
    }
    
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' )
            return false;
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }
}
