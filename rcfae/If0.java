package rcfae;

import ast.AST;

public class If0 extends AST {
	AST testExpr = new AST(); 
	AST trueCase = new AST(); 
	AST falseCase = new AST();
	
	public If0(AST testExpr, AST trueCase, AST falseCase) {
		this.testExpr = testExpr ;
		this.trueCase = trueCase ; 
		this.falseCase = falseCase ; 
	}
	
	public AST getTestExpr() {
		return this.testExpr ;
	}

	public AST getTrueCase() {
		return this.trueCase ;
	}
	
	public AST getFalseCase() { 
		return this.falseCase ; 
	}

	public String getASTCode() {
		return "(if0 " + testExpr.getASTCode() + " " + trueCase.getASTCode() + " " + falseCase.getASTCode() + ")"; 
	}
}


