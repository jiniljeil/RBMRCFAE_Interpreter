package ast;

import java.util.ArrayList;

import rcfae.ARecSub;

public class DefrdSub extends ClosureV{

	public String getASTCode() {
		if (this instanceof MtSub) {
			return ((MtSub)this).getASTCode() ; 
		}
		
		if (this instanceof ASub) { 
			return ((ASub)this).getASTCode(); 
		}
		
		if (this instanceof ARecSub) { 
			return ((ARecSub)this).getASTCode(); 
		}
		
		return param;
	}
}
