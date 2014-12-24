package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.buaa.sei.exLmf.manager.ILModelSearcher;
import cn.edu.buaa.sei.exLmf.manager.ISearcherRunner;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;

public class SearcherRunner implements ISearcherRunner{
	public static final String PACKAGE = "package_searcher";
	public static final String CLASS = "class_searcher";
	public static final String ENUM = "enum_searcher";
	public static final String ATTRIBUTE = "attribute_searcher";
	public static final String REFERENCE = "reference_searcher";
	public static final String LITERAL = "literal_searcher";
	public static final String DATAOBJECT = "dataObject_searcher";
	public static final String MULTIPLEOBJECT = "multipleObject_searcher";
	public static final String CLASSOBJECT = "classObject_searcher";
 	
	String name;
	LModelElement element;
	Map<String,ILModelSearcher> install_map = new HashMap<String,ILModelSearcher>();
	
	List<String> ins = new ArrayList<String>();
	int cur = 0;
	public static final int MAX_TASK = 1024;

	public SearcherRunner(String name){
		this.name=name;
		this.initial();
	}
	/*	Tool Functions   */
	Exception getException(String func,String arg,String reason){
		return LMFException.create("Searcher Runner "+this.name, "LMFCreator", func, arg, reason);
	}
	void initial(){
		this.install_map.put(PACKAGE, new LPackageSearcher());
		this.install_map.put(CLASS, new LClassSearcher());
		this.install_map.put(ENUM, new LEnumSearcher());
		this.install_map.put(ATTRIBUTE, new LAttributeSearcher());
		this.install_map.put(REFERENCE, new LReferenceSearcher());
		this.install_map.put(LITERAL, new LEnumLiteralSearcher());
		this.install_map.put(DATAOBJECT, new LDataObjectSearcher());
		this.install_map.put(MULTIPLEOBJECT, new LMultipleObjectSearcher());
		this.install_map.put(CLASSOBJECT, new LClassObjectSearcher());
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Object getResult(Object parant,String next) throws Exception{
		if(parant==null||next==null)
			throw this.getException("getResult(parant,next)", "parant|next", "Null");
		
		ILModelSearcher searcher = null;
		if(parant instanceof LPackage)searcher = this.install_map.get(PACKAGE);
		if(parant instanceof LClass)searcher = this.install_map.get(CLASS);
		if(parant instanceof LEnum)searcher = this.install_map.get(ENUM);
		if(parant instanceof LAttribute)searcher = this.install_map.get(ATTRIBUTE);
		if(parant instanceof LReference)searcher = this.install_map.get(REFERENCE);
		if(parant instanceof LEnumLiteral)searcher = this.install_map.get(LITERAL);
		if(parant instanceof LDataObject)searcher = this.install_map.get(DATAOBJECT);
		if(parant instanceof LMultipleObject)searcher = this.install_map.get(MULTIPLEOBJECT);
		if(parant instanceof LClassObject)searcher = this.install_map.get(CLASSOBJECT);
		
		if(parant instanceof List){
			List list = (List) parant;
			List<Object> results = new ArrayList<Object>();
			for(int i=0;i<list.size();i++){
				if(list.get(i) instanceof LModelElement){
					Object result = this.getResult(list.get(i), next);
					if(result instanceof List)
						results.addAll((List) result);
					else
						results.add(result);
				}
			}
			return results;
		}
		
		if(searcher==null||!(parant instanceof LModelElement))
			throw this.getException("getResult(parant,next)", "parant", 
					"Undefined class parant: \""+parant.getClass().getName()+"\"");
		
		searcher.setElement((LModelElement) parant);
		return searcher.next(next);
	}
	
	
	@Override
	public void install(String name, ILModelSearcher searcher) {
		if(this.install_map.containsKey(name)){
			System.err.println("Installed Searcher \""+name+"\" is coverred.");
		}
		this.install_map.put(name, searcher);
	}
	@Override
	public void uninstall(String name) {
		if(!this.install_map.containsKey(name)){
			System.err.println("Searcher \""+name+"\" has not been installed");
			return;
		}
		this.install_map.remove(name);
	}
	@Override
	public Boolean isInstalled(String name) {return this.install_map.containsKey(name);}

	@Override
	public void setMainObject(LModelElement element) {this.element=element;}
	@Override
	public void pushTask(String path) throws Exception {
		if(cur>MAX_TASK){
			throw this.getException("pushTask(path)", "cur", "Larger than MAX_TASK: "+MAX_TASK);
		}
		this.ins.add(path);cur++;
	}
	@Override
	public String popTask() throws Exception {
		if(cur<=0){
			cur=0;
			throw this.getException("pushTask(path)", "cur", "Task List is empty");
		}
		return this.ins.remove(--cur);
	}

	@Override
	public Object runOne() throws Exception {
		String path = this.popTask();
		String[] ans = path.split("\\"+ILModelSearcher.DOT);
		
		Object parant = element;
		for(int i=0;i<ans.length;i++){
			String next = ans[i];
			parant = this.getResult(parant, next);
			if(parant==null)
				throw this.getException("runOne()", "path", 
						"path is too long and failed at ["+i+"]: \""+ans[i]+"\"");
		}
		return parant;
	}

	@Override
	public Map<String, Object> runAll() throws Exception {
		Map<String,Object> results = new HashMap<String,Object>();
		
		return results;
	}
	
	/*class TaskRunner implements Runnable{
		public String path;
		public LModelElement element;
		public Object result;
		@Override
		public void run() {
			if(path==null||element==null)
				try {
					throw getException("TaskRunner", "arguments", "Null");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			Object cur = this.element;
			String[] ans = path.split("\\"+ILModeSearcher.DOT);
			
			for(int i=0;i<ans.length;i++){
				try {
					cur = this.iterator(cur, ans[i]);
					if(cur==null)
						System.err.println("Searching failed at ["+i+"]: \""+ans[i]+"\"");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			this.result=cur;
			
		}
		
		Object iterator(Object obj,String next) throws Exception{
			if(obj==null||next==null)return null;
			
			ILModeSearcher searcher = null;
			if(obj instanceof LPackage)searcher = install_map.get(PACKAGE);
			if(obj instanceof LClass)searcher = install_map.get(CLASS);
			if(obj instanceof LEnum)searcher = install_map.get(ENUM);
			if(obj instanceof LAttribute)searcher = install_map.get(ATTRIBUTE);
			if(obj instanceof LReference)searcher = install_map.get(REFERENCE);
			if(obj instanceof LEnumLiteral)searcher = install_map.get(LITERAL);
			if(obj instanceof LDataObject)searcher = install_map.get(DATAOBJECT);
			if(obj instanceof LMultipleObject)searcher = install_map.get(MULTIPLEOBJECT);
			if(obj instanceof LClassObject)searcher = install_map.get(CLASSOBJECT);
			if(obj instanceof List){
				List<Thread> threads = new ArrayList<Thread>();
				for(int i=0;i<threads.size();i++){
					//???
				}
			}
			
			if(searcher!=null&&obj instanceof LModelElement){
				searcher.setElement((LModelElement) obj);
				return searcher.next(next);
			}
			throw getException("iterator(obj,next)","obj","Undefined Type at: "+next);
		}
		
	}*/

	
}
