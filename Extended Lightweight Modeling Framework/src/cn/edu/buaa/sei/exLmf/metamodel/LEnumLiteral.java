package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LEnumLiteral {
 *		******** LModelElement *********
 *		- annotations
 *		******** LNamedElement *********
 *		- name: String
 *		******** LTypedElement *********
 *		- type: LClassifier	--> No used in literal
 *		- isOrdered/isUnique: boolean	(M)	--> No used in literal
 *		- upper/lower: int				(M) --> No used in literal
 *		******** LStructuralFeature *********
 *		- container: LClassifier
 *		- isChangable: boolean									--> No used in literal
 *		- feature id: int {identification in container space}
 *		- default value: LObject (used in constructing attribute/reference values when creating LObject)	--> No used in literal
 *	 	******** LEnumLiteral *********
 *		- literal: String [--> name]
 *		- value: int
 *	} 
 * 
 */
public interface LEnumLiteral extends LStructuralFeature{
	public int getValue();
	public void setValue(int value);
	public String getLiteral();
	public void setLiteral(String literal);
}
