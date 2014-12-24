package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LFactory;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;

public class ObjectGroup implements IObjectGroup{
	
	LClass type;
	Set<LClassObject> uset = new HashSet<LClassObject>();
	Map<String,LClassObject> nspace = new HashMap<String,LClassObject>();
	Map<LClassObject,String> _index = new HashMap<LClassObject,String>();
	IObjectWorld container;
	
	public ObjectGroup(LClass type,IObjectWorld container) throws Exception{
		if(type==null||container==null)throw new Exception("Null type|container is invalid");
		this.type=type;
		this.container = container;
	}
	
	@Override
	public LClass getType() {return this.type;}

	@Override
	public LClassObject newOne() throws Exception {
		LFactory factory = this.type.getContainer().getFactory();
		LClassObject obj = factory.create(this.type);
		this.uset.add(obj);
		return obj;
	}
	@Override
	public void delete(LClassObject val) throws Exception {
		if(val==null)throw new Exception("Removing null object is invalid");
		if(!this.uset.contains(val))throw new Exception("Undefined object cannot be deleted");
		
		if(this._index.containsKey(val)){
			String id = this._index.get(val);
			this._index.remove(val);
			this.nspace.remove(id);
		}
		this.uset.remove(val);
	}
	@Override
	public boolean in(LClassObject val) {
		if(val==null)return false;
		else return this.uset.contains(val);
	}

	@Override
	public void tag(String id, LClassObject val) throws Exception {
		if(id==null||val==null)throw new Exception("Null id | val is invalid");
		if(this.nspace.containsKey(id)){
			if(val!=this.nspace.get(id))
				throw new Exception("Name Conflict: "+id);
		}
		if(this._index.containsKey(val)){
			String oid = this._index.get(val);
			if(!oid.equals(id)){
				this.untag(val);
			}
			else return;
		}
		
		if(!this.uset.contains(val))this.uset.add(val);
		this.nspace.put(id, val);
		this._index.put(val, id);
	}
	@Override
	public boolean isTagged(LClassObject val) {
		if(val==null)return false;
		else return this._index.containsKey(val);
	}
	@Override
	public String getTag(LClassObject val) throws Exception {
		if(val==null||!this._index.containsKey(val))
			throw new Exception("Undefined object in name space");
		
		return this._index.get(val);
	}
	@Override
	public String untag(LClassObject val) throws Exception {
		if(val==null||!this._index.containsKey(val))
			throw new Exception("Undefined object in name space");
		
		String id = this._index.get(val);
		this._index.remove(val);
		this.nspace.remove(id);
		return id;
	}

	@Override
	public void clearGroup() {
		this.nspace.clear();
		this._index.clear();
		this.uset.clear();
	}
	@Override
	public void clearNameSpace() {
		this.nspace.clear();
		this._index.clear();
	}

	@Override
	public boolean isRegistered(String id) {
		if(id==null)return false;
		else return this.nspace.containsKey(id);
	}
	@Override
	public LClassObject get(String id) throws Exception {
		if(id==null)throw new Exception("Null id is invalid");
		if(this.nspace.containsKey(id))return this.nspace.get(id);
		else throw new Exception("Undefined id: "+id);
	}
	@Override
	public Map<String, LClassObject> getNSpace() {return this.nspace;}
	@Override
	public Set<LClassObject> getObjects() {return this.uset;}

	@Override
	public IObjectWorld getContainer() {return this.container;}
}
