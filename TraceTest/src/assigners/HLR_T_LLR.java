package assigners;

import java.util.Collection;
import java.util.Iterator;
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

public class HLR_T_LLR implements Assigner{
	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		
		LClass HLR = (LClass) model.getClassifierByName("HLRequirement");
		Set<LClassObject> hlrs =  db.getObjectGroup(HLR).getObjects();
		
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Variable hlr_domain = assigner.getVariableByName(top, "HLR", 0);
		hlr_domain.assign(hlrs);
		
		Function traceable = assigner.getFunctionByName(top, "traceable", 0);
		traceable.setBody(new FunctionExecutor(){
			@Override
			public void execute() throws Exception {
				LClassObject hlr = (LClassObject) this.getFunction().getTemplate().getArguments()[0].read();
				
				boolean res = this.traceable(hlr, "llrs");
				this.getFunction().getTemplate().getOutput().assign(res);
				
				LDataObject id = (LDataObject) hlr.get(hlr.getType().getFeatureByName("id"));
				if(!res)System.out.println(id.stringVal()+": "+res);
			}
			protected boolean traceable(LClassObject a,String ref) throws Exception{
				if(a==null)return false;
				
				LMultipleObject llrs = (LMultipleObject) a.get(a.getType().getFeatureByName(ref));
				if(llrs==null||llrs.getAllObjects().isEmpty()){
					LMultipleObject children =  (LMultipleObject) a.get(a.getType().getFeatureByName("children"));
					if(children!=null){
						Iterator<LObject> itor = children.getAllObjects().iterator();
						while(itor.hasNext()){
							LObject child = itor.next();
							if(child instanceof LClassObject){
								boolean res = traceable((LClassObject) child,ref);
								if(!res)return false;
							}
						}
						return true;
					}
					return false;
				}
				else return true;
			}
		});
		
	}

	public void back(LPackage model, IObjectWorld db, Struct top) throws Exception{
		LClass HLR = (LClass) model.getClassifierByName("HLRequirement");
		final LClass LLR = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> hlrs =  db.getObjectGroup(HLR).getObjects();
		Set<LClassObject> llrs =  db.getObjectGroup(LLR).getObjects();
		
		//final IObjectGroup llr_group = db.getObjectGroup(LLR);
		
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
				
				
				/*LDataObject lid = (LDataObject) b.get(b.getType().getFeatureByName("id"));
				String id = lid.stringVal();
				if(id!=null&&id.indexOf("-")>0){
					id = id.substring(0, id.lastIndexOf("-")).trim();
					if(llr_group.isRegistered(id))
						b = llr_group.get(id);
				}*/
				
				/*if(b.getType()!=LLR)System.out.println("Not a LLR!!!!!!!!!!!!!!!!!!!!!!!!");
				if(!llr_group.isTagged(b))System.out.println("#######: "+id);*/
				
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
				return false;
				
			}
		});
	}
}
