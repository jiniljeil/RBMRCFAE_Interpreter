package rbmfae;

public class Store extends ValueStore {

	public String getASTCode() {
		if (this instanceof MtSto) {
			return ((MtSto)this).getASTCode() ; 
		}
		
		if (this instanceof ASto) { 
			return ((ASto)this).getASTCode(); 
		}
		
		return null; 
	}
}
