package cn.edu.buaa.sei.SVI.manage.impl.interpreter_access;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import cn.edu.buaa.sei.SVI.manage.StructInterpreterClassLinker;

public class ClassLinker implements StructInterpreterClassLinker{
	
	public ClassLinker(){}
	
	@SuppressWarnings("rawtypes")
	Map<Class,Class> linkMap = new HashMap<Class,Class>();

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isLinked(Class stype, Class itype) {
		if(stype==null||itype==null)return false;
		
		Queue<Class> queue = new LinkedList<Class>();
		queue.add(stype);
		
		while(!queue.isEmpty()){
			stype = queue.poll();
			if(this.linkMap.containsKey(stype))
				return this.linkMap.get(stype)==itype;
			
			Class ptype = stype.getSuperclass();
			if(ptype!=null)queue.add(ptype);
			
			Class[] iss = stype.getInterfaces();
			if(iss!=null)
				for(int i=0;i<iss.length;i++)
					if(iss[i]!=null)
						queue.add(iss[i]);
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void link(Class stype, Class itype) throws Exception {
		if(stype==null||itype==null)throw new Exception("Null types are invalid");
		
		if(this.linkMap.containsKey(stype)){
			if(itype!=this.linkMap.get(stype))
				throw new Exception("Try to overwrite the links <"
						+stype.getCanonicalName()+" -- "+this.linkMap.get(stype).getCanonicalName()+"> by: "
						+itype.getCanonicalName());
			return;
		}
		else{
			this.linkMap.put(stype, itype);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void Flink(Class stype, Class itype) throws Exception {
		if(stype==null||itype==null)throw new Exception("Null types are invalid");
		if(this.linkMap.containsKey(stype)){
			if(itype!=this.linkMap.get(stype))
				System.err.println("Overwrite link: <"
						+stype.getCanonicalName()+" -- "+this.linkMap.get(stype).getCanonicalName()+"> by: "
						+itype.getCanonicalName());
		}
		this.linkMap.put(stype, itype);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTarget(Class stype) throws Exception {
		if(stype==null)throw new Exception("Null stype is invalid");
		
		Queue<Class> queue = new LinkedList<Class>();
		queue.add(stype);
		while(!queue.isEmpty()){
			stype = queue.poll();
			if(stype==null)continue;
			
			if(this.linkMap.containsKey(stype))return this.linkMap.get(stype);
			
			queue.add(stype.getSuperclass());
			Class[] iss = stype.getInterfaces();
			if(iss!=null)
				for(int i=0;i<iss.length;i++)
					if(iss[i]!=null)
						queue.add(iss[i]);
		}
		
		throw new Exception(stype.getCanonicalName()+" has not been linked with any type");
	}

}
