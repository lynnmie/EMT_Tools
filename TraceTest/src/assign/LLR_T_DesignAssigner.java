package assign;

import java.util.Collection;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class LLR_T_DesignAssigner implements Assigner{
	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass LLR = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> llrs = db.getObjectGroup(LLR).getObjects();
		
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Function tracedLLR = assigner.getFunctionByName(top, "tracedLLR", 0);
		tracedLLR.getTemplate().getArguments()[0].assign(llrs);
		
		tracedLLR.setBody(new FunctionExecutor(){
			@Override
			public void execute() throws Exception {
				@SuppressWarnings("unchecked")
				Set<LClassObject> llrs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				
				SetGroup res = new SetGroup();
				for(LClassObject llr:llrs){
					if(this.traceable(llr, "designs"))
						res.add(llr);
				}
				
				this.getFunction().getTemplate().getOutput().assign(res);
			}
			protected boolean traceable(LClassObject a,String ref) throws Exception{
				if(a==null)return false;
				
				LMultipleObject refs = (LMultipleObject) a.get(a.getType().getFeatureByName(ref));
				if(!refs.getAllObjects().isEmpty())return true;
				
				LMultipleObject children = (LMultipleObject) a.get(a.getType().getFeatureByName("children"));
				Collection<LObject> set = children.getAllObjects();
				for(LObject e:set){
					LClassObject child = (LClassObject) e;
					if(traceable(child,ref))return true;
				}
				return set.isEmpty();
				
			}});
	}

}
