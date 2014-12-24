package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LClassifier: a Model Element to present type in model
 *	LClassifier{
 *		******** LModelElement *********
 *		-annotations
 *		******** LNamedElement *********
 *		-name: String
 *		******** LClassifier *********
 *		- classifier id: int	[identification in package space]
 *		- instance name: string [class name for generating code]		
 *		- default value: LObject[used in creating attribute/reference value]
 *		- container: LPackage	[package space for managing it]
 *	}
 * 
 */
public interface LClassifier extends LNamedElement{
	public int getClassifierID();
	public void setClassifierID(int id);
	public String getInstanceName();
	public void setInstanceName(String ins);
	public LObject getDefaultValue();
	public LObject setDefaultValue(LObject val) throws Exception;
	
	public LPackage getContainer();
	public void setContainer(LPackage container);
}
