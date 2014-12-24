package cn.edu.buaa.sei.exLmf.metamodel;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;

/*
 *	LAnnotation -- Notation for Model Element
 *	LAnnotation{source,container,referencedElements}
 *	Container{name}-->referencedElements 
 */
public interface LAnnotation {
	public LModelElement getContainer();
	public void setContainer(LModelElement elm);
	
	public List<LModelElement> getReferencedElements();
	public void addReferencedElement(LModelElement element);
	public void removeReferencedElement(int i) throws LMFException;
	public LModelElement getReferenceElement(int i) throws LMFException;
	
	public void setSource(String source);
	public String getSource();
}
