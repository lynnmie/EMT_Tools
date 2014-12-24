package assign;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class UN_CTestLLRAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass CTF = (LClass) model.getClassifierByName("ConfigTestFile");
		Set<LClassObject> ctfs = db.getObjectGroup(CTF).getObjects();
		LClass LLR = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> llrs = db.getObjectGroup(LLR).getObjects();
		
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Variable llr_var = assigner.getVariableByName(top, "LLR", 0);
		
		SetGroup llrGroup = new SetGroup();
		for(LClassObject llr:llrs)
			llrGroup.add(llr);
		llr_var.assign(llrGroup);
		
		GroupFunction f = (GroupFunction) assigner.getFunctionByName(top, "testedLLR", 0);
		f.getTemplate().getArguments()[0].assign(ctfs);
		f.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> ctfs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				
				Set<LClassObject> llrs = new HashSet<LClassObject>();
				for(LClassObject ctf:ctfs){
					LMultipleObject testcases = (LMultipleObject) ctf.get(ctf.getType().getFeatureByName("testcases"));
					if(testcases.getAllObjects().isEmpty())continue;
					
					LClassObject target = (LClassObject) ctf.get(ctf.getType().getFeatureByName("llr"));
					if(target==null)continue;
					llrs.add(target);
				}
				
				llrs = this.adjust(llrs);
				
				SetGroup ans = new SetGroup();
				for(LClassObject llr:llrs)
					ans.add(llr);
				
				this.getFunction().getTemplate().getOutput().assign(ans);
			}
			protected Set<LClassObject> adjust(Set<LClassObject> llrs) throws Exception{
				Set<LClassObject> res = new HashSet<LClassObject>();
				
				Queue<LClassObject> queue = new LinkedList<LClassObject>();
				for(LClassObject llr:llrs){
					queue.clear();
					queue.add(llr);
					while(!queue.isEmpty()){
						LClassObject cur = queue.poll();
						res.add((LClassObject) cur);
						LMultipleObject children = (LMultipleObject) cur.get(cur.getType().getFeatureByName("children"));
						Collection<LObject> cset = children.getAllObjects();
						for(LObject child:cset)
							queue.add((LClassObject) child);
					}
				}
				return res;
			}
		});
	}
}
