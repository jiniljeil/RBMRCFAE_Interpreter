package rbmfae;

import ast.AST;

public class ReFun extends AST {
	String param = ""; 
	AST body = new AST();
	
	public ReFun(String param, AST body) {
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
		return "(refun \'" + param + " " + body.getASTCode() + ")";
	}
}
