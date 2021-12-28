package lfae;

import ast.AST;
import ast.DefrdSub;
import rbmfae.RBMRCFAEValue;

public class ExprV extends RBMRCFAEValue{

	AST expr ; 
	DefrdSub ds ; 
	int address ; 
	Box box; 
	
	public ExprV (AST expr, DefrdSub ds, Box box) { 
		this.expr = expr; 
		this.ds = ds; 
		this.box = box ; 
	}
	
	public ExprV (int address, DefrdSub ds, Box box) { 
		this.address = address; 
		this.ds = ds; 
		this.box = box ; 
	}
	
	public AST getExpr() {
		return this.expr; 
	}
	
	public DefrdSub getDefrdSub() { 
		return this.ds ; 
	}
	
	public Box getBox() {
		return this.box ; 
	}
	
	public int getAddress() { 
		return this.address ; 
	}
	
	public String getASTCode() { 
		return "(exprV " + this.address + " " + this.ds.getASTCode() + ")"; 
	}
	
}
