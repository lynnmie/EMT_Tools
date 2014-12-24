package cn.edu.buaa.sei.SVI.manage.impl.searcher_impl;

import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.IStructSearcher;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public class StructAssigner implements IStructAssigner{
	IStructSearcher searcher;
	
	static StructAssigner assigner ;
	
	static{
		try {
			assigner = new StructAssigner(StructSearcher1.create());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static IStructAssigner create(){return assigner;}
	
	private StructAssigner(IStructSearcher searcher) throws Exception{
		if(searcher==null)throw new Exception("Null searcher is invalid");
		this.searcher = searcher;
	}

	@Override
	public IStructSearcher getSearcher() {
		return this.searcher;
	}
	@Override
	public boolean confirmAssign(Variable variable, Object val) {
		if(variable==null)return false;
		
		boolean flag = true;
		Object ov = null;
		try {
			ov = variable.read();
			variable.assign(val);
			variable.assign(ov);
		} catch (Exception e) {
			try {
				variable.assign(ov);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			flag = false;
			
		}
		
		try {
			variable.assign(ov);
		} catch (Exception e) {
			return false;
		}
		return flag;
	}

	@Override
	public Variable getVariable(Struct base, String path) throws Exception {
		return (Variable) this.searcher.get(base, path);
	}
	@Override
	public Variable getVariableByName(Struct base, String name, int seq) {
		try {
			return this.searcher.getVariableByName(base, name, seq);
		} catch (Exception e) {
			System.err.println("Search failed");
			return null;
		}
	}
	@Override
	public boolean assign(Struct base, String path, Object val) {
		if(base==null||path==null)return false;
		
		Variable var=null;
		try {
			var = (Variable) this.searcher.get(base, path);
		} catch (Exception e) {
			System.err.println("Invalid Path: "+path);
			return false;
		}
		
		if(var!=null){
			try {
				var.assign(val);
				return true;
			} catch (Exception e) {
				System.err.println("Type match failed.");
			}
		}
		
		System.err.println("Invalid path --> not variable");
		return false;
	}
	@Override
	public boolean assignByName(Struct base, String name, Object val, int seq) {
		if(base==null||name==null)return false;
		
		if(seq==ALL_VARS){
			try {
				Set<Variable> vars = this.searcher.getVariablesByName(base, name);
				boolean flag = true;
				for(Variable var:vars){
					try{
						var.assign(val);
					}catch(Exception ex){
						System.err.println("Assignment failed at: "+var.getClass().getCanonicalName()+"@{"+var.hashCode()+"}");
						flag=false;
						continue;
					}
				}
				return flag;
			} catch (Exception e) {
				System.err.println("Search failed");
				return false;
			}
		}
		else{
			try {
				Variable var = this.searcher.getVariableByName(base, name, seq);
				try{
					var.assign(val);
					return true;
				}catch(Exception ex){
					System.err.println("Assignment failed at: "+var.getClass().getCanonicalName()+"@{"+var.hashCode()+"}");
					return false;
				}
			} catch (Exception e) {
				System.err.println("Search failed");
				return false;
			}
		}
	}

	
	@Override
	public boolean assignFunction(Function function, FunctionBody body) {
		if(function==null||body==null)return false;
		function.setBody(body);
		return true;
	}

	@Override
	public Function getFunction(Struct base, String path) throws Exception {
		if(base==null||path==null)throw new Exception("Null base|path is invalid");
		Struct result = this.searcher.get(base, path);
		if(result==null)throw new Exception("Invalid path: "+path);
		if(!(result instanceof Function))throw new Exception("Invalid result: "+result.getClass().getCanonicalName());
		return (Function) result;
	}

	@Override
	public Function getFunctionByName(Struct base, String name, int seq) {
		try {
			return this.searcher.getFunctionByName(base, name, seq);
		} catch (Exception e) {
			System.err.println("Search failed");
			return null;
		}
	}

	@Override
	public boolean assignFunction(Struct base, String name, int seq,
			FunctionBody body) {
		try {
			Function function = this.searcher.getFunctionByName(base, name, seq);
			if(function==null){
				System.err.println("Invalid name&seq: "+name+"{"+seq+"}");
				return false;
			}
			function.setBody(body);
			return true;
		} catch (Exception e) {
			System.err.println("Search failed");
			return false;
		}
	}

	
	@Override
	public boolean assignMap(Struct base, Map<String, Object> vmap){
		if(base==null||vmap==null){
			System.err.println("Error happened: Null base|Map");
			return false;
		}
		
		Set<String> names = vmap.keySet();
		boolean res = true;
		for(String name:names){
			Object val = vmap.get(name);
			
			try {
				Variable var = this.searcher.getVariableByName(base, name, 0);
				if(var!=null){
					var.assign(val);
				}
				else if(val!=null&&(val instanceof FunctionBody)){
					Function func = this.searcher.getFunctionByName(base, name, 0);
					func.setBody((FunctionBody) val);
				}
				else{
					System.err.println("No appliable variable|function for name: "+name);
					res = false; continue;
				}
			} catch (Exception e) {
				try {
					if(val!=null&&(val instanceof FunctionBody)){
						Function func = this.searcher.getFunctionByName(base, name, 0);
						func.setBody((FunctionBody) val);
					}
					else{
						System.err.println("No appliable variable|function for name: "+name);
						res = false; continue;
					}
				} catch (Exception e1) {
					System.err.println("Assignment failed for name: "+name);
					res=false; continue;
				}
			}
		}
		
		return res;
	}
	
}
