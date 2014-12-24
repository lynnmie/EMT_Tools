package cn.edu.buaa.sei.exLmf.metamodel.impl;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;

// LObject is abstract: LDataObject (LDataType), LClassObject (LClass), LMultipleObject (LStructuralFeature *: contain sub objects)
public abstract class LObjectImpl extends LModelElementImpl implements LObject{
	LClassifier type;
	
	LObjectImpl(LClassifier type){
		super(); 
		if(type==null){
			try {
				throw this.getException("LObjectImpl(type)","type","Null");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		this.type=type;
	}
	@Override
	public LClassifier type() {return this.type;}
}
