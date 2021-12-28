package lfae;

import ast.AST;
import rbmfae.RBMRCFAEValue;

public class DynamicV extends RBMRCFAEValue{
	String param ;
	AST body ; 
	
	public DynamicV() {
		this.param = ""; 
		this.body = new AST() ;
	}
	
	public DynamicV(String param, AST body) {
		this.param = param ; 
		this.body = body;  
	}
	
	public String getParam() { 
		return this.param ;
	}
	
	public AST getBody() { 
		return this.body; 
	}
	
	public String getASTCode() {
		return "(dynamicV " + this.param + " " + this.body.getASTCode() + ")"; 
	}
}
