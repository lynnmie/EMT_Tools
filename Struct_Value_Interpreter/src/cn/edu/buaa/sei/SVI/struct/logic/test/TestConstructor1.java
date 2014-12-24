package cn.edu.buaa.sei.SVI.struct.logic.test;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class TestConstructor1 implements Constructor{

	@Override
	public Struct create() throws Exception {
		DiscourseDomain HLR = LogicFactory.createDiscourseDomain("HLR");
		DiscourseDomain LLR = LogicFactory.createDiscourseDomain("LLR");
		
		LogicFunctionTemplate template = LogicFactory.createLogicFunctionTemplate(
					"traceable", new Variable[]{HLR.getIterator(),LLR.getIterator()});
		LogicFunction traceable = LogicFactory.createLogicFunction(template);
		traceable.setBody(new FunctionExecutor(){
			@Override
			public void execute() throws Exception {
				this.getFunction().getTemplate().getOutput().assign(true);
			}});
		
		LogicExpression L = LogicFactory.createExistential(LLR, traceable);
		LogicExpression P = LogicFactory.createUniversal(HLR, L);
		
		return P;
	}

}
