package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPrimitiveType;

public class LPrimitiveTypeImpl extends LDataTypeImpl implements LPrimitiveType{
	public static LPrimitiveType INT=null,LONG=null,FLOAT=null,DOUBLE=null,BOOL=null,STRING=null; 
	
	public static final String INT_NAME = "integer";
	public static final String LONG_NAME = "long";
	public static final String STRING_NAME = "String";
	public static final String FLOAT_NAME = "float";
	public static final String DOUBLE_NAME = "double";
	public static final String BOOL_NAME = "boolean";
	
	public static final int INT_ID = 0;
	public static final int LONG_ID = 1;
	public static final int STRING_ID = 2;
	public static final int  FLOAT_ID = 3;
	public static final int DOUBLE_ID = 4;
	public static final int BOOL_ID = 5;
	
	public static final String INT_INS = Integer.class.getName();
	public static final String LONG_INS = Long.class.getName();
	public static final String FLOAT_INS = Float.class.getName();
	public static final String DOUBLE_INS = Long.class.getName();
	public static final String STRING_INS = String.class.getName();
	public static final String BOOL_INS = Boolean.class.getName();
	
	/*
	 *	Default Value 
	 */
	public static LObject INT_DEFAULT = null;
	public static LObject LONG_DEFAULT = null;
	public static LObject FLOAT_DEFAULT = null;
	public static LObject DOUBLE_DEFAULT = null;
	public static LObject STRING_DEFAULT = null;
	public static LObject BOOL_DEFAULT = null;
	
	static{
		try {
			INT = new LPrimitiveTypeImpl(INT_NAME);
			LONG = new LPrimitiveTypeImpl(LONG_NAME);
			FLOAT = new LPrimitiveTypeImpl(FLOAT_NAME);
			DOUBLE = new LPrimitiveTypeImpl(DOUBLE_NAME);
			STRING= new LPrimitiveTypeImpl(STRING_NAME);
			BOOL = new LPrimitiveTypeImpl(BOOL_NAME);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		INT.setClassifierID(INT_ID);
		LONG.setClassifierID(LONG_ID);
		FLOAT.setClassifierID(FLOAT_ID);
		DOUBLE.setClassifierID(DOUBLE_ID);
		BOOL.setClassifierID(BOOL_ID);
		STRING.setClassifierID(STRING_ID);
		
		INT.setInstanceName(INT_INS);
		LONG.setInstanceName(LONG_INS);
		FLOAT.setInstanceName(FLOAT_INS);
		DOUBLE.setInstanceName(DOUBLE_INS);
		STRING.setInstanceName(STRING_INS);
		BOOL.setInstanceName(BOOL_INS);
		
		/*
		 *	Initialization the Default Values:
		 *	1) Bool: 	false
		 *	2) Int: 	0
		 *	3) Long: 	0L
		 *	4) Float:	0.0F
		 *	5) Double:	0.0
		 *	6) String: 	""
		 */
		try {
			BOOL_DEFAULT= new LDataObjectImpl(BOOL);((LDataObjectImpl)BOOL_DEFAULT).setBool(false);
			INT_DEFAULT = new LDataObjectImpl(INT); ((LDataObjectImpl)INT_DEFAULT).setInt(0);
			LONG_DEFAULT = new LDataObjectImpl(LONG);((LDataObjectImpl)LONG_DEFAULT).setLong(0L);
			STRING_DEFAULT = new LDataObjectImpl(STRING);((LDataObjectImpl)STRING_DEFAULT).setString("");
			FLOAT_DEFAULT = new LDataObjectImpl(FLOAT);((LDataObjectImpl)FLOAT_DEFAULT).setFloat(0.0f);
			DOUBLE_DEFAULT = new LDataObjectImpl(DOUBLE);((LDataObjectImpl)DOUBLE_DEFAULT).setDouble(0.0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			BOOL.setDefaultValue(BOOL_DEFAULT);
			INT.setDefaultValue(INT_DEFAULT);
			LONG.setDefaultValue(LONG_DEFAULT);
			FLOAT.setDefaultValue(FLOAT_DEFAULT);
			DOUBLE.setDefaultValue(DOUBLE_DEFAULT);
			STRING.setDefaultValue(STRING_DEFAULT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	LPrimitiveTypeImpl(String name) throws Exception{super(name,null);}

}
