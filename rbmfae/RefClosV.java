package rbmfae;

import ast.AST;
import ast.DefrdSub;

public class RefClosV extends RBMRCFAEValue {
	String param ; 
	AST body ;
	DefrdSub ds ; 
	
	public RefClosV() { 
		this.param = ""; 
		this.body = new AST() ; 
	}

	public RefClosV(String param, AST body, DefrdSub defrdSub) {
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
		return "(refclosV " + this.param + " " + this.body.getASTCode() + " " + ds.getASTCode() + ")";
	}
}
