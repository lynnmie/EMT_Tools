package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;
import cn.edu.buaa.sei.SVI.struct.numeric.ZIntegerVariable;

public class ZIntegerVariableImpl extends TypedVariableImpl implements ZIntegerVariable{

	protected ZIntegerVariableImpl(String name) throws Exception {super(name, Number.class);}

	@Override
	public void assign(Number val) throws Exception {
		if(val==null)this.val=null;
		else{
			long t = val.longValue();
			this.val = t;
		}
		/*else if(val instanceof Integer){
			int x = (Integer) val;
			long y = x;
			this.assign(y);
		}
		else if(!(val instanceof Long))
			throw new Exception("Long value required");
		else{
			Long t = (Long) val;
			this.assign(t);
		}*/
	}

	@Override
	public void assign(Long val) throws Exception {
		this.val=val;
	}
	
	@Override
	public Long read() throws Exception {
		if(this.val==null)return null;
		else{
			Number val = (Number) this.val;
			return val.longValue();
		}
	}

}
