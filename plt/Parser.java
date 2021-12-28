package plt;

import java.util.ArrayList;

import ast.AST;
import ast.Add;
import ast.App;
import ast.Fun;
import ast.Id;
import ast.Num;
import ast.Sub;
import lfae.DsFun;
import rbmfae.NewBox;
import rbmfae.OpenBox;
import rbmfae.ReFun;
import rbmfae.Seqn;
import rbmfae.SetBox;
import rbmfae.SetVar;
import rcfae.If0;
import rcfae.Mul;
import rcfae.Rec;

public class Parser {

	AST parse(String exampleCode) {
		ArrayList<String> subExpressions = splitExpressionAsSubExpressions(exampleCode);
		
		//(? number?) 
		if(subExpressions.size() == 1 && isNumeric(subExpressions.get(0))) {
			
			return new Num(subExpressions.get(0));
		}
		// (list '+ l r) 
		if(subExpressions.get(0).equals("+")) {
			
			return new Add(parse(subExpressions.get(1)),parse(subExpressions.get(2)));
		}
		
		// (list '- l r) 
		if(subExpressions.get(0).equals("-")) {
			
			return new Sub(parse(subExpressions.get(1)), parse(subExpressions.get(2))); 
		}
		
		// (list '+ l r) 
		if(subExpressions.get(0).equals("*")) {
			
			return new Mul(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}
		// (? symbol?) 
		if(subExpressions.size() == 1) {
			return new Id(subExpressions.get(0)); 
		}
		
		if(subExpressions.size() == 2) { 
			
			return new App(parse(subExpressions.get(0)), parse(subExpressions.get(1))); 
		}
		
		// (list 'with (list i v) e)  
		if(subExpressions.get(0).equals("with")) {
			ArrayList<String> sub = splitExpressionAsSubExpressions(subExpressions.get(1)); 
			
			return new App(new Fun(sub.get(0), parse(subExpressions.get(2))), parse(sub.get(1))); 
		}
		
		// (list 'fun (list p) b) -> (fun p (parse b))
		if(subExpressions.get(0).equals("fun")) {
			ArrayList<String> sub = splitExpressionAsSubExpressions(subExpressions.get(1));
			
			return new Fun(sub.get(0), parse(subExpressions.get(2))); 
		}
		
		// (list 'refun (list p) b) -> (fun p (parse b))
		// call by reference 
		if(subExpressions.get(0).equals("refun")) { 
			ArrayList<String> sub = splitExpressionAsSubExpressions(subExpressions.get(1));
			
			return new ReFun(sub.get(0), parse(subExpressions.get(2))); 
		}
		
		if(subExpressions.get(0).equals("setvar")) {
			
			return new SetVar(subExpressions.get(1), parse(subExpressions.get(2)) ); 
		}

		if(subExpressions.get(0).equals("newbox")) {
			
			return new NewBox(parse(subExpressions.get(1))); 
		}
		
		if(subExpressions.get(0).equals("openbox")) {
			
			return new OpenBox(parse(subExpressions.get(1))); 
		}
		
		if(subExpressions.get(0).equals("setbox")) {
			
			return new SetBox(parse(subExpressions.get(1)), parse(subExpressions.get(2))); 
		}
		
		if(subExpressions.get(0).equals("seqn")) {
			
			return new Seqn(parse(subExpressions.get(1)), parse(subExpressions.get(2))); 
		}
		
		// (list 'dsfun (list p) b) 
		/*
		if(subExpressions.get(0).equals("dsfun")) {
			ArrayList<String> sub = splitExpressionAsSubExpressions(subExpressions.get(1));
			
			return new DsFun(sub.get(0), parse(subExpressions.get(2))); 
		}
		*/
		
		// (list 'if0 r t e) -> (if0 (parse r) (parse t) (parse e)) 
		if(subExpressions.get(0).equals("if0")) {
			
			return new If0(parse(subExpressions.get(1)), parse(subExpressions.get(2)), parse(subExpressions.get(3))); 
		}
		
		// (list 'rec (list n e) f) -> (rec n (parse e) (parse f)) 
		if(subExpressions.get(0).equals("rec")) { 
			ArrayList<String> sub = splitExpressionAsSubExpressions(subExpressions.get(1)) ;  
			
			return new Rec(sub.get(0), parse(sub.get(1)), parse(subExpressions.get(2)));
		}
		
		
		return null; // syntax error!
	}

	private ArrayList<String> splitExpressionAsSubExpressions(String exampleCode) {

		// deal with brackets first.
		if((exampleCode.startsWith("{") && !exampleCode.endsWith("}"))
				|| (!exampleCode.startsWith("{") && exampleCode.endsWith("}"))) {
			System.out.println("Syntax error");
			System.exit(0);
		}

		if(exampleCode.startsWith("{"))
			exampleCode = exampleCode.substring(1, exampleCode.length()-1);


		return getSubExpressions(exampleCode);
	}



	/**
	 * This method return a list of sub-expression from the given expression.
	 * For example, {+ 3 {+ 3 4}  -> +, 2, {+ 3 4}
	 * TODO JC was sleepy while implementing this method...it has complex logic and might be buggy...
	 * You can do better or find an external library.
	 * @param exampleCode
	 * @return list of sub expressions 
	 */
	private ArrayList<String> getSubExpressions(String exampleCode) {

		ArrayList<String> sexpressions = new ArrayList<String>();
		int openingParenthesisCount = 0;
		String strBuffer = "";
		
		// {{fun {f2} {+ f2 y}} 2}
		for(int i=0; i < exampleCode.length() ;i++) {
			if(i == 0 && (i == 0 && exampleCode.charAt(i) == '{')) {
				openingParenthesisCount++;
				strBuffer = strBuffer + exampleCode.charAt(i);
				continue;
			}else if(i == 0 && !(i == 0 && exampleCode.charAt(i) == '{')) {
				strBuffer = strBuffer + exampleCode.charAt(i);
				continue;
			}else if(exampleCode.charAt(i)==' ' && openingParenthesisCount==0){
				// buffer is ready to be a subexpression
				if(!strBuffer.isEmpty()) {
					sexpressions.add(strBuffer);
					strBuffer = ""; // Ready to start a new buffer
				} 
				continue;
			} else {
				if(exampleCode.charAt(i)=='{' && openingParenthesisCount==0){
					openingParenthesisCount++;
					// Ready to start a new buffer
					strBuffer = "" + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='{') {
					openingParenthesisCount++;
					strBuffer = strBuffer + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='}' && openingParenthesisCount>0) {
					openingParenthesisCount--;
					strBuffer = strBuffer + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='}') {
					// buffer is ready to be a subexpression
					sexpressions.add(strBuffer);
					continue;
				}
			}
			strBuffer = strBuffer + exampleCode.charAt(i);
		}
		
		sexpressions.add(strBuffer);

		return sexpressions;
	}

	public static boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

}
