package rbmfae;

public class ValueStore extends RBMRCFAEValue {
	
	RBMRCFAEValue value ; 
	Store store ;
	
	public ValueStore() {
		
//		this.store = new MtSto(); 
	}
	
	public ValueStore(RBMRCFAEValue value, Store store) {
		this.value = value; 
		this.store = store ; 
	}
	
	public RBMRCFAEValue getValue() { 
		return value ;
	}  
	
	public Store getStore() { 
		return store; 
	}
	
	public String getASTCode() { 
		return "(v*s " + value.getASTCode() + " " + store.getASTCode() + ")"; 
	}
	
}
