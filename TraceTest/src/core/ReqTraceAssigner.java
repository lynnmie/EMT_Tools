package core;

import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;

public class ReqTraceAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		if(model==null||db==null||top==null)throw new Exception("Null model|data|top is invalid");
		
		LClass HLRequirement = (LClass) model.getClassifierByName("HLRequirement");
		LClass LLRequirement = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> hlrs = db.getObjectGroup(HLRequirement).getObjects();
		Set<LClassObject> llrs = db.getObjectGroup(LLRequirement).getObjects();
		
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		assigner.assignByName(top, "HLR", hlrs, 0);
		assigner.assignByName(top, "LLR", llrs, 0);
		
		Function trace = assigner.getFunctionByName(top, "traceable", 0);
		assigner.assignFunction(trace, new FunctionExecutor(){
			@Override
			public void execute() throws Exception {
				LogicFunctionTemplate template = (LogicFunctionTemplate) this.getFunction().getTemplate();
				Variable hlr = template.getArguments()[0];
				Variable llr = template.getArguments()[1];
				LClassObject hobj = (LClassObject) hlr.read();
				LClassObject lobj = (LClassObject) llr.read();
				if(hobj==null||lobj==null)throw new Exception("Invalid arguments in traceable(hlr,llr)");
				
				LMultipleObject llrs = (LMultipleObject) hobj.get(hobj.getType().getFeatureByName("llrs"));
				Boolean res = llrs.containObject(lobj);
				template.getOutput().assign(res);
			}});
	}

}
