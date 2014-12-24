package cn.edu.buaa.sei.SVI.interpreter.group.impl;


import java.util.Iterator;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.DifferenceInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Difference;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;

public class DifferenceInterpreterImpl implements DifferenceInterpreter{

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Difference))throw new Exception("Difference required");
		Difference op = (Difference) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Difference))throw new Exception("Difference required");
		Difference op = (Difference) input;
		return this.interpret(op);
	}

	@Override
	public Group interpret(Difference op) throws Exception {
		if(op==null)throw new Exception("Null operator is invalid");
		
		GroupStruct left = op.getLeftOperand();
		GroupStruct right =op.getRightOperand();
		if(left==null||right==null)
			throw new Exception("Structure Error: null operands");
		
		GroupInterpreter li = (GroupInterpreter) RegisterMachine.getRegister().get(left);
		if(li==null)throw new Exception("Left: "+left.getClass().getCanonicalName()+" has not been registered");
		GroupInterpreter ri = (GroupInterpreter) RegisterMachine.getRegister().get(right);
		if(ri==null)throw new Exception("Right: "+right.getClass().getCanonicalName()+" has not been registered");
		
		Group a = li.interpret(left);
		Group b = ri.interpret(right);
		
		return this.sub(a, b);
	}
	
	protected Group sub(Group a,Group b){
		if(a==null)return null;
		Group group = new SetGroup();
		
		Iterator<Object> itor = a.iterator();
		while(itor.hasNext()){
			Object val = itor.next();
			Boolean in = b.contains(val);
			
			if(in!=null&&in==false)
				group.add(val);
		}
		
		return group;
	}
	
}
