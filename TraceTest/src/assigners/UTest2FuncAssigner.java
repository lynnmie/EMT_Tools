package assigners;

import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class UTest2FuncAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		
		if(!(top instanceof GroupFunction)){
			throw new Exception("GroupFunction required!");
		}
		LClass UnitTestCase = (LClass) model.getClassifierByName("UnitTestCase");
		if(!db.containModelClass(UnitTestCase)){
			throw new Exception(UnitTestCase.getAbsolutePath()+" has not been loaeded into model");
		}
		IObjectGroup utc_grp = db.getObjectGroup(UnitTestCase);
		Set<LClassObject> utcs = utc_grp.getObjects();
		
		GroupFunction func = (GroupFunction) top;
		func.getTemplate().getArguments()[0].assign(utcs);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> uts = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				SetGroup group = new SetGroup();
				
				if(uts!=null)
					for(LClassObject ut:uts){
						LClassObject target = (LClassObject) ut.get(ut.getType().getFeatureByName("target"));
						LObject result = ut.get(ut.getType().getFeatureByName("result"));
						if(target!=null&&result!=null)
							group.add(target);
					}
				
				this.getFunction().getTemplate().getOutput().assign(group);
			}});
	}

}
