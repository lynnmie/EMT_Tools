package cn.edu.buaa.sei.SVI.interpreter.numeric.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.numeric.AdditionComputer;
import cn.edu.buaa.sei.SVI.interpreter.numeric.Computer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.Addition;

public class AdditionComputerImpl implements AdditionComputer{

	@Override
	public Number interpret(NumericStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Addition))throw new Exception("Addition required");
		
		Addition op = (Addition) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Addition))throw new Exception("Addition required");
		
		Addition op = (Addition) input;
		return this.interpret(op);
	}

	@Override
	public Number interpret(Addition op) throws Exception {
		if(op==null)throw new Exception("Null operator is invalid");
		NumericStruct left = op.getLeftOperand();
		NumericStruct right = op.getRightOperand();
		
		Computer li = (Computer) RegisterMachine.getRegister().get(left);
		Computer ri = (Computer) RegisterMachine.getRegister().get(right);
		
		if(li==null)
			throw new Exception("Left operand: "+left.getClass().getCanonicalName()+" has not been registered");
		if(ri==null)
			throw new Exception("Right operand: "+right.getClass().getCanonicalName()+" has not been registered");
		
		Number lv = li.interpret(left);
		if(lv==null)return null;
		Number rv = ri.interpret(right);
		if(rv==null)return null;
		
		if(lv instanceof Double||rv instanceof Double||lv instanceof Float||rv instanceof Float){
			double a = lv.doubleValue();double b = rv.doubleValue();
			return a+b;
		}
		else if(lv instanceof Long||rv instanceof Long){
			long a = lv.longValue(); long b = rv.longValue();
			return a+b;
		}
		else{
			int a = lv.intValue(); int b = rv.intValue();
			return a+b;
		}
	}
	
}
