package cn.edu.buaa.sei.exLmf.metamodel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LFactory;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public class LPackageImpl extends LNamedElementImpl implements LPackage{
	String nsURI;
	String prefix;
	public LFactory factory;
	List<LPackage> supPackages = new ArrayList<LPackage>();
	List<LClassifier> types = new ArrayList<LClassifier>();
	Map<String,LPackage> package_index = new HashMap<String,LPackage>();
	Map<String,LClassifier> type_index = new HashMap<String,LClassifier>();
	Map<Integer,LClassifier> id_type = new HashMap<Integer,LClassifier>();
	
	public LPackageImpl(String name,String nsURI,String prefix) throws Exception{
		super(name);
		this.nsURI=nsURI;
		this.prefix=prefix;
		this.factory=new LFactoryImpl(this,true);
	}
	public LPackageImpl(String name,String nsURI,String prefix,Boolean isLight) throws Exception{
		super(name);
		this.nsURI=nsURI;
		this.prefix=prefix;
		this.factory=new LFactoryImpl(this,isLight);
	}

	/**
	 *	1. containSubPackage(p): return true if it is the direct child package or it is sub packages of one of the direct child.
	 *	2. addSubPackage():
	 *		- containSubPackage(p): return
	 *		- p.container!=this: release the link with original container package.
	 *	3. removeSubPackage(p): only remove the local sub packages (not trace to the leaf)
	 * 	4. getSubPackageByName(name): only return sub packages with the specified name
	 */
	@Override
	public boolean containSubPackage(LPackage p){
		if(p==null)return false;
		if(this.supPackages.contains(p))return true;
		
		for(int i=0;i<this.supPackages.size();i++)
			if(this.supPackages.get(i).containSubPackage(p))
				return true;
		
		return false;
	}
	@Override
	public List<LPackage> getSubPackages() {return this.supPackages;}
	@Override
	public void addSubPackage(LPackage pack) throws Exception {
		if(pack==null){
			throw this.getException("addSubPackage(pack)", "pack", "Null");
		}
		
		if(this.containSubPackage(pack))return;
		
		if(this.package_index.containsKey(pack.getName())){
			throw this.getException("addSubPackage(pack)", "pack", pack.getName()+" has been defined");
		}
		
		this.supPackages.add(pack);
		this.package_index.put(pack.getName(), pack);
		
		pack.setContainer(this);
	}
	@Override
	public void removeSubPackage(LPackage pack) throws Exception {
		if(!this.supPackages.contains(pack)){
			throw this.getException("removeSubPackage(pack)", "pack", "Undefined");
		}
		
		this.supPackages.remove(pack);
		this.package_index.remove(pack.getName());
	}
	@Override
	public LPackage getSubPackageByName(String name) throws Exception {
		if(this.package_index.containsKey(name))
			return this.package_index.get(name);
		else{
			throw this.getException("getSubPackageByName(name)", "name", name);
		}
	}

	@Override
	public String getNsURI() {return this.nsURI;}
	@Override
	public void setNsURI(String nsUri) {this.nsURI=nsUri;}

	@Override
	public String getNsPrefix(){return this.prefix;}
	@Override
	public void setNsPrefix(String prefix){this.prefix=prefix;}

	/**
	 *	1. getTypes(): return all local types (LClass|LEnum)
	 *	2. getClassifierByName/ID(): return local type by specified name/id
	 *		-ex: name|id is not defined in sub types.
	 *	3. containType(type): check whether local sub types contain the type
	 * 
	 */
	@Override
	public List<LClassifier> getTypes() {return this.types;}
	@Override
	public void addType(LClassifier type) throws Exception {
		if(type==null){
			throw this.getException("addType(type)", "type", "Null");
		}
		if(this.types.contains(type))return;
		if(this.type_index.containsKey(type.getName())){
			throw this.getException("addType(type)", "type", "Name \""+ type.getName()+ "\" has been defined");
		}
		if(this.id_type.containsKey(type.getClassifierID())){
			throw this.getException("addType(type)", "type", "ID: \""+type.getClassifierID()+ "\" has been defined");
		}
		LPackage ct = type.getContainer();
		if(ct!=null&&ct!=this){
			ct.removeType(type);
		}
		
		this.types.add(type);
		this.id_type.put(type.getClassifierID(), type);
		this.type_index.put(type.getName(), type);
		type.setContainer(this);
	}
	@Override
	public void removeType(LClassifier type) throws Exception {
		if(type==null||!this.types.contains(type)){
			throw this.getException("removeType(type)", "type", "Undefined");
		}
		
		this.types.remove(type);
		this.id_type.remove(type.getClassifierID());
		this.type_index.remove(type.getName());
		
		type.setContainer(null);
	}
	@Override
	public Boolean containType(LClassifier type) {
		if(type==null)return false;
		if(this.types.contains(type))return true;
		else{
			for(int i=0;i<this.supPackages.size();i++)
				if(this.supPackages.get(i).containType(type))
					return true;
			return false;
		}
	}
	@Override
	public LClassifier getClassifierByID(int id) throws Exception {
		if(this.id_type.containsKey(id))
			return this.id_type.get(id);
		else{
			throw this.getException("getClassifierByID(id)", "id", id+" has not been defined");
		}
	}
	@Override
	public LClassifier getClassifierByName(String name) throws Exception {
		if(name==null){
			throw this.getException("getClassifierByName(name)", "name","Null");
		}
		else if(this.type_index.containsKey(name))
			return this.type_index.get(name);
		else{
			throw this.getException("getClassifierByName(name)", "name", name+" has not been defined");
		}
	}

	@Override
	public LFactory getFactory() {return this.factory;}

	LPackage container;
	@Override
	public LPackage getContainer() {return this.container;}
	@Override
	public void setContainer(LPackage container) throws Exception {
		if(this.container==container)return;
		if(this.container!=null)
			this.container.removeSubPackage(this);
		this.container=container;
	}
	@Override
	public String getAbsolutePath() {
		LPackage cur = this;
		stack.clear();
		
		while(cur!=null){
			stack.push(cur);
			cur = cur.getContainer();
		}
		
		StringBuilder code = new StringBuilder();
		while(!stack.isEmpty()){
			cur = stack.pop();
			code.append(cur.getName());
			if(!stack.isEmpty())
				code.append(SPLIT);
		}
		return code.toString();
	}
}
