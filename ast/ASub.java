package ast;

import rbmfae.RBMRCFAEValue;

public class ASub extends DefrdSub {
	String name = ""; 
	RBMRCFAEValue value ;
	int address ; // RBMFAE ; 
	DefrdSub ds = new MtSub(); 
	
	public ASub(String name, RBMRCFAEValue value, DefrdSub ds) {
		this.name = name; 
		this.value = value; 
		this.ds = ds; 
	}
	
	public ASub(String name, int address, DefrdSub ds) { 
		this.name = name ; 
		this.address = address ; 
		this.ds = ds ;
	}
	
	public ASub(String name, RBMRCFAEValue value, int address, DefrdSub ds ) { 
		this.name = name ;
		this.value = value ; 
		this.address = address ; 
		this.ds = ds; 
	}
	
	public String getName() { 
		return name; 
	}
	
	public RBMRCFAEValue getValue() { 
		return value; 
	}
	
	public int getAddress() { 
		return address ; 
	}
	
	public DefrdSub getDs() { 
		return ds; 
	}
	
	public String getASTCode() {
		return "(aSub \'" + this.name + " " +  this.value.getASTCode() + " " + this.address + " " + ds.getASTCode() + ")"; 
	}
//	public String getASTCode() { 
//		return "(aSub \'" + this.name + " " + this.address + " " + ds.getASTCode() + ")"; 
//	}
}
