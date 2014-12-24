package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.group.IntersectionInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.AbstractGroup;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.Intersection;
import cn.edu.buaa.sei.SVI.struct.group.impl.ConditionGroup;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class IntersectionInterpreterImpl implements IntersectionInterpreter{
	
	

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Intersection))throw new Exception("Intersection required");
		
		Intersection op = (Intersection) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Intersection))throw new Exception("Intersection required");
		
		Intersection op = (Intersection) input;
		return this.interpret(op);
	}

	@Override
	public Group interpret(Intersection op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		GroupStruct[] grps = op.getOperands();
		
		if(grps==null||grps.length<2)
			throw new Exception("Structure Errors: at least 2 operands");
		
		List<AbstractGroup> alist = new ArrayList<AbstractGroup>();
		List<Group> blist = new ArrayList<Group>();
		
		alist.clear(); blist.clear();
		
		for(int i=0;i<grps.length;i++){
			GroupInterpreter interpreter = (GroupInterpreter) RegisterMachine.getRegister().get(grps[i]);
			Group group = interpreter.interpret(grps[i]);
			if(group==null)throw new Exception("operands["+i+"] interpretation failed: "+grps[i].hashCode());
			
			if(i==0)blist.add(group);
			else if(group instanceof AbstractGroup)alist.add((AbstractGroup) group);
			else blist.add(group);
		}
		
		Group domain = this.mergeAll(blist);
		
		if(alist.isEmpty())return domain;
		else{
			LogicFunctionTemplate template = LogicFactory.createLogicFunctionTemplate("pand", 
					new Variable[]{VariableFactory.createFreeVariable("x")});
			LogicFunction function = LogicFactory.createLogicFunction(template);
			
			final LogicStruct[] operands = new LogicStruct[alist.size()];
			for(int i=0;i<alist.size();i++)
				operands[i]=alist.get(i).getCondition();
			
			function.setBody(new FunctionExecutor(){
				@Override
				public void execute() throws Exception {
					Object val = this.getFunction().getTemplate().getArguments()[0].read();
					
					boolean containNull = false;
					Boolean result=null;
					for(int i=0;i<operands.length;i++){
						LogicFunction fi = (LogicFunction) operands[i];
						Inferencer inferencer = (Inferencer) RegisterMachine.getRegister().get(fi);
						fi.getTemplate().getArguments()[0].assign(val);
						
						result = inferencer.interpret(fi);
						if(result==null)containNull=true;
						else if(result==false){
							this.getFunction().getTemplate().getOutput().assign(false);
							return;
						}
					}
					
					if(containNull)result=null;
					else result=true;
					
					this.getFunction().getTemplate().getOutput().assign(result);
				}});
			
			return new ConditionGroup(function,domain);
		}
		
	}
	
	protected Group merge(Group a,Group b){
		if(a==null||b==null)return null;
		Group group = new SetGroup();
		if(a.size()==0||b.size()==0)return group;
		
		if(a.size()>b.size()){
			Group t = a;
			a = b;
			b = t;
		}
		
		Iterator<Object> itor = a.iterator();
		while(itor.hasNext()){
			Object val = itor.next();
			Boolean in = b.contains(val);
			if(in!=null&&in==true)
				group.add(val);
		}
		
		return group;
	}
	protected Group mergeAll(List<Group> blist){
		Queue<Group> qlist = new LinkedList<Group>();
		qlist.clear();
		
		for(int i=0;i<blist.size();i++)
			qlist.add(blist.get(i));
		
		while(!qlist.isEmpty()){
			if(qlist.size()<2)return qlist.poll();
			
			Group a = qlist.poll();
			Group b = qlist.poll();
			Group c = this.merge(a, b);
			if(c==null)
				try {
					throw new Exception("Merge exception happened: {"+a.hashCode()+"} * {"+b.hashCode()+"}");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			qlist.add(c);
		}
		
		return new SetGroup();
	}

}
