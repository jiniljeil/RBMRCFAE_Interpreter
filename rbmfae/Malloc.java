package rbmfae;

public class Malloc {
	static int maxAddress ; 
	
	public Malloc() { 
		maxAddress = 0 ;
	}
	
	public static int malloc(Store sto) {
		maxAddress++; 
		return maxAddress; 
	}
}
