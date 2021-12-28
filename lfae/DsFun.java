package lfae;

import ast.AST;

public class DsFun extends AST {
	String param = ""; 
	AST body = new AST();
	
	public DsFun(String param, AST body) {
		this.param = param;
		this.body = body;
	}
	
	public String getParam() {
		return this.param;
	}

	public AST getBody() {
		return this.body;
	}

	public String getASTCode() {
		return "(dsfun \'" + param + " " + body.getASTCode() + ")";
	}
}
