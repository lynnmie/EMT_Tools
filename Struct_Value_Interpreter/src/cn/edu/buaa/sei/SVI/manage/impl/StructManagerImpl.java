package cn.edu.buaa.sei.SVI.manage.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.QuantifierOperator;

public class StructManagerImpl implements StructManager{
	
	Set<Struct> tops = new HashSet<Struct>();

	@Override
	public boolean contain(Struct struct) {
		if(struct==null)return false;
		if(this.tops.contains(struct))return true;
		
		for(Struct e:this.tops){
			if(this.contain(e, struct))return true;
			//if(this.contain2(e, struct))return true;
		}
		
		return false;
	}

	@Override
	public Set<Struct> getTopStructs() {return tops;}

	@Override
	public void putTopStruct(Struct struct) throws Exception {
		if(this.tops.contains(struct))return;
		else if(this.contain(struct))throw new Exception("Try to put a un-top struct to the top {invalid operation}");
		else this.tops.add(struct);
	}

	@Override
	public void removeTopStruct(Struct struct) throws Exception {
		if(this.tops.contains(struct))this.tops.remove(struct);
		else throw new Exception("Only top struct could be removed directly");
	}

	@Override
	public void clear() {this.tops.clear();}
	
	protected boolean contain(Struct base,Struct child){
		if(base==null||child==null)return false;
		
		if(base instanceof Variable)return this.containInVariable((Variable) base, child);
		else if(base instanceof Expression)return this.containInExpression((Expression) base, child);
		else if(base instanceof Operator)return this.containInOperator((Operator) base, child);
		else if(base instanceof Function)return this.containInFunction((Function) base, child);
		else if(base instanceof FunctionTemplate)return this.containInTemplate((FunctionTemplate) base, child);
		else return false;
	}
	protected boolean containInVariable(Variable variable,Struct child){
		if(variable==null||child==null)return false;
		
		if(variable instanceof DiscourseDomain)
			return ((DiscourseDomain) variable).getIterator()==child;
		else return false;
	}
	protected boolean containInExpression(Expression expr,Struct child){
		if(expr==null||child==null)return false;
		
		if(expr.getOperator()==child)return true;
		else return this.contain(expr.getOperator(), child);
	}
	protected boolean containInOperator(Operator op,Struct child){
		if(op==null||child==null)return false;
		
		if(op instanceof UnaryOperator){
			if(((UnaryOperator) op).getOperand()==child)return true;
			else if(op instanceof Complement){
				if(((Complement) op).getDomain()==child)return true;
			}
			return this.contain(((UnaryOperator) op).getOperand(), child);
		}
		else if(op instanceof BinaryOperator){
			if(((BinaryOperator) op).getLeftOperand()==child)return true;
			else if(((BinaryOperator) op).getRightOperand()==child)return true;
			else if(op instanceof QuantifierOperator){
				if(((QuantifierOperator) op).getDomain()==child)return true;
				else if(((QuantifierOperator) op).getScope()==child)return true;
				else return this.contain(((QuantifierOperator) op).getScope(), child)
						||this.contain(((QuantifierOperator) op).getDomain(), child);
			}
			else return this.contain(((BinaryOperator) op).getLeftOperand(), child)
					||this.contain(((BinaryOperator) op).getRightOperand(), child);
		}
		else if(op instanceof MultipleOperator){
			Struct[] operands = ((MultipleOperator) op).getOperands();
			int n = ((MultipleOperator) op).getDimension();
			for(int i=0;i<n;i++)
				if(operands[i]==child)return true;
			
			for(int i=0;i<n;i++)
				if(this.contain(operands[i], child))return true;
			return false;
		}
		else if(op instanceof FlexibleOperator){
			Struct[] operands = ((FlexibleOperator) op).getOperands();
			int n = operands.length;
			
			for(int i=0;i<n;i++)
				if(operands[i]==child)return true;
			
			for(int i=0;i<n;i++)
				if(this.contain(operands[i], child))return true;
			return false;
		}
		else return false;
	}
	protected boolean containInFunction(Function function,Struct child){
		if(function==null||child==null)return false;
		
		if(function.getTemplate()==child)return true;
		else if(function.getBody()==child)return true;
		else if(function.getContext()==child)return true;
		else return this.contain(function.getTemplate(), child);
	}
	protected boolean containInTemplate(FunctionTemplate template,Struct child){
		if(template==null||child==null)return false;
		
		if(template.getOutput()==child)return true;
		
		Variable[] arguments = template.getArguments();
		if(arguments!=null){
			for(int i=0;i<arguments.length;i++)
				if(child==arguments[i])return true;
			
			for(int i=0;i<arguments.length;i++)
				if(this.contain(arguments[i], child))
					return true;
		}
		
		return this.contain(template.getOutput(), child);		
	}
	
	protected boolean contain2(Struct base,Struct child){
		if(base==null||child==null)return false;
		
		Queue<Struct> queue = new LinkedList<Struct>();
		queue.add(base);
		while(!queue.isEmpty()){
			base = queue.poll();
			if(base==child)return true;
			
			if(base instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) base).getChildrenStructs();
				int n = ((CompositeStruct) base).getChildrenStructSize();
				for(int i =0;i<n;i++)
					queue.add(children[i]);
			}
		}
		
		return false;
	}

}
