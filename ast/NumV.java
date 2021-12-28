package ast;

import rbmfae.RBMRCFAEValue;

public class NumV extends RBMRCFAEValue{
	String num = ""; 
	
	public NumV(String num) {
		this.num = num; 
	}
	
	public String getStrNum() { 
		return num;
	}
	
	public String getASTCode() { 
		return "(numV " + num +")"; 
	}
}
