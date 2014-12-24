package assigners;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.Assigner;
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

public class LLRToFuncAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		
		if(!(top instanceof GroupFunction))throw new Exception("GroupFunction is required in top!");
		
		LClass HLR = (LClass) model.getClassifierByName("LLRequirement");
		LClass Design = (LClass) model.getClassifierByName("Design");
		
		IObjectGroup dgroup = db.getObjectGroup(Design);
		final Set<LClassObject> designs = dgroup.getObjects();
		
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
				
				Set<LClassObject> tdesigns = traceableDesign(designs);
				System.out.println(tdesigns.size()+" designs have been implemented.");
				for(LClassObject hlr:hlrs){
					if(traceable(hlr,tdesigns))result.add(hlr);
				}
				this.getFunction().getTemplate().getOutput().assign(result);
			}});
	}
	
	protected static boolean traceable(LClassObject llr,Set<LClassObject> designs){
		if(llr==null)return false;
		
		try {
			LMultipleObject elements = (LMultipleObject) llr.get(llr.getType().getFeatureByName("designs"));
			if(elements==null||elements.getAllObjects().isEmpty()){
				LMultipleObject children =  (LMultipleObject) llr.get(llr.getType().getFeatureByName("children"));
				if(children!=null){
					Iterator<LObject> itor = children.getAllObjects().iterator();
					while(itor.hasNext()){
						LObject child = itor.next();
						if(child instanceof LClassObject){
							boolean res = traceable((LClassObject) child,designs);
							if(!res)return false;
						}
					}
					return true;
				}
				return false;
			}
			else{
				Collection<LObject> es = elements.getAllObjects();
				for(LObject e:es)
					if(designs.contains(e))return true;
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	protected static Set<LClassObject> traceableDesign(Set<LClassObject> designs){
		Set<LClassObject> results = new HashSet<LClassObject>();
		
		for(LClassObject design:designs)
			if(traceableDesign(design))
				results.add(design);
		
		return results;
	}
	protected static boolean traceableDesign(LClassObject design){
		if(design==null)return false;
		
		try {
			LMultipleObject llrs = (LMultipleObject) design.get(design.getType().getFeatureByName("functions"));
			if(llrs==null||llrs.getAllObjects().isEmpty()){
				LMultipleObject children =  (LMultipleObject) design.get(design.getType().getFeatureByName("children"));
				if(children!=null){
					Iterator<LObject> itor = children.getAllObjects().iterator();
					while(itor.hasNext()){
						LObject child = itor.next();
						if(child instanceof LClassObject){
							boolean res = traceableDesign((LClassObject) child);
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
