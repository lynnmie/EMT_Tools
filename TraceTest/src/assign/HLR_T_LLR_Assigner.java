package assign;

import java.util.Collection;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class HLR_T_LLR_Assigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass HLR = (LClass) model.getClassifierByName("HLRequirement");
		final LClass LLR = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> hlrs =  db.getObjectGroup(HLR).getObjects();
		Set<LClassObject> llrs =  db.getObjectGroup(LLR).getObjects();
		
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Variable hlr_domain = assigner.getVariableByName(top, "HLR", 0);
		Variable llr_domain = assigner.getVariableByName(top, "LLR", 0);
		hlr_domain.assign(hlrs); llr_domain.assign(llrs);
		
		Function traceable = assigner.getFunctionByName(top, "traceable", 0);
		traceable.setBody(new FunctionExecutor(){
			@Override
			public void execute() throws Exception {
				LClassObject hlr = (LClassObject) this.getFunction().getTemplate().getArguments()[0].read();
				LClassObject llr = (LClassObject) this.getFunction().getTemplate().getArguments()[1].read();
				
				boolean res = this.traceable(hlr, llr, "llrs");
				this.getFunction().getTemplate().getOutput().assign(res);
				
				LDataObject id = (LDataObject) hlr.get(hlr.getType().getFeatureByName("id"));
				if(!res)System.out.println(id.stringVal()+": "+res);
			}
			protected boolean traceable(LClassObject a,LClassObject b,String ref) throws Exception{
				if(a==null||b==null)return false;
				
				LClassObject cur = a;
				while(cur!=null){
					LMultipleObject llrs = (LMultipleObject) cur.get(cur.getType().getFeatureByName(ref));
					if(llrs.getAllObjects().contains(b))return true;
					cur = (LClassObject) cur.get(cur.getType().getFeatureByName("parent"));
				}
				
				LMultipleObject children = (LMultipleObject) a.get(a.getType().getFeatureByName("children"));
				Collection<LObject> set = children.getAllObjects();
				for(LObject e:set){
					LClassObject child = (LClassObject) e;
					if(traceable(child,b,ref))return true;
				}
				return set.isEmpty();
				
			}
		});
	}
}
