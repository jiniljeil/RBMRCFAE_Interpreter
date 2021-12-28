package rcfae;

import rbmfae.RBMRCFAEValue;

public class numZero {

	public static boolean isNumZero(RBMRCFAEValue n) {
		
		String strNum = n.getASTCode(); 
		
		if (strNum.equals("(numV 0)")) {
			return true ;
		}
		return false; 
	}
}
