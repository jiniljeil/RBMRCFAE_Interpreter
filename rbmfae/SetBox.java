package rbmfae;

import ast.AST;

public class SetBox extends AST {	
	
	AST var = new AST();  
	AST value = new AST() ; 
	
	public SetBox (AST var, AST value ) { 
		this.var = var ; 
		this.value = value ; 
	}
	
	
	public AST getVar() { 
		return var ; 
	}
	
	public AST getValue() { 
		return value ; 
	}
	
	public String getASTCode() {
		return "(setbox " + var.getASTCode() + " " + value.getASTCode() + ")" ; 
	}
}