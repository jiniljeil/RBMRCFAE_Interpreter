package plt;

import ast.AST;
import ast.DefrdSub;
import ast.MtSub;
import rbmfae.MtSto;
import rbmfae.RBMRCFAEValue;
import rbmfae.Store;

public class Main {
	
	static boolean onlyParser = false; // for -p option
	
	public static void main(String[] args) {
		
		// This is just an example code. Use args to get -p option and actuall code from CLI

		String exampleCode = null ; 
		
		if (args[0] != null && args[0].equals("-p")) {
			onlyParser = true; 
			exampleCode = args[1]; 
		} else {
			exampleCode = args[0] ; 
		}
		
		// Parser
		Parser parser = new Parser();
		AST ast = parser.parse(exampleCode);
		
		if(ast == null) {
			System.out.println("Syntax Error!");
			System.exit(1);
		}
		
		if(onlyParser)
			System.out.println(ast.getASTCode());
		else {
			// interpreter
			DefrdSub mtSub = new MtSub(); 
			Store mtSto = new MtSto(); 
			RBMRCFAEValue result = Interpreter.interp(ast, mtSub, mtSto);
			
			System.out.println(result.getASTCode());
		}
	}
}
