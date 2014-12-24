package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LStructualFeature: a typed element with structure
 *	LStructuralFeature {
 *		******** LModelElement *********
 *		- annotations
 *		******** LNamedElement *********
 *		- name: String
 *		******** LTypedElement *********
 *		- type: LClassifier
 *		- isOrdered/isUnique: boolean	(M)
 *		- upper/lower: int				(M)
 *		******** LStructuralFeature *********
 *		- container: LClassifier
 *		- isChangable: boolean
 *		- feature id: int {identification in container space}
 *		- default value: LObject (used in constructing attribute/reference values when creating LObject)
 *	} 
 */
public interface LStructuralFeature extends LTypedElement{
	public LClassifier getContainer();
	public void setContainer(LClassifier type);
	public Boolean isChangable();
	public void setChangable(boolean changable);
	public Boolean isRequired();
	public void setRequired(Boolean required);
	public int getFeatureID();	/*Local Identification*/
	public LObject getDefaultValue();
	public void setDefaultValue(LObject value) throws Exception;
	
}
