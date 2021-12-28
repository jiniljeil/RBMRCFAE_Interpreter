package rbmfae;

public class BoxV extends RBMRCFAEValue {
	int address ;
	
	public BoxV (int address) { 
		this.address = address; 
	}
	
	public void setAddress(int  address) { 
		this.address = address ; 
	}
	
	public int getAddress() {
		return address; 
	}
	
	public String getASTCode() {
		
		return "(boxV " + address + ")"; 
	}
}
