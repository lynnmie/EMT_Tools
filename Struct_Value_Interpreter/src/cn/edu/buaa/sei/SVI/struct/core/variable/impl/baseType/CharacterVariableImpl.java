package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.CharacterVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class CharacterVariableImpl extends TypedVariableImpl implements CharacterVariable{

	public CharacterVariableImpl(String name)
			throws Exception {super(name, Character.class);}

	@Override
	public void assign(Character val) throws Exception {this.val=val;}
	@Override
	public Character read() throws Exception {
		if(this.val==null)return null;
		return (Character) this.val;
	}

}
