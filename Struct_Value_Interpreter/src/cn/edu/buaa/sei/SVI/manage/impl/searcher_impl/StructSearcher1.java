package cn.edu.buaa.sei.SVI.manage.impl.searcher_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import cn.edu.buaa.sei.SVI.manage.IStructSearcher;
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
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.QuantifierOperator;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;

public class StructSearcher1 implements IStructSearcher{
	
	Queue<Struct> queue = new LinkedList<Struct>();
	Set<Struct> records = new HashSet<Struct>();
	Stack<Struct> path = new Stack<Struct>();
	List<Variable> vlist = new ArrayList<Variable>();
	
	public static IStructSearcher searcher = new StructSearcher1();
	
	public static IStructSearcher create(){return searcher;}
	
	private StructSearcher1(){}
	
	protected Struct next(Variable variable,String name) throws Exception{
		if(variable==null||name==null)throw new Exception("Null variable is invalid");
		
		if(name.length()==0)return null;
		else if((variable instanceof DiscourseDomain)&&name.equals(PathLib.ITERATOR)){
			return ((DiscourseDomain)variable).getIterator();
		}
		else throw new Exception("Invalid match: {"+name+"} at "+variable.getClass().getCanonicalName());
	}
	protected Struct next(Expression expr,String name) throws Exception{
		if(expr==null||name==null)throw new Exception("Null expression is invalid");
		
		if(name.equals(PathLib.OPERATOR))return expr.getOperator();
		else throw new Exception("Invalid match: {"+name+"} at "+expr.getClass().getCanonicalName());
	}
	protected Struct next(Operator op,String name) throws Exception{
		if(op==null||name==null)throw new Exception("Null op is invalid");
		
		name = name.trim();
		if(op instanceof UnaryOperator){
			if(name.equals(PathLib.OPERAND))return ((UnaryOperator) op).getOperand();
			else if(name.startsWith(PathLib.OPERANDS)){
				String i = name.substring(4, name.length()-1);
				if(i==null)throw new Exception("Invalid name in operands: "+name);
				Integer id = Integer.parseInt(i);
				
				if(id<0||id>=1)throw new Exception("Out of range: at "+id+" {[0,+"+1+")}");
				return ((UnaryOperator) op).getOperand();
			}
			else if(op instanceof Complement){
				if(name.equals(PathLib.COMPLEMENT_DOMAIN))return ((Complement) op).getDomain();
			}
			else throw new Exception("Invalid match: {"+name+"} at "+op.getClass().getCanonicalName());
		}
		else if(op instanceof BinaryOperator){
			if(name.equals(PathLib.LEFT_OPERAND))return ((BinaryOperator) op).getLeftOperand();
			else if(name.equals(PathLib.RIGHT_OPERAND))return ((BinaryOperator) op).getRightOperand();
			else if(name.startsWith(PathLib.OPERANDS)){
				String i = name.substring(4, name.length()-1);
				if(i==null)throw new Exception("Invalid name in operands: "+name);
				Integer id = Integer.parseInt(i);
				
				if(id<0||id>=2)throw new Exception("Out of range: at "+id+" {[0,+"+2+")}");
				
				if(id==1)return ((BinaryOperator) op).getLeftOperand();
				else return ((BinaryOperator) op).getRightOperand();
			}
			else if(op instanceof QuantifierOperator){
				if(name.equals(PathLib.DISCOURSE_DOMAIN))return ((QuantifierOperator) op).getDomain();
				else if(name.equals(PathLib.SCOPE))return ((QuantifierOperator) op).getScope();
				else throw new Exception("Invalid match: {"+name+"} at "+op.getClass().getCanonicalName());
			}
			else throw new Exception("Invalid match: {"+name+"} at "+op.getClass().getCanonicalName());
		}
		else if(op instanceof MultipleOperator){
			if(name.startsWith(PathLib.OPERANDS)){
				String i = name.substring(4, name.length()-1);
				if(i==null)throw new Exception("Invalid name in operands: "+name);
				Integer id = Integer.parseInt(i);
				int n = ((MultipleOperator) op).getDimension();
				
				if(id<0||id>=n)throw new Exception("Out of range: at "+id+" {[0,+"+n+")}");
				
				return ((MultipleOperator) op).getOperands()[id];
			}
			else throw new Exception("Invalid match: {"+name+"} at "+op.getClass().getCanonicalName());
		}
		else if(op instanceof FlexibleOperator){
			if(name.startsWith(PathLib.OPERANDS)){
				String i = name.substring(4, name.length()-1);
				if(i==null)throw new Exception("Invalid name in operands: "+name);
				Integer id = Integer.parseInt(i);
				int n = ((FlexibleOperator) op).getOperands().length;
				
				if(id<0||id>=n)throw new Exception("Out of range: at "+id+" {[0,+"+n+")}");
				
				return ((FlexibleOperator) op).getOperands()[id];
			}
			else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
		}
		
		throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
	}
	protected Struct next(Function function,String name) throws Exception{
		if(function==null||name==null)throw new Exception("Null function|name is invalid");
		
		if(name.equals(PathLib.TEMPLATE))return function.getTemplate();
		else if(name.equals(PathLib.BODY))return function.getBody();
		else if(name.equals(PathLib.CONTEXT))return function.getContext();
		else throw new Exception("Unknown operator type: "+function.getClass().getCanonicalName());
	}
	protected Struct next(FunctionTemplate template,String name) throws Exception{
		if(template==null||name==null)throw new Exception("Null function template|name is invalid");
		
		if(name.startsWith(PathLib.ARGUMENT)){
			String i = name.substring(4, name.length()-1);
			if(i==null)throw new Exception("Invalid name in operands: "+name);
			Integer id = Integer.parseInt(i);
			int n = template.getArguments().length;
			
			if(id<0||id>=n)throw new Exception("Out of range: at "+id+" {[0,+"+n+")}");
			
			return template.getArguments()[id];
		}
		else if(name.equals(PathLib.OUTPUT))return template.getOutput();
		else throw new Exception("Unknown operator type: "+template.getClass().getCanonicalName());
	}
	protected Struct nextOne(Struct base,String name) throws Exception{
		if(base==null||name==null)throw new Exception("Null base|name is invalid");
		
		if(base instanceof Variable){
			Variable variable = (Variable) base;
			return this.next(variable, name);
		}
		else if(base instanceof Expression){
			Expression expr = (Expression) base;
			return this.next(expr, name);
		}
		else if(base instanceof Function){
			Function function = (Function) base;
			return this.next(function, name);
		}
		else if(base instanceof Operator){
			Operator op = (Operator) base;
			return this.next(op, name);
		}
		else if(base instanceof FunctionTemplate){
			FunctionTemplate template = (FunctionTemplate) base;
			return this.next(template, name);
		}
		else throw new Exception("Unknown type: "+base.getClass().getCanonicalName());
	}

	@Override
	public Struct get(Struct base, String path) throws Exception {
		if(base==null||path==null)throw new Exception("Null base|path is invalid");
		
		if(path.trim().length()==0)return base;
		
		String[] paths = path.split("\\.");
		int i=1;
		
		while(base!=null&&i<paths.length){
			base = this.nextOne(base, paths[i++].trim());
		}
		
		if(i<paths.length){
			StringBuilder left = new StringBuilder();
			while(i<paths.length)
				left.append(".").append(paths[i++]);
			System.err.println("Out of range in path: "+left.toString());
		}
		
		return base;
	}
	@Override
	public boolean contain(Struct base, Struct child) {
		if(base==null||child==null)return false;
		
		this.records.clear();
		this.queue.clear();
		this.queue.add(base);
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==child)return true;
			if(records.contains(base))continue;
			records.add(base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		return false;
	}
	@Override
	public Struct[] generatePath(Struct src, Struct trg) throws Exception {
		if(src==null||trg==null)throw new Exception("Null node is invalid");
		
		Stack<Integer> path_id = new Stack<Integer>();
		this.records.clear();
		this.path.clear();
		
		this.path.push(src);path_id.push(0);
		
		while(!this.path.isEmpty()){
			Struct cur = this.path.peek();
			if(cur==trg){
				Struct[] ps = new Struct[this.path.size()];
				for(int i=0;i<this.path.size();i++)
					ps[i]=this.path.get(i);
				return ps;
			}
			
			if(cur instanceof CompositeStruct){
				int id = path_id.peek();
				int n = ((CompositeStruct) cur).getChildrenStructSize();
				if(id<n){
					Struct next = ((CompositeStruct) cur).getChildrenStructs()[id];
					path_id.pop();path_id.push(++id);
					if(!this.records.contains(next)){
						this.path.push(next);path_id.push(0);
					}
				}
				else{
					this.path.pop();path_id.pop();
					this.records.add(cur);
				}
			}
			else{
				this.path.pop();path_id.pop();
				this.records.add(cur);
			}
		}
		
		throw new Exception("No path from src to trg");
	}
	@Override
	public boolean confirmPath(Struct base, String path) {
		if(base==null||path==null)return false;
		
		if(path.trim().length()==0)return true;
		
		String[] paths = path.split("\\.");
		int i=1;
		
		while(base!=null&&i<paths.length){
			try {
				base = this.nextOne(base, paths[i++].trim());
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return false;
			}
		}
		
		if(i<paths.length){
			StringBuilder left = new StringBuilder();
			while(i<paths.length)
				left.append(".").append(paths[i++]);
			System.err.println("Out of range in path: "+left.toString());
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public Set<Variable> getVariablesUnderBase(Struct base) throws Exception {
		if(base==null)throw new Exception("Null base struct is invalid");
		this.queue.clear();
		Set<Variable> variables = new HashSet<Variable>();
		
		this.queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base instanceof Variable){variables.add((Variable) base);}
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		return variables;
	}
	@Override
	public Set<Variable> getVariablesByName(Struct base, String name)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base struct is invalid");
		this.queue.clear();
		Set<Variable> variables = new HashSet<Variable>();
		
		this.queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base instanceof Variable){
				if(((Variable) base).getName().equals(name))
					variables.add((Variable) base);
			}
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		return variables;
	}
	@Override
	public Variable getFirstVariableByName(Struct base, String name)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base struct is invalid");
		this.queue.clear();
		
		this.queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base instanceof Variable){
				if(((Variable) base).getName().equals(name))
					return (Variable) base;
			}
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		return null;
	}
	@Override
	public Variable getVariableByName(Struct base, String name, int seq)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base struct is invalid");
		this.queue.clear();
		
		this.queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base instanceof Variable){
				if(((Variable) base).getName().equals(name)){
					if(seq--==0)
						return (Variable) base;
				}
			}
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		return null;
	}
	public Variable[] getVariableListByName(Struct base,String name) throws Exception{
		if(base==null||name==null)throw new Exception("Null base struct is invalid");
		this.queue.clear();
		this.vlist.clear();
		
		this.queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base instanceof Variable){
				if(((Variable) base).getName().equals(name)){
					vlist.add((Variable) base);
				}
			}
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		Variable[] variables = null;
		if(this.vlist.size()>0){
			variables = new Variable[vlist.size()];
			for(int i=0;i<vlist.size();i++)
				variables[i]=this.vlist.get(i);
		}
		
		return variables;
	}
	@Override
	public Map<String, Variable> getVariableMap(Struct base) throws Exception {
		if(base==null)throw new Exception("Null base struct is invalid");
		this.queue.clear();
		Map<String,Variable> map = new HashMap<String,Variable>();
		
		this.queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base instanceof Variable){
				map.put(((Variable) base).getName(), (Variable) base);
			}
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					this.queue.add(children[i]);
			}
		}
		
		return map;
	}

	
	
	@Override
	public Set<Expression> getExpressionsUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<Expression> expressions = new HashSet<Expression>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof Expression)
				expressions.add((Expression) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}

	@Override
	public Set<LogicExpression> getLogicExpressionsUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<LogicExpression> expressions = new HashSet<LogicExpression>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof LogicExpression)
				expressions.add((LogicExpression) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}

	@Override
	public Set<NumericExpression> getNumericExpressionUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<NumericExpression> expressions = new HashSet<NumericExpression>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof NumericExpression)
				expressions.add((NumericExpression) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}

	@Override
	public Set<GroupExpression> getGroupExpressionsUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<GroupExpression> expressions = new HashSet<GroupExpression>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof GroupExpression)
				expressions.add((GroupExpression) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}
	
	

	@Override
	public Set<Function> getFunctionsUnderBase(Struct base) throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<Function> expressions = new HashSet<Function>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof Function)
				expressions.add((Function) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}

	@Override
	public Set<LogicFunction> getLogicFunctionUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<LogicFunction> expressions = new HashSet<LogicFunction>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof LogicFunction)
				expressions.add((LogicFunction) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}

	@Override
	public Set<NumericFunction> getNumericFunctionUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<NumericFunction> expressions = new HashSet<NumericFunction>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof NumericFunction)
				expressions.add((NumericFunction) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}

	@Override
	public Set<GroupFunction> getGroupFunctionUnderBase(Struct base)
			throws Exception {
		if(base==null)throw new Exception("Null base is invalid");
		
		Set<GroupFunction> expressions = new HashSet<GroupFunction>();
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof GroupFunction)
				expressions.add((GroupFunction) base);
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return expressions;
	}
	
	

	@Override
	public Function getFunctionByName(Struct base,String name, int seq) throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()&&seq>=0){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof Function){
				FunctionTemplate template = ((Function) base).getTemplate();
				if(template.getName().equals(name)){
					if(seq--==0)return (Function) base;
				}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return null;
	}

	@Override
	public LogicFunction getLogicFunctionUnderBase(Struct base,String name, int seq)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()&&seq>=0){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof LogicFunction){
				FunctionTemplate template = ((Function) base).getTemplate();
				if(template.getName().equals(name)){
					if(seq--==0)return (LogicFunction) base;
				}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return null;
	}

	@Override
	public NumericFunction getNumericFunctionUnderBase(Struct base,String name, int seq)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()&&seq>=0){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof NumericFunction){
				FunctionTemplate template = ((Function) base).getTemplate();
				if(template.getName().equals(name)){
					if(seq--==0)return (NumericFunction) base;
				}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return null;
	}

	@Override
	public GroupFunction getGroupFunctionUnderBase(Struct base,String name, int seq)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		this.queue.clear();this.queue.add(base);this.records.clear();;
		
		while(!this.queue.isEmpty()&&seq>=0){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof GroupFunction){
				FunctionTemplate template = ((Function) base).getTemplate();
				if(template.getName().equals(name)){
					if(seq--==0)return (GroupFunction) base;
				}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return null;
	}
	
	

	@Override
	public List<Function> getFunctionsByName(Struct base,String name) throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		List<Function> list = new ArrayList<Function>();
		this.queue.clear();this.queue.add(base);this.records.clear();
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof Function){
				FunctionTemplate template = ((Function) base).getTemplate();
				if(template.getName().equals(name)){list.add((Function) base);}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return list;
	}

	@Override
	public List<LogicFunction> getLogicFunctionsUnderBase(Struct base,String name)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		List<LogicFunction> list = new ArrayList<LogicFunction>();
		this.queue.clear();this.queue.add(base);this.records.clear();
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof LogicFunction){
				FunctionTemplate template = ((LogicFunction) base).getTemplate();
				if(template.getName().equals(name)){list.add((LogicFunction) base);}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return list;
	}

	@Override
	public List<NumericFunction> getNumericFunctionsUnderBase(Struct base,String name)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		List<NumericFunction> list = new ArrayList<NumericFunction>();
		this.queue.clear();this.queue.add(base);this.records.clear();
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof NumericFunction){
				FunctionTemplate template = ((NumericFunction) base).getTemplate();
				if(template.getName().equals(name)){list.add((NumericFunction) base);}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return list;
	}

	@Override
	public List<GroupFunction> getGroupFunctionsUnderBase(Struct base,String name)
			throws Exception {
		if(base==null||name==null)throw new Exception("Null base is invalid");
		
		List<GroupFunction> list = new ArrayList<GroupFunction>();
		this.queue.clear();this.queue.add(base);this.records.clear();
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(base instanceof GroupFunction){
				FunctionTemplate template = ((GroupFunction) base).getTemplate();
				if(template.getName().equals(name)){list.add((GroupFunction) base);}
			}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return list;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public Set<Struct> getStructByClass(Struct base,Class type) throws Exception {
		if(type==null||base==null)throw new Exception("Null type is invalid");
		
		Set<Struct> elements = new HashSet<Struct>();
		this.queue.clear(); this.queue.add(base); this.records.clear();
		
		while(!this.queue.isEmpty()){
			base = this.queue.poll();
			if(base==null||this.records.contains(base))continue;
			this.records.add(base);
			
			if(type.isInstance(base)){elements.add(base);}
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null&&!this.records.contains(children[i]))
						this.queue.add(children[i]);
			}
		}
		
		return elements;
	}

}
