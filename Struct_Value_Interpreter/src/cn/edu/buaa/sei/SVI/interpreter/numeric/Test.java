package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.impl.NumericFactory;

public class Test {

	static InterpreterRegisterMachine register;
	
	public static void main(String[] args) {
		try {
			register();
			Struct p = create1();
			System.out.println(p.toString());
			
			Interpreter interpreter = register.get(p);
			System.out.println("result: "+interpreter.interpret(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void register() throws Exception{
		register = RegisterMachine.getRegister();
	}
	
	public static Struct create1() throws Exception{
		NumericVariable x = NumericFactory.createZIntegerVariable("x");
		NumericVariable y = NumericFactory.createZIntegerVariable("y");
		NumericVariable z = NumericFactory.createZIntegerVariable("z");
		
		NumericExpression t1 = NumericFactory.createAddition(x, y);
		NumericExpression t21 = NumericFactory.createMultiplication(x, z);
		NumericExpression t22 = NumericFactory.createMultiplication(y, y);
		NumericExpression t2 = NumericFactory.createDivision(t21,t22);
		
		x.assign(15); y.assign(1); z.assign(6);
		
		return NumericFactory.createSubstract(t1, t2);
	}
	
}
