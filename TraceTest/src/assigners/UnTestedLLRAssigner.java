package assigners;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class UnTestedLLRAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		Variable LLR = assigner.getVariableByName(top, "LLR", 0);
		Function CTest = assigner.getFunctionByName(top, "CTest", 0);
		Function UTest = assigner.getFunctionByName(top, "UTest", 0);
		
		LClass LLRequirement = (LClass) model.getClassifierByName("LLRequirement");
		final Set<LClassObject> llrs = db.getObjectGroup(LLRequirement).getObjects();
		LClass CTF = (LClass) model.getClassifierByName("ConfigTestFile");
		Set<LClassObject> ctfs = db.getObjectGroup(CTF).getObjects();
		LClass UTC = (LClass) model.getClassifierByName("UnitTestCase");
		Set<LClassObject> utcs = db.getObjectGroup(UTC).getObjects();
		
		LClass Design = (LClass) model.getClassifierByName("Design");
		final Set<LClassObject> designs = db.getObjectGroup(Design).getObjects();
		
		SetGroup group = new SetGroup();
		for(LClassObject llr:llrs)
			group.add(llr);
		LLR.assign(group);
		
		CTest.getTemplate().getArguments()[0].assign(ctfs);
		UTest.getTemplate().getArguments()[0].assign(utcs);
		
		CTest.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> ctfs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				//Set<LClassObject> llrs = Tracer.CTF2LLR(ctfs);
				
				Set<LClassObject> llrs = new HashSet<LClassObject>();
				for(LClassObject ctf:ctfs){
					LMultipleObject testcases = (LMultipleObject) ctf.get(ctf.getType().getFeatureByName("testcases"));
					if(testcases.getAllObjects().isEmpty())continue;
					
					LClassObject llr = (LClassObject) ctf.get(ctf.getType().getFeatureByName("llr"));
					if(llr==null)continue;
					llrs.add(llr);
				}
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
					
					//int lev = 0;
					while(!queue.isEmpty()){
						//if(lev>4)break;
						LClassObject cur = queue.poll();
						res.add((LClassObject) cur);
						
						LMultipleObject children = (LMultipleObject) cur.get(cur.getType().getFeatureByName("children"));
						Collection<LObject> cset = children.getAllObjects();
						for(LObject child:cset)
							queue.add((LClassObject) child);
						//lev++;
					}
					
					queue.clear();
				}
				
				return res;
			}
		});
	
		UTest.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> utcs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				Set<LClassObject> t_functions = Tracer.UTestCase2Func(utcs);
				Set<LClassObject> t_designs = Tracer.Function2Design(t_functions, designs);
				Set<LClassObject> t_llrs = Tracer.Design2LLR(t_designs, llrs);
				t_llrs = this.adjust(t_llrs);
				
				SetGroup group = new SetGroup();
				for(LClassObject llr:t_llrs)group.add(llr);
				
				this.getFunction().getTemplate().getOutput().assign(group);
			}
			protected Set<LClassObject> adjust(Set<LClassObject> llrs) throws Exception{
				Set<LClassObject> res = new HashSet<LClassObject>();
				
				Queue<LClassObject> queue = new LinkedList<LClassObject>();
				for(LClassObject llr:llrs){
					queue.clear();
					queue.add(llr);
					
					//int lev = 0;
					while(!queue.isEmpty()){
						//if(lev>4)break;
						LClassObject cur = queue.poll();
						res.add((LClassObject) cur);
						
						LMultipleObject children = (LMultipleObject) cur.get(cur.getType().getFeatureByName("children"));
						Collection<LObject> cset = children.getAllObjects();
						for(LObject child:cset)
							queue.add((LClassObject) child);
						//lev++;
					}
					
					queue.clear();
				}
				
				return res;
			}});
	}
	
}
