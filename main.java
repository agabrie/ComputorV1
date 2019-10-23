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
	static void statementDebug(ArrayList<String>lhs, ArrayList<String> rhs){
		for(String elem : lhs){
			System.out.print(elem + " ");
		}
		System.out.print("= ");
		for(String elem : rhs){
			System.out.print(elem + " ");
		}
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