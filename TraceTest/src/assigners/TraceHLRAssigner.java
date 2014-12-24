package assigners;
import java.util.Iterator;
import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class TraceHLRAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		
		if(!(top instanceof GroupFunction))throw new Exception("GroupFunction is required in top!");
		
		LClass HLR = (LClass) model.getClassifierByName("HLRequirement");
		IObjectGroup group = db.getObjectGroup(HLR);
		Set<LClassObject> hlrs = group.getObjects();
		
		GroupFunction func = (GroupFunction) top;
		func.getTemplate().getArguments()[0].assign(hlrs);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> hlrs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				Group result = new SetGroup();
				for(LClassObject hlr:hlrs){
					if(traceable(hlr))result.add(hlr);
				}
				this.getFunction().getTemplate().getOutput().assign(result);
			}});
	}
	
	protected static boolean traceable(LClassObject hlr){
		if(hlr==null)return false;
		
		try {
			LMultipleObject llrs = (LMultipleObject) hlr.get(hlr.getType().getFeatureByName("llrs"));
			if(llrs==null||llrs.getAllObjects().isEmpty()){
				LMultipleObject children =  (LMultipleObject) hlr.get(hlr.getType().getFeatureByName("children"));
				if(children!=null){
					Iterator<LObject> itor = children.getAllObjects().iterator();
					while(itor.hasNext()){
						LObject child = itor.next();
						if(child instanceof LClassObject){
							boolean res = traceable((LClassObject) child);
							if(!res)return false;
						}
					}
					return true;
				}
				return false;
			}
			else return true;
		} catch (Exception e) {
			return false;
		}
	}

}
