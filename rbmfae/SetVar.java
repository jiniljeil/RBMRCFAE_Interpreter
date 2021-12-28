package rbmfae;

import ast.AST;

public class SetVar extends AST {
	String name = ""; 
	AST value = new AST();
	
	public SetVar(String name, AST value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}

	public AST getValue() {
		return this.value;
	}

	public String getASTCode() {
		return "(setvar \'" + name + " " + value.getASTCode() + ")";
	}
}
