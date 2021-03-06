package assign;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class MCDCCoverageAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass Coverage = (LClass) model.getClassifierByName("Coverage");
		//LClass Function = (LClass) model.getClassifierByName("Function");
		Set<LClassObject> coverages = db.getObjectGroup(Coverage).getObjects();
		Set<LClassObject> functions = Tracer.Coverage2Func(coverages);
		
		NumericFunction func = (NumericFunction) top;
		func.getTemplate().getArguments()[0].assign(functions);
		func.getTemplate().getArguments()[1].assign(coverages);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> coverages = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[1].read();
				Set<LClassObject> functions = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				
				/*Map<LClassObject,Double> stmt_map = new HashMap<LClassObject,Double>();
				Map<LClassObject,Integer> count_map = new HashMap<LClassObject,Integer>();*/
				Map<LClassObject,Integer> stmt_map = new HashMap<LClassObject,Integer>();
				
				for(LClassObject cov:coverages){
					LDataObject stmt = (LDataObject) cov.get(cov.getType().getFeatureByName("mcdc"));
					Integer s = stmt.integerVal();
					LClassObject target = (LClassObject) cov.get(cov.getType().getFeatureByName("target"));
					if(target==null)continue;
					if(s==null||s<0)continue;
					
					if(!stmt_map.containsKey(target))stmt_map.put(target, 0);
					
					if(stmt_map.get(target)>=s)continue;
					stmt_map.put(target, stmt_map.get(target)+s);
				}
				
				double stmts = 0;
				for(LClassObject function:functions){
					stmts += stmt_map.get(function);
				}
				
				Map<LClassObject,Integer> tested_map = new HashMap<LClassObject,Integer>();
				for(LClassObject cov:coverages){
					LClassObject target = (LClassObject) cov.get(cov.getType().getFeatureByName("target"));
					if(!tested_map.containsKey(target))tested_map.put(target, 0);
					LDataObject ts = (LDataObject) cov.get(cov.getType().getFeatureByName("tested_mcdc"));
					if(ts==null||ts.integerVal()==null||ts.integerVal()<tested_map.get(target))continue;
					tested_map.put(target, ts.integerVal());
				}
				
				int tested = 0;
				for(LClassObject function:functions){
					tested+=tested_map.get(function);
				}
				
				System.out.println("mcdcs: "+((int)stmts)+"\t\t tested: "+tested);
				Double ans = ((double)tested)/((double)stmts);
				if(ans>=1.0)ans=1.0;
				
				this.getFunction().getTemplate().getOutput().assign(ans);
			}});
	}

}
