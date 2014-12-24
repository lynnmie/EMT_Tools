package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LNamedElement;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;

public class ObjectWorld implements IObjectWorld{
	
	LPackage model;
	Map<LClass,IObjectGroup> gmap = new HashMap<LClass,IObjectGroup>();

	Queue<LPackage> queue = new LinkedList<LPackage>();
	Set<LPackage> records = new HashSet<LPackage>();
	
	public ObjectWorld(LPackage p) throws Exception{
		this.loadModelSpace(p);
	}
	
	protected void loadModelSpace(LPackage p) throws Exception {
		if(p==null)throw new Exception("Null model is invalid");
		if(this.model==p)return;
		
		records.clear();queue.clear();
		
		this.clearModelSpace();
		this.model = p;
		queue.add(this.model);
		
		while(!queue.isEmpty()){
			p = queue.poll();
			if(records.contains(p))continue;
			records.add(p);
			
			List<LClassifier> types = p.getTypes();
			for(int i=0;i<types.size();i++){
				LClassifier type = types.get(i);
				if(!(type instanceof LClass))continue;
				
				if(this.gmap.containsKey(type))
					throw new Exception("Invalid Model Structure at: "+type.getAbsolutePath());
				
				IObjectGroup group = new ObjectGroup((LClass) type,this);
				this.gmap.put((LClass) type, group);
			}
			
			List<LPackage> sub_ps = p.getSubPackages();
			for(int i=0;i<sub_ps.size();i++)
				queue.add(sub_ps.get(i));
			
			System.out.println(this.gmap.size()+" types have been loaded...");
		}
	}
	protected void clearModelSpace(){
		Collection<IObjectGroup> groups = this.gmap.values();
		for(IObjectGroup group:groups)
			group.clearGroup();
		
		this.gmap.clear();
		this.model = null;
	}

	@Override
	public LPackage getModelSpace() {return this.model;}
	public void load(LPackage model)throws Exception{
		if(model==null)throw new Exception("Null model is invalid");
		this.clearModelSpace();
		this.loadModelSpace(model);
	}
	
	@Override
	public LClass getModelClass(String path) throws Exception {
		LNamedElement res = ModelAccessor.access(this.model, path);
		if(res==null||!(res instanceof LClass)){
			throw new Exception("Undefined LClass of Path: "+path);
		}
		return (LClass) res;
	}
	@Override
	public IObjectGroup getObjectGroup(String path) throws Exception {
		LClass type = this.getModelClass(path);
		if(this.gmap.containsKey(type))return this.gmap.get(type);
		else throw new Exception("Un-Registered type: "+type.getAbsolutePath());
	}
	@Override
	public IObjectGroup getObjectGroup(LClass type) throws Exception {
		if(type==null||!this.gmap.containsKey(type))
			throw new Exception("Undefined type: "+type);
		return this.gmap.get(type);
	}
	@Override
	public boolean containModelClass(LClass type) {
		if(type==null)return false;
		return this.gmap.containsKey(type);
	}
	@Override
	public boolean containModelClass(String path) {
		if(path==null)return false;
		try {
			LNamedElement res = ModelAccessor.access(this.model, path);
			if(res instanceof LClass)return this.gmap.containsKey(res);
			else return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public void clearObjectSpace() {
		Collection<IObjectGroup> groups = this.gmap.values();
		for(IObjectGroup group:groups)
			group.clearGroup();
	}

	@Override
	public Map<LClass, IObjectGroup> getGroups() {return this.gmap;}
	

}
