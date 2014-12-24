package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LNamedElement: a Model Element with name
 *	LNamedElement {name:string} --> LModelElement
 *
 * 	LNamedElement{
 * 		********* LModelElement **********
 * 		-annotations
 * 		********* LNamedElement **********
 * 		-name: string
 * 	}
 */
public interface LNamedElement extends LModelElement{
	public static final String SPLIT = ".";
	
	public String getName();
	public void setName(String name) throws Exception;
	
	public String getAbsolutePath();
}
