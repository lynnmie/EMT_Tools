package assigners;

import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import core.Assigner;

public class Coverage2FuncAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass Coverage = (LClass) model.getClassifierByName("Coverage");
		Set<LClassObject> coverages = db.getObjectGroup(Coverage).getObjects();
		
		GroupFunction func = (GroupFunction) top;
		func.getTemplate().getArguments()[0].assign(coverages);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> coverages = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				Set<LClassObject> t_functions = Tracer.Coverage2Func(coverages);
				
				SetGroup group = new SetGroup();
				for(LClassObject function:t_functions)
					group.add(function);
				
				this.getFunction().getTemplate().getOutput().assign(group);
			}});
	}
	
}
