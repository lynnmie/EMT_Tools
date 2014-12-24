package cn.edu.buaa.sei.exLmf.metamodel.impl;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;

public class LDataObjectImpl extends LObjectImpl implements LDataObject{
	Object val;
	
	/* Control the input type from constructor */
	public LDataObjectImpl(LDataType type) {
		super(type);
	}

	@Override
	public LDataType getType() {return (LDataType) this.type;}
	
	@Override
	public Boolean boolVal() throws Exception {
		if(this.type==LPrimitiveTypeImpl.BOOL)
			return (Boolean) this.val;
		else{
			throw this.getException("boolVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as Boolean");
		}
	}
	@Override
	public Integer integerVal() throws Exception {
		if(this.type==LPrimitiveTypeImpl.INT)
			return (Integer) this.val;
		else{
			throw this.getException("integerVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as Integer");
		}
	}
	@Override
	public Long longVal() throws Exception {
		if(this.type==LPrimitiveTypeImpl.LONG)
			return (Long) this.val;
		else{
			throw this.getException("longVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as Long");
		}
	}
	@Override
	public Float floatVal() throws Exception {
		if(this.type==LPrimitiveTypeImpl.FLOAT)
			return (Float) this.val;
		else{
			throw this.getException("floatVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as Float");
		}
	}
	@Override
	public Double doubleVal() throws Exception {
		if(this.type==LPrimitiveTypeImpl.DOUBLE)
			return (Double) this.val;
		else{
			throw this.getException("doubleVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as Double");
		}
	}
	@Override
	public String stringVal() throws Exception {
		if(this.type==LPrimitiveTypeImpl.STRING)
			return (String) this.val;
		else{
			throw this.getException("stringVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as String");
		}
	}
	@Override
	public LEnumLiteral literalVal() throws Exception {
		if(this.type instanceof LEnum)
			return (LEnumLiteral) this.val;
		else{
			throw this.getException("literalVal()", "type",
						"Try to get instance type <"+this.type.getName()+"> as Enum");
		}
	}

	/*
	 *	General Setter/Getter
	 *	setValue(Object):
	 *		- null: just set null and return, we permit to set null to a data object so that it present Null Data Object
	 *		- class: must be <Boolean, Integer, Long, Float, Double, String> or <LEnumLiteral> 
	 */
	@Override
	public Object getValue() {
		return this.val;
	}
	@Override
	public void setValue(Object val) throws Exception {
		if(val==null){
			this.val=null;
			return;
		}
		
		if(val instanceof Boolean)
			this.setBool((Boolean) val);
		else if(val instanceof Integer)
			this.setInt((Integer) val);
		else if(val instanceof Long)
			this.setLong((Long) val);
		else if(val instanceof Float)
			this.setFloat((Float) val);
		else if(val instanceof Double)
			this.setDouble((Double) val);
		else if(val instanceof String)
			this.setString((String) val);
		else if(val instanceof LEnumLiteral)
			this.setLiteral((LEnumLiteral) val);
		else{
			throw this.getException("setValue(val)", "val", 
						val.getClass().getName()+" is not primitive input data type.");
		}
	}

	// Setter has type checking.
	@Override
	public void setBool(Boolean val) throws Exception {
		if(this.type==LPrimitiveTypeImpl.BOOL)
			this.val=val;
		else{
			throw this.getException("setBool(val)", "this.val", this.type.getName() + " does not match!");
		}
	}
	@Override
	public void setInt(Integer val) throws Exception {
		if(this.type==LPrimitiveTypeImpl.INT)
			this.val=val;
		else{
			throw this.getException("setInt(val)", "this.val", this.type.getName() + " does not match!");
		}
	}
	@Override
	public void setLong(Long val) throws Exception {
		if(this.type==LPrimitiveTypeImpl.LONG)
			this.val=val;
		else{
			throw this.getException("setLong(val)", "this.val", this.type.getName() + " does not match!");
		}
	}
	@Override
	public void setFloat(Float val) throws Exception {
		if(this.type==LPrimitiveTypeImpl.FLOAT)
			this.val=val;
		else{
			throw this.getException("setFloat(val)", "this.val", this.type.getName() + " does not match!");
		}
	}
	@Override
	public void setDouble(Double val) throws Exception {
		if(this.type==LPrimitiveTypeImpl.DOUBLE)
			this.val=val;
		else{
			throw this.getException("setDouble(val)", "this.val", this.type.getName() + " does not match!");
		}
	}
	@Override
	public void setString(String val) throws Exception {
		if(this.type==LPrimitiveTypeImpl.STRING)
			this.val=val;
		else{
			throw this.getException("setString(val)", "this.val", this.type.getName() + " does not match!");
		}
	}
	// LEnumLiteral must be one element in the enumeration type.
	@Override
	public void setLiteral(LEnumLiteral literal) throws Exception {
		if(literal==null){
			this.val=null;
			return;
		}
		
		if(this.type instanceof LEnum){
			if(((LEnum) type).containLiteral(literal))
				this.val=literal;
			else{
				throw this.getException("setLiteral(val)", "this.val", 
							literal.getName()+" is not defined in Enumeration <"+type.getName()+">");
			}
		}
		else{
			throw this.getException("setLiteral(val)", "this.val", this.type.getName() + " is not Enumeration!");
		}
	}
}
