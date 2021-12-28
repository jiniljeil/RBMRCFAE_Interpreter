package ast;

public class Id extends AST{
	String id = "";
	
	public Id(String id){
		this.id = id;
	}
	
	public String getStrId() {
		return id;
	}
	
	public String getASTCode() {
		return "(id \'" + this.id +")";
	}
}
