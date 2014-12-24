package cn.edu.buaa.sei.exLmf.manager;

import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;

public interface IModelCreator {
	
	/**
	 * 	1. UNIQUE_ORDER|UNIQUE_INORDER|INUNIQUE_ORDER|INUNIQUE_INORDER
	 *		The argument UNIQUE|ORDER are used in createMultipleXXXX(..., multipleType) 
	 * 	2. getRoot()
	 * 		return the root package of the model creator. For each creator's space, there is exactly one root.
	 * 		The default name/uri/prefix of the root package is _ROOT, nsURI, and prefix.
	 * 	3. createXXX() throws Exception
	 * 		-Exception: when generation failed based on consistency or other reasons of the input
	 * 	3. createPackage()
	 * 		- container: parent package. should not be null.
	 * 		- name: package name. should not conflict with other names of packages in the same container.
	 * 		- nsURI: the URI of the generated package. default = ::nsURI
	 * 		- prefix: the prefix of the generated package. default = ::prefix
	 * 	4. createClass()/createAbstractClass()/createFinalClass()
	 * 		- container: the package that contain the generated class. should not be null.
	 * 		- name: the class name. should not conflict with other classes in the same container.
	 * 		- ins: the instance name of class used in code generation. default = name
	 * 		- exceptions:
	 * 			-a. Null Input Argument.
	 * 			-b. Empty Name/Ins Name.
	 * 			-c. Name Conflict with Other Classes in the same Package container.
	 * 		- specify:
	 * 			-a. createClass(): type.isAbstract()=false; type.isFinal()=false;
	 * 			-b. createAbstractClass(): type.isAbstract()=true; type.isFinal()=false;
	 * 			-c. createFinalClass(): type.isAbstract()=false; type.isFinal()=true;
	 * 			-d. type.setDefaultValue(null); type.setClassifierID(new ID);
	 * 	5. createEnum()
	 * 		- container: the package that contains the enumeration
	 * 		- name: the name of the enumeration
	 * 		- ins: the instance name of the enumeration used in code generation. default = name.
	 * 		- exceptions:
	 * 			-a. Null Input Arguments
	 * 			-b. Empty Name/Ins Name
	 * 			-c. Name Conflict with Other Classifiers in the same container.
	 * 		- specify:
	 * 			-a. etype.setDefaultValue(null)
	 * 			-b. etype.setClassifier(new ID)
	 * 	6. createAttribute()/createConstantAttribute()/createMultipleAttribute()
	 * 		- container: the LClass in which the attribute is contained. should not be null.
	 * 		- name: the name of the attribute, should not conflict with other name of features in the same container.
	 * 		- type: the value type of the attribute, should be LDataType and not null.
	 * 		- lower/upper: the bound of the features, upper==LMultipleObject.UNBOUNDED || upper>=lower; lower>=0; default: lower=0, upper=1;
	 * 		- multipleType: as in the 1 arguments. default = UNIQUE_ORDER;
	 * 		- exceptions:
	 * 			-a. Null Input Arguments
	 * 			-b. Empty Name
	 * 			-c. Bound Errors: lower<0 || lower>0&&upper!=LMultipleObject.UNBOUNDED&&lower>upper
	 * 			-d. MultipleType: not in {UNIQUE_ORDER...}
	 * 		- specify:
	 * 			-a. createAttribute(): attr.setChangable(true); attr.setDefaultValue(attr.dtype.getDefaultValue()); container.addAttribute(attr);
	 * 			-b. createConstantAttribute(): attr = this.createAttribute(); attr.setChangable(false);
	 * 			-c. createMultipleAttribute(): attr = this.createConstantAttribute(); attr.setLower(lower)/setUpper(upper); attr.setUnique()/setOrder();
	 * 	7. createEnumLiteral()
	 * 		- container: the enumeration that contain the literal
	 * 		- name: the name of the literal as a features (managed in container classifiers)
	 * 		- literal: the name of the literal as a ICON. default = name.
	 * 		- value: the value of the literal for code generation
	 * 		- exceptions:
	 * 			-a. Null Input Arguments
	 * 			-b. Empty Name/Literal
	 * 			-c. Conflict Name/Literal/Value
	 * 		- specify:
	 * 			-a. container.addLiteral(this)
	 * 	8. Getter Functions
	 * 		- getIDs(): return all the id of model elements in creator space.
	 * 		- getElement(id): return the model element with id. failed when id is not contained in id space [getIDs()] <return null>
	 * 		- getIDof(element): return the id of element in creator space. return null and exception when element is not contained in creator space.
	 * 		- containElement(element): return whether the element is in creator space. never exceptions.
	 * 		- removeElement(element): remove the model element from the creator space. failed when element is not in creator sapce. the function will release
	 * 		id in original id space.
	 * 
	 */
	public static final int UNIQUE_ORDER = 0;
	public static final int UNIQUE_INORDER = 1;
	public static final int INUNIQUE_ORDER = 2;
	public static final int INUNIQUE_INORDER = 3;
	public static final String _ROOT = "";
	public static final String nsURI = "www.lmf.com/manager";
	public static final String prefix = "";
	
	public LPackage getRoot();
	
	public LPackage createPackage(LPackage container,String name) throws Exception;
	public LPackage createPackage(LPackage container,String name,String nsURI,String prefix) throws Exception;
	
	public LClass createClass(LPackage container,String name) throws Exception;
	public LClass createClass(LPackage container,String name,String ins) throws Exception;
	
	public LClass createAbstractClass(LPackage container,String name) throws Exception;
	public LClass createAbstractClass(LPackage container,String name,String ins) throws Exception;
	
	public LClass createFinalClass(LPackage container,String name) throws Exception;
	public LClass createFinalClass(LPackage container,String name,String ins) throws Exception;
	
	public LEnum createEnum(LPackage container,String name) throws Exception;
	public LEnum createEnum(LPackage container,String name,String ins) throws Exception;
	
	public LAttribute createAttribute(LClass container,String name,LDataType type) throws Exception;
	public LAttribute createConstantAttribute(LClass container,String name,LDataType type) throws Exception;
	public LAttribute createMultipleAttribute(LClass container,String name,LDataType type,int lower,int upper,int multipleType) throws Exception;
	
	public LReference createReference(LClass container,String name,LClass type) throws Exception;
	public LReference createConstantReference(LClass container,String name,LClass type) throws Exception;
	public LReference createMultipleReference(LClass container,String name,LClass type,int lower,int upper,int multipleType) throws Exception;
	
	public LEnumLiteral createLiteral(LEnum container,String name,int value) throws Exception;
	public LEnumLiteral createLiteral(LEnum container,String name,int value,String literal) throws Exception;
	
	public Set<Integer> getIDs();
	public LModelElement getElement(Integer id);
	public Integer getIDOf(LModelElement elm);
	public Boolean containElement(LModelElement elm);
	public Boolean removeElement(LModelElement elm);
	
	public Boolean updateSpace() throws Exception;
}
