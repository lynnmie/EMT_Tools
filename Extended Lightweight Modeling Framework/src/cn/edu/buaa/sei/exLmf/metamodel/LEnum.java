package cn.edu.buaa.sei.exLmf.metamodel;

import java.util.List;

/*
 *	LEnum: enumeration in LMF model elements
 *	LEnum {
 *		******** LModelElement *********
 *		-annotations
 *		******** LNamedElement *********
 *		-name: String
 *		******** LClassifier *********
 *		- classifier id: int	[identification in package space]
 *		- instance name: string [class name for generating code]		
 *		- default value: LObject[used in constructing default value in each related reference]
 *		- container: LPackage	[package space for managing it]
 *		******** LEnum ********* 
 *		<literal+value> literals
 * 	}
 */
public interface LEnum extends LDataType{
	public List<LEnumLiteral> getLiterals();
	public void addLiteral(LEnumLiteral literal) throws Exception;
	
	public LEnumLiteral getLiteralByValue(int value) throws Exception;
	public LEnumLiteral getLiteralByName(String literal) throws Exception;

	public void removeLiteral(LEnumLiteral literal) throws Exception;
	public Boolean containLiteral(LEnumLiteral literal);
}
