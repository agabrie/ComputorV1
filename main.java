import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

class MainClass{

	static ArrayList<String> tokenize(String str){
		String arr[] = str.split("\\s+");
		ArrayList<String> lst= new ArrayList<String>();
		for(String element : arr){
			element = element.trim();
			lst.add(element);
		}
		lst.removeAll(Collections.singletonList(""));
		return lst;
	}
	static boolean isOperator(String op){
		return (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"));
	}
	static String inverseOperator(String op){
		String inverse;
		switch(op){
			case "+":
				inverse = "-";
				break;
			case "-":
				inverse = "+";
				break;
			case "*":
				inverse = "/";
				break;
			case "/":
				inverse = "*";
				break;
			default :
				inverse = ""; 
				break;
		}
		return inverse;
	}
	static double doop(double first, String op, double second){
		double result = 0.0;
		switch(op){
			case "+":
				result = add(first,second);
				break;
			case "-":
				result = sub(first,second);
				break;
			case "/":
				result = div(first,second,3);
				break;
			case "*":
				result = mul(first, second);
			default:
				result = add(first,second);
				break;
		}
		return(result);
	}
	static ArrayList<String> evaluate(ArrayList<String> lst){
		double first = 0.0;
		String op = "";
		double second = 0.0;
		boolean next = false;
		ArrayList<String> remove = new ArrayList<>();
		for(String elem:lst){
			// System.out.println(elem +" : " + remove.size());
			if(isOperator(elem)){
				op = elem;
				remove.add(elem);
			}
			else if(next){
				second = Double.parseDouble(elem);
				remove.add(elem);
				next = false;
			}
			else{
				first = Double.parseDouble(elem);
				remove.add(elem);
				next = true;
			}
			if(remove.size() == 3){
				break;
			}
		}
		for(String elem:remove){
			lst.remove(elem);
		}
		System.out.printf("%f %s %f\n",first,op,second);
		if(doop(first,op,second) > 0.0){
			lst.add("+");
			lst.add(abs(doop(first,op,second)) + "");
		}
		if(doop(first,op,second) < 0.0){
			lst.add("-");
			lst.add(abs(doop(first,op,second)) + "");
		}
		return lst;
	}
	static void reduce(ArrayList<String> lhs,ArrayList<String> rhs){
		boolean next = true;
		int i = 0;
		ArrayList<String> remove = new ArrayList<String>();
		for(String elem:rhs){
			i++;
			if(!next && isOperator(elem)){
				// lhs.add("-");
				lhs.add(inverseOperator(elem));
				next = true;
				remove.add(elem);
				continue;
			}
			else if(next){
				if(i == 1)
					lhs.add("-");
				lhs.add(elem);
				next = false;
				remove.add(elem);
				continue;
			}
			else{
				// System.out.println("none");
			}
		}
		for(String rem:remove){
			rhs.remove(rem);
		}
	}
	
	static double add(double val1, double val2)
	{
		return (val1 + val2);
	}

	static double sub(double val1, double val2)
	{
		return (val1 + (-1 * val2));
	}

	static double mul(double val1, double val2)
	{
		return (val1 * val2);
	}
	// 5 / 6
	// 0 * 6

	static double div(double dividend, double divisor, int accuracy)
	{
		double quotient = 0.0;
		int sign = 1;

		if (dividend * divisor < 0)
			sign = -1;
		dividend = abs(dividend);
		divisor = abs(divisor);

		// if (divisor == 0)
		// {
		// 	throw("div by 0");
		// }

		while (dividend > divisor)
		{
			dividend = sub(dividend, divisor);
			quotient += 1;
		}

		if (accuracy > 0 && dividend != 0)
		{
			quotient += div(dividend * 10, divisor, (int) sub(accuracy ,1)) * 0.1;
		}

		return (quotient * sign);
	}

	static double pow(double base, int exponent)
	{
		double val = 1;

		if (exponent > 0)
		{
			for (int i = 0; i < exponent; i++)
			{
				val *= base;
			}
		}

		// else
		// {
		// 	for (int i = exponent; i < 0; i++)
		// 	{
		// 		val *= div(1, base);
		// 	}
		// }
		return (val);
	}

	// static double sqrt(double val, double seed, int accuracy)
	// {
	// 	double high = seed;
	// 	double low = 0;
	// 	double average;

	// 	while (high * high < val)
	// 	{
	// 		low = high;
	// 		high = high * 2;
	// 	}

	// 	for (int i = 0; i < accuracy; i++)
	// 	{
	// 		average = div(low + high, 2);
	// 		if ((average * average) > val)
	// 		{
	// 			high = average;
	// 		} else if (average * average < val)
	// 		{
	// 			low = average;
	// 		}
	// 		else
	// 			return (average);
	// 	}

	// 	return (average);
	// }

	static double abs(double val)
	{
		if (val < 0)
			return (val * -1);
		return (val);
	}

	static void statementDebug(ArrayList<String>lhs, ArrayList<String> rhs){
		for(String elem : lhs){
			System.out.print(elem + " ");
		}
		System.out.print("= ");
		for(String elem : rhs){
			System.out.print(elem + " ");
		}
		System.out.println();
	}
	static ArrayList<String> fix(ArrayList<String> lst){
		boolean first = false;
		String op = ""; 
		ArrayList<String> remove = new ArrayList<>();
		for(String elem:lst){
			if(!first && isOperator(elem)){
				first = true;
				remove.add(elem);
			}
			else if(first && !isOperator(elem)){
				remove.add(elem);
			}
			else{
				break;
			}
		}
		return lst;
	}

	public static void main(String[] args){
		try{
			ArrayList<String> lhs= new ArrayList<String>();
			ArrayList<String> rhs= new ArrayList<String>();
			String input;
			Scanner scLine = new Scanner(System.in);
			input = scLine.nextLine();
			scLine.close();
			try{
				Scanner scInput = new Scanner(input).useDelimiter("=");
				lhs = tokenize(scInput.next());
				rhs = tokenize(scInput.next());
				reduce(lhs, rhs);
				while(lhs.size() > 2){
					lhs = evaluate(lhs);
					lhs = fix(lhs);
					statementDebug(lhs, rhs);
				}
				statementDebug(lhs, rhs);
				scInput.close();
			}catch(Exception e2){
				System.out.println("Exception : " +e2);
			}
		}catch(Exception e){
			System.out.println("Exception : " +e);
		}
	}
}