package rbmfae;

import ast.MtSub;

public class ASto extends Store {
	int address ; 
	RBMRCFAEValue value ; 
	Store store = new MtSto(); 
	
	public ASto(int address, RBMRCFAEValue value, Store store) {
		this.address = address; 
		this.value = value; 
		this.store = store; 
	}
	
	public int getAddress() { 
		return address; 
	}
	
	public RBMRCFAEValue getValue() { 
		return value; 
	}
	
	public Store getStore() { 
		return store; 
	}
	
	public String getASTCode() { 
		return "(aSto " + this.address + " " + this.value.getASTCode() + " " + store.getASTCode() + ")"; 
	}
}

