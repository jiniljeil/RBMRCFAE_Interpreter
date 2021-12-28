package rcfae;

import ast.AST;

public class Rec extends AST {
	String name = ""; 
	AST namedExpr = new AST(); 
	AST fstCall = new AST() ;
	
	public Rec(String name, AST namedExpr, AST fstCall) {
		this.name = name ;
		this.namedExpr = namedExpr; 
		this.fstCall = fstCall ; 
	}
	
	public String getName() {
		return this.name;
	}

	public AST getNameExpr() { 
		return this.namedExpr; 
	}
	
	public AST getFstCall() { 
		return this.fstCall ;
	}

	public String getASTCode() {
		return "(rec \'" + name + " " + namedExpr.getASTCode() + " " + fstCall.getASTCode() + ")";
	}
}