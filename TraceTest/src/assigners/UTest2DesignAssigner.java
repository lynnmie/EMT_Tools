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

public class UTest2DesignAssigner implements Assigner{

	@Override
	public void assign(LPackage model, IObjectWorld db, Struct top)
			throws Exception {
		LClass UTC = (LClass) model.getClassifierByName("UnitTestCase");
		LClass Design = (LClass) model.getClassifierByName("Design");
		Set<LClassObject> utcs = db.getObjectGroup(UTC).getObjects();
		final Set<LClassObject> designs = db.getObjectGroup(Design).getObjects();
		
		GroupFunction func = (GroupFunction) top;
		func.getTemplate().getArguments()[0].assign(utcs);
		
		func.setBody(new FunctionExecutor(){
			@SuppressWarnings("unchecked")
			@Override
			public void execute() throws Exception {
				Set<LClassObject> utcs = (Set<LClassObject>) this.getFunction().getTemplate().getArguments()[0].read();
				Set<LClassObject> t_functions = Tracer.UTestCase2Func(utcs);
				Set<LClassObject> t_designs = Tracer.Function2Design(t_functions, designs);
				
				SetGroup group = new SetGroup();
				for(LClassObject function:t_designs)
					group.add(function);
				
				this.getFunction().getTemplate().getOutput().assign(group);
			}});
	}
	

}
