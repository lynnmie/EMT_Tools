package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import java.util.Map;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.MapVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class MapVariableImpl extends TypedVariableImpl implements MapVariable{

	public MapVariableImpl(String name) throws Exception {super(name, Map.class);}

	@SuppressWarnings("rawtypes")
	@Override
	public void assign(Map val) throws Exception {this.val=val;}
	@SuppressWarnings("rawtypes")
	@Override
	public Map read() throws Exception {
		if(this.val==null)return null;
		else return (Map) this.val;
	}

}
