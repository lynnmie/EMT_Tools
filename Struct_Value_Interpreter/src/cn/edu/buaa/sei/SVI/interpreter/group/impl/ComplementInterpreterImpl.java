package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import java.util.Iterator;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.ComplementInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.AbstractGroup;
import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.impl.ConditionGroup;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class ComplementInterpreterImpl implements ComplementInterpreter{

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Complement))
			throw new Exception("Complement required");
		
		Complement op = (Complement) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Complement))
			throw new Exception("Complement required");
		
		Complement op = (Complement) input;
		return this.interpret(op);
	}

	@Override
	public Group interpret(Complement op) throws Exception {
		if(op==null)throw new Exception("Null operator is invalid");
		
		GroupStruct domain = op.getDomain();
		GroupStruct operand = op.getOperand();
		
		if(domain==null||operand==null)
			throw new Exception("Structure Error: null domain|operand");
		
		GroupInterpreter di = (GroupInterpreter) RegisterMachine.getRegister().get(domain);
		if(di==null)throw new Exception("Domain: "+domain.getClass().getCanonicalName()+" has not been registered");
		GroupInterpreter oi = (GroupInterpreter) RegisterMachine.getRegister().get(operand);
		if(oi==null)throw new Exception("Operand: "+operand.getClass().getCanonicalName()+" has not been registered");
		
		Group dg = di.interpret(domain);
		if(dg==null)throw new Exception("Interpretation failed: "+domain.hashCode());
		Group og = oi.interpret(operand);
		if(og==null)throw new Exception("Interpretation failed: "+operand.hashCode());
		
		if(og instanceof AbstractGroup){
			final LogicFunction condition = ((AbstractGroup) og).getCondition();
			
			LogicFunctionTemplate template = LogicFactory.createLogicFunctionTemplate("condition", 
					new Variable[]{VariableFactory.createFreeVariable("x")});
			LogicFunction function = LogicFactory.createLogicFunction(template);
			function.setBody(new FunctionExecutor(){
				@Override
				public void execute() throws Exception {
					Object val = this.getFunction().getTemplate().getArguments()[0].read();
					
					Inferencer inferencer = (Inferencer) RegisterMachine.getRegister().get(condition);
					condition.getTemplate().getArguments()[0].assign(val);
					Boolean result = inferencer.interpret(condition);
					
					if(result!=null)result = !result;
					this.getFunction().getTemplate().getOutput().assign(result);
				}});
			
			return new ConditionGroup(function,dg);
		}
		else{
			Iterator<Object> itor = dg.iterator();
			Group group = new SetGroup();
			
			while(itor.hasNext()){
				Object v = itor.next();
				Boolean in = og.contains(v);
				if(in==null||in==false)continue;
				else group.add(v);
			}
			
			return group;
		}
	}
	
}
