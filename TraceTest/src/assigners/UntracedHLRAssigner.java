package assigners;

import java.util.Collection;
import java.util.Set;

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

public class UntracedHLRAssigner implements Assigner{
	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		
		LClass HLR = (LClass) model.getClassifierByName("HLRequirement");
		Set<LClassObject> hlrs =  db.getObjectGroup(HLR).getObjects();
		
		/*IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Variable hlr_domain = assigner.getVariableByName(top, "HLR", 0);
		hlr_domain.assign(hlrs);*/
		
		Function test = (Function) top;
		test.getTemplate().getArguments()[0].assign(hlrs);
		test.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> hlrs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				
				SetGroup res = new SetGroup();
				for(LClassObject hlr:hlrs){
					if(!traceable(hlr,"llrs"))res.add(hlr);
						
				}
				this.getFunction().getTemplate().getOutput().assign(res);
				/*LClassObject hlr = (LClassObject) this.getFunction().getTemplate().getArguments()[0].read();
				
				boolean res = this.traceable(hlr, "llrs");
				this.getFunction().getTemplate().getOutput().assign(res);
				
				LDataObject id = (LDataObject) hlr.get(hlr.getType().getFeatureByName("id"));
				if(!res)System.out.println(id.stringVal()+": "+res);*/
			}
			protected boolean traceable(LClassObject a,String ref) throws Exception{
				if(a==null)return false;
				
				LClassObject cur = a;
				while(cur!=null){
					LMultipleObject refs = (LMultipleObject) cur.get(cur.getType().getFeatureByName(ref));
					if(!refs.getAllObjects().isEmpty())return true;
					//if(cur.get(cur.getType().getFeatureByName("parent"))==null)break;
					cur = (LClassObject) cur.get(cur.getType().getFeatureByName("parent"));
				}
				
				LMultipleObject cset = (LMultipleObject) a.get(a.getType().getFeatureByName("children"));
				Collection<LObject> children = cset.getAllObjects();
				if(children.isEmpty())return false;
				for(LObject e:children){
					LClassObject child = (LClassObject) e;
					if(this.traceable(child, ref))return false;
				}
				return false;
			}
		});
		
	}
}
