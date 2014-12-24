package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;

public class LEnumLiteralImpl extends LStructuralFeatureImpl implements LEnumLiteral{

	int value;
	String literal;
	
	public LEnumLiteralImpl(int fid, String name, LClassifier container) throws Exception {super(fid, name, container);}
	
	@Override
	public int getValue() {return this.value;}
	@Override
	public void setValue(int value) {this.value=value;}
	@Override
	public String getLiteral() {return this.literal;}
	@Override
	public void setLiteral(String literal) {this.literal=literal;}

	// No used functions would cause exceptions.
	@Override
	public void setOrdered(boolean ordered) {
		try {
			throw this.getException("setOrdered(ordered)", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void setUnique(boolean unique) {
		try {
			throw this.getException("setUnique(unique)", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void setUpperBound(int upperBound) {
		try {
			throw this.getException("setUpperBound(up)", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void setLowerBound(int lowerBound) {
		try {
			throw this.getException("setLowerBound(lower)", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Boolean isOrdered() {
		try {
			throw this.getException("isOrdered()", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Boolean isUnique() {
		try {
			throw this.getException("setUnique()", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getUpperBound() {
		try {
			throw this.getException("getUpperBound()", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int getLowerBound() {
		try {
			throw this.getException("getLowerBound()", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Boolean isChangable() {return false;}
	@Override
	public void setChangable(boolean changable) {}
	@Override
	public LObject getDefaultValue() {
		try {
			throw this.getException("getDefaultValue()", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void setDefaultValue(LObject value) throws Exception {
		throw this.getException("setDefaultValue(value)", "", "the function cannot be assessed in enum literal");
	}

	@Override
	public LClassifier getType() {
		try {
			throw this.getException("getType()", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// Would be rewritten in sub class
	@Override
	public void setType(LClassifier type) {
		try {
			throw this.getException("setType(type)", "", "the function cannot be assessed in enum literal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Boolean isRequired() {
		try {
			throw this.getException("isRequired()", "", "cannot be accessed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void setRequired(Boolean required) {
		try {
			throw this.getException("setRequired(required)", "", "cannot be accessed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
