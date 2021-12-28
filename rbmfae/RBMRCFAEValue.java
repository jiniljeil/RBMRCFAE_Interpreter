package rbmfae;

import ast.AST;
import ast.ClosureV;
import ast.NumV;
import lfae.DynamicV;
import lfae.ExprV;

public class RBMRCFAEValue extends AST {
	NumV num ;
	ClosureV closureV ; 
	DynamicV dynamicV ; 
	ExprV exprV ; 
	
	// RBMFAE 
	RefClosV refClosV ;  
	BoxV boxV ; 
	public RBMRCFAEValue() { }
	
	public RBMRCFAEValue(NumV num, ClosureV closureV, ExprV exprV){
		this.num = num;
		this.closureV = closureV; 
		this.exprV = exprV; 
	}
	
	public RBMRCFAEValue(NumV num, DynamicV dynamicV, ExprV exprV) {
		this.num = num;
		this.dynamicV = dynamicV; 
		this.exprV = exprV; 
	}
	
	public RBMRCFAEValue(NumV num, ClosureV closureV, RefClosV refClosV, BoxV boxV) { 
		this.refClosV = refClosV ; 
		this.boxV = boxV; 
	}
	
	public void setNumV(NumV num) {
		this.num = num ; 
	}
	
	public NumV getNumV() {
		return num ; 
	}
	
	public void setClosureV(ClosureV closureV) {
		this.closureV = closureV; 
	}
	
	public ClosureV getClosureV() { 
		return this.closureV; 
	}
	
	public void setDynamicV(DynamicV dynamicV) {
		this.dynamicV = dynamicV; 
	}
	
	public DynamicV getDynamicV() { 
		return this.dynamicV; 
	}
	
	public void setExprV(ExprV exprV) { 
		this.exprV = exprV; 
	}
	
	public ExprV getExprV() {
		return this.exprV; 
	}
	
	public String getStrNum() {
		return num.getASTCode();
	}
	
	public void setRefClosV(RefClosV refClosV) { 
		this.refClosV = refClosV; 
	}
	
	public RefClosV getRefClosV() {  
		return refClosV ; 
	}
	
	public void setBoxV(BoxV boxV) { 
		this.boxV = boxV; 
	}
	
	public BoxV getBoxV() { 
		return boxV ; 
	}
}
