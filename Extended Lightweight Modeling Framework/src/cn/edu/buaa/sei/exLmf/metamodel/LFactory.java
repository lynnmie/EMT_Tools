package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LFactory {
 *		****** LModelElement ******
 *		- annotations
 *		****** LFactory ******
 *		- container: LPackage (1--1)
 *		- create(type): type must in container package.
 *		- create(type,code): type (enum) must in container package, or PrimitiveType (in LPrimitiveTypeImpl)
 *	} 
 */
public interface LFactory extends LModelElement{
	public LPackage getContainer();
	public LClassObject create(LClass type) throws Exception;
	public LDataObject create(LDataType type,String code) throws Exception;
}
