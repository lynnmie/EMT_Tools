package assigners;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class UnsatisfiedLLRAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Function unsatisfied = assigner.getFunctionByName(top, "unsatisfiedLLR", 0);
		
		LClass CTF = (LClass) model.getClassifierByName("ConfigTestFile");
		Set<LClassObject> ctfs = db.getObjectGroup(CTF).getObjects();
		
		unsatisfied.getTemplate().getArguments()[0].assign(ctfs);
		
		
		unsatisfied.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> ctfs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				
				Set<LClassObject> llrs = new HashSet<LClassObject>();
				for(LClassObject ctf:ctfs){
					LMultipleObject testcases = (LMultipleObject) ctf.get(ctf.getType().getFeatureByName("testcases"));
					if(testcases.getAllObjects().isEmpty())continue;
					
					Collection<LObject> tcs = testcases.getAllObjects();
					boolean isFail = false;
					for(LObject ttc:tcs){
						LClassObject tc = (LClassObject) ttc;
						LDataObject fail = (LDataObject) tc.get(tc.getType().getFeatureByName("fail"));
						if(fail!=null&&fail.integerVal()!=null&&fail.integerVal()>0){
							isFail=true;break;
						}
					}
					
					LClassObject llr = (LClassObject) ctf.get(ctf.getType().getFeatureByName("llr"));
					if(llr==null)continue;
					
					if(isFail)
						llrs.add(llr);
				}
				//llrs = this.adjust(llrs);
				
				SetGroup group = new SetGroup();
				for(LClassObject llr:llrs)group.add(llr);
				
				System.out.println("Unsatisfied: "+llrs.size());
				this.getFunction().getTemplate().getOutput().assign(group);
			}
		});
	}

}
