package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LDataObject{
 *		- type (LDataType)
 *		- value (Object) ==> Bool, Int, Long, String, Float, Double, Enum Literal
 *	} 
 */
public interface LDataObject extends LObject{
	public LDataType getType();
	
	public Object getValue();
	public void setValue(Object val) throws Exception;
	
	public Boolean boolVal() throws Exception;
	public Integer integerVal() throws Exception;
	public Long longVal() throws Exception;
	public Float floatVal() throws Exception;
	public Double doubleVal() throws Exception;
	public String stringVal() throws Exception;
	public LEnumLiteral literalVal() throws Exception;
	
	public void setBool(Boolean val) throws Exception;
	public void setInt(Integer val) throws Exception;
	public void setLong(Long val) throws Exception;
	public void setFloat(Float val) throws Exception;
	public void setDouble(Double val) throws Exception;
	public void setString(String val) throws Exception;
	public void setLiteral(LEnumLiteral literal) throws Exception;
}
