package cn.edu.buaa.sei.SVI.editor.translator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
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
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupExpressionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupFunctionTreeNode;
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
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicExpressionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicFunctionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LowerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.NegationTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.SmallerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.UniversalTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.UpperTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.AddTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.CardinalityTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.DivTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ModTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.MulTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NumericExpressionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NumericFunctionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RationalTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RationalVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RealTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RealVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.SubTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntTemplateTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntVariableTreeNode;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.core.expression.FlexibleOperator;
import cn.edu.buaa.sei.SVI.struct.core.expression.MultipleOperator;
import cn.edu.buaa.sei.SVI.struct.core.expression.Operator;
import cn.edu.buaa.sei.SVI.struct.core.expression.UnaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;
import cn.edu.buaa.sei.SVI.struct.core.variable.FreeVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.BooleanVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.CharacterVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.DoubleVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.FloatVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.IntegerVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.ListVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.LongVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.MapVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.SetVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.StringVariable;
import cn.edu.buaa.sei.SVI.struct.group.Cardinality;
import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.group.Contain;
import cn.edu.buaa.sei.SVI.struct.group.Difference;
import cn.edu.buaa.sei.SVI.struct.group.GroupEqual;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.Include;
import cn.edu.buaa.sei.SVI.struct.group.Intersection;
import cn.edu.buaa.sei.SVI.struct.group.Union;
import cn.edu.buaa.sei.SVI.struct.group.extend.FilterTemplate;
import cn.edu.buaa.sei.SVI.struct.group.extend.MapTemplate;
import cn.edu.buaa.sei.SVI.struct.group.extend.TableMapTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.AtLeast;
import cn.edu.buaa.sei.SVI.struct.logic.AtMost;
import cn.edu.buaa.sei.SVI.struct.logic.Between;
import cn.edu.buaa.sei.SVI.struct.logic.Conjunction;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.Disjunction;
import cn.edu.buaa.sei.SVI.struct.logic.Equivalence;
import cn.edu.buaa.sei.SVI.struct.logic.Existential;
import cn.edu.buaa.sei.SVI.struct.logic.Implication;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;
import cn.edu.buaa.sei.SVI.struct.logic.Negation;
import cn.edu.buaa.sei.SVI.struct.logic.Universal;
import cn.edu.buaa.sei.SVI.struct.numeric.Addition;
import cn.edu.buaa.sei.SVI.struct.numeric.Division;
import cn.edu.buaa.sei.SVI.struct.numeric.Mod;
import cn.edu.buaa.sei.SVI.struct.numeric.Multiplication;
import cn.edu.buaa.sei.SVI.struct.numeric.NaturalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.numeric.RationalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.RealVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.Substraction;
import cn.edu.buaa.sei.SVI.struct.numeric.ZIntegerVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Bigger;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.EBigger;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.ESmaller;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Equal;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Smaller;

public class Struct2TreeNode {
	
	Map<Struct,SVITreeNode> map = new HashMap<Struct,SVITreeNode>();
	
	public StructRootTreeNode tranlate(StructManager manager) throws Exception{
		if(manager==null)throw new Exception("Null manager is invalid");
		this.map.clear();
		
		
		StructRootTreeNode root = new StructRootTreeNode(null,"root");
		JTree tree = new JTree(root);
		root.setTree(tree);
		//System.out.println("Initialization of translation complete...");
		
		Set<Struct> tops = manager.getTopStructs();
		for(Struct top:tops){
			SVITreeNode node = this.createFromStruct(tree, top);
			root.add(node);
		}
		//System.out.println("Tree have been constructed...");
		
		for(Struct top:tops){
			this.refAll(top);
		}
		//System.out.println("Root have been linked...");
		return root;
	}
	
	protected SVITreeNode createFromStruct(JTree tree,Struct struct) throws Exception{
		if(tree==null||struct==null)throw new Exception("Null struct is invalid");
		if(this.map.containsKey(struct))return this.map.get(struct);
		
		if(struct instanceof Variable){return this.createFromVariable(tree, (Variable) struct);}
		else if(struct instanceof Function){return this.createFromFunction(tree, (Function) struct);}
		else if(struct instanceof FunctionTemplate){return this.createFromTemplate(tree, (FunctionTemplate) struct);}
		else if(struct instanceof Expression){return this.createFromExpression(tree, (Expression) struct);}
		else if(struct instanceof Operator){return this.createFromOperator(tree, (Operator) struct);}
		else throw new Exception("Unknown struct type: "+struct.getClass().getCanonicalName());
	}
	protected SVITreeNode createFromVariable(JTree tree,Variable var) throws Exception{
		if(var==null)throw new Exception("Null var is invalid");
		
		VariableTreeNode node = null;
		
		if(var instanceof BooleanVariable){node = new BooleanVariableTreeNode(tree,var.getName());}
		else if(var instanceof DiscourseDomain){
			node = new DiscourseDomainTreeNode(tree,var.getName());
			this.map.put(((DiscourseDomain) var).getIterator(), (SVITreeNode) node.getChildAt(0));
		}
		else if(var instanceof IntegerVariable){node = new IntVariableTreeNode(tree,var.getName());}
		else if(var instanceof LongVariable){node = new LongVariableTreeNode(tree,var.getName());}
		else if(var instanceof FloatVariable){node = new FloatVariableTreeNode(tree,var.getName());}
		else if(var instanceof DoubleVariable){node = new DoubleVariableTreeNode(tree,var.getName());}
		else if(var instanceof CharacterVariable){node = new CharVariableTreeNode(tree,var.getName());}
		else if(var instanceof StringVariable){node = new StringVariableTreeNode(tree,var.getName());}
		else if(var instanceof SetVariable){node = new SetVariableTreeNode(tree,var.getName());}
		else if(var instanceof ListVariable){node = new ListVariableTreeNode(tree,var.getName());}
		else if(var instanceof MapVariable){node = new MapVariableTreeNode(tree,var.getName());}
		else if(var instanceof LogicVariable){node = new LogicVariableTreeNode(tree,var.getName());}
		else if(var instanceof GroupVariable){node = new GroupVariableTreeNode(tree,var.getName());}
		else if(var instanceof NaturalVariable){node = new NaturalVariableTreeNode(tree,var.getName());}
		else if(var instanceof ZIntegerVariable){node = new ZIntVariableTreeNode(tree,var.getName());}
		else if(var instanceof RationalVariable){node = new RationalVariableTreeNode(tree,var.getName());}
		else if(var instanceof RealVariable){node = new RealVariableTreeNode(tree,var.getName());}
		else if(var instanceof FreeVariable){node = new FreeVariableTreeNode(tree,var.getName());}
		else if(var instanceof ReferenceVariable){node = new ReferencerTreeNode(tree,var.getName());}
		else throw new Exception("Unknown variable type: "+var.getClass().getCanonicalName());
		
		this.map.put(var, node);
		return node;
	}
	protected SVITreeNode createFromExpression(JTree tree,Expression expr) throws Exception{
		if(tree==null||expr==null)throw new Exception("Null tree and expression is invalid");
		
		ExpressionTreeNode node = null;
		
		Operator op = expr.getOperator();
		SVITreeNode op_node = this.createFromStruct(tree, op);
		if(op_node==null)throw new Exception("Operator generation failed...");
		
		if(expr instanceof LogicExpression){
			node = new LogicExpressionTreeNode(tree,DefaultNodeNames.LOGIC_EXPR);
		}
		else if(expr instanceof GroupExpression){
			node = new GroupExpressionTreeNode(tree,DefaultNodeNames.GROUP_EXPR);
		}
		else if(expr instanceof NumericExpression){
			node = new NumericExpressionTreeNode(tree,DefaultNodeNames.NUM_EXPR);
		}
		else throw new Exception("Unknown expression type: "+expr.getClass().getCanonicalName());
		
		node.add(op_node);
		this.map.put(expr, node);
		return node;
	}
	protected SVITreeNode createFromFunction(JTree tree,Function function) throws Exception{
		if(tree==null||function==null)throw new Exception("Null function|tree is invalid");
		
		FunctionTreeNode node = null;
		FunctionTemplate template = function.getTemplate();
		
		if(template instanceof FilterTemplate){node = new FilterTreeNode(tree,DefaultNodeNames.FILTER);}
		else if(template instanceof MapTemplate){node = new MapperTreeNode(tree,DefaultNodeNames.MAPPER);}
		else if(template instanceof TableMapTemplate){node = new TableMapperTreeNode(tree,DefaultNodeNames.TABLE_MAPPER);}
		else if(template instanceof LogicFunctionTemplate){
			SVITreeNode temp_node = this.createFromStruct(tree, template);
			node = new LogicFunctionTreeNode(tree,DefaultNodeNames.LOGIC_FUNC);
			node.add(temp_node);
		}
		else if(template instanceof GroupFunctionTemplate){
			SVITreeNode temp_node = this.createFromStruct(tree, template);
			node = new GroupFunctionTreeNode(tree,DefaultNodeNames.GROUP_FUNC);
			node.add(temp_node);
		}
		else if(template instanceof NumericFunctionTemplate){
			SVITreeNode temp_node = this.createFromStruct(tree, template);
			node = new NumericFunctionTreeNode(tree,DefaultNodeNames.NUM_FUNC);
			node.add(temp_node);
		}
		else throw new Exception("Unknown function type: "+function.getClass().getCanonicalName());
		
		this.map.put(function, node);
		return node;
	}
	protected SVITreeNode createFromTemplate(JTree tree,FunctionTemplate template) throws Exception{
		if(tree==null||template==null)throw new Exception("Null tree|template is invalid");
		
		TemplateTreeNode node = null;
		
		SVITreeNode[] arg_nodes = null;
		Variable[] arguments = template.getArguments();
		if(arguments!=null){
			int n =arguments.length;
			arg_nodes = new SVITreeNode[n];
			
			for(int i=0;i<n;i++){
				SVITreeNode arg_node = this.createFromStruct(tree, arguments[i]);
				if(arg_node==null)throw new Exception("Generation failed at argument["+i+"]");
				arg_nodes[i] = arg_node;
			}
		}
		
		if(template instanceof LogicFunctionTemplate){
			node = new LogicTemplateTreeNode(tree,template.getName());
		}
		else if(template instanceof GroupFunctionTemplate){
			node = new GroupTemplateTreeNode(tree,template.getName());
		}
		else if(template instanceof NumericFunctionTemplate){
			Bindable out = template.getOutput();
			if(out==null)throw new Exception("Null out arg in template: "+template.getName());
			if(out instanceof NaturalVariable){node = new NaturalTemplateTreeNode(tree,template.getName());}
			else if(out instanceof RationalVariable){node = new RationalTemplateTreeNode(tree,template.getName());}
			else if(out instanceof ZIntegerVariable){node = new ZIntTemplateTreeNode(tree,template.getName());}
			else if(out instanceof RealVariable){node = new RealTemplateTreeNode(tree,template.getName());}
			else throw new Exception("Unknonw output: "+out.getClass().getCanonicalName());
		}
		else throw new Exception("Unknown template type: "+template.getClass().getCanonicalName());
		
		if(arg_nodes!=null)
			for(int i=0;i<arg_nodes.length;i++){
				node.add(arg_nodes[i]);
			}
		
		this.map.put(template, node);
		return node;
	}
	protected SVITreeNode createFromOperator(JTree tree,Operator op) throws Exception{
		if(tree==null||op==null)throw new Exception("Null tree|operator is invalid");
		
		OperatorTreeNode node = null;
		SVITreeNode[] operand_nodes =null;
		if(op instanceof UnaryOperator){
			operand_nodes = new SVITreeNode[1];
			operand_nodes[0] = this.createFromStruct(tree, ((UnaryOperator) op).getOperand());
		}
		else if(op instanceof BinaryOperator){
			operand_nodes = new SVITreeNode[2];
			operand_nodes[0] = this.createFromStruct(tree, ((BinaryOperator) op).getLeftOperand());
			operand_nodes[1] = this.createFromStruct(tree, ((BinaryOperator) op).getRightOperand());
		}
		else if(op instanceof MultipleOperator){
			int n = ((MultipleOperator) op).getDimension();
			operand_nodes = new SVITreeNode[n];
			Struct[] operands = ((MultipleOperator) op).getOperands();
			for(int i=0;i<n;i++){
				operand_nodes[i] = this.createFromStruct(tree, operands[i]);
			}
		}
		else if(op instanceof FlexibleOperator){
			Struct[] operands = ((FlexibleOperator) op).getOperands();
			int n = operands.length;
			operand_nodes = new SVITreeNode[n];
			
			for(int i=0;i<n;i++){
				operand_nodes[i] = this.createFromStruct(tree, operands[i]);
			}
		}
		else throw new Exception("Unknown Operator type; "+op.getClass().getCanonicalName());
		
		if(op instanceof Negation){node = new NegationTreeNode(tree,DefaultNodeNames.NEGATION);}
		else if(op instanceof Conjunction){node = new ConjunctionTreeNode(tree,DefaultNodeNames.CONJUNCTION);}
		else if(op instanceof Disjunction){node = new DisjunctionTreeNode(tree,DefaultNodeNames.DISJUNCTION);}
		else if(op instanceof Implication){node = new ImplicationTreeNode(tree,DefaultNodeNames.IMPLICATION);}
		else if(op instanceof Equivalence){node = new EquivalenceTreeNode(tree,DefaultNodeNames.EQUIVALENCE);}
		else if(op instanceof Universal){
			node = new UniversalTreeNode(tree,DefaultNodeNames.UNIVERSAL);
			//System.out.println("Universal: "+op.toString());
			//System.out.println("##########: "+operand_nodes[0].toString());
			System.out.println("&&&&&&: "+operand_nodes[0].getClass().getCanonicalName());
			node.removeAllChildren();
		}
		else if(op instanceof Existential){
			node = new ExistentialTreeNode(tree,DefaultNodeNames.EXISTENTIAL);
			node.removeAllChildren();
		}
		else if(op instanceof AtLeast){
			node = new AtLeastTreeNode(tree,DefaultNodeNames.ATLEAST);
			node.removeAllChildren();
			Integer l = ((AtLeast) op).getLowerBound();
			SVITreeNode lower = new LowerTreeNode(tree,l.toString());
			node.add(lower);
		}
		else if(op instanceof AtMost){
			node = new AtMostTreeNode(tree,DefaultNodeNames.ATMOST);
			node.removeAllChildren();
			Integer u = ((AtMost) op).getUpperBound();
			SVITreeNode upper = new UpperTreeNode(tree,u.toString());
			node.add(upper);
		}
		else if(op instanceof Between){
			node = new BetweenTreeNode(tree,DefaultNodeNames.BETWEEN);
			node.removeAllChildren();
			Integer l = ((Between) op).getLowerBound();
			SVITreeNode lower = new LowerTreeNode(tree,l.toString());
			Integer u = ((Between) op).getUpperBound();
			SVITreeNode upper = new UpperTreeNode(tree,u.toString());
			
			node.add(lower); node.add(upper);
		}
		else if(op instanceof ESmaller){node = new ESmallerTreeNode(tree,DefaultNodeNames.ESMALLER);}
		else if(op instanceof Smaller){node = new SmallerTreeNode(tree,DefaultNodeNames.SMALLER);}
		else if(op instanceof Equal){node = new EqualTreeNode(tree,DefaultNodeNames.EQUAL);}
		else if(op instanceof EBigger){node = new EBiggerTreeNode(tree,DefaultNodeNames.EBIGGER);}
		else if(op instanceof Bigger){node = new BiggerTreeNode(tree,DefaultNodeNames.BIGGER);}
		else if(op instanceof GroupEqual){node = new GroupEqualTreeNode(tree,DefaultNodeNames.GROUPEQUAL);}
		else if(op instanceof Contain){node = new ContainTreeNode(tree,DefaultNodeNames.CONTAIN);}
		else if(op instanceof Include){node = new IncludeTreeNode(tree,DefaultNodeNames.INCLUDE);}
		else if(op instanceof Addition){node = new AddTreeNode(tree,DefaultNodeNames.ADD);}
		else if(op instanceof Substraction){node = new SubTreeNode(tree,DefaultNodeNames.SUB);}
		else if(op instanceof Multiplication){node = new MulTreeNode(tree,DefaultNodeNames.MUL);}
		else if(op instanceof Division){node = new DivTreeNode(tree,DefaultNodeNames.DIV);}
		else if(op instanceof Mod){node = new ModTreeNode(tree,DefaultNodeNames.MOD);}
		else if(op instanceof Cardinality){node = new CardinalityTreeNode(tree,DefaultNodeNames.EQUIVALENCE);}
		else if(op instanceof Intersection){node = new IntersectionTreeNode(tree,DefaultNodeNames.INTERSECTION);}
		else if(op instanceof Union){node = new UnionTreeNode(tree,DefaultNodeNames.UNION);}
		else if(op instanceof Difference){node = new DifferenceTreeNode(tree,DefaultNodeNames.DIFFERENCE);}
		else if(op instanceof Complement){node = new ComplementTreeNode(tree,DefaultNodeNames.COMPLEMENT);}
		else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
		
		if(node!=null){
			
			for(int i=0;i<operand_nodes.length;i++)
				node.add(operand_nodes[i]);
			
			if(op instanceof Complement){
				SVITreeNode domain = this.createFromStruct(tree, ((Complement) op).getDomain());
				node.add(domain);
			}
		}
		
		this.map.put(op, node);
		return node;
	}
	
	protected void refAll(Struct top) throws Exception{
		if(top==null||!this.map.containsKey(top))
			throw new Exception("Undefined top is invalid");
		
		Queue<Struct> queue = new LinkedList<Struct>();
		queue.add(top);
		
		while(!queue.isEmpty()){
			top = queue.poll();
			
			if(top instanceof ReferenceVariable){
				ReferenceVariable var = (ReferenceVariable) top;
				if(var.getRefer()!=null){
					ReferencerTreeNode var_node = (ReferencerTreeNode) this.map.get(var);
					VariableTreeNode ref_node = (VariableTreeNode) this.map.get(var.getRefer());
					
					if(var_node==null)throw new Exception("Null var_node");
					if(ref_node==null)throw new Exception("Null ref_node");
					
					var_node.setRefer(ref_node);
				}
				//System.out.println("###Reference: "+var.getName()+"-->"+var.getRefer().toString());
			}
			
			if(top instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) top).getChildrenStructs();
				int n = ((CompositeStruct) top).getChildrenStructSize();
				for(int i=0;i<n;i++)
					queue.add(children[i]);
			}
		}
		
		
	}
	
	
	
	
}
