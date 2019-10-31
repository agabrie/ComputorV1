import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

class Polynomial{
	ArrayList<String> lst = new ArrayList<String>();
	int degree = 0;
	double discriminant;
	double []coeff = {0.0, 0.0, 0.0};
	String []oper = {"+", "+", "+"};
	
	public double getDiscriminant(){calcDiscriminant(); return this.discriminant;}
	public int getDegree(){return this.degree;}

	public Polynomial(String s){
		s = s.replaceAll(Pattern.quote(" * "), "*");
		s = s.replaceAll(Pattern.quote("*"), "");
		s = s.replaceAll(Pattern.quote(" ^ "), "^");
		lst = tokenize(s);
		findDegreeCoefficients();
		calcDiscriminant();
	}
	public Polynomial(){
	}
	public void calcDiscriminant(){
		double a = getA();
		double b = getB();
		double c = getC();
		this.discriminant = (MainClass.pow(b,2) - 4*(a)*(c));
	}
	public void reduce(Polynomial p2){
		for(int i = 0; i < 3; i++){
			this.coeff[i] = isPositive(this.oper[i])*this.coeff[i] + (-1 * isPositive(p2.oper[i])*p2.coeff[i]);
			if (this.coeff[i] < 0){
				this.oper[i] = "-";
				this.coeff[i] *= -1;
			}
		}
	}
	
	public int findDegreeCoefficients(){
		String elemsplit[];
		for (String elem : lst){
			elemsplit = elem.split(Pattern.quote("X^"));
			if(elemsplit.length == 2){
				if(Integer.parseInt(elemsplit[1]) > 2)
				{
					System.out.println("Enter equation of degree 2 or lower");
				}
				coeff[Integer.parseInt(elemsplit[1])] = (isPositive(oper[Integer.parseInt(elemsplit[1])]))*Double.parseDouble(elemsplit[0]);
				if (this.coeff[Integer.parseInt(elemsplit[1])] < 0){
					this.oper[Integer.parseInt(elemsplit[1])] = "-";
					this.coeff[Integer.parseInt(elemsplit[1])] *= -1;
				}
				if(degree < Integer.parseInt(elemsplit[1])){
					degree = Integer.parseInt(elemsplit[1]);
				}
			}
		}
		return 0;
	}

	static boolean isOperator(String op){
		return (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"));
	}
	
	static int isPositive(String op){
		return (op.equals("+") ? 1 : -1);
	}

	public double evaluate(boolean sign){
		double a = getA();
		double b = getB();
		double c = getC();
		double x = 0.0;
		if (sign)
			x = (-b + MainClass.sqrt(this.discriminant))/(2 * a);
		else
			x = (-b - MainClass.sqrt(this.discriminant))/(2 * a);			
		return x;
	}

	public double evaluate(){
		double a = getA();
		double b = getB();
		double x = 0;
		x = ((-b) / (2 * a));
		return x;
	}
	
	ArrayList<String> tokenize(String str){
		String arr[] = str.split("\\s+");
		int op = 0;
		boolean first = true;
		ArrayList<String> lst= new ArrayList<String>();
		for(String element : arr){
			element = element.trim();
			if(isOperator(element)){
				oper[op++] = (isPositive(element) == 1 ? "+" : "-");
			}
			else{
				if(first){
					oper[op++] = "+";
					first = false;
				}
				lst.add(element);
			}
		}
		lst.removeAll(Collections.singletonList(""));
		return lst;
	}

	public double getA(){
		return(isPositive(oper[2]) * coeff[2]);
	}
	public double getB(){
		return(isPositive(oper[1]) * coeff[1]);
	}
	public double getC(){
		return(isPositive(oper[0]) * coeff[0]);
	}

	public String toString(){
		String s = "";
		boolean first = false;
		for(int i = 0 ; i < 3; i++){
			if(coeff[i] != 0.0){
				if(!first){
					first = true;
					if(isPositive(oper[i]) == -1)
						s += oper[i]+" ";
				}
				else{
					s += oper[i]+" ";
				}
				s += String.format("%.1f * X^%d ", coeff[i],i);
			}
		}
		return s;
	}
}