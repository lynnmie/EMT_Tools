package cn.edu.buaa.sei.SVI.manage.impl.interpreter_access;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.manage.InterpreterClassLib;

public class SInterpreterLib implements InterpreterClassLib{
	
	public static InterpreterClassLib lib = new SInterpreterLib();
	
	public static InterpreterClassLib getLibrary(){return lib;}
	
	private SInterpreterLib(){}
	
	@SuppressWarnings("rawtypes")
	Set<Class> types = new HashSet<Class>();
	
	@SuppressWarnings("rawtypes")
	Map<Class,Interpreter> emap = new HashMap<Class,Interpreter>();

	@SuppressWarnings("rawtypes")
	@Override
	public synchronized Set<Class> getLoadedInterpreters() {return this.types;}

	@SuppressWarnings("rawtypes")
	@Override
	public synchronized boolean isLoaded(Class itype) {
		if(itype==null)return false;
		else return this.types.contains(itype);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public synchronized void load(Class itype) throws Exception {
		if(itype==null)throw new Exception("Null itype is invalid");
		if(this.types.contains(itype))return;
		
		Object obj = itype.newInstance();
		
		if(!(obj instanceof Interpreter))
			throw new Exception("itype should be type of Interpreter");
		
		this.types.add(itype);
		this.emap.put(itype, (Interpreter) obj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public synchronized Interpreter createInterpreter(Class itype) throws Exception {
		if(itype==null)throw new Exception("Null itype is invalid");
		if(!this.types.contains(itype))
			throw new Exception(itype.getCanonicalName()+" has not been loaded in the library");
		
		return this.emap.get(itype);
	}
}
