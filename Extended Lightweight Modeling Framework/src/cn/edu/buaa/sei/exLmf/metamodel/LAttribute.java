package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LAttribute: attribute of LMF Model Elements
 *	LAttribute {
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
 *	 	******** LAttribute *********
 *		- type (LDataType)
 * 	}
 */
public interface LAttribute extends LStructuralFeature{
	public LDataType getDataType();
	public void setDataType(LDataType type) throws Exception;
}
