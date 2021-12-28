package rbmfae;

import ast.AST;

public class NewBox extends AST {
	AST var = new AST(); 
	
	public NewBox(AST var) { 
		this.var = var ;
	}
	
	public AST getVar() { 
		return var ;
	}
	
	public String getASTCode() {
		return "(newbox " + var.getASTCode() + ")" ; 
	}
}
