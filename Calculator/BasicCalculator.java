package calculator;

public class BasicCalculator implements Basic_calc_interface{
    
    
    @Override public double add(double a, double b)
    {
        return a+b;
    }
    
    @Override public double subtract(double a, double b)
    {
        return a-b;
    }
    
    @Override public double multiply(double a, double b)
    {
        return a*b;
    }
    
    @Override public double divide(double a, double b)
    {
        return a/b;
    }
    

    public double applyOp(char op, double b, double a)
    {
        
        switch (op)
        {
        case '+':
            return add(a,b);
        case '-':
            return subtract(a,b);
        case '*':
            return multiply(a,b);
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return divide(a,b);
        }
        return 0;
    }   
}
