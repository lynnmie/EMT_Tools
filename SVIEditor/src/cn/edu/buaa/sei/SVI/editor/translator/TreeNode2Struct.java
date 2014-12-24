package cn.edu.buaa.sei.SVI.editor.translator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.BooleanVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.CharVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.DoubleVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ExpressionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FloatVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FreeVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FunctionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.IntVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ListVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.LongVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.MapVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.OperatorTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ReferencerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.SetVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.StringVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.StructRootTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.TemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.VariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.ComplementTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.DifferenceTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.FilterTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.IntersectionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.MapperTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.TableMapperTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.UnionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.AtLeastTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.AtMostTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.BetweenTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.BiggerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ConjunctionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ContainTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.DiscourseDomainTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.DisjunctionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.EBiggerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ESmallerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.EqualTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.EquivalenceTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ExistentialTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.GroupEqualTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ImplicationTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.IncludeTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.NegationTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.SmallerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.UniversalTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.AddTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.CardinalityTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.DivTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ModTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.MulTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RationalTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RationalVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RealTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RealVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.SubTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntVariableTreeNode;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.impl.GroupFactory;
import cn.edu.buaa.sei.SVI.struct.logic.AtMost;
import cn.edu.buaa.sei.SVI.struct.logic.Between;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.numeric.impl.NumericFactory;

public class TreeNode2Struct {
	
	Map<SVITreeNode,Struct> map = new HashMap<SVITreeNode,Struct>();
	
	public StructManager getFromRoot(StructRootTreeNode node) throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		this.map.clear();
		
		StructManager manager = SVIManageFactory.createStructManager();
		int n = node.getChildCount();
		
		for(int i=0;i<n;i++){
			Struct top = this.getStruct((SVITreeNode) node.getChildAt(i));
			manager.putTopStruct(top);
		}
		this.referAll(node);
		
		return manager;
	}
	
	public Struct getStruct(SVITreeNode node) throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		if(this.map.containsKey(node))return this.map.get(node);
		
		if(node instanceof VariableTreeNode){return this.getVariable((VariableTreeNode) node);}
		else if(node instanceof ExpressionTreeNode){return this.getExpression((ExpressionTreeNode) node);}
		else if(node instanceof OperatorTreeNode){return this.getOperator((OperatorTreeNode) node);}
		else if(node instanceof FunctionTreeNode){return this.getFunction((FunctionTreeNode) node);}
		else if(node instanceof TemplateTreeNode){return this.getTemplate((TemplateTreeNode) node);}
		else throw new Exception("Unknown tree node: "+node.getClass().getCanonicalName());
	}
	
	protected Variable getVariable(VariableTreeNode node) throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		
		String name = node.getUserObject().toString();
		
		Variable x = null;
		if(node instanceof BooleanVariableTreeNode){x = VariableFactory.createBoolean(name);}
		else if(node instanceof IntVariableTreeNode){x = VariableFactory.createInteger(name);}
		else if(node instanceof LongVariableTreeNode){x = VariableFactory.createLong(name);}
		else if(node instanceof FloatVariableTreeNode){x = VariableFactory.createFloat(name);}
		else if(node instanceof DoubleVariableTreeNode){x = VariableFactory.createDouble(name);}
		else if(node instanceof CharVariableTreeNode){x = VariableFactory.createCharacter(name);}
		else if(node instanceof StringVariableTreeNode){x = VariableFactory.createString(name);}
		else if(node instanceof FreeVariableTreeNode){x = VariableFactory.createFreeVariable(name);}
		else if(node instanceof ReferencerTreeNode){
			ReferenceVariable var = VariableFactory.createReference(name);
			/*if(((ReferencerTreeNode) node).getRefer()!=null){
				Variable ref = (Variable) this.getStruct(((ReferencerTreeNode) node).getRefer());
				System.out.println("Linking Ref: "+((ReferencerTreeNode) node).getRefer());
				var.refer(ref);
			}*/
			x = var;
		}
		else if(node instanceof ListVariableTreeNode){x = VariableFactory.createList(name);}
		else if(node instanceof SetVariableTreeNode){x = VariableFactory.createSet(name);}
		else if(node instanceof MapVariableTreeNode){x = VariableFactory.createMap(name);}
		else if(node instanceof LogicVariableTreeNode){x = LogicFactory.createLogicVariable(name);}
		else if(node instanceof GroupVariableTreeNode){x = GroupFactory.createGroupVariable(name);}
		else if(node instanceof DiscourseDomainTreeNode){
			x = LogicFactory.createDiscourseDomain(name);
			Variable itor = ((DiscourseDomain)x).getIterator();
			SVITreeNode itor_node = (SVITreeNode) node.getChildAt(0);
			this.map.put(itor_node, itor);
		}
		else if(node instanceof NaturalVariableTreeNode){x = NumericFactory.createNaturalVariable(name);}
		else if(node instanceof ZIntVariableTreeNode){x = NumericFactory.createZIntegerVariable(name);}
		else if(node instanceof RationalVariableTreeNode){x = NumericFactory.createRationalVariable(name);}
		else if(node instanceof RealVariableTreeNode){x = NumericFactory.createRealVariable(name);}
		else throw new Exception("Unknown tree node: "+node.getClass().getCanonicalName());
		
		this.map.put(node, x);
		return x;
	}
	protected Expression getExpression(ExpressionTreeNode node) throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		if(node.getChildCount()!=1)
			throw new Exception("Invalid Structure at Expression Node: "+node.getUserObject().toString());
		
		Expression expr = null;
		expr = (Expression) this.getStruct((SVITreeNode) node.getChildAt(0));
		this.map.put(node, expr);
		return expr;
	}
	protected Expression getOperator(OperatorTreeNode node) throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		
		Expression expr = null;
		if(node instanceof NegationTreeNode){
			if(node.getChildCount()!=1)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			SVITreeNode child = (SVITreeNode) node.getChildAt(0);
			LogicStruct child_struct = (LogicStruct) this.getStruct(child);
			expr = LogicFactory.createNegation(child_struct);
		}
		else if(node instanceof ConjunctionTreeNode){
			if(node.getChildCount()<=1)
				throw new Exception(node.getClass().getName()+" required at least 2 child!");
			int n = node.getChildCount();
			LogicStruct[] operands = new LogicStruct[n];
			
			for(int i=0;i<n;i++){
				operands[i] = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(i));
			}
			
			expr = LogicFactory.createConjunction(operands);
		}
		else if(node instanceof DisjunctionTreeNode){
			if(node.getChildCount()<=1)throw new Exception(node.getClass().getName()+" required at least 2 child!");
			int n = node.getChildCount();
			LogicStruct[] operands = new LogicStruct[n];
			
			for(int i=0;i<n;i++){
				operands[i] = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(i));
			}
			
			expr = LogicFactory.createDisjunction(operands);
		}
		else if(node instanceof ImplicationTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			LogicStruct left = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			LogicStruct right = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = LogicFactory.createImplication(left, right);
		}
		else if(node instanceof EquivalenceTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			LogicStruct left = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			LogicStruct right = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = LogicFactory.createEquivalence(left, right);
		}
		else if(node instanceof UniversalTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			DiscourseDomain left = (DiscourseDomain) this.getStruct((SVITreeNode) node.getChildAt(0));
			LogicStruct right = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = LogicFactory.createUniversal(left, right);
		}
		else if(node instanceof ExistentialTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			DiscourseDomain left = (DiscourseDomain) this.getStruct((SVITreeNode) node.getChildAt(0));
			LogicStruct right = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = LogicFactory.createExistential(left, right);
		}
		else if(node instanceof AtLeastTreeNode){
			if(node.getChildCount()!=3)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			SVITreeNode item0 = (SVITreeNode) node.getChildAt(0);
			int lower=0;
			try{
				lower = Integer.parseInt(item0.getUserObject().toString());
			}catch(Exception ex){
				System.err.println("TreeNode \""+item0.getUserObject().toString()+"\" value invalid for int.");
				lower=0;
			}
			
			DiscourseDomain domain = (DiscourseDomain) this.getStruct((SVITreeNode) node.getChildAt(1));
			LogicStruct scope = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(2));
			
			expr = LogicFactory.createAtLeast(domain, scope, lower);
		}
		else if(node instanceof AtMostTreeNode){
			if(node.getChildCount()!=3)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			SVITreeNode item0 = (SVITreeNode) node.getChildAt(0);
			int upper=AtMost.UNBOUNDED;
			try{
				upper = Integer.parseInt(item0.getUserObject().toString());
			}catch(Exception ex){
				System.err.println("TreeNode \""+item0.getUserObject().toString()+"\" value invalid for int.");
				upper=0;
			}
			
			DiscourseDomain domain = (DiscourseDomain) this.getStruct((SVITreeNode) node.getChildAt(1));
			LogicStruct scope = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(2));
			
			expr = LogicFactory.createAtMost(domain, scope, upper);
		}
		else if(node instanceof BetweenTreeNode){
			if(node.getChildCount()!=4)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			SVITreeNode item0 = (SVITreeNode) node.getChildAt(0);
			SVITreeNode item1 = (SVITreeNode) node.getChildAt(1);
			int lower=0, upper=Between.UNBOUNDED;
			try{
				lower = Integer.parseInt(item0.getUserObject().toString());
			}catch(Exception ex){
				System.err.println("TreeNode \""+item0.getUserObject().toString()+"\" value invalid for int.");
				lower=0;
			}
			try{
				upper = Integer.parseInt(item1.getUserObject().toString());
			}catch(Exception ex){
				System.err.println("TreeNode \""+item1.getUserObject().toString()+"\" value invalid for int.");
				upper=Between.UNBOUNDED;
			}
			
			DiscourseDomain domain = (DiscourseDomain) this.getStruct((SVITreeNode) node.getChildAt(1));
			LogicStruct scope = (LogicStruct) this.getStruct((SVITreeNode) node.getChildAt(2));
			
			expr = LogicFactory.createBetween(domain, scope, upper, lower);
		}
		else if(node instanceof SmallerTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createSmaller(left, right);
		}
		else if(node instanceof ESmallerTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createESmaller(left, right);
		}
		else if(node instanceof EqualTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createEqual(left, right);
		}
		else if(node instanceof EBiggerTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr= NumericFactory.createEBigger(left, right);
		}
		else if(node instanceof BiggerTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createBigger(left, right);
		}
		else if(node instanceof GroupEqualTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			GroupStruct left = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			GroupStruct right = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = GroupFactory.createGroupEqual(left, right);
		}
		else if(node instanceof ContainTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			GroupStruct left = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			GroupStruct right = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = GroupFactory.createContain(left, right);
		}
		else if(node instanceof IncludeTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			Struct left = (Struct) this.getStruct((SVITreeNode) node.getChildAt(0));
			GroupStruct right = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = GroupFactory.createInclude(left, right);
		}
		else if(node instanceof AddTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createAddition(left, right);
		}
		else if(node instanceof SubTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createSubstract(left, right);
		}
		else if(node instanceof MulTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createMultiplication(left, right);
		}
		else if(node instanceof DivTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createDivision(left, right);
		}
		else if(node instanceof ModTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			NumericStruct left = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			NumericStruct right = (NumericStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = NumericFactory.createMod(left, right);
		}
		else if(node instanceof CardinalityTreeNode){
			if(node.getChildCount()!=1)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			GroupStruct child = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			expr = GroupFactory.createCardinality(child);
		}
		else if(node instanceof IntersectionTreeNode){
			if(node.getChildCount()<=1)
				throw new Exception(node.getClass().getName()+" required at least 2 child!");
			
			int n = node.getChildCount();
			GroupStruct[] operands = new GroupStruct[n];
			for(int i=0;i<n;i++){
				operands[i] = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(i));
			}
			expr = GroupFactory.createIntersection(operands);
		}
		else if(node instanceof UnionTreeNode){
			if(node.getChildCount()<=1)
				throw new Exception(node.getClass().getName()+" required at least 2 child!");
			
			int n = node.getChildCount();
			GroupStruct[] operands = new GroupStruct[n];
			for(int i=0;i<n;i++){
				operands[i] = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(i));
			}
			expr = GroupFactory.createUnion(operands);
		}
		else if(node instanceof DifferenceTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			GroupStruct left = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			GroupStruct right = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = GroupFactory.createDifference(left, right);
		}
		else if(node instanceof ComplementTreeNode){
			if(node.getChildCount()!=2)
				throw new Exception(node.getClass().getName()+" required "+node.requiredChildrenCount()+" child!");
			
			GroupStruct left = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(0));
			GroupStruct right = (GroupStruct) this.getStruct((SVITreeNode) node.getChildAt(1));
			expr = GroupFactory.createComplement(left, right);
		}
		else throw new Exception("Unknown node type: "+node.getClass().getCanonicalName());
		
		this.map.put(node, expr);
		return expr;
	}
	protected Function getFunction(FunctionTreeNode node) throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		if(node.getChildCount()!=1)
			throw new Exception("Invalid Structure at Function Node: "+node.getUserObject().toString());
		
		Function func = null;
		if(node instanceof FilterTreeNode){
			func = GroupFactory.createFilter();
		}
		else if(node instanceof MapperTreeNode){
			func = GroupFactory.createMap();
		}
		else if(node instanceof TableMapperTreeNode){
			func = GroupFactory.createTableMap();
		}
		else
			func = (Function) this.getStruct((SVITreeNode) node.getChildAt(0));
		this.map.put(node, func);
		return func;
	}
	protected Function getTemplate(TemplateTreeNode node)throws Exception{
		if(node==null)throw new Exception("Null node is invalid");
		String name = node.getUserObject().toString();
		
		Function func = null;
		int n = node.getChildCount();
		Variable[] arguments = new Variable[n];
		for(int i=0;i<n;i++)
			arguments[i] = (Variable) this.getStruct((SVITreeNode) node.getChildAt(i));
		
		if(node instanceof LogicTemplateTreeNode){
			LogicFunctionTemplate template = LogicFactory.createLogicFunctionTemplate(name, arguments);
			func = LogicFactory.createLogicFunction(template);
		}
		else if(node instanceof GroupTemplateTreeNode){
			GroupFunctionTemplate template = GroupFactory.createGroupFunctionTemplate(name, arguments);
			func = GroupFactory.createGroupFunction(template);
		}
		else if(node instanceof NaturalTemplateTreeNode){
			NumericFunctionTemplate template = NumericFactory.createNaturalFunctionTemplate(name, arguments);
			func = NumericFactory.createNumericFunction(template, null, null);
		}
		else if(node instanceof RationalTemplateTreeNode){
			NumericFunctionTemplate template = NumericFactory.createRationalFunctionTemplate(name, arguments);
			func = NumericFactory.createNumericFunction(template, null, null);
		}
		else if(node instanceof RealTemplateTreeNode){
			NumericFunctionTemplate template = NumericFactory.createRealFunctionTemplate(name, arguments);
			func = NumericFactory.createNumericFunction(template, null, null);
		}
		else if(node instanceof ZIntTemplateTreeNode){
			NumericFunctionTemplate template = NumericFactory.createZIntegerFunctionTemplate(name, arguments);
			func = NumericFactory.createNumericFunction(template, null, null);
		}
		else throw new Exception("Unknown node type: "+node.getClass().getCanonicalName());
		
		this.map.put(node, func);
		return func;
	}
	
	protected void referAll(StructRootTreeNode root) throws Exception{
		if(root==null)throw new Exception("Null root is invalid");
		
		Queue<SVITreeNode> queue = new LinkedList<SVITreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			SVITreeNode node = queue.poll();
			if(node instanceof ReferencerTreeNode){
				ReferencerTreeNode rnode = (ReferencerTreeNode) node;
				//ReferenceVariable var = (ReferenceVariable) this.map.get(node);
				if(rnode.getRefer()!=null){
					ReferenceVariable var = (ReferenceVariable) this.map.get(rnode);
					Variable ref = (Variable) this.getStruct(rnode.getRefer());
					var.refer(ref);
					System.out.println(var.getName()+"-->"+ref.getName());
				}
			}
			
			int n = node.getChildCount();
			for(int i=0;i<n;i++)
				queue.add((SVITreeNode) node.getChildAt(i));
		}
		
	}
}
