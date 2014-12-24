package cn.edu.buaa.sei.exLmf.manager;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;

public interface ILModelSearcher {
	public static final String DOT = ".";
	public static final String ROOT= "";
	
	/**	Package Member  */
	public static final String CLASS = "class";
	public static final String ENUM = "enum";
	public static final String PACKAGE = "package";
	/**	Class Member   */
	public static final String ATTRIBUTE = "attribute";
	public static final String REFERENCE = "reference";
	public static final String SUPER = "super";
	public static final String FEATURE = "feature";
	public static final String LITERAL = "literal";
	public static final String CONTAINER = "container";
	public static final String IDATTR = "id_attribute";
	/**	LStructuredFeature  */
	public static final String DEFAULT = "default";
	public static final String TYPE = "type";
	/**Attribute*/
	public static final String DATATYPE = "dtype";
	/**Reference*/
	public static final String CLASSTYPE = "ctype";
	public static final String OPPOSITE = "opposite";
	/**LEnumLiteral*/
	/**LObject*/
	//public static final String TYPE = "type";
	/**LDataObject*/
	//public static final String DATATYPE = "dtype";
	public static final String VALUE = "value";
	/**LClassObject*/
	//public static final String CLASSTYPE = "ctype";
	/**LMultipleObject*/
	public static final String PARAMETER_TYPE = "parameter_type";
	public static final String UNIQUE_INORDER = "u_io";
	public static final String UNIQUE_ORDER = "u_o";
	public static final String INUNIQUE_ORDER = "iu_o";
	public static final String INUNIQUE_INORDER = "iu_io";
	/*Tags*/
	public static final String LEFT = "[";
	public static final String RIGHT = "]";
	
	public LModelElement getElement();
	public void setElement(LModelElement elm);
	public void verifyElement() throws Exception;
	public LModelElement nextElement(String name) throws Exception;
	@SuppressWarnings("rawtypes")
	public List nextElements(String name) throws Exception;
	
	public Object next(String name)throws Exception;
}
