package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class Test {
	
	public static void main(String[] args){
		LogicStruct p;
		try {
			p = create2();
			System.out.println(p.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static LogicStruct create1() throws Exception{
		LogicVariable a = LogicFactory.createLogicVariable("a");
		LogicVariable b = LogicFactory.createLogicVariable("b");
		LogicVariable c = LogicFactory.createLogicVariable("c");
		LogicVariable d = LogicFactory.createLogicVariable("d");
		LogicVariable e = LogicFactory.createLogicVariable("e");
		
		LogicExpression t12 = LogicFactory.createNegation(c);
		LogicExpression t1 = LogicFactory.createDisjunction(new LogicStruct[]{b,t12});
		LogicExpression t2 = LogicFactory.createImplication(d, b);
		LogicExpression t3 = LogicFactory.createEquivalence(a, e);
		return LogicFactory.createConjunction(new LogicStruct[]{a,t1,t2,t3});
	}
	
	public static LogicStruct create2() throws Exception{
		DiscourseDomain HLR = LogicFactory.createDiscourseDomain("HLR");
		DiscourseDomain LLR = LogicFactory.createDiscourseDomain("LLR");
		LogicFunctionTemplate trace_temp = LogicFactory.createLogicFunctionTemplate("traceable", new Variable[]{HLR.getIterator(),LLR.getIterator()});
		LogicFunction traceable = LogicFactory.createLogicFunction(trace_temp);
		
		traceable.setBody(new FunctionExecutor(){

			@Override
			public void execute() throws Exception {
				FunctionTemplate template = this.getFunction().getTemplate();
				Variable[] arguments = template.getArguments();
				Object hlr = arguments[0].read();
				Object llr = arguments[1].read();
				System.out.println(hlr+"-->"+llr);
			}});
		
		LogicExpression L = LogicFactory.createExistential(LLR, traceable);
		return LogicFactory.createUniversal(HLR, L);
	}

}
