package ast;

import lfae.Strict;
import rbmfae.RBMRCFAEValue;
import rbmfae.Store;

public class NumOperator {
	
	public static RBMRCFAEValue numPlus(RBMRCFAEValue n1, RBMRCFAEValue n2, Store st) {
		if (n1 == null) {
			System.out.println("First value is incorrect!"); 
		}
		
		if (n2 == null) {
			System.out.println("Second value is incorrect!"); 
		}

		NumV v = new NumV("" + (Integer.parseInt(Strict.strict(n1, st).getStrNum()) 
									+ Integer.parseInt(Strict.strict(n2, st).getStrNum()))); 

		
		return v; 
	}
	
	public static RBMRCFAEValue numMinus(RBMRCFAEValue n1, RBMRCFAEValue n2, Store st) { 
		
		if (n1 == null) {
			System.out.println("First value is incorrect!"); 
		}
		
		if (n2 == null) {
			System.out.println("Second value is incorrect!"); 
		}
		
		NumV v = new NumV("" + (Integer.parseInt(Strict.strict(n1, st).getStrNum()) 
								- Integer.parseInt(Strict.strict(n2, st).getStrNum()))); 
		
		return v; 
	}
	
	public static RBMRCFAEValue numMulti(RBMRCFAEValue n1, RBMRCFAEValue n2, Store st) {
		if (n1 == null) {
			System.out.println("First value is incorrect!"); 
		}
		
		if (n2 == null) {
			System.out.println("Second value is incorrect!"); 
		}
		
		NumV v = new NumV("" + (Integer.parseInt(Strict.strict(n1, st).getStrNum())
									* Integer.parseInt(Strict.strict(n2, st).getStrNum())));
		
		return v ; 
	}
}
