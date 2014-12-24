package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.StructArray;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.Cardinality;
import cn.edu.buaa.sei.SVI.struct.group.CartesianProduct;
import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.group.Contain;
import cn.edu.buaa.sei.SVI.struct.group.Difference;
import cn.edu.buaa.sei.SVI.struct.group.GroupEqual;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.GroupOperator;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.Include;
import cn.edu.buaa.sei.SVI.struct.group.Intersection;
import cn.edu.buaa.sei.SVI.struct.group.Union;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.impl.NumericFactory;

public class GroupFactory {
	
	public static GroupVariable createGroupVariable(String name) throws Exception{
		return new GroupVariableImpl(name);
	}
	
	public static GroupExpression createIntersection(GroupStruct[] operands) throws Exception{
		Intersection op = new IntersectionImpl(operands,new StructArray());
		return new GroupExpressionImpl(op,new StructArray());
	}
	public static GroupExpression createUnion(GroupStruct[] operands) throws Exception{
		Union op = new UnionImpl(operands,new StructArray());
		return new GroupExpressionImpl(op,new StructArray());
	}
	public static GroupExpression createCartesianProduct(GroupStruct[] operands) throws Exception{
		CartesianProduct op = new CartesianProductImpl(operands,new StructArray());
		return new GroupExpressionImpl(op,new StructArray());
	}
	public static GroupExpression createComplement(GroupStruct operand,GroupStruct domain) throws Exception{
		Complement op = new ComplementImpl(operand,domain,new StructArray());
		return new GroupExpressionImpl(op,new StructArray());
	}
	public static GroupExpression createDifference(GroupStruct left,GroupStruct right) throws Exception{
		Difference op = new DifferenceImpl(left,right,new StructArray());
		return new GroupExpressionImpl(op,new StructArray());
	}
	
	public static GroupFunctionTemplate createGroupFunctionTemplate(String name,Variable[] arguments) throws Exception{
		return new GroupFunctionTemplateImpl(name,arguments,new StructArray());
	}
	public static GroupFunction createGroupFunction(GroupFunctionTemplate template) throws Exception{
		return new GroupFunctionImpl(template,null,null,new StructArray());
	}
	
	public static LogicExpression createInclude(Struct left,GroupStruct right) throws Exception{
		Include op = new IncludeImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	public static LogicExpression createContain(GroupStruct left,GroupStruct right) throws Exception{
		Contain op = new ContainImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	public static LogicExpression createGroupEqual(GroupStruct left,GroupStruct right) throws Exception{
		GroupEqual op = new GroupEqualImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	
	public static NumericExpression createCardinality(GroupStruct operand) throws Exception{
		Cardinality op = new CardinalityImpl(operand,new StructArray());
		return NumericFactory.createNumericExpression(op);
	}
	
	public static GroupExpression createGroupExpression(GroupOperator op) throws Exception{
		return new GroupExpressionImpl(op,new StructArray());
	}
	
	public static GroupFunction createFilter() throws Exception{
		GroupFunctionTemplate template = new FilterTemplateImpl(new StructArray());
		return new GroupFunctionImpl(template,null,null,new StructArray());
	}
	public static GroupFunction createMap() throws Exception{
		GroupFunctionTemplate template = new MapTemplateImpl(new StructArray());
		return new GroupFunctionImpl(template,null,null,new StructArray());
	}
	public static GroupFunction createTableMap() throws Exception{
		GroupFunctionTemplate template = new TableMapTemplateImpl(new StructArray());
		return new GroupFunctionImpl(template,null,null,new StructArray());
	}
}
