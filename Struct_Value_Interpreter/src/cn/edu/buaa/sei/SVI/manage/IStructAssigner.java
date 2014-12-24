package cn.edu.buaa.sei.SVI.manage;

import java.util.Map;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public interface IStructAssigner {
	
	public static final int FIRST_ONE = 0;
	public static final int ALL_VARS = -1;
	
	public boolean confirmAssign(Variable variable,Object val);
	public IStructSearcher getSearcher();
	
	public boolean assign(Struct base,String path,Object val);
	public Variable getVariable(Struct base,String path) throws Exception;
	public boolean assignByName(Struct base,String name,Object val,int seq);
	public Variable getVariableByName(Struct base,String name,int seq);
	
	public boolean assignFunction(Function function,FunctionBody body);
	public Function getFunction(Struct base,String path) throws Exception;
	public Function getFunctionByName(Struct base,String name,int seq);
	public boolean assignFunction(Struct base,String name,int seq,FunctionBody body);
	
	public boolean assignMap(Struct base,Map<String,Object> vmap);
}
