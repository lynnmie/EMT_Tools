package core;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;

public interface Assigner {
	public void assign(LPackage model,IObjectWorld db,Struct top) throws Exception;
}
