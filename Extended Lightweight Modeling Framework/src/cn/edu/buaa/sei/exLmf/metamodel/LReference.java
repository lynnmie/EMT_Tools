package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LReference: reference in LMF
 *	LReference {
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
 *	 	******** LReference *********
 *		- containment
 *		- type (LClass)
 *		- opposite (1..1)
 *	} 
 * 
 */
public interface LReference extends LStructuralFeature{
	public LClass getLClass();
	public void setLClass(LClass type) throws Exception;
	
	/*
	 *	setOpposite(op){
	 *		if(this.op!=null)this.release_opposite();
	 *		if(op!=null)op.release_opposite();
	 *		this.link_opposite(op);
	 *		if(op!=null)op.link_opposite(this);
	 *	} 
	 *	release_opposite(){this.op=null;}
	 *	link_opposite(op){this.op=op;}
	 */
	public LReference getOpposite();
	public void setOpposite(LReference opposite);
	void release_opposite();
	void link_opposite(LReference opposite);
	
	public Boolean isContainment();
	public void setContainment(Boolean containment);
}
