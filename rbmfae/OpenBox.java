package rbmfae;

import ast.AST;

public class OpenBox extends AST {	
	
	AST var = new AST();  
	
	public OpenBox (AST var) { 
		this.var = var; 
	}
	
	public AST getVar() { 
		return var ; 
	}
	
	public String getASTCode() {
		return "(openbox " + var.getASTCode() + ")" ; 
	}
}
