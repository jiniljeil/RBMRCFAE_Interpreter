package plt;

import java.util.ArrayList;

import ast.AST;
import ast.ASub;
import ast.Add;
import ast.App;
import ast.ClosureV;
import ast.DefrdSub;
import ast.Fun;
import ast.Id;
import ast.MtSub;
import ast.Num;
import ast.NumOperator;
import ast.NumV;
import ast.Sub;
import lfae.Box;
import lfae.DsFun;
import lfae.DynamicV;
import lfae.ExprV;
import lfae.Strict;
import rbmfae.ASto;
import rbmfae.BoxV;
import rbmfae.Malloc;
import rbmfae.MtSto;
import rbmfae.NewBox;
import rbmfae.OpenBox;
import rbmfae.RBMRCFAEValue;
import rbmfae.ReFun;
import rbmfae.RefClosV;
import rbmfae.Seqn;
import rbmfae.SetBox;
import rbmfae.SetVar;
import rbmfae.Store;
import rbmfae.ValueStore;
import rcfae.ARecSto;
import rcfae.ARecSub;
import rcfae.If0;
import rcfae.Mul;
import rcfae.Rec;
import rcfae.numZero;

public class Interpreter {

	/* (Before) Code
	public static RBMRCFAEValue lookup(String name, DefrdSub ds) { 
		if (ds instanceof MtSub) {
			System.out.println("lookup \"free identifier\""); 
			System.exit(1); 
		}
		
		if (ds instanceof ASub) {
			if ( name.equals(((ASub) ds).getName()) ) {
				return ((ASub) ds).getValue(); 
			}else {
				if ( ((ASub) ds).getDs() != null ) {
					return lookupValue(name, ((ASub) ds).getDs());
				}	
			}
		}
		
		if (ds instanceof ARecSub) {
			if ( name.equals(((ARecSub) ds).getName()) ) {
				return ((ARecSub) ds).getValueBox().unBox();  
			} else {
				if ( ((ARecSub) ds).getDs() != null) {
					return lookupValue(name, ((ARecSub) ds).getDs()) ; 
				}
			}
		}
		
		return ds ;
	}
	*/
	
	public static int lookup(String name, DefrdSub ds) {
		if (ds instanceof MtSub) {
			System.out.println("lookup \"free identifier\""); 
			System.exit(1); 
		}
		
		if (ds instanceof ASub) { 
			if (name.equals(((ASub) ds).getName())) { 
				return ((ASub) ds).getAddress() ; 
			} else { 
				if ( ((ASub) ds).getDs() != null) {
					return lookup(name, ((ASub) ds).getDs()) ; 
				}
			}
		}
		
		if (ds instanceof ARecSub) {
			if ( name.equals(((ARecSub) ds).getName()) ) {
				return ((ARecSub) ds).getAddress(); 
			} else {
				if ( ((ARecSub) ds).getDs() != null) {
					return lookup(name, ((ARecSub) ds).getDs()) ; 
				}
			}
		}
		return -1; 
	}
	
	public static RBMRCFAEValue storeLookup(int address, Store sto) { 
		
		if (sto instanceof MtSto) { 
			System.out.println("store-lookup \"No value at address\""); 
			System.exit(1);
		}
		
		if (sto instanceof ASto) {
			if ( address == ((ASto) sto).getAddress()) { 
				return ((ASto) sto).getValue();
			} else {
				return storeLookup(address, ((ASto) sto).getStore()); 
			}
		}
		
		if (sto instanceof ARecSto) {
			if ( address == ((ARecSto) sto).getAddress()) { 
				return ((Box) ((ARecSto) sto).getBox()).unBox() ; 
			} else { 
				return storeLookup(address, ((ARecSto) sto).getStore()); 
			}
		}
		
		return ((ASto) sto).getValue() ;
	}
	
	
	public static RBMRCFAEValue interp(AST ast, DefrdSub ds, Store st) {
		
		if(ast instanceof Num) {
			
			Num num = (Num)ast ;
			NumV numV = new NumV(num.getStrNum());
			
			ValueStore vs = new ValueStore(numV, st); 
			
			return vs; 
		}
		
		if(ast instanceof Id) {
			Id id = (Id)ast; 
			ValueStore vs = new ValueStore(storeLookup(lookup(id.getStrId(), ds), st), st); 
			
			return vs;  
		}
		
		// RCFAE 
		if(ast instanceof If0) {
			If0 if0 = (If0)ast ; 
							
			RBMRCFAEValue v = interp(if0.getTestExpr(), ds, st) ;

			if ( v instanceof ValueStore ) {
				if ( ((ValueStore) v).getValue() instanceof NumV && numZero.isNumZero(((NumV) ((ValueStore) v).getValue()))) {
						
					return interp(if0.getTrueCase(), ds, ((ValueStore) v).getStore()) ;
				} else {
				
					return interp(if0.getFalseCase(), ds, ((ValueStore) v).getStore()) ; 
				}
			}
		}
		
		if(ast instanceof Add) {
			Add add = (Add)ast;
			
			RBMRCFAEValue left = interp(add.getLhs(), ds, st) ; 
			if ( left instanceof ValueStore) {
				RBMRCFAEValue right = interp(add.getRhs(), ds, ((ValueStore) left).getStore()); 
				if ( right instanceof ValueStore) { 
					RBMRCFAEValue num = NumOperator.numPlus(((ValueStore) left).getValue(), ((ValueStore) right).getValue(), st); 
					ValueStore vs = new ValueStore(num, ((ValueStore) right).getStore()); 
					
					return vs ; 
				}
			}
		}
		
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast ; 
			
			RBMRCFAEValue left = interp(sub.getLhs(), ds, st) ; 
			if ( left instanceof ValueStore) {
				RBMRCFAEValue right = interp(sub.getRhs(), ds, ((ValueStore) left).getStore()); 
				if ( right instanceof ValueStore) { 
					RBMRCFAEValue num = NumOperator.numMinus(((ValueStore) left).getValue(), ((ValueStore) right).getValue(), st); 
					ValueStore vs = new ValueStore(num, ((ValueStore) right).getStore()); 
					
					return vs ; 
				}
			}
		}
		
		if(ast instanceof Mul) {
			Mul mul = (Mul)ast ; 
			
			RBMRCFAEValue left = interp(mul.getLhs(), ds, st) ; 
			if ( left instanceof ValueStore) {
				RBMRCFAEValue right = interp(mul.getRhs(), ds, ((ValueStore) left).getStore()); 
				if ( right instanceof ValueStore) { 
					RBMRCFAEValue num = NumOperator.numMulti(((ValueStore) left).getValue(), ((ValueStore) right).getValue(), st); 
					ValueStore vs = new ValueStore(num, ((ValueStore) right).getStore()); 
					
					return vs ; 
				}
			}
		}
		
		if(ast instanceof Fun) { 
			Fun fun = (Fun)ast; 
			ClosureV closureV = new ClosureV(fun.getParam(), fun.getBody(), ds); 
			ValueStore vs = new ValueStore(closureV, st); 
			return vs; 
		}
		
		if(ast instanceof ReFun) {
			ReFun reFun = (ReFun) ast ;
			RefClosV refClosV = new RefClosV(reFun.getParam(), reFun.getBody(), ds) ;
			ValueStore vs = new ValueStore(refClosV, st); 
			return vs ;
		}
		
		
		// This version does not support dynamic Scope. 
		/*
		if(ast instanceof DsFun) {
			DsFun dsFun = (DsFun)ast; 
			DynamicV dynamicV = new DynamicV(dsFun.getParam(), dsFun.getBody()); 
			
			return dynamicV; 
		}
		*/
		
		/* (Before) Code
		if(ast instanceof App) {
			App app = (App)ast ; 
			
			RBMRCFAEValue arg_value = new ExprV(app.getArg(),ds, null); 
			
			if ( Strict.strict(interp(app.getFun(), ds)) instanceof DynamicV) {
				DynamicV ftn_value = (DynamicV) Strict.strict(interp(app.getFun(), ds));  
				return interp(ftn_value.getBody(), new ASub(ftn_value.getParam(), arg_value, ds)); 
			} 
			
			ClosureV ftn_value = (ClosureV) Strict.strict(interp(app.getFun(), ds));  
				
			return interp(
				ftn_value.getBody(), 
				new ASub(ftn_value.getParam(), 
				interp(app.getArg(), ds),
//				arg_value, 
				ftn_value.getDefrdSub())); 		
		}
		*/ 
		
		if(ast instanceof App) {
			App app = (App) ast ;
			
			RBMRCFAEValue f = interp(app.getFun(), ds, st); 
			
			if ( f instanceof ValueStore ) {
				
				RBMRCFAEValue v = Strict.strict(((ValueStore) f).getValue(), st) ;
				
				if ( v instanceof ClosureV ) {
					
					RBMRCFAEValue a = interp(app.getArg(), ds, ((ValueStore) f).getStore()); 
					
					if ( a instanceof ValueStore )  {
						
						int newAddress = Malloc.malloc(((ValueStore) a).getStore()); 
						
						RBMRCFAEValue argVal = new ExprV(app.getArg(), ds, null); 
						
						return interp(((ClosureV) v).getBody(), 
										new ASub(((ClosureV) v).getParam(), argVal, newAddress, ((ClosureV) v).getDefrdSub()), 
										new ASto(newAddress, ((ValueStore) a).getValue(), ((ValueStore) a).getStore()));
					}
				} else if ( v instanceof RefClosV ) {
					int address = lookup(((Id) app.getArg()).getStrId(), ds);
					
					RBMRCFAEValue argVal = new ExprV(app.getArg(), ds, null);
					
					return interp(((RefClosV) v).getBody(), 
									new ASub(((RefClosV) v).getParam(), argVal, address,((RefClosV) v).getDefrdSub()),
									((ValueStore) f).getStore());
				} else { 
					
					System.out.println("Error: interp trying to apply a number"); 
					System.exit(1);
				}
			}
		}
		
		// RBMFAE 
		if(ast instanceof NewBox) { 
			NewBox newbox = (NewBox) ast; 
			
			RBMRCFAEValue v = interp(newbox.getVar(), ds, st); 
			
			if ( v instanceof ValueStore) { 
				int a = Malloc.malloc(((ValueStore) v).getStore()) ; 
				
				ValueStore vs = new ValueStore(new BoxV(a), 
												new ASto(a, ((ValueStore) v).getValue(), ((ValueStore) v).getStore())); 
				return vs ; 
			}
		}
		
		if(ast instanceof OpenBox) { 
			OpenBox openbox = (OpenBox) ast;  
			
			RBMRCFAEValue v = interp(openbox.getVar(), ds, st); 
			
			if ( v instanceof ValueStore ) { 
				
				ValueStore vs = new ValueStore(storeLookup(((ValueStore) v).getValue().getBoxV().getAddress(), 
															((ValueStore) v).getStore()),
												((ValueStore) v).getStore());
				
				return vs; 
			}
		}
		
		if(ast instanceof SetBox) {
			SetBox setbox = (SetBox) ast ; 
			
			RBMRCFAEValue v = interp(setbox.getVar(), ds, st); 
			
			if (v instanceof ValueStore ) { 
				
				RBMRCFAEValue v2 = interp(setbox.getValue(), ds, ((ValueStore) v).getStore()); 
				
				if (v2 instanceof ValueStore ) { 
					
					ValueStore vs = new ValueStore(((ValueStore) v2).getValue(), 
													new ASto(((ValueStore) v).getValue().getBoxV().getAddress(), 
																((ValueStore) v2).getValue(),
																((ValueStore) v2).getStore())); 
					
					return vs; 
				}
			}
		}
		
		if(ast instanceof Seqn) { 
			Seqn seqn = (Seqn) ast; 
			
			RBMRCFAEValue v = interp(seqn.getFirst(), ds, st); 
			
			if (v instanceof ValueStore) { 
				
				return interp(seqn.getSecond(), ds, ((ValueStore) v).getStore()); 
			}
		}
		
		if(ast instanceof SetVar) {
			SetVar setVar = (SetVar) ast ; 
			
			int a = lookup(setVar.getName(), ds) ; 
			
			RBMRCFAEValue v = interp(setVar.getValue(), ds, st); 
			
			if (v instanceof ValueStore) { 
				
				ValueStore vs = new ValueStore(((ValueStore) v).getValue(), 
											new ASto(a, ((ValueStore) v).getValue(), st)); 
				
				return vs; 
			}
			
		}
		
		
		// Only RCFAE 
		/*
		if(ast instanceof Rec) {
			Rec rec = (Rec)ast ; 
			// local 
			Box valueHolder = new Box(new NumV("197")); 
			ARecSub newDs = new ARecSub(rec.getName(), valueHolder, ds); 
			
			// begin: The exprs are evaluated in order, and the result of all but the last expr is ignored. 
			valueHolder.setBox( interp(rec.getNameExpr(), newDs, st) );
			return interp(rec.getFstCall(), newDs, st); 
		}
		*/
		
		if(ast instanceof Rec) {
			Rec rec = (Rec) ast; 
			
			RBMRCFAEValue v = new ValueStore(new Box(new NumV("197")), st); 
			
			if ( v instanceof ValueStore) { 
				
				int address = Malloc.malloc(((ValueStore) v).getStore()); 
				
				ARecSub newDs = new ARecSub(rec.getName(), address, ds); 
				
				RBMRCFAEValue v2 = interp(rec.getNameExpr(), newDs, st); 
				
				if ( v2 instanceof ValueStore ) { 
					// begin: The exprs are evaluated in order, and the result of all but the last expr is ignored. 
					((Box) ((ValueStore) v).getValue()).setBox(((ValueStore) v2).getValue()); 
					
					return interp(rec.getFstCall(), newDs, // ((ValueStore) v).getStore()); 
							 new ARecSto(address, ((Box) ((ValueStore) v).getValue()), ((ValueStore) v).getStore())) ;
				}
			}
		}
		
		return null;
	}
}
