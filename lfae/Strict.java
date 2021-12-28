package lfae;

import ast.ASub;
import ast.ClosureV;
import plt.Interpreter;
import rbmfae.RBMRCFAEValue;
import rbmfae.RefClosV;
import rbmfae.Store;
import rbmfae.ValueStore;

public class Strict {
	
	public static RBMRCFAEValue strict(RBMRCFAEValue v, Store st) {
		

		if (v instanceof ClosureV)  {
				
			if ( ((ClosureV)v).getDefrdSub() instanceof ASub ) { 
				
				RBMRCFAEValue e = ((ASub) ((ClosureV) v).getDefrdSub()).getExprV() ; 
					
				if (e instanceof ExprV) {
						
					if (e instanceof Box) {
						// if not empty 
						if (((Box) e).emptyBox()) {
							RBMRCFAEValue value = strict(Interpreter.interp(((ExprV) e).getExpr(), ((ExprV) e).getDefrdSub(), st), st);
							Box box = new Box(value); 
							return box; 
						}else {
							return ((Box)e).unBox();
						}
					}
					return strict(Interpreter.interp(((ExprV) e).getExpr(), ((ExprV) e).getDefrdSub(), st), st); 
				}
			}		
		} else if (v instanceof RefClosV) { 
			if ( ((RefClosV)v).getDefrdSub() instanceof ASub ) { 
				
				RBMRCFAEValue e = ((ASub) ((RefClosV) v).getDefrdSub()).getExprV() ; 
					
				if (e instanceof ExprV) {
						
					if (e instanceof Box) {
						// if not empty 
						if (((Box) e).emptyBox()) {
							RBMRCFAEValue value = strict(Interpreter.interp(((ExprV) e).getExpr(), ((ExprV) e).getDefrdSub(), st), st);
							Box box = new Box(value); 
							return box; 
						}else {
							return ((Box)e).unBox();
						}
					}
					return strict(Interpreter.interp(((ExprV) e).getExpr(), ((ExprV) e).getDefrdSub(), st), st); 
				}
			}	
		}
		
		return v;
	}
}
