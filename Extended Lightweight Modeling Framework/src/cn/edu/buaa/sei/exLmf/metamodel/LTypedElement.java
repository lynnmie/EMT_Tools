package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LTypedElement: a model element with typed ==> feature but without structure (no container)
 *	LTypedElement {
 *		******** LModelElement *********
 *		- annotations
 *		******** LNamedElement *********
 *		- name
 *		******** LTypedElement *********
 *		- type: LClassifier
 *		- isOrdered/isUnique: boolean	(M)
 *		- upper/lower: int				(M)
 *	}
 */
public interface LTypedElement extends LNamedElement{
	public LClassifier getType();
	public void setType(LClassifier type) throws Exception;
	public Boolean isOrdered();
	public Boolean isUnique();
	public int getUpperBound();
	public int getLowerBound();
	
	public void setOrdered(boolean ordered);
	public void setUnique(boolean unique);
	public void setUpperBound(int upperBound) throws Exception;
	public void setLowerBound(int lowerBound) throws Exception;
}
