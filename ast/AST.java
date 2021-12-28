package ast;

import rcfae.If0;

public class AST {
	
	public String getASTCode() {
		String astCode="";
		if(this instanceof Add)
			astCode = ((Add)this).getASTCode();
		
		if(this instanceof Sub) 
			astCode = ((Sub)this).getASTCode(); 
		
		if(this instanceof Num)
			astCode = ((Num)this).getASTCode();
		
		if(this instanceof NumV) 
			astCode = ((NumV)this).getASTCode();
		
		if(this instanceof Id)
			astCode = ((Id)this).getASTCode();
		
		if(this instanceof Fun)
			astCode = ((Fun)this).getASTCode(); 
		
		if(this instanceof App)
			astCode = ((App)this).getASTCode();
		
		if(this instanceof DefrdSub)
			astCode = ((DefrdSub)this).getASTCode();
		
		if(this instanceof If0) 
			astCode = ((If0)this).getASTCode();
		return astCode;
	}
}

