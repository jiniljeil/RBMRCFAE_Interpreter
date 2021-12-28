package rcfae;

import lfae.Box;
import rbmfae.MtSto;
import rbmfae.RBMRCFAEValue;
import rbmfae.Store;

public class ARecSto extends Store {
	int address ; 
	Box box ; 
	Store store = new MtSto(); 
	
	public ARecSto(int address, Box box, Store store) {
		this.address = address; 
		this.box = box; 
		this.store = store; 
	}
	
	public int getAddress() { 
		return address; 
	}
	
	public RBMRCFAEValue getBox() { 
		return box; 
	}
	
	public Store getStore() { 
		return store; 
	}
	
	public String getASTCode() { 
		return "(aSto " + this.address + " " + this.box.getASTCode() + " " + store.getASTCode() + ")"; 
	}
}
