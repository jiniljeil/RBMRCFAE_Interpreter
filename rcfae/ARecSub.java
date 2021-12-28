package rcfae;

import ast.DefrdSub;
import ast.MtSub;
import lfae.Box;
import rbmfae.RBMRCFAEValue;

public class ARecSub extends DefrdSub {
	String name = ""; 
	Box valueBox ; // no use 
	int address ; 
	DefrdSub ds = new MtSub(); 
	
	public ARecSub(String name, Box value, DefrdSub ds) {
		this.name = name; 
		this.valueBox = value; 
		this.ds = ds; 
	}
	
	public ARecSub(String name, int address, DefrdSub ds) { 
		this.name = name ; 
		this.address = address; 
		this.ds = ds ; 
	}
	
	public String getName() { 
		return name; 
	}
	
	public Box getValueBox() { 
		return valueBox; 
	}
	
	public int getAddress() {  
		return address ; 
	}
	public DefrdSub getDs() { 
		return ds; 
	}
	
	public String getASTCode() { 
		
		return "(aRecSub \'" + this.name + " " + this.address + " " + ds.getASTCode() + ")"; 
	}
	/*
	public String getASTCode() { 
		
		return "(aRecSub \'" + this.name + " " + this.valueBox.getASTCode() + " " + ds.getASTCode() + ")"; 
	}
	*/
	
	/*
	String name = ""; 
	RBMRCFAEValue value ;
	Box box ; 
	int address ; // RBMFAE ; 
	DefrdSub ds = new MtSub(); 
	
	public ARecSub(String name, Box box, int address, DefrdSub ds) { 
		this.name = name ; 
		this.box = box ;  
		this.address = address ; 
		this.ds = ds ;
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
		return "(aRecSub \'" + this.name + " " + this.value.getASTCode() + " " + ds.getASTCode() + ")"; 
	}*/
}
