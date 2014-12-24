package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;
import cn.edu.buaa.sei.SVI.struct.numeric.RealVariable;

public class RealVariableImpl extends TypedVariableImpl implements RealVariable{

	protected RealVariableImpl(String name) throws Exception {super(name, Number.class);}

	@Override
	public void assign(Number val) throws Exception {
		if(val==null)this.val=null;
		else{
			double t = val.doubleValue();
			this.val = t;
		}
		/*else if(val instanceof Float){
			float x = (Float) val;
			Double y = (double) x;
			this.assign(y);
		}
		else if(val instanceof Integer){
			int x = (Integer) val;
			Double y = (double) x;
			this.assign(y);
		}
		else if(val instanceof Long){
			long x = (Long) val;
			Double y = (double) x;
			this.assign(y);
		}
		else if(val instanceof Double)this.val=val;
		else throw new Exception("Double value required");*/
	}

	@Override
	public void assign(Double val) throws Exception {
		this.val=val;
	}
	
	@Override
	public Double read() throws Exception {
		if(val==null)return null;
		else{
			Number val = (Number) this.val;
			return val.doubleValue();
		}
	}
	
}
