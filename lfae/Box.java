package lfae;

import rbmfae.RBMRCFAEValue;

public class Box extends RBMRCFAEValue{
	RBMRCFAEValue value = null; 
	
	public Box(RBMRCFAEValue value) { 
		this.value = value ; 
	}
	
	public boolean emptyBox() {
		if (value == null) {
			return true; 
		}else {
			return false; 
		}
	}
	
	public void setBox(RBMRCFAEValue value) {
		this.value = value ; 
	}
	
	public RBMRCFAEValue unBox() { 
		return value; 
	}
}
