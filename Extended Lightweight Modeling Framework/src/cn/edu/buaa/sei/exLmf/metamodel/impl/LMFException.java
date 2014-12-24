package cn.edu.buaa.sei.exLmf.metamodel.impl;

public class LMFException extends Exception{

	/**
	 * New Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int MAX_REASON_NUM = 32;
	String actor;
	String type;
	String func;
	String location;
	String[] reasons = new String[MAX_REASON_NUM];
	int cur = 0;
	
	/*
	 *	Common Reasons 
	 */
	public static final String NULL = "Null";
	
	public static LMFException create(String actor,String type,String func,String location,String reason){
		if(actor==null||type==null||location==null||reason==null||func==null){
			try {
				throw new Exception("LMFException creating failed: null arguments");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new LMFException(actor,type,func,location,reason);
	}
	
	private LMFException(String actor,String type,String func,String location,String reason){
		this.type=type;this.func=func;this.location=location;
		this.pushReason(reason);
	}	
	Exception getArgException(String args,String func,String reason){
		StringBuilder code = new StringBuilder();
		code.append("Exception generated failed.");
		//code.append("\nType: Argument Errors: ");
		code.append("\nArgument <"+args).append(">");
		code.append(" in function <").append(func).append(">");
		code.append("\nReason: ").append(reason);
		return new Exception(code.toString());
	}
	
	public void pushReason(String reason){
		if(reason==null){
			try {
				throw this.getArgException("reason", "pushReason(reason)", "Null");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if(cur>=MAX_REASON_NUM){
			try {
				throw this.getArgException("this.cur", "pushReason(reason)", "Full Reason List");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		this.reasons[cur++]=reason;
	}
	public String popReason(){
		if(cur<0){
			try {
				throw this.getArgException("no arguments", "popReason()", "Empty List of Reasons");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return this.reasons[--cur];
	}
	
	public String getMessage(){
		StringBuilder code = new StringBuilder();
		
		code.append("Verifier <").append(actor).append("> report exception: ");
		code.append("\n\t").append("In ").append(type).append("::").append(func).
		append("::(").append(this.location).append(")");
		code.append("\n\t").append("Reaons: [");
		for(int i=0;i<cur;i++)
			code.append("\n\t").append(reasons[i]);
		code.append("\n\t]");
		
		return code.toString();
	}
	public String getLocalizedMessage(){
		StringBuilder code = new StringBuilder();
		
		code.append("Verifier <").append(actor).append("> report exception: ");
		code.append("\n\t").append("In <class>::").append(func).
		append("::(").append(this.location).append(")");
		code.append("\n\t").append("Reaons: [");
		for(int i=0;i<cur;i++)
			code.append("\n\t").append(reasons[i]);
		code.append("\n\t]");
		
		return code.toString();
	}
	

}
