package cn.edu.buaa.sei.SVI.manage.impl.interpreter_access;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.StructClassLib;

public class StructLib implements StructClassLib{
	
	public static StructClassLib struct_lib = new StructLib();
	
	public static StructClassLib getStructLibrary(){return struct_lib;}
	
	private StructLib(){}
	
	@SuppressWarnings("rawtypes")
	Set<Class> types = new HashSet<Class>();
	@SuppressWarnings("rawtypes")
	Queue<Class> queue = new LinkedList<Class>();

	@SuppressWarnings("rawtypes")
	@Override
	public Set<Class> getLoadedStructClasses() {return this.types;}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isLoaded(Class stype) {
		if(stype==null)return false;
		
		this.queue.clear();this.queue.add(stype);
		
		while(!this.queue.isEmpty()){
			stype = this.queue.poll();
			if(this.types.contains(stype))return true;
			
			if(stype.getSuperclass()!=null)
				this.queue.add(stype.getSuperclass());
			Class[] iss = stype.getInterfaces();
			if(iss!=null)
				for(int i=0;i<iss.length;i++)
					if(iss[i]!=null)
						this.queue.add(iss[i]);
		}
		
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void load(Class stype) throws Exception {
		if(stype==null)throw new Exception("Null type is invalid");
		this.types.add(stype);
	}

}
