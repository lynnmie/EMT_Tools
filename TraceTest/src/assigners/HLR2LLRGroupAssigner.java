package assigners;

import java.util.Collection;
import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class HLR2LLRGroupAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass HLR = (LClass) model.getClassifierByName("HLRequirement");
		final LClass LLR = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> hlrs =  db.getObjectGroup(HLR).getObjects();		
		final IObjectGroup llr_group = db.getObjectGroup(LLR);
		
		GroupFunction func = (GroupFunction) top;
		func.getTemplate().getArguments()[0].assign(hlrs);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> hlrs =  (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				SetGroup err = new SetGroup();
				
				for(LClassObject hlr:hlrs){
					String hid = ((LDataObject)hlr.get(hlr.getType().getFeatureByName("id"))).stringVal();
					
					LMultipleObject llrs_p = (LMultipleObject) hlr.get(hlr.getType().getFeatureByName("llrs"));
					Collection<LObject> set = llrs_p.getAllObjects();
					for(LObject obj:set){
						LClassObject llr = (LClassObject) obj;
						LDataObject lid = (LDataObject) llr.get(llr.getType().getFeatureByName("id"));
						String id = lid.stringVal();
						/*if(id!=null&&id.indexOf("-")>0){
							id = id.substring(0, id.lastIndexOf("-")).trim();
							if(llr_group.isRegistered(id))
								llr = llr_group.get(id);
						}*/
						
						if(llr.getType()!=LLR)System.out.println("In "+hid+": llr is not LLR!!!");
						if(!llr_group.isTagged(llr))System.out.println("#######: ["+hid+": "+id+"]");
					}
				}
				
				this.getFunction().getTemplate().getOutput().assign(err);
			}
			
		});
	}

}
