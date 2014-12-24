package assigners;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class CTestFile2LLRAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass CTF = (LClass) model.getClassifierByName("ConfigTestFile");
		Set<LClassObject> ctfs = db.getObjectGroup(CTF).getObjects();
		
		GroupFunction func = (GroupFunction) top;
		func.getTemplate().getArguments()[0].assign(ctfs);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> ctfs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				Set<LClassObject> llrs = Tracer.CTF2LLR(ctfs);
				llrs = this.adjust(llrs);
				
				SetGroup group = new SetGroup();
				for(LClassObject llr:llrs)group.add(llr);
				
				this.getFunction().getTemplate().getOutput().assign(group);
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
