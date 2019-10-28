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
		// s = s.replaceAll(Pattern.quote("+ "), "+");
		s = s.replaceAll(Pattern.quote(" * "), "");
		// s = s.replaceAll(Pattern.quote("- "), "-");
		lst = tokenize(s);
		findDegreeCoefficients();
		calcDiscriminant();
		// System.out.printf("Polynomial string: %s\n  degree: %d\n",s,degree);
		// System.out.printf("Coeffiecients : %s %.1f * X^2 %s %.1f * X^1 %s %.1f * X^0\n",oper[0],coeff[0],oper[1],coeff[1],oper[2],coeff[2]);
		// System.out.printf("Coefficients : %s",this);
		// reduce(p2);
	}
	public Polynomial(){
	}
	public void calcDiscriminant(){
		double a = isPositive(oper[2])*coeff[2];
		double b = isPositive(oper[1])*coeff[1];
		double c = isPositive(oper[0])*coeff[0];
		this.discriminant = (MainClass.pow(b,2) - 4*(a)*(c));
		// System.out.printf("power function : a , b , c ::: %.2f, %.2f, %.2f \n then descriminant is ==>>> %.2f\n", a, b, c, this.discriminant);
	}
	public void reduce(Polynomial p2){
		// this.coeff[0] += p2.coeff[0];
		// this.coeff[1] += p2.coeff[1];
		// this.coeff[2] += p2.coeff[2];
		for(int i = 0; i < 3; i++){
			// System.out.printf("(%s %.2f) - (%s %.2f)\n",this.oper[i],this.coeff[i],p2.oper[i],p2.coeff[i]);
			this.coeff[i] = isPositive(this.oper[i])*this.coeff[i] + (-1 * isPositive(p2.oper[i])*p2.coeff[i]);
			if (this.coeff[i] < 0){
				this.oper[i] = "-";
				this.coeff[i] *= -1;
			}
			// System.out.printf("result = (%s %.2f)\n",this.oper[i],this.coeff[i]);
		}
	}
	
	public int findDegreeCoefficients(){
		String elemsplit[];
		for (String elem : lst){
			elemsplit = elem.split(Pattern.quote("X^"));
			// switch(elemsplit[1]){
			// 	case "1":
			// 	case "2":

			// }
			if(elemsplit.length == 2){
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
		double a = isPositive(oper[2])*coeff[2];
		double b = isPositive(oper[1])*coeff[1];
		double c = isPositive(oper[0])*coeff[0];
		double x = 0.0;
		// if (sign)
			// x = (b + MainClass.sqrt(this.discriminant))/(2 * a);
		// else
			// x = (b - MainClass.sqrt(this.discriminant))/(2 * a);			
		return x;
	}

	public double evaluate(){
		double a = isPositive(oper[2])*coeff[2];
		double b = isPositive(oper[1])*coeff[1];
		double c = isPositive(oper[0])*coeff[0];
		double x = 0;
		// x = (((-2 * b)) / (2 * a * c));
		return x;
	}
	ArrayList<String> tokenize(String str){
		
		// str = str.replaceAll(Pattern.quote("+ "), "+");
		String arr[] = str.split("\\s+");
		int op = 0;
		boolean first = true;
		ArrayList<String> lst= new ArrayList<String>();
		for(String element : arr){
			element = element.trim();
			if(isOperator(element)){
				oper[op++] = (isPositive(element) == 1 ? "+" : "-");
				// oper[op++] = element;
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
	public String toString(){
		String s = "";
		for(int i = 0 ; i < lst.size(); i++){
			if(coeff[i] != 0.0){
			if (isPositive(oper[i]) == -1 || i != 0)
				s += oper[i]+" ";
			s += String.format("%.1f * X^%d ", coeff[i],i);
			}
			// s += String.format("%.1f * X^2 ",oper[i], coeff[i]);
			// s += String.format("%.1f * X^2 ", coeff[2]);
		}
		return s;
	}
}