package ast;

import java.util.ArrayList;

import rbmfae.RBMRCFAEValue;

public class ClosureV extends RBMRCFAEValue {
	String param ; 
	AST body ; 		
	DefrdSub ds ; 

	public ClosureV() { 
//		this.param = ""; 
//		this.body = new AST() ; 
	}

	public ClosureV(String param, AST body, DefrdSub defrdSub) {
		this.param = param ; 
		this.body = body ; 
		this.ds = defrdSub ; 
	}
		
	public String getParam() { 
		return this.param ; 
	}
	
	public AST getBody() {
		return this.body ; 
	}
		
	public DefrdSub getDefrdSub() { 
		return ds; 
	}
	
	public String getASTCode() {
		return "(closureV " + this.param + " " + this.body.getASTCode() + " " + ds.getASTCode() + ")";
	}
}
