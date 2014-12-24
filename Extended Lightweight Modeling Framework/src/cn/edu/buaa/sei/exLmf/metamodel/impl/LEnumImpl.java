package cn.edu.buaa.sei.exLmf.metamodel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public class LEnumImpl extends LDataTypeImpl implements LEnum{
	
	List<LEnumLiteral> literals = new ArrayList<LEnumLiteral>();
	Map<Integer,LEnumLiteral> id_literals = new HashMap<Integer,LEnumLiteral>();
	Map<String,LEnumLiteral> name_literals = new HashMap<String,LEnumLiteral>();
	
	public LEnumImpl(String name,LPackage container) throws Exception{super(name,container);}
	
	@Override
	public List<LEnumLiteral> getLiterals() {return this.literals;}
	/*
	 *	addLiteral(l):
	 *	1) this.containLiteral(l)==>do nothing
	 *	2) conflict with name/value of existing literals ==> exception
	 *	3) literal.container!=null ==> remove literal from former container.
	 */
	@Override
	public void addLiteral(LEnumLiteral literal) throws Exception {
		if(literal==null||this.literals.contains(literal))return;
		
		String name = literal.getLiteral();
		int value = literal.getValue();
		
		if(this.name_literals.containsKey(name)){
			throw this.getException("addLiteral(literal)", "literal", name+" conflict");
		}
		if(this.id_literals.containsKey(value)){
			throw this.getException("addLiteral(literal)", "literal", value+" conflict");
		}
		if(literal.getContainer()!=null&&literal.getContainer()!=this){
			LEnum ltype = (LEnum) literal.getContainer();
			ltype.removeLiteral(literal);
		}
		
		this.literals.add(literal);
		this.id_literals.put(value, literal);
		this.name_literals.put(name, literal);
		literal.setContainer(this);
	}
	@Override
	public LEnumLiteral getLiteralByValue(int value) throws Exception {
		if(!this.id_literals.containsKey(value)){
			throw this.getException("getLiteralByValue(value)", "value", value+" Undefined");
		}
		return this.id_literals.get(value);
	}
	@Override
	public LEnumLiteral getLiteralByName(String literal) throws Exception {
		if(!this.name_literals.containsKey(literal))
		{
			throw this.getException("getLiteralByName(literal)", "literal", literal+" Undefined");
		}
		return this.name_literals.get(literal);
	}
	@Override
	public void removeLiteral(LEnumLiteral literal) throws Exception {
		if(literal==null||!this.literals.contains(literal)){
			throw this.getException("removeLiteral(literal)", "literal", "Undefined");
		}
		
		this.literals.remove(literal);
		this.id_literals.remove(literal.getValue());
		this.name_literals.remove(literal.getLiteral());
	}

	@Override
	public Boolean containLiteral(LEnumLiteral literal) {
		return this.literals.contains(literal);
	}

}
