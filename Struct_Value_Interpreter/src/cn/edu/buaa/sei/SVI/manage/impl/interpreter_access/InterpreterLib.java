package cn.edu.buaa.sei.SVI.manage.impl.interpreter_access;

import java.util.HashSet;
import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.manage.InterpreterClassLib;

public class InterpreterLib implements InterpreterClassLib{
	
	public static InterpreterClassLib lib = new InterpreterLib();
	
	public static InterpreterClassLib getLibrary(){return lib;}
	
	private InterpreterLib(){}
	
	@SuppressWarnings("rawtypes")
	Set<Class> types = new HashSet<Class>();

	@SuppressWarnings("rawtypes")
	@Override
	public Set<Class> getLoadedInterpreters() {return this.types;}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isLoaded(Class itype) {
		if(itype==null)return false;
		else return this.types.contains(itype);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void load(Class itype) throws Exception {
		if(itype==null)throw new Exception("Null itype is invalid");
		if(this.types.contains(itype))return;
		
		Object obj = itype.newInstance();
		
		if(!(obj instanceof Interpreter))
			throw new Exception("itype should be type of Interpreter");
		
		this.types.add(itype);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Interpreter createInterpreter(Class itype) throws Exception {
		if(itype==null)throw new Exception("Null itype is invalid");
		if(!this.types.contains(itype))
			throw new Exception(itype.getCanonicalName()+" has not been loaded in the library");
		
		return (Interpreter) itype.newInstance();
	}

}
