package cn.edu.buaa.sei.exLmf.metamodel;

import java.util.List;
import java.util.Stack;

/**
 *	LPackage: model element that can package other elements.
 *	LPackage {
 *		************ LModelElement *************
 *		- annotations
 *		************ LNamedElement *************
 *		- name
 *		************ LPackage *************
 *		- nsUri
 *		- nsPrefix
 *		- <name> subPackages
 *		- <id & name> types
 *		- container
 *		- factory (not null)
 *	} 	
 * 
 */
public interface LPackage extends LNamedElement{
	
	Stack<LPackage> stack = new Stack<LPackage>();
	
	public List<LPackage> getSubPackages();
	public void addSubPackage(LPackage pack) throws Exception;
	public void removeSubPackage(LPackage pack) throws Exception;
	public LPackage getSubPackageByName(String name) throws Exception;
	public boolean containSubPackage(LPackage p);
	
	public String getNsURI();
	public void setNsURI(String nsUri);
	public String getNsPrefix();
	public void setNsPrefix(String prefix);
	
	public List<LClassifier> getTypes();
	public void addType(LClassifier type) throws Exception;
	public void removeType(LClassifier type) throws Exception;
	public Boolean containType(LClassifier type);
	public LClassifier getClassifierByID(int id) throws Exception;
	public LClassifier getClassifierByName(String name) throws Exception;
	
	public LPackage getContainer();
	public void setContainer(LPackage container) throws Exception;
	
	public LFactory getFactory();
}
