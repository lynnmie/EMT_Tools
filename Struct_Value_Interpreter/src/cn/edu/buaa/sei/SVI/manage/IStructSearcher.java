package cn.edu.buaa.sei.SVI.manage;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;

public interface IStructSearcher {
	
	public boolean confirmPath(Struct base,String path);
	public Struct get(Struct base,String path) throws Exception;
	public boolean contain(Struct base,Struct child);
	public Struct[] generatePath(Struct src,Struct trg) throws Exception;
	
	public Set<Variable> getVariablesUnderBase(Struct base) throws Exception;
	public Set<Variable> getVariablesByName(Struct base,String name) throws Exception;
	public Variable getFirstVariableByName(Struct base,String name) throws Exception;
	public Variable getVariableByName(Struct base,String name,int seq) throws Exception;
	public Variable[] getVariableListByName(Struct base,String name) throws Exception;
	public Map<String,Variable> getVariableMap(Struct base) throws Exception;
	
	public Set<Expression> getExpressionsUnderBase(Struct base) throws Exception;
	public Set<LogicExpression> getLogicExpressionsUnderBase(Struct base) throws Exception;
	public Set<NumericExpression> getNumericExpressionUnderBase(Struct base) throws Exception;
	public Set<GroupExpression> getGroupExpressionsUnderBase(Struct base) throws Exception;
	
	public Set<Function> getFunctionsUnderBase(Struct base) throws Exception;
	public Set<LogicFunction> getLogicFunctionUnderBase(Struct base) throws Exception;
	public Set<NumericFunction> getNumericFunctionUnderBase(Struct base) throws Exception;
	public Set<GroupFunction> getGroupFunctionUnderBase(Struct base) throws Exception;
	
	public Function getFunctionByName(Struct base,String name,int seq) throws Exception;
	public LogicFunction getLogicFunctionUnderBase(Struct base,String name,int seq) throws Exception;
	public NumericFunction getNumericFunctionUnderBase(Struct base,String name,int seq) throws Exception;
	public GroupFunction getGroupFunctionUnderBase(Struct base,String name,int seq) throws Exception;
	
	public List<Function> getFunctionsByName(Struct base,String name) throws Exception;
	public List<LogicFunction> getLogicFunctionsUnderBase(Struct base,String name) throws Exception;
	public List<NumericFunction> getNumericFunctionsUnderBase(Struct base,String name) throws Exception;
	public List<GroupFunction> getGroupFunctionsUnderBase(Struct base,String name) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public Set<Struct> getStructByClass(Struct base,Class type) throws Exception;
	
}
